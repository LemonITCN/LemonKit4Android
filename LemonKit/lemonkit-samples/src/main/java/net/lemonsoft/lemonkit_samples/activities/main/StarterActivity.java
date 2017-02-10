package net.lemonsoft.lemonkit_samples.activities.main;

import android.os.Build;

import net.lemonsoft.lemonkit.annotations.SetContentView;
import net.lemonsoft.lemonkit.base.LKTabBarActivity;
import net.lemonsoft.lemonkit.ui.view.LKTabBar;
import net.lemonsoft.lemonkit.ui.view.LKTabBarItem;
import net.lemonsoft.lemonkit_samples.R;

/**
 * 启动器activity
 * Created by LiuRi on 2017/2/10.
 */
@SetContentView(R.layout.activity_starter)
public class StarterActivity extends LKTabBarActivity {

    @Override
    protected int tabItemCount(LKTabBarActivity activity, LKTabBar tabBar) {
        return 4;
    }

    @Override
    protected LKTabBarItem createTabBarItem(LKTabBarActivity activity, LKTabBar tabBar, int index) {
        String[] titles = {"柠檬家", "示例", "服务", "我的"};
        int[] icons = {R.mipmap.main_tab_homepage, R.mipmap.main_tab_demo, R.mipmap.main_tab_service, R.mipmap.main_tab_my};
        LKTabBarItem item = new LKTabBarItem(this);
        item.setText(titles[index]);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            item.setIcon(getDrawable(icons[index]));
        else
            item.setIcon(getResources().getDrawable(icons[index]));
        return item;
    }
}
