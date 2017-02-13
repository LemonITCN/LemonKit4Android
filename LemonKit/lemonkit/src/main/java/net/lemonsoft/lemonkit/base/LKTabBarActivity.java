package net.lemonsoft.lemonkit.base;

import android.app.Fragment;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import net.lemonsoft.lemonkit.ui.view.LKTabBar;
import net.lemonsoft.lemonkit.ui.view.LKTabBarItem;

import java.util.ArrayList;
import java.util.List;

/**
 * 带有LKTabBar选项卡的Activity
 * Created by LiuRi on 2017/2/10.
 */

public class LKTabBarActivity extends LKActivity {

    private LKTabBar tabBar;
    private List<Fragment> fragments = new ArrayList<>();
    private FrameLayout contentLayout;
    private static final int TAB_BAR_DEFAULT_HEIGHT = 52;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this._initTabBar();
        this.getWindow().addContentView(tabBar, new ViewGroup.LayoutParams(_ST.screenWidthPx(), _ST.DP(TAB_BAR_DEFAULT_HEIGHT)));
    }

    private void _initTabBar() {
        this.contentLayout = new FrameLayout(this);
        this.tabBar = new LKTabBar(this);
        this.tabBar.setY(_ST.screenHeightPx() - _ST.DP(TAB_BAR_DEFAULT_HEIGHT) - _ST.actionBarHeight(this) - _ST.statusBarHeight());
        for (int i = 0; i < tabItemCount(this, this.tabBar); i++)
            this.tabBar.getItems().add(createTabBarItem(this, this.tabBar, i));
        this.reloadFragments();
        this.tabBar.setOnTabChangeListener(new LKTabBar.OnTabChangeListener() {
            @Override
            public void onTabChanged(LKTabBar tabBar, int index) {
                
            }
        });
        this.tabBar.getItems().get(0).select();// 默认选中第一个选项卡
    }

    /**
     * 重新加载Fragment序列
     */
    public void reloadFragments() {
        fragments.clear();
        for (int i = 0; i < tabItemCount(this, this.tabBar); i++)
            fragments.add(_createWithFragment(this, this.tabBar, i));
    }

    protected int tabItemCount(LKTabBarActivity activity, LKTabBar tabBar) {
        return 0;
    }

    protected LKTabBarItem createTabBarItem(LKTabBarActivity activity, LKTabBar tabBar, int index) {
        return null;
    }

    private Fragment _createWithFragment(LKTabBarActivity activity, LKTabBar tabBar, int index) {
        Fragment fragment = null;
        try {
            Class fragmentClass = createWithFragmentClass(activity, tabBar, index);
            if (fragmentClass != null)
                fragment = (Fragment) fragmentClass.newInstance();
            else
                fragment = createWithFragmentInstance(activity, tabBar, index);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fragment;
    }

    // 优先度 - 1
    protected Class createWithFragmentClass(LKTabBarActivity activity, LKTabBar tabBar, int index) {
        return null;
    }

    // 优先度 - 2
    protected Fragment createWithFragmentInstance(LKTabBarActivity activity, LKTabBar tabBar, int index) {
        return null;
    }

    public LKTabBar getTabBar() {
        return tabBar;
    }

}
