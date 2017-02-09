package net.lemonsoft.lemonkit_samples;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.lemonsoft.lemonkit.annotations.SetContentView;
import net.lemonsoft.lemonkit.base.LKActivity;
import net.lemonsoft.lemonkit.ui.view.LKTabBar;
import net.lemonsoft.lemonkit.ui.view.LKTabBarItem;

@SetContentView(R.layout.activity_main)
public class MainActivity extends LKActivity {

    private TextView myTextView;
    private ImageView myImageView;
    private LKTabBar tabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myImageView.setBackgroundColor(Color.RED);
        tabBar.setOnTabChangeListener(new LKTabBar.OnTabChangeListener() {
            @Override
            public void onTabChanged(LKTabBar tabBar, int index) {
                System.out.println("current selected: " + index);
            }
        });
        tabBar.getItems().get(0).select();
        View view = new View(this);
        view.setX(100);
        view.setY(100);
        view.setBackgroundColor(Color.parseColor("#abcdef"));
        getWindow().addContentView(view, new ViewGroup.LayoutParams(200, 200));
    }

}