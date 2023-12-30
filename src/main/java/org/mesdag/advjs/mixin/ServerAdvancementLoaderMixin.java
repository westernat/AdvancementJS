package org.mesdag.advjs.mixin;

import dev.latvian.mods.kubejs.script.ScriptType;
import net.minecraft.advancement.Advancement;
import net.minecraft.server.ServerAdvancementLoader;
import net.minecraft.util.Identifier;
import org.mesdag.advjs.AdvCreateEvent;
import org.mesdag.advjs.AdvJS;
import org.mesdag.advjs.getter.AdvGetter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.mesdag.advjs.util.Data.*;

@Mixin(ServerAdvancementLoader.class)
public abstract class ServerAdvancementLoaderMixin {
    @ModifyArg(
        method = "apply(Ljava/util/Map;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/advancement/AdvancementManager;load(Ljava/util/Map;)V"))
    private Map<Identifier, Advancement.Builder> advjs$reload(Map<Identifier, Advancement.Builder> map) {
        new AdvCreateEvent().post(ScriptType.SERVER, "advjs");
        advJS$remove(map);
        advJS$add(map);
        AdvJS.LOGGER.info("AdvJS Loaded!");
        return map;
    }

    @Unique
    private static void advJS$remove(Map<Identifier, Advancement.Builder> map) {
        for (Identifier remove : REMOVES) {
            map.remove(remove);
        }
    }

    @Unique
    private static void advJS$add(Map<Identifier, Advancement.Builder> builderMap) {
        HashMap<Identifier, Advancement> advMap = new HashMap<>();
        ArrayList<AdvGetter> remains = new ArrayList<>();
        for (Map.Entry<Identifier, AdvGetter> entry : GETTER_MAP.entrySet()) {
            AdvGetter getter = entry.getValue();
            if (getter.isRoot()) {
                advMap.put(entry.getKey(), advJS$buildAdv(getter, null));
            } else if (!advJS$buildAndPut(advMap, getter)) {
                remains.add(getter);
            }
        }

        if (remains.size() > 0) {
            for (AdvGetter getter : remains) {
                advJS$buildAndPut(advMap, getter);
            }
        }

        for (Map.Entry<Identifier, Advancement> builder : advMap.entrySet()) {
            builderMap.put(builder.getKey(), builder.getValue().createTask());
        }
    }

    @Unique
    private static boolean advJS$buildAndPut(HashMap<Identifier, Advancement> advMap, AdvGetter getter) {
        Identifier location = getter.getParent();
        if (advMap.containsKey(location)) {
            Advancement advancement = advJS$buildAdv(getter, advMap.get(location));
            advMap.get(location).addChild(advancement);
            advMap.put(getter.getSavePath(), advancement);
            return true;
        }
        return false;
    }

    @Unique
    private static Advancement advJS$buildAdv(AdvGetter getter, Advancement parent) {
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
