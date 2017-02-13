package net.lemonsoft.lemonkit.tools;

import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.ColorInt;

import net.lemonsoft.lemonkit.consts.LKNameConst;
import net.lemonsoft.lemonkit.core.LemonKit;

/**
 * LemonKit颜色工具类
 * Created by LiuRi on 2017/2/13.
 */

public class LKColorTool {

    public static
    @ColorInt
    int themeColor() {
        int colorId = LKResourceTool.getColorId(LemonKit.sharedInstance().getAppContext(), LKNameConst.THEME_COLOR);
        Resources resources = LemonKit.sharedInstance().getAppContext().getResources();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            return resources.getColor(colorId, null);
        else
            return resources.getColor(colorId);
    }

}
