package net.lemonsoft.lemonkit.interfaces.ui;

import android.graphics.drawable.Drawable;

import net.lemonsoft.lemonkit.model.LKUIExtensionModel;

/**
 * LKUIView 的标准接口
 * Created by lemonsoft on 2017/1/28.
 */

public interface LKUIView {

    void setLk(LKUIExtensionModel lkuiExtensionModel);

    void setBackground(Drawable drawable);

}
