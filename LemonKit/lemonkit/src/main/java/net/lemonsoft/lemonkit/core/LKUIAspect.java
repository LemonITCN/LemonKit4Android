package net.lemonsoft.lemonkit.core;


import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import net.lemonsoft.lemonkit.enums.LKUIDelegateOnDrawState;
import net.lemonsoft.lemonkit.interfaces.ui.LKUIView;
import net.lemonsoft.lemonkit.model.LKUIExtensionModel;
import net.lemonsoft.lemonkit.tools.LKDrawableTool;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.HashMap;
import java.util.Map;

/**
 * 对LKUI控件的公共方法部分的AOP
 * 切面注入方法
 * Created by lemonsoft on 2017/1/31.
 */
@Aspect
public class LKUIAspect {

    private Map<String, LKUIExtensionModel> lkPool = new HashMap<>();

    @After("execution(net.lemonsoft.lemonkit.ui.view.LKRelativeLayout.new(..))")
    public void lkConstructor(JoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        LKUIExtensionModel model = new LKUIExtensionModel();
        if (args.length >= 2)
            model = LKUIAttrsCore.p((View) joinPoint.getTarget(), (AttributeSet) args[1]);
        lkPool.put(joinPoint.getTarget().hashCode() + "", model);
        applyLKComplete(model, (LKUIView) joinPoint.getTarget());
        System.out.println(" add model :" + joinPoint.getTarget().hashCode());
    }

    @Around("execution(* net.lemonsoft.lemonkit.ui.view.LK*.onDraw(..))")
    public void lkOnDraw(ProceedingJoinPoint joinPoint) throws Throwable {
        boolean canRun = lkPool.containsKey(joinPoint.getTarget().hashCode() + "");
        System.out.println("is can run ???" + canRun + " - size: " + lkPool.size() + " hashcode: " + joinPoint.getTarget().hashCode());
        if (canRun)
            lkPool.get(joinPoint.getTarget().hashCode() + "").onDrawHandler(
                    (Canvas) joinPoint.getArgs()[0],
                    LKUIDelegateOnDrawState.PRE_SUPER_DRAW
            );
        joinPoint.proceed();
        if (canRun)
            lkPool.get(joinPoint.getTarget().hashCode() + "").onDrawHandler(
                    (Canvas) joinPoint.getArgs()[0],
                    LKUIDelegateOnDrawState.AFT_SUPER_DRAW
            );
    }

    @Around("execution(* net.lemonsoft.lemonkit.ui.view.LK*.setBackground(..))")
    public void lkSetBackground(ProceedingJoinPoint joinPoint) throws Throwable {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
            joinPoint.proceed();
        else
            ((View) joinPoint.getTarget()).setBackgroundDrawable(((Drawable) joinPoint.getArgs()[0]));
    }

    @After("execution(* net.lemonsoft.lemonkit.ui.view.LK*.finalize(..))")
    public void lkFinalize(JoinPoint joinPoint) throws Throwable {
        if (lkPool.containsKey(joinPoint.getTarget().hashCode()))
            lkPool.remove(joinPoint.getTarget().hashCode());
    }

    private void applyLKComplete(LKUIExtensionModel lk, LKUIView lkui) {
        View view = (View) lkui;
        lk.setView(view);
        if (lk.getCornerRadius() >= 0 && view.getBackground() != null)
            lkui.setBackground(
                    LKDrawableTool.createRoundCornerDrawable(view.getBackground(), lk.getCornerRadius())
            );
    }

}
