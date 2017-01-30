package net.lemonsoft.lemonkit.tools;

import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.view.Window;
import android.view.WindowManager;

/**
 * LemonKit工具类 - 状态栏相关工具
 * Created by lemonsoft on 2017/1/29.
 */

public class LKStatusBarTool {


    public static void setBackgroundColor(Activity activity, @ColorInt int color) {
        setBackgroundColor(activity.getWindow(), color);
    }

    public static void setBackgroundColor(Dialog dialog, @ColorInt int color) {
        setBackgroundColor(dialog.getWindow(), color);
    }

    public static void setBackgroundColor(Window window, @ColorInt int color) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(color);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
