package net.lemonsoft.lemonkit.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * Created by LiuRi on 2017/2/21.
 */

public class LKNavigationBar extends RelativeLayout {

    public LKNavigationBar(Context context) {
        super(context);
        this._init();
    }

    public LKNavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this._init();
    }

    public LKNavigationBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this._init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LKNavigationBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this._init();
    }

    private void _init() {

    }

}
