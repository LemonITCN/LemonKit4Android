package net.lemonsoft.lemonkit.core;

import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import net.lemonsoft.lemonkit.R;
import net.lemonsoft.lemonkit.interfaces.ui.LKUIView;
import net.lemonsoft.lemonkit.model.LKUIExtensionModel;

/**
 * 处理视图的扩展attribute的核心类
 * Created by LiuRi on 2017/1/25.
 */

public final class LKUIAttrsCore {

    /**
     * 解析LKUI扩展的XML属性
     *
     * @param view  要作用于的控件
     * @param attrs XML属性集
     */
    public static void parse(LKUIView view, AttributeSet attrs) {
        LKUIExtensionModel extModel = new LKUIExtensionModel();
        // 解析attrs并设置
        TypedArray array = ((View) view).getContext().obtainStyledAttributes(attrs, R.styleable.View);
        assert array != null;// 断言array不为空
        extModel.setCornerRadius(array.getDimensionPixelSize(R.styleable.View_cornerRadius, extModel.getCornerRadius()));
        extModel.setBorderWidth(array.getDimensionPixelSize(R.styleable.View_borderWidth, extModel.getBorderWidth()));
        extModel.setBorderColor(array.getColor(R.styleable.View_borderColor, extModel.getBorderColor()));
        extModel.setClipToBounds(array.getBoolean(R.styleable.View_clipToBounds, extModel.isClipToBounds()));
        view.setLk(extModel);
    }

    /**
     * 解析LKUI扩展的XML属性
     *
     * @param view  要作用于的控件
     * @param attrs XML属性集
     */
    public static LKUIExtensionModel p(View view, AttributeSet attrs) {
        LKUIExtensionModel extModel = new LKUIExtensionModel();
        // 解析attrs并设置
        TypedArray array = ((View) view).getContext().obtainStyledAttributes(attrs, R.styleable.View);
        assert array != null;// 断言array不为空
        extModel.setCornerRadius(array.getDimensionPixelSize(R.styleable.View_cornerRadius, extModel.getCornerRadius()));
        extModel.setBorderWidth(array.getDimensionPixelSize(R.styleable.View_borderWidth, extModel.getBorderWidth()));
        extModel.setBorderColor(array.getColor(R.styleable.View_borderColor, extModel.getBorderColor()));
        extModel.setClipToBounds(array.getBoolean(R.styleable.View_clipToBounds, extModel.isClipToBounds()));
        return extModel;
    }

}
