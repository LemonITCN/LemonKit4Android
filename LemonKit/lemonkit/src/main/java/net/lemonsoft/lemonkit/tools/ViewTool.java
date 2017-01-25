package net.lemonsoft.lemonkit.tools;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.View;

import net.lemonsoft.lemonkit.model.NativeViewExtensionAttrsModel;

/**
 * 对原生的View的工具，原生的View指的是android.view.View以及其直接或间接的子类
 * Created by LiuRi on 2017/1/25.
 */

public final class ViewTool {

    public static void applyExtensionAttrs(View view, Canvas canvas, NativeViewExtensionAttrsModel attrs) {
        int backColor = Color.argb(0, 255, 255, 255);// 不直接用Color.TRANSPARENT是因为其颜色值是0,0,0,0，后期动画可能会出现问题
        System.out.println(view.getBackground().getClass());
        if (view.getBackground() instanceof ColorDrawable)
            backColor = ((ColorDrawable) view.getBackground()).getColor();
        // 根据属性创建并渲染背景对象
        GradientDrawable backDrawable = new GradientDrawable();
        backDrawable.setShape(GradientDrawable.RECTANGLE);
        backDrawable.setCornerRadius(attrs.getCornerRadius());
        backDrawable.setColor(backColor);
        backDrawable.setStroke(attrs.getBorderWidth(), attrs.getBorderColor());
        // 应用
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            view.setBackground(backDrawable);
        else
            view.setBackgroundDrawable(backDrawable);

        if (attrs.isClipToBounds() && canvas != null) {
            // 需要切割到边界部分
            Path path = new Path();
            path.addRoundRect(
                    new RectF(0, 0, canvas.getWidth(), canvas.getHeight()),
                    attrs.getCornerRadius(),
                    attrs.getCornerRadius(),
                    Path.Direction.CW
            );
            canvas.clipPath(path, Region.Op.REPLACE);
        }
    }

}
