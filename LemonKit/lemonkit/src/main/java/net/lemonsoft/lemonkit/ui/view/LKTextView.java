package net.lemonsoft.lemonkit.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import net.lemonsoft.lemonkit.core.LKUIAttrsCore;
import net.lemonsoft.lemonkit.enums.LKUIDelegateOnDrawState;
import net.lemonsoft.lemonkit.interfaces.ui.LKUIDelegate;
import net.lemonsoft.lemonkit.interfaces.ui.LKUIView;
import net.lemonsoft.lemonkit.model.LKUIExtensionModel;
import net.lemonsoft.lemonkit.tools.LKDrawableTool;

import java.lang.reflect.Field;

/**
 * LKTextView,对系统的基本TextView进行扩展
 * Created by LiuRi on 2017/1/25.
 */

public class LKTextView extends TextView implements LKUIView {

    public LKUIExtensionModel lk;

    public LKTextView(Context context) {
        super(context);
    }

    public LKTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LKUIAttrsCore.parse(this, attrs);
    }

    public LKTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LKUIAttrsCore.parse(this, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LKTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        LKUIAttrsCore.parse(this, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (lk != null)
            lk.onDrawHandler(canvas, LKUIDelegateOnDrawState.PRE_SUPER_DRAW);
        super.onDraw(canvas);
        if (lk != null)
            lk.onDrawHandler(canvas, LKUIDelegateOnDrawState.AFT_SUPER_DRAW);
    }

    @Override
    public void setLk(LKUIExtensionModel lk) {
        this.lk = lk;
        lk.setView(this);
        if (lk.getCornerRadius() >= 0 && getBackground() != null) {
            Drawable drawable = LKDrawableTool.createRoundCornerDrawable(getBackground(), lk.getCornerRadius());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                setBackground(drawable);
            else
                setBackgroundDrawable(drawable);
        }
    }

}
