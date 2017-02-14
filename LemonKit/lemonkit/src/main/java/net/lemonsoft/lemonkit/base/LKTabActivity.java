package net.lemonsoft.lemonkit.base;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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

public class LKTabActivity extends LKActivity {

    private LKTabBar tabBar;
    private List<Fragment> fragments = new ArrayList<>();
    private FrameLayout contentLayout;
    private FragmentManager fragmentManager;
    private static final int TAB_BAR_DEFAULT_HEIGHT = 52;
    private int topBarHeight;
    private int currentIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.topBarHeight = _ST.actionBarHeight(this) + _ST.statusBarHeight();
        this._initContentLayout();
        this._initTabBar();
    }

    private void _initContentLayout() {
        this.fragmentManager = getFragmentManager();
        this.contentLayout = new FrameLayout(this) {
            @Override
            public int getId() {
                if (super.getId() == NO_ID)
                    this.setId(this.hashCode());
                return this.hashCode();
            }
        };
        this.getWindow().addContentView(this.contentLayout, new ViewGroup.LayoutParams(_ST.screenWidthPx(), _ST.screenHeightPx() - _ST.DP(TAB_BAR_DEFAULT_HEIGHT) - topBarHeight));
    }

    private void _initTabBar() {
        this.tabBar = new LKTabBar(this);
        this.reloadFragments();
        this.tabBar.setY(_ST.screenHeightPx() - _ST.DP(TAB_BAR_DEFAULT_HEIGHT) - this.topBarHeight);
        for (int i = 0; i < tabItemCount(this, this.tabBar); i++)
            this.tabBar.getItems().add(createTabBarItem(this, this.tabBar, i));
        this.tabBar.setOnTabChangeListener(new LKTabBar.OnTabChangeListener() {
            @Override
            public void onTabChanged(LKTabBar tabBar, int index) {
                // 采用hide和add、show的方法，而不是replace，避免每次的重新新实例化对象而对内存产生影响
                // 也避免每次页面中的网络请求都执行，而浪费用户流量
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                if (!fragments.get(index).isAdded())
                    // 未添加过这个fragment，那么添加
                    transaction.add(contentLayout.getId(), fragments.get(index));
                if (currentIndex != index) {// 更换显示的fragment
                    transaction.show(fragments.get(index));
                    if (currentIndex != -1)
                        transaction.hide(fragments.get(currentIndex));
                    currentIndex = index;
                    transaction.commit();
                }
            }
        });
        this.tabBar.getItems().get(0).select();// 默认选中第一个选项卡
        this.getWindow().addContentView(tabBar, new ViewGroup.LayoutParams(_ST.screenWidthPx(), _ST.DP(TAB_BAR_DEFAULT_HEIGHT)));
    }

    /**
     * 重新加载Fragment序列
     */
    public void reloadFragments() {
        fragments.clear();
        for (int i = 0; i < tabItemCount(this, this.tabBar); i++)
            fragments.add(_createWithFragment(this, this.tabBar, i));
    }

    protected int tabItemCount(LKTabActivity activity, LKTabBar tabBar) {
        return 0;
    }

    protected LKTabBarItem createTabBarItem(LKTabActivity activity, LKTabBar tabBar, int index) {
        return null;
    }

    private Fragment _createWithFragment(LKTabActivity activity, LKTabBar tabBar, int index) {
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
    protected Class createWithFragmentClass(LKTabActivity activity, LKTabBar tabBar, int index) {
        return null;
    }

    // 优先度 - 2
    protected Fragment createWithFragmentInstance(LKTabActivity activity, LKTabBar tabBar, int index) {
        return null;
    }

    public LKTabBar getTabBar() {
        return tabBar;
    }

}
