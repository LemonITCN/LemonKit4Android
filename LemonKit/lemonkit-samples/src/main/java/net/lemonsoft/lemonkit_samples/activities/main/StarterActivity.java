package net.lemonsoft.lemonkit_samples.activities.main;

import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import net.lemonsoft.lemonkit.annotations.SetContentView;
import net.lemonsoft.lemonkit.base.LKActivity;
import net.lemonsoft.lemonkit.base.LKTabBarActivity;
import net.lemonsoft.lemonkit.ui.view.LKTabBar;
import net.lemonsoft.lemonkit.ui.view.LKTabBarItem;
import net.lemonsoft.lemonkit_samples.R;

import org.w3c.dom.Text;

/**
 * 启动器activity
 * Created by LiuRi on 2017/2/10.
 */
@SetContentView(R.layout.activity_starter)
public class StarterActivity extends LKActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent().setClass(StarterActivity.this, MainActivity.class));
                finish();
            }
        }, 1000);
    }

}
