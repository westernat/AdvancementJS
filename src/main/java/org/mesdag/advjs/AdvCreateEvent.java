package org.mesdag.advjs;

import dev.latvian.mods.kubejs.event.EventJS;
import net.minecraft.resources.ResourceLocation;
import org.mesdag.advjs.adv.AdvBuilder;
import org.mesdag.advjs.adv.AdvGetter;
import org.mesdag.advjs.predicate.Predicate;
import org.mesdag.advjs.trigger.Trigger;
import org.mesdag.advjs.util.MinMaxBoundsProvider;

import static org.mesdag.advjs.adv.Data.REMOVES;

public class AdvCreateEvent extends EventJS {
    public final Trigger TRIGGER = new Trigger();
    public final Predicate PREDICATE = new Predicate();
    public final MinMaxBoundsProvider BOUNDS = new MinMaxBoundsProvider();

    public AdvBuilder create(ResourceLocation rootPath) {
        return new AdvBuilder(null, "root", rootPath, false);
    }

    public void remove(ResourceLocation remove) {
        REMOVES.add(remove);
    }

    public AdvGetter get(ResourceLocation path) {
        return new AdvGetter(path);
    }
}
