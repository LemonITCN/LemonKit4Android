package net.lemonsoft.lemonkit.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.RadioButton;

/**
 * LKRadioButton,对系统的基本RadioButton进行扩展
 * Created by lemonsoft on 2017/1/29.
 */

public class LKRadioButton extends RadioButton {

    public LKRadioButton(Context context) {
        super(context);
    }

    public LKRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LKRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LKRadioButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
