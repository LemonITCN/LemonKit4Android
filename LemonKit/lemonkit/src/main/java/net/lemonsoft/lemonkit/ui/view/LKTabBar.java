package net.lemonsoft.lemonkit.ui.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import net.lemonsoft.lemonkit.R;
import net.lemonsoft.lemonkit.tools.LKSizeTool;

import java.util.ArrayList;
import java.util.List;

/**
 * LemonKit扩展选项卡栏
 * Created by lemonsoft on 2017/2/7.
 */
public class LKTabBar extends RelativeLayout {

    private static final int DEFAULT_COLOR = Color.parseColor("#aaaaaa");
    private static final int DEFAULT_SELECTED_COLOR = Color.parseColor("#4169E1");
    private static final int DEFAULT_BACKGROUND_COLOR = Color.parseColor("#ebebeb");

    private int defaultColor = DEFAULT_COLOR;
    private int defaultSelectedColor = DEFAULT_SELECTED_COLOR;

    private LKSizeTool _ST = LKSizeTool.getDefaultSizeTool();

    private ArrayList<LKTabBarItem> items = new ArrayList<LKTabBarItem>() {
        @Override
        public boolean add(LKTabBarItem o) {
            boolean result = false;
            if (!items.contains(o))
                result = super.add(o);
            if (indexOfChild(o) < 0)
                addView(o);
            refresh();// 刷新UI
            return result;
        }
    };
    private LKTabBarItem selectedItem;

    public interface OnTabChangeListener {
        void onTabChanged(LKTabBar tabBar, int index);
    }

    private OnTabChangeListener onTabChangeListener;

    public LKTabBar(Context context) {
        super(context);
        init();
    }

    public LKTabBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.LKTabBar);
        assert array != null;// 断言array不为空
        setDefaultColor(array.getColor(R.styleable.LKTabBar_defaultColor, DEFAULT_COLOR));
        setDefaultSelectedColor(array.getColor(R.styleable.LKTabBar_defaultSelectedColor, DEFAULT_SELECTED_COLOR));
    }

    private void init() {
        if (getBackground() == null)
            setBackgroundColor(DEFAULT_BACKGROUND_COLOR);
    }

    // item呼叫入口，说明被改变了
    protected void callChanged(LKTabBarItem item, boolean checked) {
        if (!item.equals(selectedItem)) {
            if (selectedItem != null)
                selectedItem.setChecked(false);
            if (onTabChangeListener != null)
                onTabChangeListener.onTabChanged(this, items.indexOf(item));
        }
        selectedItem = item;
    }

    /**
     * 刷新显示UI
     */
    private void refresh() {
        for (int i = 0; i < items.size(); i++) {
            LKTabBarItem child = items.get(i);
            child.setLayoutParams(new RelativeLayout.LayoutParams(180, ViewGroup.LayoutParams.MATCH_PARENT));
            child.setX(_ST.screenWidthPx() / items.size() / 2 + (_ST.screenWidthPx() / items.size() * i) - 180 / 2);
        }
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        if (child instanceof LKTabBarItem) {
            this.items.add((LKTabBarItem) child);
            ((LKTabBarItem) child).belongTabBar = this;// 设置归属tabBar
            ((LKTabBarItem) child).setChecked(((LKTabBarItem) child).isChecked());
        }
    }

    public OnTabChangeListener getOnTabChangeListener() {
        return onTabChangeListener;
    }

    public void setOnTabChangeListener(OnTabChangeListener onTabChangeListener) {
        this.onTabChangeListener = onTabChangeListener;
    }

    public List<LKTabBarItem> getItems() {
        return items;
    }

    public
    @ColorInt
    int getDefaultColor() {
        return defaultColor;
    }

    public void setDefaultColor(int defaultColor) {
        this.defaultColor = defaultColor;
    }

    public
    @ColorInt
    int getDefaultSelectedColor() {
        return defaultSelectedColor;
    }

    public void setDefaultSelectedColor(int defaultSelectedColor) {
        this.defaultSelectedColor = defaultSelectedColor;
    }

    // 下面这行代码块是为了在xml中不设置width和height时防止闪退
    @Override
    public RelativeLayout.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LKTabBar.LayoutParams(getContext(), attrs);
    }

    public static class LayoutParams extends RelativeLayout.LayoutParams {
        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        @Override
        protected void setBaseAttributes(TypedArray a, int widthAttr, int heightAttr) {
            width = height = WRAP_CONTENT;
        }
    }

}