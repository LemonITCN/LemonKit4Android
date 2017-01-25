package net.lemonsoft.lemonkit.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import net.lemonsoft.lemonkit.core.LKUIAttrsCore;
import net.lemonsoft.lemonkit.interfaces.ui.LKUI;
import net.lemonsoft.lemonkit.model.LKUIAttrsModel;

/**
 * LKImageView,对系统的基本ImageView进行扩展
 * Created by LiuRi on 2017/1/25.
 */

public class LKImageView extends ImageView implements LKUI {

    private Canvas canvas;
    private LKUIAttrsModel attrsModel;

    public LKImageView(Context context) {
        super(context);
    }

    public LKImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LKUIAttrsCore.parse(this, attrs);
    }

    public LKImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LKUIAttrsCore.parse(this, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public LKImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        LKUIAttrsCore.parse(this, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.canvas = canvas;
        RectF rectF = new RectF(0, 0, canvas.getWidth(), canvas.getHeight());
        if (attrsModel.isClipToBounds()) {
            // 需要切割到边界部分
            Path path = new Path();
            path.addRoundRect(
                    rectF,
                    attrsModel.getCornerRadius(),
                    attrsModel.getCornerRadius(),
                    Path.Direction.CW
            );
            canvas.clipPath(path, Region.Op.REPLACE);
        }
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(attrsModel.getBorderWidth());
        paint.setColor(attrsModel.getBorderColor());
        canvas.drawRoundRect(
                rectF,
                attrsModel.getCornerRadius(),
                attrsModel.getCornerRadius(),
                paint
        );
    }

    @Override
    public Canvas getCanvas() {
        return canvas;
    }

    @Override
    public void setLKUIAttrs(LKUIAttrsModel attrs) {
        this.attrsModel = attrs;
        LKUIAttrsCore.parse(this, attrs);
    }

    @Override
    public LKUIAttrsModel getLKUIAttrs() {
        return this.attrsModel;
    }

    @Override
    public View getView() {
        return this;
    }
}
