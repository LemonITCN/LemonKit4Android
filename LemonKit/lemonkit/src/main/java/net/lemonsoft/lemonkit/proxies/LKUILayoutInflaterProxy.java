package net.lemonsoft.lemonkit.proxies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xmlpull.v1.XmlPullParser;

import java.lang.reflect.Field;

/**
 * Created by lemonsoft on 2017/1/26.
 */

public class LKUILayoutInflaterProxy extends LayoutInflater {

    private LayoutInflater inflater;

    protected LKUILayoutInflaterProxy(Context context) {
        super(context);
    }

    public LKUILayoutInflaterProxy(LayoutInflater original, Context newContext) {
        super(original, newContext);
        this.inflater = original;
        try {
            Field field = this.inflater.getClass().getDeclaredField("sClassPrefixList");
            field.setAccessible(true);
            field.set(this.inflater, new String[]{
                    "ui.LK",
                    "android.widget.",
                    "android.webkit.",
                    "android.app."
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public LayoutInflater cloneInContext(Context newContext) {
        return this;
    }

    @Override
    public View inflate(int resource, ViewGroup root) {
        View view = inflater.inflate(resource, root);
        if (view instanceof ViewGroup) {
            System.out.println("这个类是： " + view.getClass());
        }
        return view;
    }

    @Override
    public View inflate(XmlPullParser parser, ViewGroup root) {
        System.out.println("INFLATE PARSER ROOT");
        return inflater.inflate(parser, root);
    }

    @Override
    public View inflate(int resource, ViewGroup root, boolean attachToRoot) {
        System.out.println("INFLATE ROSOURCE ROOT ATTACHTOROOT");
        return inflater.inflate(resource, root, attachToRoot);
    }

    @Override
    public View inflate(XmlPullParser parser, ViewGroup root, boolean attachToRoot) {
        System.out.println("INFLATE PARSER ROOT ATTCHTOROOT");
        return inflater.inflate(parser, root, attachToRoot);
    }
}
