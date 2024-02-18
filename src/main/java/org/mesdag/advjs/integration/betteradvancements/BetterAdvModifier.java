package org.mesdag.advjs.integration.betteradvancements;

import dev.latvian.mods.kubejs.util.ConsoleJS;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.mesdag.advjs.advancement.AdvBuilder;
import org.mesdag.advjs.util.Data;

import java.util.HashSet;

import static betteradvancements.advancements.BetterDisplayInfo.*;

public class BetterAdvModifier {
    public static final HashSet<BetterAdvModifier> MODIFIERS = new HashSet<>();

    final ResourceLocation id;
    int completedIconColor = defaultCompletedIconColor;
    int unCompletedIconColor = defaultUncompletedIconColor;
    int completedTitleColor = defaultCompletedTitleColor;
    int unCompletedTitleColor = defaultUncompletedTitleColor;
    @Nullable Boolean drawDirectLines = defaultDrawDirectLines;
    int completedLineColor = defaultCompletedLineColor;
    int unCompletedLineColor = defaultUncompletedLineColor;
    @Nullable Integer posX;
    @Nullable Integer posY;
    @Nullable Boolean hideLines;
    boolean allowDragging = false;

    BetterAdvModifier(ResourceLocation id) {
        this.id = id;
        MODIFIERS.add(this);
    }

    public BetterAdvModifier completedIconColor(int completedIconColor) {
        this.completedIconColor = completedIconColor;
        return this;
    }

    public BetterAdvModifier unCompletedIconColor(int unCompletedIconColor) {
        this.unCompletedIconColor = unCompletedIconColor;
        return this;
    }

    public BetterAdvModifier completedTitleColor(int completedTitleColor) {
        this.completedTitleColor = completedTitleColor;
        return this;
    }

    public BetterAdvModifier unCompletedTitleColor(int unCompletedTitleColor) {
        this.unCompletedTitleColor = unCompletedTitleColor;
        return this;
    }

    public BetterAdvModifier drawDirectLines() {
        this.drawDirectLines = true;
        return this;
    }

    public BetterAdvModifier completedLineColor(int completedLineColor) {
        this.completedLineColor = completedLineColor;
        return this;
    }

    public BetterAdvModifier unCompletedLineColor(int unCompletedLineColor) {
        this.unCompletedLineColor = unCompletedLineColor;
        return this;
    }

    public BetterAdvModifier posX(int posX) {
        this.posX = posX;
        return this;
    }

    public BetterAdvModifier posY(int posY) {
        this.posY = posY;
        return this;
    }

    public BetterAdvModifier hideLines() {
        this.hideLines = true;
        return this;
    }

    public BetterAdvModifier allowDragging() {
        this.allowDragging = true;
        return this;
    }

    @HideFromJS
    public ResourceLocation getId() {
        return id;
    }

    @HideFromJS
    public boolean modify() {
        AdvBuilder builder = Data.BUILDERS.get(id);
        if (builder == null) {
            ConsoleJS.SERVER.error("AdvJS: BetterAdvModifier only available in AdvBuilder");
        } else {
            DisplayInfo displayInfo = builder.getDisplayInfo();
            if (displayInfo == null) {
                ConsoleJS.SERVER.error("AdvJS/BetterAdvModifier: Advancement '%s' has no display".formatted(id));
            } else {
                builder.setBetterDisplayInfo(new BetterAdvancementDisplay(displayInfo, this));
            }
        }
        return true;
    }
}
