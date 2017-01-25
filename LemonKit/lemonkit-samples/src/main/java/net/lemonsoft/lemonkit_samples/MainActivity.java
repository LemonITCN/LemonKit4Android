package net.lemonsoft.lemonkit_samples;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MainActivity extends Activity {

    class MyInflater implements InvocationHandler {

        LayoutInflater inflater;

        public MyInflater(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("=================Object:  " + proxy);
            System.out.println("=================Method:  " + method);
            System.out.println("=================args:  " + args);
            return null;

//            http://www.jianshu.com/p/b30ea19c444b
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Proxy.newProxyInstance(LayoutInflater.class.getClassLoader(),
                getLayoutInflater().getClass().getInterfaces(),
                new MyInflater(getLayoutInflater()));
        setContentView(R.layout.activity_main);
    }
}