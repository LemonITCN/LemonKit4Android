package net.lemonsoft.lemonkit.core;


import android.util.AttributeSet;

import net.lemonsoft.lemonkit.interfaces.ui.LKUIView;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by lemonsoft on 2017/1/31.
 */
@Aspect
public class LKUIAspect {

    @After("execution(net.lemonsoft.lemonkit.ui.view.LKTextView.new(..))")
    public void lk(JoinPoint joinPoint) throws Throwable {
        System.out.println("================================================ lk new inject ok - " + joinPoint.getArgs()[0]);
        Object[] args = joinPoint.getArgs();
        if (args.length >= 2) {
            LKUIAttrsCore.parse((LKUIView) joinPoint.getTarget(), (AttributeSet) args[1]);
        }
    }

}
