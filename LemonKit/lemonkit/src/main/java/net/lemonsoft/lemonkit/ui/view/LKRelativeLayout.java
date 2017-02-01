package net.lemonsoft.lemonkit.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import net.lemonsoft.lemonkit.interfaces.ui.LKUIView;
import net.lemonsoft.lemonkit.model.LKUIExtensionModel;

/**
 * Created by lemonsoft on 2017/2/1.
 */

public class LKRelativeLayout extends RelativeLayout implements LKUIView {

    public LKRelativeLayout(Context context) {
        super(context);
    }

    public LKRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setLk(LKUIExtensionModel lkuiExtensionModel) {

    }
}
