package net.lemonsoft.lemonkit_samples.activities.main;

import android.graphics.Color;

import net.lemonsoft.lemonkit.annotations.SetContentView;
import net.lemonsoft.lemonkit.base.LKTabActivity;
import net.lemonsoft.lemonkit.tools.LKColorTool;
import net.lemonsoft.lemonkit.tools.LKStatusBarTool;
import net.lemonsoft.lemonkit.ui.view.LKTabBar;
import net.lemonsoft.lemonkit.ui.view.LKTabBarItem;
import net.lemonsoft.lemonkit_samples.R;
import net.lemonsoft.lemonkit_samples.fragments.main.DemoFragment;
import net.lemonsoft.lemonkit_samples.fragments.main.HomepageFragment;
import net.lemonsoft.lemonkit_samples.fragments.main.MyFragment;
import net.lemonsoft.lemonkit_samples.fragments.main.ServiceFragment;

@SetContentView(R.layout.activity_main)
public class MainActivity extends LKTabActivity {

    @Override
    protected int tabItemCount(LKTabActivity activity, LKTabBar tabBar) {
        tabBar.setDefaultSelectedColor(LKColorTool.themeColor());
        return 4;
    }

    @Override
    protected LKTabBarItem createTabBarItem(LKTabActivity activity, LKTabBar tabBar, int index) {
        String[] titles = {"柠檬家", "示例", "服务", "我的"};
        int[] icons = {R.mipmap.main_tab_homepage, R.mipmap.main_tab_demo, R.mipmap.main_tab_service, R.mipmap.main_tab_my};
        LKTabBarItem item = new LKTabBarItem(this);
        item.setText(titles[index]);
        item.setIcon(icons[index]);
        return item;
    }

    @Override
    protected Class createWithFragmentClass(LKTabActivity activity, LKTabBar tabBar, int index) {
        Class[] classes = {HomepageFragment.class, DemoFragment.class, ServiceFragment.class, MyFragment.class};
        return classes[index];
    }

    @Override
    protected void initView() {
        super.initView();
        LKStatusBarTool.setBackgroundColor(this, Color.RED);
    }
}