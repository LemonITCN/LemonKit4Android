package net.lemonsoft.lemonkit.core.parser;

import android.app.Activity;
import android.view.View;

import net.lemonsoft.lemonkit.annotations.FindView;
import net.lemonsoft.lemonkit.tools.LKResourceTool;

import java.lang.reflect.Field;

/**
 * LKUI的注解解析器
 * Created by lemonsoft on 2017/2/5.
 */

public class LKUIAnnotationParser {

    /**
     * 除了注解之外，只要是符合View的子类、并且属性名可以作为id的属性全部自动初始化
     * 即LKUIAnnotationParser.parse(activity,false);
     */
    public static void parse(Activity activity) {
        parse(activity, false);
    }

    /**
     * 透过Activity解析LKUI注解
     * 可以通过注解配置contentView，field的id等
     */
    public static void parse(Activity activity, boolean ignoreNoFindViewField) {
        try {
            // 解析属性注解：FindView
            Field[] fields = activity.getClass().getDeclaredFields();
            for (Field fieldItem : fields) {
                fieldItem.setAccessible(true);
                if (!(View.class.isAssignableFrom(fieldItem.getType())))
                    continue;
                FindView findView = fieldItem.getAnnotation(FindView.class);
                if (findView != null)
                    // 配置了FindView注解
                    if (findView.value() > 0)
                        fieldItem.set(activity, activity.findViewById(findView.value()));
                    else // 如果没有设置view的id，那么自动尝试以属性名作为id进行查询赋值
                        fieldItem.set(activity, LKResourceTool.findViewByIdStr(activity, fieldItem.getName()));
                else if (!ignoreNoFindViewField)// 没有配置FindView注解并且没有设置忽略无注解属性（ignoreNoFindViewField为false），尝试以属性名作为id，进行查询赋值
                    fieldItem.set(activity, LKResourceTool.findViewByIdStr(activity, fieldItem.getName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
