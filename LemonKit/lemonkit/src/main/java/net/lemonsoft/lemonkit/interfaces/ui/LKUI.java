package net.lemonsoft.lemonkit.interfaces.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import net.lemonsoft.lemonkit.model.LKUIAttrsModel;

/**
 * Created by LiuRi on 2017/1/25.
 */

public interface LKUI {

    Canvas getCanvas();

    Context getContext();

    void setLKUIAttrs(LKUIAttrsModel attrs);

    LKUIAttrsModel getLKUIAttrs();

}
