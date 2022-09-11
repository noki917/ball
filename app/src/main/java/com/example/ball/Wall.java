package com.example.ball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class Wall extends View {

    int l,t,r,b;
    private Paint mPaint = new Paint();

    public Wall(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.YELLOW);

        canvas.drawRect(l, t, r, b, mPaint);
    }
}