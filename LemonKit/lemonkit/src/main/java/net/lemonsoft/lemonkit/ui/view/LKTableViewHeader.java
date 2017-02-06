package net.lemonsoft.lemonkit.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.widget.RelativeLayout;

/**
 * LKUITableViewCell的行元素
 * Created by LiuRi on 16/8/3.
 */
public class LKTableViewHeader extends RelativeLayout {

    /**
     * 服用标识
     */
    private String reuseIdentifier = null;

    public LKTableViewHeader(Context context) {
        super(context);
        initCell();
    }

    public LKTableViewHeader(Context context, String reuseIdentifier) {
        super(context);
        this.reuseIdentifier = reuseIdentifier;
        initCell();
    }

    public void initCell() {
        this.setBackgroundColor(Color.WHITE);
    }

    public String getReuseIdentifier() {
        return reuseIdentifier;
    }
}
