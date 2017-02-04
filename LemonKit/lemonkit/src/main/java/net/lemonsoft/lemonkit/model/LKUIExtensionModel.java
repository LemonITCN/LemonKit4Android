package net.lemonsoft.lemonkit.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.view.View;

import net.lemonsoft.lemonkit.enums.LKUIDelegateOnDrawState;
import net.lemonsoft.lemonkit.interfaces.ui.LKUIDelegate;
import net.lemonsoft.lemonkit.interfaces.ui.LKUIView;

import java.util.ArrayList;
import java.util.List;

/**
 * LemonKit UI属性扩展模型
 * 对Android的View属性进行扩展的部分全部在这个模型对象中进行描述
 * Created by LiuRi on 2017/1/25.
 */

public class LKUIExtensionModel {

    // UI代理扩展
    /**
     * 控件的核心UI代理列表
     */
    private final List<LKUIDelegate> uiDelegateList = new ArrayList<>();

    // UI样式扩展
    /**
     * 圆角半径
     */
    private int cornerRadius = 0;
    /**
     * 边框宽度
     */
    private int borderWidth = 0;
    /**
     * 边框颜色
     */
    private int borderColor = Color.argb(0, 255, 255, 255);
    /**
     * 是否沿着边框剪切，若为真，那么超过边框的部分都会被剪切，无法看见
     */
    private boolean clipToBounds = true;
    /**
     * 所作用于的控件
     */
    private View view;

    // 内部存储属性


    // 公共的画笔对象
    private Paint paint = new Paint();
    // 控件的边缘路径
    private Path boundsPath;
    // 控件的边缘矩形信息对象
    private RectF boundsRectF;

    public LKUIExtensionModel() {
        this.init(null);
    }

    public LKUIExtensionModel(View view) {
        this.init(view);
    }

    private void init(View view) {
        this.view = view;
        uiDelegateList.add(new LKUIDelegate() {
            @Override
            public void onDrawHandler(View view, Canvas canvas, LKUIDelegateOnDrawState state) {
                if (boundsRectF == null)// 若为空，那么为新创建
                    boundsRectF = new RectF(0, 0, canvas.getWidth(), canvas.getHeight());
                if (isClipToBounds()) {// 处理clipToBounds
                    // 需要切割到边界部分
                    if (boundsPath == null) {// 若空，那么才创建
                        boundsPath = new Path();
                        boundsPath.addRoundRect(
                                boundsRectF,
                                getCornerRadius(),
                                getCornerRadius(),
                                Path.Direction.CW
                        );
                    }
                    if (state == LKUIDelegateOnDrawState.PRE_SUPER_DRAW)// 准备开始画super，先切割canvas
                        canvas.clipPath(boundsPath, Region.Op.REPLACE);
                }
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(getBorderWidth());// 边框线宽度
                paint.setColor(getBorderColor());// 边框线颜色
                paint.setAntiAlias(true);// 抗锯齿
                if (state == LKUIDelegateOnDrawState.AFT_SUPER_DRAW)// super已画完，开始画边框线
                    canvas.drawRoundRect(
                            boundsRectF,
                            getCornerRadius(),
                            getCornerRadius(),
                            paint
                    );// 画边框
            }
        });
    }


    public void addUIDelegate(LKUIDelegate... delegates) {
        for (LKUIDelegate d : delegates)
            uiDelegateList.add(d);
    }

    public void addUIDelegate(int index, LKUIDelegate delegate) {
        uiDelegateList.add(index, delegate);
    }

    public void removeUIDelegate(LKUIDelegate delegate) {
        uiDelegateList.remove(delegate);
    }

    public void removeUIDelegate(int index) {
        uiDelegateList.remove(index);
    }

    /**
     * 清除所有的UI核心代理
     */
    public void clearUIDelegate() {
        uiDelegateList.clear();
    }

    public void updateUIDelegate(int index, LKUIDelegate delegate) {
        uiDelegateList.set(index, delegate);
    }

    public LKUIDelegate getUIDelegate(int index) {
        return uiDelegateList.get(index);
    }

    public List<LKUIDelegate> getUIDelegateList() {
        return uiDelegateList;
    }

    public int getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(int cornerRadius) {
        this.cornerRadius = cornerRadius;
        applyLKUI();
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        applyLKUI();
    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
        applyLKUI();
    }

    public boolean isClipToBounds() {
        return clipToBounds;
    }

    public void setClipToBounds(boolean clipToBounds) {
        this.clipToBounds = clipToBounds;
        applyLKUI();
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
        if (view != null)
            view.postInvalidate();
    }

    private void applyLKUI() {
        if (this.view != null)// 当view对象还没有赋值的时候不能进行刷新UI
            this.view.postInvalidate();
    }

    // 供LKUIView对接的方法
    public void onDrawHandler(Canvas canvas, LKUIDelegateOnDrawState state) {
        for (LKUIDelegate delegate : uiDelegateList) {
            // 依次调用核心UI代理列表中的代理函数
            delegate.onDrawHandler(view, canvas, state);
        }
    }
}
