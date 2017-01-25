package ui;

import android.content.Context;
import android.util.AttributeSet;

/**
 * LKView的引用，为了方便在XML中使用，简短包名
 * Created by LiuRi on 2017/1/25.
 */

public class LKView extends net.lemonsoft.lemonkit.ui.view.LKView {

    public LKView(Context context) {
        super(context);
    }

    public LKView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LKView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public LKView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
