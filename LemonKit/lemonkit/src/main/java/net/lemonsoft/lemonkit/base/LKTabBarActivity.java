package net.lemonsoft.lemonkit.base;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;

import net.lemonsoft.lemonkit.ui.view.LKTabBar;
import net.lemonsoft.lemonkit.ui.view.LKTabBarItem;

import java.util.List;

/**
 * Created by LiuRi on 2017/2/10.
 */

public class LKTabBarActivity extends LKActivity {

    private LKTabBar tabBar;
    private static final int TAB_BAR_DEFAULT_HEIGHT = 52;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this._initTabBar();
        this.getWindow().addContentView(tabBar, new ViewGroup.LayoutParams(_ST.screenWidthPx(), _ST.DP(TAB_BAR_DEFAULT_HEIGHT)));
    }

    private void _initTabBar() {
        this.tabBar = new LKTabBar(this);
        this.tabBar.setY(_ST.screenHeightPx() - _ST.DP(TAB_BAR_DEFAULT_HEIGHT) - _ST.actionBarHeight(this) - _ST.statusBarHeight());
        for (int i = 0; i < tabItemCount(this, this.tabBar); i++) {
            this.tabBar.getItems().add(createTabBarItem(this, this.tabBar, i));
        }
        this.tabBar.getItems().get(0).select();// 默认选中第一个选项卡
    }

    protected int tabItemCount(LKTabBarActivity activity, LKTabBar tabBar) {
        return 0;
    }

    protected LKTabBarItem createTabBarItem(LKTabBarActivity activity, LKTabBar tabBar, int index) {
        return null;
    }

    protected Fragment createWithFragment(LKTabBarActivity activity, LKTabBar tabBar, int index) {
        return null;
    }

    protected android.support.v4.app.Fragment createWithV4Fragment(LKTabBarActivity activity, LKTabBar tabBar, int index) {
        return null;
    }

    protected Class createWithFragmentClass(LKTabBarActivity activity, LKTabBar tabBar, int index) {
        return null;
    }

    public LKTabBar getTabBar() {
        return tabBar;
    }

}
