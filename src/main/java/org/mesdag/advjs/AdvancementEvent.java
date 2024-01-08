package org.mesdag.advjs;

import dev.latvian.mods.kubejs.event.EventJS;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.mesdag.advjs.adv.AdvBuilder;
import org.mesdag.advjs.adv.AdvGetter;
import org.mesdag.advjs.predicate.Predicate;
import org.mesdag.advjs.trigger.Trigger;
import org.mesdag.advjs.util.Bounds;

import static org.mesdag.advjs.adv.Data.LOCK_MAP;
import static org.mesdag.advjs.adv.Data.REMOVES;

public class AdvancementEvent extends EventJS {
    @Info("""
        Trigger required in advancement.
        
        More details please goto https://minecraft.wiki/w/Advancement/JSON_format
        """)
    public final Trigger TRIGGER = new Trigger();
    @Info("Predicate required in trigger.")
    public final Predicate PREDICATE = new Predicate();
    @Info("Bounds required in predicate.")
    public final Bounds BOUNDS = new Bounds();

    @Info("Create a new advancement root")
    public AdvBuilder create(ResourceLocation rootPath) {
        return new AdvBuilder(null, "root", rootPath, false);
    }

    @Info("Remove an exist advancement.")
    public void remove(ResourceLocation remove) {
        REMOVES.add(remove);
    }

    @Info("Get an exist advancement to modify.")
    public AdvGetter get(ResourceLocation path) {
        return new AdvGetter(path);
    }

    @Info(value = "Lock recipe by advancement.",
        params = {
            @Param(name = "toLock"),
            @Param(name = "lockBy")
        })
    public void lock(ItemStack toLock, ResourceLocation lockBy) {
        LOCK_MAP.put(toLock.getItem(), lockBy);
    }

    @Info(value = "Lock recipe by advancement.",
        params = {
            @Param(name = "toLock"),
            @Param(name = "lockBy")
        })
    public void lock(ItemStack toLock, AdvBuilder lockBy) {
        LOCK_MAP.put(toLock.getItem(), lockBy.getSavePath());
    }
}
