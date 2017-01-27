package net.lemonsoft.lemonkit_samples;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;


import net.lemonsoft.lemonkit.proxies.LKUILayoutInflaterProxy;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import ui.LKView;

public class MainActivity extends Activity {

    private LKView myView;
    private ProxyInterface text = new ProxyText();


    class MyInflater implements InvocationHandler {

        ProxyInterface inflater;

        public MyInflater(ProxyInterface inflater) {
            this.inflater = inflater;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//            System.out.println("=================Object:  " + proxy);
            System.out.println("=================Method:  " + method);
            System.out.println("=================args:  " + args);

            method.invoke(inflater, args);
            return null;

//            http://www.jianshu.com/p/b30ea19c444b
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Proxy.newProxyInstance(LayoutInflater.class.getClassLoader(),
//                getLayoutInflater().getClass().getInterfaces(),
//                new MyInflater(getLayoutInflater()));

//        this.getClass().getDeclaredField()


        try {
            Window windowObj = getWindow();
            Field layoutInflaterField = windowObj.getClass().getDeclaredField("mLayoutInflater");
            layoutInflaterField.setAccessible(true);
            LayoutInflater inflaterObj = (LayoutInflater) layoutInflaterField.get(windowObj);
            LKUILayoutInflaterProxy layoutInflaterProxy = new LKUILayoutInflaterProxy(inflaterObj, this);
            layoutInflaterField.set(windowObj, layoutInflaterProxy);
            System.out.println(windowObj);
            System.out.println(inflaterObj);


            setContentView(R.layout.activity_main);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        getLayoutInflater()
//
//        for (Field field : getClass().getFields()) {
//            System.out.println(field.getName() + field);
//        }

        this.text = (ProxyInterface) Proxy.newProxyInstance(ProxyText.class.getClassLoader(),
                new Class[]{ProxyInterface.class},
                new MyInflater(text));

        myView = (LKView) findViewById(R.id.myView);
        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text.ok();
            }
        });

    }

    class ProxyText implements ProxyInterface {
        public void ok() {
            System.out.println("now is ok!");
        }
    }

    interface ProxyInterface {
        void ok();
    }

}