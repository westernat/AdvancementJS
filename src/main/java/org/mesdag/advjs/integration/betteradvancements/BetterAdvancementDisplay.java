package org.mesdag.advjs.integration.betteradvancements;

import betteradvancements.api.IBetterDisplayInfo;
import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class BetterAdvancementDisplay extends AdvancementDisplay implements IBetterDisplayInfo {
    private final Identifier id;
    private final int completedIconColor;
    private final int unCompletedIconColor;
    private final int completedTitleColor;
    private final int unCompletedTitleColor;
    private final @Nullable Boolean drawDirectLines;
    private final int completedLineColor;
    private final int unCompletedLineColor;
    private final @Nullable Integer posX;
    private final @Nullable Integer posY;
    private final @Nullable Boolean hideLines;
    private final boolean allowDragging;

    BetterAdvancementDisplay(AdvancementDisplay displayInfo, BetterAdvModifier modifier) {
        super(
            displayInfo.getIcon(),
            displayInfo.getTitle(),
            displayInfo.getDescription(),
            displayInfo.getBackground(),
            displayInfo.getFrame(),
            displayInfo.shouldShowToast(),
            displayInfo.shouldAnnounceToChat(),
            displayInfo.isHidden()
        );
        this.id = modifier.id;
        this.completedIconColor = modifier.completedIconColor;
        this.unCompletedIconColor = modifier.unCompletedIconColor;
        this.completedTitleColor = modifier.completedTitleColor;
        this.unCompletedTitleColor = modifier.unCompletedTitleColor;
        this.drawDirectLines = modifier.drawDirectLines;
        this.completedLineColor = modifier.completedLineColor;
        this.unCompletedLineColor = modifier.unCompletedLineColor;
        this.posX = modifier.posX;
        this.posY = modifier.posY;
        this.hideLines = modifier.hideLines;
        this.allowDragging = modifier.allowDragging;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public int getCompletedIconColor() {
        return completedIconColor;
    }

    @Override
    public int getUnCompletedIconColor() {
        return unCompletedIconColor;
    }

    @Override
    public int getCompletedTitleColor() {
        return completedTitleColor;
    }

    @Override
    public int getUnCompletedTitleColor() {
        return unCompletedTitleColor;
    }

    @Override
    public Boolean drawDirectLines() {
        return drawDirectLines;
    }

    @Override
    public int getCompletedLineColor() {
        return completedLineColor;
    }

    @Override
    public int getUnCompletedLineColor() {
        return unCompletedLineColor;
    }

    @Override
    public @Nullable Integer getPosX() {
        return posX;
    }

    @Override
    public @Nullable Integer getPosY() {
        return posY;
    }

    @Override
    public Boolean hideLines() {
        return hideLines;
    }

    @Override
    public boolean allowDragging() {
        return allowDragging;
    }
}
