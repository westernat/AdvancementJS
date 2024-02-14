package org.mesdag.advjs.advancement;

import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.typings.Param;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.mesdag.advjs.util.DisplayOffset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import static org.mesdag.advjs.util.Data.*;

public class AdvBuilder {
    @Nullable
    private final Identifier parent;
    private final String name;
    private final Identifier rootPath;
    @Nullable
    private DisplayBuilder displayBuilder;
    private final RewardsBuilder rewardsBuilder = new RewardsBuilder();
    private final CriteriaBuilder criteriaBuilder = new CriteriaBuilder();
    private boolean sendsTelemetryEvent = false;

    private WarnType warn;
    private final Identifier id;

    public AdvBuilder(@Nullable Identifier parent, String name, Identifier rootPath, WarnType warn) {
        this.parent = parent;
        this.name = name;
        this.rootPath = rootPath;
        this.warn = warn;
        this.id = generateId();
        BUILDERS.put(id, this);
    }

    @Info("Add a nameless child to this advancement, just for test. Returns child.")
    public AdvBuilder addChild(Consumer<AdvBuilder> advBuilderConsumer) {
        AdvBuilder child = new AdvBuilder(id, UUID.randomUUID().toString(), rootPath, WarnType.NAMELESS);
        advBuilderConsumer.accept(child);
        return child;
    }

    @Info("Add a named child to this advancement. Returns child.")
    public AdvBuilder addChild(String name, Consumer<AdvBuilder> advBuilderConsumer) {
        AdvBuilder child = new AdvBuilder(id, name, rootPath, WarnType.NONE);
        advBuilderConsumer.accept(child);
        return child;
    }

    @Info("Data related to the advancement's display.")
    public AdvBuilder display(Consumer<DisplayBuilder> displayBuilderConsumer) {
        DisplayBuilder builder = new DisplayBuilder(id);
        displayBuilderConsumer.accept(builder);
        if (parent == null && builder.getBackground() == null) {
            builder.setBackground(new Identifier("textures/gui/advancements/backgrounds/stone.png"));
        }
        this.displayBuilder = builder;
        return this;
    }

    @Info("The criteria to be tracked by this advancement.")
    public AdvBuilder criteria(Consumer<CriteriaBuilder> criteriaBuilderConsumer) {
        criteriaBuilderConsumer.accept(criteriaBuilder);
        return this;
    }

    @Info("The rewards provided when this advancement is obtained.")
    public AdvBuilder rewards(Consumer<RewardsBuilder> rewardsBuilderConsumer) {
        rewardsBuilderConsumer.accept(rewardsBuilder);
        return this;
    }

    @Info("Determines whether telemetry data should be collected when this advancement is achieved or not. Defaults to false.")
    public AdvBuilder sendsTelemetryEvent() {
        this.sendsTelemetryEvent = true;
        return this;
    }

    @Info("It will check if parent done. Defaults do not check.")
    public AdvBuilder requireParentDone() {
        if (parent == null) {
            ConsoleJS.SERVER.warn("AdvJS/requireParentDone: Advancement '%s' is a root, so it can't check parent done".formatted(id));
            return this;
        }

        if (REQUIRE_DONE.containsKey(id)) {
            ArrayList<Identifier> list = new ArrayList<>(Arrays.stream(REQUIRE_DONE.get(id)).toList());
            list.add(parent);
            REQUIRE_DONE.put(id, list.toArray(Identifier[]::new));
        } else {
            REQUIRE_DONE.put(id, new Identifier[]{parent});
        }
        return this;
    }

    @Info("It will check if advancements that you put in had done.")
    public AdvBuilder requireOthersDone(Identifier... requires) {
        if (REQUIRE_DONE.containsKey(id)) {
            ArrayList<Identifier> list = new ArrayList<>(Arrays.stream(requires).toList());
            list.add(parent);
            REQUIRE_DONE.put(id, list.toArray(Identifier[]::new));
        } else {
            REQUIRE_DONE.put(id, requires);
        }
        return this;
    }

    @Info(value = "Configure this advancement's position",
            params = {
                    @Param(name = "offsetX", value = "The offset x of display."),
                    @Param(name = "offsetY", value = "The offset y of display.")
            })
    public AdvBuilder displayOffset(float x, float y) {
        DISPLAY_OFFSET.put(id, new DisplayOffset(x, y, false));
        return this;
    }

    @Info(value = "Configure this advancement's position",
            params = {
                    @Param(name = "offsetX", value = "The offset x of display."),
                    @Param(name = "offsetY", value = "The offset y of display."),
                    @Param(name = "modifyChildren", value = "Determine should its children apply the same offset.")
            })
    public AdvBuilder displayOffset(float x, float y, boolean modifyChildren) {
        DISPLAY_OFFSET.put(id, new DisplayOffset(x, y, modifyChildren));
        return this;
    }

    @Info("""
        If invoked this method, the advancement will revoke after grant automatically.
        
        This is useful when you want to trigger it repeatedly.
        """)
    public AdvBuilder repeatable() {
        REPEATABLE.add(id);
        return this;
    }

    @Info("Get parent of this advancement.")
    public @Nullable Identifier getParent() {
        return parent;
    }

    private Identifier generateId() {
        if (name.contains(":")) {
            return new Identifier(name);
        }
        return new Identifier(rootPath.getNamespace(), rootPath.getPath() + "/" + name);
    }

    @Info("Get the id of this advancement.")
    public Identifier getId() {
        return id;
    }

    @HideFromJS
    public AdvancementDisplay getDisplayInfo() {
        return displayBuilder == null ? null : displayBuilder.build();
    }

    @HideFromJS
    public Map<String, AdvancementCriterion> getCriteria() {
        return criteriaBuilder.getCriteria();
    }

    @HideFromJS
    public String[][] getRequirements() {
        return criteriaBuilder.getRequirements();
    }

    @HideFromJS
    public AdvancementRewards getRewards() {
        return rewardsBuilder.build();
    }

    @HideFromJS
    public boolean isSendsTelemetryEvent() {
        return sendsTelemetryEvent;
    }

    @HideFromJS
    public WarnType getWarn() {
        return warn;
    }

    @HideFromJS
    public AdvBuilder setWarn(WarnType warn) {
        this.warn = warn;
        return this;
    }

    public enum WarnType {
        NONE(Text.empty()),
        NAMELESS(Text.translatable("advjs.attention.nameless")),
        NO_SPACE(Text.translatable("advjs.attention.no_space")),
        NO_PARENT(Text.translatable("advjs.attention.no_parent"));

        public final Text msg;

        WarnType(Text msg) {
            this.msg = msg;
        }
    }
}
