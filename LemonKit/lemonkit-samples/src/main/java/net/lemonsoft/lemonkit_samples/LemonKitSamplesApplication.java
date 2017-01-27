package net.lemonsoft.lemonkit_samples;

import android.app.Application;

import net.lemonsoft.lemonkit.core.LemonKit;

/**
 * Created by lemonsoft on 2017/1/27.
 */

public class LemonKitSamplesApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LemonKit.start(this);
    }
}
