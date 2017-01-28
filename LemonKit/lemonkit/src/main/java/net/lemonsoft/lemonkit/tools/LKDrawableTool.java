package net.lemonsoft.lemonkit.tools;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

/**
 * Drawable工具类
 * Created by lemonsoft on 2017/1/28.
 */

public class LKDrawableTool {

    /**
     * 根据制定的drawable和圆角半径，生成对应的圆角的Drawable
     *
     * @param drawable     要生成圆角Drawable的原Drawable
     * @param cornerRadius 圆角半径
     * @return 带有圆角的Drawable
     */
    public static Drawable createRoundCornerDrawable(Drawable drawable, int cornerRadius) {
        Drawable outDrawable = drawable;
        if (drawable instanceof ColorDrawable) {
            // 根据颜色Drawable生成圆角Drawable
            outDrawable = new GradientDrawable();
            ((GradientDrawable) outDrawable).setColor(((ColorDrawable) drawable).getColor());
            ((GradientDrawable) outDrawable).setCornerRadius(cornerRadius);
        }
        return outDrawable;
    }

}
