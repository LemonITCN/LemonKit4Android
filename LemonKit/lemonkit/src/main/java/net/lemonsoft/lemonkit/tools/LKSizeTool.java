package net.lemonsoft.lemonkit.tools;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

import net.lemonsoft.lemonkit.core.LemonKit;
import net.lemonsoft.lemonkit.core.graphics.CGRect;


/**
 * LemonKit工具类 - 尺寸相关工具
 * Created by LiuRi on 2016/12/23.
 */

public class LKSizeTool {

    private float _density;
    private DisplayMetrics _metrics;

    private static LKSizeTool _privateSizeTool;

    public static synchronized LKSizeTool getDefaultSizeTool() {
        if (_privateSizeTool == null)
            _privateSizeTool = new LKSizeTool();
        return _privateSizeTool;
    }

    public LKSizeTool() {
        _density = LemonKit.sharedInstance().getAppContext().getResources().getDisplayMetrics().density;
        _metrics = new DisplayMetrics();
        ((WindowManager) (LemonKit.sharedInstance().getAppContext().getSystemService(Context.WINDOW_SERVICE))).getDefaultDisplay().getMetrics(_metrics);
    }

    // 将该工具类拷贝到工程单独使用（脱离LemoNkit）时候解开注释
//    public void setContext(Context context) {
//        _density = context.getResources().getDisplayMetrics().density;
//        _metrics = new DisplayMetrics();
//        ((WindowManager) (context.getSystemService(Context.WINDOW_SERVICE))).getDefaultDisplay().getMetrics(_metrics);
//    }

    /**
     * 换算dp到px
     *
     * @param dpValue dp的数值
     * @return 对应的px数值
     */
    public int dpToPx(float dpValue) {
        return (int) (_density * dpValue + 0.5f);
    }

    /**
     * 换算px到dp
     *
     * @param pxValue px的数值
     * @return 对应的dp数值
     */
    public int pxToDp(float pxValue) {
        return (int) (pxValue / _density + 0.5f);
    }

    /**
     * DP值，实际就是把数值当做DP来换算成PX返回
     *
     * @param dpValue DP的值
     * @return
     */
    public int DP(float dpValue) {
        return dpToPx(dpValue);
    }

    /**
     * 获取屏幕的宽，单位dp
     *
     * @return 屏幕宽度dp值
     */
    public int screenWidthDp() {
        return pxToDp(_metrics.widthPixels);
    }

    /**
     * 获取屏幕的高，单位dp
     *
     * @return 屏幕高度的dp值
     */
    public int screenHeightDp() {
        return pxToDp(_metrics.heightPixels);
    }

    /**
     * 获取屏幕的宽，单位px
     *
     * @return 屏幕宽度dp值
     */
    public int screenWidthPx() {
        return _metrics.widthPixels;
    }

    /**
     * 获取屏幕的高，单位px
     *
     * @return 屏幕高度的dp值
     */
    public int screenHeightPx() {
        return _metrics.heightPixels;
    }

    /**
     * 获取屏幕的矩形信息，单位DP
     *
     * @return 屏幕的矩形信息
     */
    public CGRect screenFrame() {
        return CGRect.make(0, 0, screenWidthDp(), screenHeightDp());
    }

    /**
     * 获取ActionBar的高度
     * 隐藏，或者主题中不包括系统自带ActionBar，那么返回0
     */
    public int actionBarHeight(Activity activity) {
        if (activity.getActionBar() == null || !activity.getActionBar().isShowing())
            return 0;
        int actionBarHeight = activity.getActionBar().getHeight();
        if (actionBarHeight != 0)
            return actionBarHeight;
        final TypedValue tv = new TypedValue();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB
                && activity.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, _metrics);
        else if (activity.getTheme().resolveAttribute(android.support.v7.appcompat.R.attr.actionBarSize, tv, true))
            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, _metrics);
        return actionBarHeight;
    }

    /**
     * 获取状态栏高度
     */
    public int statusBarHeight() {
        // 由于无法直接获取状态栏高度，所以使用反射来间接刚获取
        Class<?> c;
        Object obj;
        java.lang.reflect.Field field = null;
        int x;
        int statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = LemonKit.sharedInstance().getAppContext().getResources().getDimensionPixelSize(x);
            return statusBarHeight;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusBarHeight;
    }

}
