package net.lemonsoft.lemonkit.tools;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

/**
 * LK资源工具
 * 通常，我们获取资源（View，Drawable，mipmap等）的时候都是通过R.id.xxx或R.drawable.xxx的int值获取，
 * 如果我们只知道后面xxx的字符串的话，可以通过此类来进行获取对应的资源
 * Created by lemonsoft on 2017/2/5.
 */

public class LKResourceTool {

    /**
     * 根据控件的ID字符串获取真实的控件ID int类型
     */
    public static int getViewId(Context context, String name) {
        return getId(context, "id", name);
    }

    /**
     * 根据mipmap的ID字符串获取真实的mipmap ID int类型
     */
    public static int getMipmapId(Context context, String name) {
        return getId(context, "mipmap", name);
    }

    /**
     * 根据drawable的ID字符串获取真实的drawable ID int类型
     */
    public static int getDrawableId(Context context, String name) {
        return getId(context, "drawable", name);
    }

    /**
     * 根据layout的ID字符串获取真实的layout ID int类型
     */
    public static int getLayoutId(Context context, String name) {
        return getId(context, "layout", name);
    }

    /**
     * 根据string的ID字符串获取真实的string ID int类型
     */
    public static int getStringId(Context context, String name) {
        return getId(context, "string", name);
    }

    /**
     * 根据color的ID字符串获取真实的color ID int类型
     */
    public static int getColorId(Context context, String name) {
        return getId(context, "color", name);
    }

    /**
     * 通过控件的ID字符串直接获取到控件对象
     */
    public static View findViewByIdStr(Activity activity, String name) {
        return activity.findViewById(getViewId(activity, name));
    }

    /**
     * 通过控件的ID字符串直接获取到控件对象
     */
    public static View findViewByIdStr(View view, String name) {
        return view.findViewById(getViewId(view.getContext(), name));
    }

    /**
     * 根据字符串id名称字符串直接获取到字符串对应的对象
     */
    public static String findStringByIdStr(Activity activity, String name) {
        return activity.getResources().getString(getStringId(activity, name));
    }

    /**
     * 根据资源类型和名称直接获取对应的int类型id
     */
    public static int getId(Context context, String type, String name) {
        return context.getResources().getIdentifier(name, type, context.getPackageName());
    }

}
