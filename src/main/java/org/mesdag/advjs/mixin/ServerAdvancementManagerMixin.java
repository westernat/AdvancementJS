package org.mesdag.advjs.mixin;

import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.ServerAdvancementManager;
import org.jetbrains.annotations.Nullable;
import org.mesdag.advjs.getter.AdvGetter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.mesdag.advjs.getter.AdvGetter.getterMap;
import static org.mesdag.advjs.util.Data.removes;

@Mixin(ServerAdvancementManager.class)
public abstract class ServerAdvancementManagerMixin {
    @ModifyArg(
        method = "apply(Ljava/util/Map;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/advancements/AdvancementList;add(Ljava/util/Map;)V"))
    private Map<ResourceLocation, Advancement.Builder> addNew(Map<ResourceLocation, Advancement.Builder> map) {
        HashMap<ResourceLocation, Advancement> advMap = new HashMap<>();
        advJS$remove(map);
        advJS$build(advMap);
        for (Map.Entry<ResourceLocation, Advancement> builder : advMap.entrySet()) {
            map.put(builder.getKey(), builder.getValue().deconstruct());
        }
        return map;
    }

    @Unique
    private static void advJS$remove(Map<ResourceLocation, Advancement.Builder> map) {
        for (ResourceLocation remove : removes) {
            map.remove(remove);
        }
    }

    @Unique
    private static void advJS$build(HashMap<ResourceLocation, Advancement> map) {
        ArrayList<AdvGetter> remains = new ArrayList<>();
        for (Map.Entry<ResourceLocation, AdvGetter> entry : getterMap.entrySet()) {
            AdvGetter getter = entry.getValue();
            if (getter.isRoot()) {
                map.put(entry.getKey(), advJS$buildAdv(getter, null));
            } else if (!advJS$addAndPut(map, getter)) {
                remains.add(getter);
            }
        }

        if (remains.size() > 0) {
            for (AdvGetter getter : remains) {
                advJS$addAndPut(map, getter);
            }
        }
    }

    @Unique
    private static boolean advJS$addAndPut(HashMap<ResourceLocation, Advancement> map, AdvGetter getter) {
        ResourceLocation location = getter.getParent();
        if (map.containsKey(location)) {
            Advancement advancement = advJS$buildAdv(getter, map.get(location));
            map.get(location).addChild(advancement);
            map.put(getter.getSavePath(), advancement);
            return true;
        }
        return false;
    }

    @Unique
    private static Advancement advJS$buildAdv(AdvGetter getter, @Nullable Advancement parent) {
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
