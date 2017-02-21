package net.lemonsoft.lemonkit.model;

import android.view.View;

import java.util.List;

/**
 * Created by LiuRi on 2017/2/21.
 */

public class LKNavigationInfo {

    private String title;
    private String subTitle;
    // 当实例对象没有设置centerView的时候，在被插入到NavigationBar的时候，该属性会被放置一个含有主标题和次标题的View控件，请参考运行时效果
    private View centerView;
    private List<LKNavigationBarButtonItem> leftBarButtonItems;
    private List<LKNavigationBarButtonItem> rightBarButtonItems;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public View getCenterView() {
        return centerView;
    }

    public void setCenterView(View centerView) {
        this.centerView = centerView;
    }

    public List<LKNavigationBarButtonItem> getLeftBarButtonItems() {
        return leftBarButtonItems;
    }

    public List<LKNavigationBarButtonItem> getRightBarButtonItems() {
        return rightBarButtonItems;
    }

    

}
