package org.mesdag.advjs.util;

public class DisplayOffset {
    public final float x;
    public final float y;
    public final boolean modifyChildren;

    public DisplayOffset(float offsetX, float offsetY, boolean modifyChildren) {
        this.x = offsetX;
        this.y = offsetY;
        this.modifyChildren = modifyChildren;
    }
}
