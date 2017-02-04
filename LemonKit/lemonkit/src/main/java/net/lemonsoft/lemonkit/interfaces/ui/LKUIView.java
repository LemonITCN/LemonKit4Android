package net.lemonsoft.lemonkit.interfaces.ui;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import net.lemonsoft.lemonkit.model.LKUIExtensionModel;

/**
 * LKUIView 的标准接口
 * Created by lemonsoft on 2017/1/28.
 */

public interface LKUIView {

//    void onDraw(Canvas canvas);

//    void finalize();

    void setBackground(Drawable drawable);

    LKUIExtensionModel getLk();

}
