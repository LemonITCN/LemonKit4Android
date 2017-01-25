package net.lemonsoft.lemonkit.model;

import android.graphics.Color;

/**
 * 对原生控件的属性扩展模型
 * 原生控件指的是android.view.View以及其直接或间接的子类
 * Created by LiuRi on 2017/1/25.
 */

public class NativeViewExtensionAttrsModel {

    /**
     * 圆角半径
     */
    private int cornerRadius = 0;
    /**
     * 边框宽度
     */
    private int borderWidth = 0;
    /**
     * 边框颜色
     */
    private int borderColor = Color.WHITE;
    /**
     * 是否沿着边框剪切，若为真，那么超过边框的部分都会被剪切，无法看见
     */
    private boolean clipToBounds = true;

    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
    }

    public boolean isClipToBounds() {
        return clipToBounds;
    }

    public void setClipToBounds(boolean clipToBounds) {
        this.clipToBounds = clipToBounds;
    }
}
