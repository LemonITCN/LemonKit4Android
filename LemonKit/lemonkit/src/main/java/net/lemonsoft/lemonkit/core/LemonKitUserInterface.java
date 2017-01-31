package net.lemonsoft.lemonkit.core;


import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import net.lemonsoft.lemonkit.ui.view.LKView;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * LemonKit UI 核心类
 * 当前类的主要作用是动态的注入系统的UI包，让其使用LKUI包下的子类
 * Created by lemonsoft on 2017/1/27.
 */

public class LemonKitUserInterface {

    /**
     * 注入LemonKitUI，使LemonKitUI生效
     */
    public synchronized static void injectLKUI(Context context) {
        try {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            Field field = layoutInflater.getClass().getDeclaredField("sClassPrefixList");
            field.setAccessible(true);
            String[] oldPrefixList = (String[]) field.get(layoutInflater);
            String[] newPrefixList = new String[oldPrefixList.length + 1];
            newPrefixList[0] = "net.lemonsoft.lemonkit.ui.view.LK";
            System.arraycopy(oldPrefixList, 0, newPrefixList, 1, oldPrefixList.length);
            field.set(layoutInflater, newPrefixList);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
