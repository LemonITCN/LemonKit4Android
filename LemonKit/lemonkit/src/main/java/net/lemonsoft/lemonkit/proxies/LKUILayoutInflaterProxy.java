package net.lemonsoft.lemonkit.proxies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xmlpull.v1.XmlPullParser;

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
    }

    @Override
    public LayoutInflater cloneInContext(Context newContext) {
        return this;
    }

    @Override
    public View inflate(int resource, ViewGroup root) {
        System.out.println("========" + root);
        View view = inflater.inflate(resource, root);
        System.out.println(" ==OK=====  INFLATE RESOURCE ROOT" + resource + " - - - " + view);
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
