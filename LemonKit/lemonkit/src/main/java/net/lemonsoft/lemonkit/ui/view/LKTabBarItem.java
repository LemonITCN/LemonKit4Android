package net.lemonsoft.lemonkit.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import net.lemonsoft.lemonkit.R;


/**
 * LemonKit扩展选项卡栏 - 选项
 * Created by lemonsoft on 2017/2/7.
 */
public class LKTabBarItem extends RadioButton {
    // 默认颜色
    private int color = -1;
    // 选中颜色，当没有设置selectedIcon的时候使用此颜色渲染图标
    private int selectedColor = -1;
    // 默认图标
    private Drawable icon;
    // 选中图标，当此对象不为null的时候使用此图标作为选中样式，而不用渲染色
    private Drawable selectedIcon;

    protected LKTabBar belongTabBar;

    public LKTabBarItem(Context context) {
        super(context);
        initView();
    }

    public LKTabBarItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        this.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.LKTabBarItem);
        assert array != null;// 断言array不为空
        setColor(array.getColor(R.styleable.LKTabBarItem_color, -1));
        setSelectedColor(array.getColor(R.styleable.LKTabBarItem_selectedColor, -1));
        setIcon(array.getDrawable(R.styleable.LKTabBarItem_icon));
        setSelectedIcon(array.getDrawable(R.styleable.LKTabBarItem_selectedIcon));
    }

    public void select() {
        this.setChecked(true);
    }

    public void initView() {
        //隐藏默认单选按钮
        this.setButtonDrawable(null);
        this.setTextSize(10);
        this.setGravity(Gravity.CENTER);
    }

    public
    @ColorInt
    int getColor() {
        return color != -1 ?
                color :
                belongTabBar == null ?
                        Color.parseColor("#aaaaaa") :
                        belongTabBar.getDefaultColor();
    }

    public void setColor(int color) {
        this.color = color;
        if (!isChecked())
            setChecked(false);
    }

    public
    @ColorInt
    int getSelectedColor() {
        return selectedColor != -1 ?
                selectedColor :
                belongTabBar == null ?
                        Color.parseColor("#4169E1") :
                        belongTabBar.getDefaultSelectedColor();
    }

    public void setSelectedColor(int selectedColor) {
        this.selectedColor = selectedColor;
        if (isChecked())
            setChecked(true);
    }

    public Drawable getIcon() {
        return icon;
    }

    public void setIcon(Drawable icon) {
        this.icon = icon;
        if (icon != null)
            icon.setBounds(0, 20, 60, 80);
        if (!isChecked())
            setChecked(false);
    }

    public Drawable getSelectedIcon() {
        return selectedIcon;
    }

    public void setSelectedIcon(Drawable selectedIcon) {
        if (selectedIcon != null)
            selectedIcon.setBounds(0, 20, 60, 80);
        this.selectedIcon = selectedIcon;
        if (isChecked())
            setChecked(true);
    }

    @Override
    public void setChecked(boolean checked) {
        super.setChecked(checked);
        Drawable currDrawable = checked ? icon : (selectedIcon == null ? icon : selectedIcon);
        if (currDrawable != null) {
            setCompoundDrawables(null, currDrawable, null, null);
            if (selectedIcon == null)
                currDrawable.setColorFilter(checked ? getSelectedColor() : getColor(), PorterDuff.Mode.SRC_IN);
            else
                currDrawable.clearColorFilter();// 清除渲染色
        }
        setTextColor(checked ? getSelectedColor() : getColor());
        if (belongTabBar != null)
            belongTabBar.callChanged(this, checked);
    }

}
