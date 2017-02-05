package net.lemonsoft.lemonkit.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import net.lemonsoft.lemonkit.core.parser.LKUIAnnotationParser;

/**
 * LKActivity，对原Activity的封装和增强
 * Created by lemonsoft on 2017/1/30.
 */
public class LKActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LKUIAnnotationParser.parse(this); // 解析注解
    }

    public void startActivity(Class<Activity> activityClass) {
        startActivity(new Intent().setClass(this, activityClass));
    }

}
