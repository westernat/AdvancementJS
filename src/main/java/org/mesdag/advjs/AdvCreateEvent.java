package org.mesdag.advjs;

import dev.latvian.mods.kubejs.event.EventJS;
import dev.latvian.mods.kubejs.typings.Info;
import net.minecraft.resources.ResourceLocation;
import org.mesdag.advjs.adv.AdvBuilder;
import org.mesdag.advjs.adv.AdvGetter;
import org.mesdag.advjs.predicate.Predicate;
import org.mesdag.advjs.trigger.Trigger;
import org.mesdag.advjs.util.MinMaxBoundsProvider;

import static org.mesdag.advjs.adv.Data.REMOVES;

public class AdvCreateEvent extends EventJS {
    @Info("Trigger required in advancement.")
    public final Trigger TRIGGER = new Trigger();
    @Info("Predicate required in trigger.")
    public final Predicate PREDICATE = new Predicate();
    @Info("Bounds required in predicate.")
    public final MinMaxBoundsProvider BOUNDS = new MinMaxBoundsProvider();

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
}
