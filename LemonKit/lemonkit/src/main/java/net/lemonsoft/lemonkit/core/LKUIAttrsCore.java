package net.lemonsoft.lemonkit.core;

import android.content.res.TypedArray;
import android.util.AttributeSet;

import net.lemonsoft.lemonkit.R;
import net.lemonsoft.lemonkit.interfaces.ui.LKUI;
import net.lemonsoft.lemonkit.model.LKUIAttrsModel;
import net.lemonsoft.lemonkit.tools.ViewTool;

/**
 * 处理视图的扩展attribute的核心类
 * Created by LiuRi on 2017/1/25.
 */

public final class LKUIAttrsCore {

    public static void dealWithView(LKUI lkui, AttributeSet attrs) {
        LKUIAttrsModel attrsModel = new LKUIAttrsModel(lkui);
        // 解析attrs并设置
        TypedArray array = lkui.getContext().obtainStyledAttributes(attrs, R.styleable.LKView);
        assert array != null;// 断言array不为空
        attrsModel.getAttrsModel().setCornerRadius(array.getDimensionPixelSize(R.styleable.LKView_cornerRadius, attrsModel.getCornerRadius()));
        attrsModel.getAttrsModel().setBorderWidth(array.getDimensionPixelSize(R.styleable.LKView_borderWidth, attrsModel.getBorderWidth()));
        attrsModel.getAttrsModel().setBorderColor(array.getColor(R.styleable.LKView_borderColor, attrsModel.getBorderColor()));
        attrsModel.getAttrsModel().setClipToBounds(array.getBoolean(R.styleable.LKView_clipToBounds, attrsModel.isClipToBounds()));
        lkui.setLKUIAttrs(attrsModel);
    }

    public static void dealWithView(LKUI lkui, LKUIAttrsModel model) {
        ViewTool.applyExtensionAttrs(lkui.getView(), lkui.getCanvas(), model.getAttrsModel());
    }

}
