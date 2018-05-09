package com.example.debug.recyclerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

public class CenterAlignImageSpan extends ImageSpan {
    public CenterAlignImageSpan(Drawable d) {
        super(d);
    }

    public CenterAlignImageSpan(Context context, Bitmap b) {
        super(context, b);
    }

    public CenterAlignImageSpan(Context context, int resourceId) {
        super(context, resourceId);
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int
            y, int bottom, Paint paint) {
        Drawable b = getDrawable();
        Paint.FontMetricsInt fm = paint.getFontMetricsInt();
        int transY = (y + fm.descent + y + fm.ascent) / 2 - b.getBounds().bottom / 2;//计算y方向的位移
        canvas.save(); canvas.translate(x, transY);//绘制图片位移一段距离
         b.draw(canvas);
         canvas.restore();
        //super.draw(canvas, text, start, end, x, top, y, bottom, paint);
    }
}
