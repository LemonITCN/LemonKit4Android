package net.lemonsoft.lemonkit.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import net.lemonsoft.lemonkit.core.LKUIAttrsCore;
import net.lemonsoft.lemonkit.interfaces.ui.LKUI;
import net.lemonsoft.lemonkit.model.LKUIAttrsModel;

/**
 * LKView,对系统的基本View进行扩展
 * Created by LiuRi on 2017/1/25.
 */

public class LKView extends View implements LKUI {

    private Canvas canvas;
    private LKUIAttrsModel attrsModel;

    public LKView(Context context) {
        super(context);
    }

    public LKView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LKUIAttrsCore.dealWithView(this, attrs);
    }

    public LKView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LKUIAttrsCore.dealWithView(this, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LKView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        LKUIAttrsCore.dealWithView(this, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
    }

    @Override
    public Canvas getCanvas() {
        return canvas;
    }

    @Override
    public void setLKUIAttrs(LKUIAttrsModel attrs) {
        this.attrsModel = attrs;
        LKUIAttrsCore.dealWithView(this, attrs);
    }

    @Override
    public LKUIAttrsModel getLKUIAttrs() {
        return this.attrsModel;
    }

    @Override
    public View getView() {
        return this;
    }
}
