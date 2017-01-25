package net.lemonsoft.lemonkit.interfaces.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import net.lemonsoft.lemonkit.model.LKUIAttrsModel;

/**
 * 对原生view进行属性直接扩展之后的基本要求接口
 * Created by LiuRi on 2017/1/25.
 */

public interface LKUI {

    /**
     * 获取控件的画布
     */
    Canvas getCanvas();

    /**
     * 获取控件的上下文对象
     */
    Context getContext();

    /**
     * 设置对象的LKUI属性
     */
    void setLKUIAttrs(LKUIAttrsModel attrs);

    /**
     * 获取控件的LKUI属性
     */
    LKUIAttrsModel getLKUIAttrs();

    /**
     * 获取实体控件
     */
    View getView();

}
