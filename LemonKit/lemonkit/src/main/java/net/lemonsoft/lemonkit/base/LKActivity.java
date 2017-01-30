package net.lemonsoft.lemonkit.base;

import android.app.Activity;
import android.content.Intent;

/**
 * LKActivity，对原Activity的封装和增强
 * Created by lemonsoft on 2017/1/30.
 */

public class LKActivity extends Activity {

    public void startActivity(Class<Activity> activityClass) {
        startActivity(new Intent().setClass(this, activityClass));
    }

}
