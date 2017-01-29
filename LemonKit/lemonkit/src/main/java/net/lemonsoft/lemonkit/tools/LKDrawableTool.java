package net.lemonsoft.lemonkit.tools;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

/**
 * Drawable工具类
 * Created by lemonsoft on 2017/1/28.
 */

public class LKDrawableTool {

    /**
     * 根据制定的drawable和圆角半径，生成对应的圆角的Drawable
     *
     * @param drawable     要生成圆角Drawable的原Drawable
     * @param cornerRadius 圆角半径
     * @return 带有圆角的Drawable
     */
    public static Drawable createRoundCornerDrawable(Drawable drawable, int cornerRadius) {
        Drawable outDrawable = drawable;
        if (drawable instanceof ColorDrawable) {
            // 根据颜色Drawable生成圆角Drawable
            outDrawable = new GradientDrawable();
            ((GradientDrawable) outDrawable).setColor(((ColorDrawable) drawable).getColor());
            ((GradientDrawable) outDrawable).setCornerRadius(cornerRadius);
        } else if (drawable instanceof BitmapDrawable) {
            // 根据BitmapDrawable生成圆角Drawable
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                    bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(output);

            final Paint paint = new Paint();
            final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

            paint.setAntiAlias(true);
            canvas.drawRoundRect(new RectF(rect), cornerRadius, cornerRadius, paint);

            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);

            outDrawable = new BitmapDrawable(null, output);
        }
        return outDrawable;
    }

}
