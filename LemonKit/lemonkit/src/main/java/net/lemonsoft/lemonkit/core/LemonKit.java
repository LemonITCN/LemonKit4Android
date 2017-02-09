package net.lemonsoft.lemonkit.core;

import android.app.Application;
import android.content.Context;

/**
 * LemonKit入口类
 * Created by lemonsoft on 2017/1/27.
 */

public class LemonKit {

    private static LemonKit lemonKitObj;

    private boolean started = false;

    private Application application;

    public synchronized static LemonKit sharedInstance() {
        if (lemonKitObj == null)
            lemonKitObj = new LemonKit();
        return lemonKitObj;
    }

    public static synchronized LemonKit start(Application app) {
        sharedInstance().execStart(app);
        return sharedInstance();
    }

    private synchronized void execStart(Application app) {
        if (started)
            return;

        // 开始执行启动LemonKit
        this.application = app;
        // 注入LemonKitUI，使其生效
        LemonKitUserInterface.injectLKUI(app.getApplicationContext());

        started = true;
    }

    public Context getAppContext() {
        if (started)
            return application.getApplicationContext();
        new Exception("LemonKit have not start!").printStackTrace();
        return null;
    }
}
