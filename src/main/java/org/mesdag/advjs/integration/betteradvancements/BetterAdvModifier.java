package org.mesdag.advjs.integration.betteradvancements;

import betteradvancements.util.ColorHelper;
import com.google.gson.JsonObject;
import dev.latvian.mods.kubejs.typings.Info;
import dev.latvian.mods.kubejs.util.ConsoleJS;
import dev.latvian.mods.rhino.util.HideFromJS;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;

import static betteradvancements.advancements.BetterDisplayInfo.*;

public class BetterAdvModifier {
    public static final HashSet<BetterAdvModifier> MODIFIERS = new HashSet<>();

    final ResourceLocation id;
    int completedIconColor = defaultCompletedIconColor;
    int unCompletedIconColor = defaultUncompletedIconColor;
    int completedTitleColor = defaultCompletedTitleColor;
    int unCompletedTitleColor = defaultUncompletedTitleColor;
    boolean drawDirectLines = defaultDrawDirectLines;
    int completedLineColor = defaultCompletedLineColor;
    int unCompletedLineColor = defaultUncompletedLineColor;
    @Nullable Integer posX;
    @Nullable Integer posY;
    boolean hideLines = defaultHideLines;
    boolean allowDragging = false;

    BetterAdvModifier(ResourceLocation id) {
        this.id = id;
        MODIFIERS.add(this);
    }

    public BetterAdvModifier completedIconColor(int completedIconColor) {
        this.completedIconColor = completedIconColor;
        return this;
    }

    @Info("Use #RRGGBB format")
    public BetterAdvModifier completedIconColor(String hex) {
        this.completedIconColor = ColorHelper.RGB(hex);
        return this;
    }

    public BetterAdvModifier completedIconColor(int r, int g, int b) {
        this.completedIconColor = ColorHelper.RGB(r, g, b);
        return this;
    }

    public BetterAdvModifier unCompletedIconColor(int unCompletedIconColor) {
        this.unCompletedIconColor = unCompletedIconColor;
        return this;
    }

    @Info("Use #RRGGBB format")
    public BetterAdvModifier unCompletedIconColor(String hex) {
        this.unCompletedIconColor = ColorHelper.RGB(hex);
        return this;
    }

    public BetterAdvModifier unCompletedIconColor(int r, int g, int b) {
        this.unCompletedIconColor = ColorHelper.RGB(r, g, b);
        return this;
    }

    public BetterAdvModifier completedTitleColor(int completedTitleColor) {
        this.completedTitleColor = completedTitleColor;
        return this;
    }

    @Info("Use #RRGGBB format")
    public BetterAdvModifier completedTitleColor(String hex) {
        this.completedTitleColor = ColorHelper.RGB(hex);
        return this;
    }

    public BetterAdvModifier completedTitleColor(int r, int g, int b) {
        this.completedTitleColor = ColorHelper.RGB(r, g, b);
        return this;
    }

    public BetterAdvModifier unCompletedTitleColor(int unCompletedTitleColor) {
        this.unCompletedTitleColor = unCompletedTitleColor;
        return this;
    }

    @Info("Use #RRGGBB format")
    public BetterAdvModifier unCompletedTitleColor(String hex) {
        this.unCompletedTitleColor = ColorHelper.RGB(hex);
        return this;
    }

    public BetterAdvModifier unCompletedTitleColor(int r, int g, int b) {
        this.unCompletedTitleColor = ColorHelper.RGB(r, g, b);
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

    @Info("Use #RRGGBB format")
    public BetterAdvModifier completedLineColor(String hex) {
        this.completedLineColor = ColorHelper.RGB(hex);
        return this;
    }

    public BetterAdvModifier completedLineColor(int r, int g, int b) {
        this.completedLineColor = ColorHelper.RGB(r, g, b);
        return this;
    }

    public BetterAdvModifier unCompletedLineColor(int unCompletedLineColor) {
        this.unCompletedLineColor = unCompletedLineColor;
        return this;
    }

    @Info("Use #RRGGBB format")
    public BetterAdvModifier unCompletedLineColor(String hex) {
        this.unCompletedLineColor = ColorHelper.RGB(hex);
        return this;
    }

    public BetterAdvModifier unCompletedLineColor(int r, int g, int b) {
        this.unCompletedLineColor = ColorHelper.RGB(r, g, b);
        return this;
    }

    public BetterAdvModifier posX(int x) {
        this.posX = x;
        return this;
    }

    @Info("Equals to posX(x * 32)")
    public BetterAdvModifier locationX(int x) {
        this.posX = x * 32;
        return this;
    }

    public BetterAdvModifier posY(int y) {
        this.posY = y;
        return this;
    }

    @Info("Equals to posY(y * 27)")
    public BetterAdvModifier locationY(int y) {
        this.posY = y * 27;
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
    public boolean modify(Advancement.Builder builder) {
        if (builder == null) {
            ConsoleJS.SERVER.error("AdvJS/BetterAdvModifier: Advancement '%s' is not exist".formatted(id));
            return true;
        }
        JsonObject jsonObject = builder.serializeToJson();
        if (jsonObject.has("display")) {
            builder.display(new BetterAdvancementDisplay(DisplayInfo.fromJson(jsonObject.get("display").getAsJsonObject()), this));
            return true;
        }
        ConsoleJS.SERVER.error("AdvJS/BetterAdvModifier: Advancement '%s' has no display".formatted(id));
        return true;
    }
}
