package net.lemonsoft.lemonkit.model;

import android.support.annotation.NonNull;

import net.lemonsoft.lemonkit.core.LKUIAttrsCore;
import net.lemonsoft.lemonkit.interfaces.ui.LKUI;

/**
 * LemonKit UI属性扩展模型
 * 对Android的View属性进行扩展的部分全部在这个模型对象中进行描述
 * Created by LiuRi on 2017/1/25.
 */

public class LKUIAttrsModel {

    private NativeViewExtensionAttrsModel attrsModel;

    private LKUI lkui;

    public LKUIAttrsModel(@NonNull LKUI lkui) {
        this.lkui = lkui;
        this.attrsModel = new NativeViewExtensionAttrsModel();
    }

    public NativeViewExtensionAttrsModel getAttrsModel() {
        return attrsModel;
    }

    public int getCornerRadius() {
        return this.attrsModel.getCornerRadius();
    }

    public void setCornerRadius(int cornerRadius) {
        this.attrsModel.setCornerRadius(cornerRadius);
        // 重新应用扩展属性的样式
        LKUIAttrsCore.dealWithView(lkui, this);
    }

    public int getBorderWidth() {
        return this.attrsModel.getBorderWidth();
    }

    public void setBorderWidth(int borderWidth) {
        this.attrsModel.setBorderWidth(borderWidth);
        // 重新应用扩展属性的样式
        LKUIAttrsCore.dealWithView(lkui, this);
    }

    public int getBorderColor() {
        return this.attrsModel.getBorderColor();
    }

    public void setBorderColor(int borderColor) {
        this.attrsModel.setBorderColor(borderColor);
        // 重新应用扩展属性的样式
        LKUIAttrsCore.dealWithView(lkui, this);
    }

    public boolean isClipToBounds() {
        return this.attrsModel.isClipToBounds();
    }

    public void setClipToBounds(boolean clipToBounds) {
        this.attrsModel.setClipToBounds(clipToBounds);
        // 重新应用扩展属性的样式
        LKUIAttrsCore.dealWithView(lkui, this);
    }

}
