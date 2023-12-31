package org.mesdag.advjs.configure;

import com.google.gson.JsonElement;
import dev.latvian.mods.kubejs.event.EventJS;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.mesdag.advjs.predicate.Predicate;
import org.mesdag.advjs.trigger.Trigger;
import org.mesdag.advjs.util.Bounds;

import static org.mesdag.advjs.configure.Data.FILTERS;
import static org.mesdag.advjs.configure.Data.LOCK_MAP;

public class AdvConfigureEvent extends EventJS {
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

    @Info("""
        It will automatically remove all of its children.

        If you put in a string, it will remove advancement by its path.
  
        Else if you put in a json object, it will remove advancement by available filter:

            modid: the mod id of advancement.

            icon: the id of icon item/block.

            frame: type of frame for the icon. Available value is 'challenge', 'goal' or 'task'.
            
            parent: the parent advancement path of this advancement.
        """)
    public void remove(JsonElement jsonElement) {
        FILTERS.add(RemoveFilter.of(jsonElement));
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
