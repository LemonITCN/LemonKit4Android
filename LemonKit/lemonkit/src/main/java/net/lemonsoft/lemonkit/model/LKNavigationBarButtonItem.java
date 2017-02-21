package net.lemonsoft.lemonkit.model;

import android.graphics.drawable.Drawable;
import android.view.View;

import net.lemonsoft.lemonkit.model.general.LKEdgeDeviationInfo;
import net.lemonsoft.lemonkit.model.general.LKSizeInfo;

/**
 * Created by LiuRi on 2017/2/21.
 */

public class LKNavigationBarButtonItem {

    private Object content;

    private LKSizeInfo size;

    private LKEdgeDeviationInfo edgeDeviation;

    private LKNavigationBarButtonItem() {
    }

    public LKNavigationBarButtonItem(Drawable icon) {
        this.content = icon;
    }

    public LKNavigationBarButtonItem(String title) {
        this.content = title;
    }

    public LKNavigationBarButtonItem(View view) {
        this.content = view;
    }

}
