package org.mesdag.advjs.mixin;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.Criterion;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.ServerAdvancementManager;
import org.mesdag.advjs.AdvCreateEvent;
import org.mesdag.advjs.AdvJS;
import org.mesdag.advjs.adv.AdvBuilder;
import org.mesdag.advjs.adv.AdvGetter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.mesdag.advjs.adv.Data.*;

@Mixin(ServerAdvancementManager.class)
public abstract class ServerAdvancementManagerMixin {
    @ModifyArg(
        method = "apply(Ljava/util/Map;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/advancements/AdvancementList;add(Ljava/util/Map;)V"))
    private Map<ResourceLocation, Advancement.Builder> advjs$reload(Map<ResourceLocation, Advancement.Builder> map) {
        AdvJS.ADVANCEMENT.post(new AdvCreateEvent());
        advJS$remove(map);
        advJS$modify(map);
        advJS$add(map);
        AdvJS.LOGGER.info("AdvJS loaded!");
        return map;
    }

    @Unique
    private static void advJS$remove(Map<ResourceLocation, Advancement.Builder> map) {
        for (ResourceLocation remove : REMOVES) {
            map.remove(remove);
        }
    }

    @Unique
    private static void advJS$modify(Map<ResourceLocation, Advancement.Builder> map) {
        for (Map.Entry<ResourceLocation, AdvGetter> entry : GETTER_MAP.entrySet()) {
            ResourceLocation path = entry.getKey();
            Advancement.Builder builder = map.get(path);
            if (builder != null) {
                AdvGetter getter = entry.getValue();
                ResourceLocation parentId = new ResourceLocation(builder.serializeToJson().get("parent").getAsString());
                Advancement.Builder neo = Advancement.Builder.advancement()
                    .parent(parentId)
                    .display(getter.getDisplayInfo())
                    .rewards(getter.getRewards())
                    .requirements(getter.getRequirements());
                for (Map.Entry<String, Criterion> pair : getter.getCriteria().entrySet()) {
                    neo.addCriterion(pair.getKey(), pair.getValue());
                }
                map.put(path, neo);
            }
        }
    }

    @Unique
    private static void advJS$add(Map<ResourceLocation, Advancement.Builder> builderMap) {
        HashMap<ResourceLocation, Advancement> advMap = new HashMap<>();
        ArrayList<AdvBuilder> remains = new ArrayList<>();
        for (Map.Entry<ResourceLocation, AdvBuilder> entry : BUILDER_MAP.entrySet()) {
            AdvBuilder builder = entry.getValue();
            if (builder.isRoot()) {
                advMap.put(entry.getKey(), advJS$buildAdv(builder, null));
            } else if (!advJS$buildAndPut(advMap, builder)) {
                remains.add(builder);
            }
        }

        if (remains.size() > 0) {
            for (AdvBuilder builder : remains) {
                advJS$buildAndPut(advMap, builder);
            }
        }

        for (Map.Entry<ResourceLocation, Advancement> entry : advMap.entrySet()) {
            builderMap.put(entry.getKey(), entry.getValue().deconstruct());
        }
    }

    @Unique
    private static boolean advJS$buildAndPut(HashMap<ResourceLocation, Advancement> advMap, AdvBuilder builder) {
        ResourceLocation location = builder.getParent();
        if (advMap.containsKey(location)) {
            Advancement advancement = advJS$buildAdv(builder, advMap.get(location));
            advMap.get(location).addChild(advancement);
            advMap.put(builder.getSavePath(), advancement);
            return true;
        }
        return false;
    }

    @Unique
    private static Advancement advJS$buildAdv(AdvBuilder getter, Advancement parent) {
        if (getter.isAttention()) {
            getter.display(builder -> {
                builder.setTitle(ATTENTION);
                builder.setDescription(ATTENTION_DESC);
            });
        }
        return new Advancement(
            getter.getSavePath(),
            parent,
            getter.getDisplayInfo(),
            getter.getRewards(),
            getter.getCriteria(),
            getter.getRequirements()
        );
    }
}
