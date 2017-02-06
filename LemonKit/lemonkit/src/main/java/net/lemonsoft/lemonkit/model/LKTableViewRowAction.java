package net.lemonsoft.lemonkit.model;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import net.lemonsoft.lemonkit.tools.LKSizeTool;
import net.lemonsoft.lemonkit.ui.view.LKTableView;


/**
 * LKTableView的侧滑事件，在cell的对应代理函数中，返回一个该类型的list后即可生成对应效果的侧滑菜单
 * Created by lemonsoft on 16-9-28.
 */
public class LKTableViewRowAction {

    /**
     * 容器控件
     */
    private View containerView;
    /**
     * 当前action的宽度
     */
    private Integer width = 0;
    /**
     * 背景颜色
     */
    private int backgroundColor = Color.RED;
    /**
     * 所处行的位置
     */
    private LKIndexPath indexPath;

    /**
     * 触摸事件
     */
    private TouchAction touchAction;

    private LKTableViewRowAction() {
    }

    public LKTableViewRowAction(View containerView, Integer width, int backgroundColor, TouchAction touchAction) {
        this.containerView = containerView;
        this.width = width;
        this.backgroundColor = backgroundColor;
        this.touchAction = touchAction;
    }

    public LKTableViewRowAction(Context context, String title, int textColor, int backgroundColor, TouchAction touchAction) {
        this(new TextView(context), LKSizeTool.getDefaultSizeTool().dpToPx(30f), backgroundColor, touchAction);
        TextView titleView = (TextView) containerView;
        titleView.setText(title);
        titleView.setTextColor(textColor);
        titleView.setGravity(Gravity.CENTER);
        setWidth(LKSizeTool.getDefaultSizeTool().dpToPx(14f) * title.length() + getWidth());
    }


    public View getContainerView() {
        return containerView;
    }

    public void setContainerView(View containerView) {
        this.containerView = containerView;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public LKIndexPath getIndexPath() {
        return indexPath;
    }

    public void setIndexPath(LKIndexPath indexPath) {
        this.indexPath = indexPath;
    }

    public TouchAction getTouchAction() {
        return touchAction;
    }

    public void setTouchAction(TouchAction touchAction) {
        this.touchAction = touchAction;
    }

    public interface TouchAction {
        /**
         * 触摸事件
         *
         * @param rowAction 点击的rowAction
         * @param indexPath 当前行的位置
         */
        void onTouch(LKTableView tableView, LKTableViewRowAction rowAction, LKIndexPath indexPath);
    }

}
