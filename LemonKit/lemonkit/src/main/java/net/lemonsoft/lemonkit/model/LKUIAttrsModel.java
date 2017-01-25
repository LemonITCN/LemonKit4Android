package net.lemonsoft.lemonkit.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.NonNull;

import net.lemonsoft.lemonkit.core.LKUIAttrsCore;
import net.lemonsoft.lemonkit.interfaces.ui.LKUI;

/**
 * LemonKit UI属性扩展模型
 * 对Android的View属性进行扩展的部分全部在这个模型对象中进行描述
 * Created by LiuRi on 2017/1/25.
 */

public class LKUIAttrsModel {

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

    private LKUI lkui;

    public LKUIAttrsModel(@NonNull LKUI lkui) {
        this.lkui = lkui;
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        // 重新应用扩展属性的样式
        LKUIAttrsCore.dealWithView(lkui, this);
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        // 重新应用扩展属性的样式
        LKUIAttrsCore.dealWithView(lkui, this);
    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
        // 重新应用扩展属性的样式
        LKUIAttrsCore.dealWithView(lkui, this);
    }

    public boolean isClipToBounds() {
        return clipToBounds;
    }

    public void setClipToBounds(boolean clipToBounds) {
        this.clipToBounds = clipToBounds;
        // 重新应用扩展属性的样式
        LKUIAttrsCore.dealWithView(lkui, this);
    }
}
