package net.lemonsoft.lemonkit.core;


import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import net.lemonsoft.lemonkit.interfaces.ui.LKUIView;
import net.lemonsoft.lemonkit.model.LKUIExtensionModel;

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
        LKUIExtensionModel model = new LKUIExtensionModel();
        if (args.length >= 2) {
            model = LKUIAttrsCore.p((View) joinPoint.getTarget(), (AttributeSet) args[1]);
            System.out.println(model.getCornerRadius());
        }
    }

}
