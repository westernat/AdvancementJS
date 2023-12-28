package org.mesdag.advjs.util;

import net.minecraft.advancement.AdvancementFrame;

public interface FrameTypeWrapper {
    AdvancementFrame TASK = AdvancementFrame.TASK;
    AdvancementFrame GOAL = AdvancementFrame.GOAL;
    AdvancementFrame CHALLENGE = AdvancementFrame.CHALLENGE;

    AdvancementFrame task = AdvancementFrame.TASK;
    AdvancementFrame goal = AdvancementFrame.GOAL;
    AdvancementFrame challenge = AdvancementFrame.CHALLENGE;
}
