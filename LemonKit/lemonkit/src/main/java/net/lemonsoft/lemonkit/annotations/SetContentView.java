package net.lemonsoft.lemonkit.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * LK SetContentView的封装，可以直接通过注解配置layout的id，自动处理setContentView
 * Created by lemonsoft on 2017/2/5.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SetContentView {

    /**
     * setContentView的layout的id
     */
    int value() default 0;

}
