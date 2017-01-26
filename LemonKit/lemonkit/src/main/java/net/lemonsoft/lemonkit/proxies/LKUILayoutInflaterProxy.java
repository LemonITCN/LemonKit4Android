package net.lemonsoft.lemonkit.proxies;

import android.content.Context;
import android.view.LayoutInflater;

/**
 * Created by lemonsoft on 2017/1/26.
 */

public class LKUILayoutInflaterProxy extends LayoutInflater {

    protected LKUILayoutInflaterProxy(Context context) {
        super(context);
    }

    protected LKUILayoutInflaterProxy(LayoutInflater original, Context newContext) {
        super(original, newContext);
    }

    @Override
    public LayoutInflater cloneInContext(Context newContext) {
        return this;
    }

}
