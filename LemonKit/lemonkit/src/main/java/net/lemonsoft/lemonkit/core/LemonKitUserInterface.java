package net.lemonsoft.lemonkit.core;


import android.content.Context;
import android.view.LayoutInflater;

import java.lang.reflect.Field;

/**
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
            for (int i = 0; i < oldPrefixList.length; i++)
                newPrefixList[i + 1] = oldPrefixList[i];
            field.set(layoutInflater, newPrefixList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
