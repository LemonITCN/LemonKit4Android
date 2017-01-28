package net.lemonsoft.lemonkit.interfaces.ui;

import android.graphics.Canvas;
import android.view.View;

import net.lemonsoft.lemonkit.enums.LKUIDelegateOnDrawState;

/**
 * 对原生view进行属性直接扩展之后的基本要求接口
 * Created by LiuRi on 2017/1/25.
 */

public interface LKUIDelegate {

    /**
     * 在绘制时候的回调
     *
     * @param view   调用回调的控件
     * @param canvas 控件画板
     */
    void onDrawHandler(View view, Canvas canvas, LKUIDelegateOnDrawState state);

}
