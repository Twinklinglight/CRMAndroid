package com.wtcrmandroid.view.custompricing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;

public class WaveRipView extends View {

    /**
     * 外层的几层光环
     */
    private Paint circlePaint;
    /**
     * 波浪
     */
    private Paint wavePaint;
    /**
     * 振幅
     */
    private int angle = 0;
    /**
     * 容器
     */
    private Paint containPaint;
    /**
     * 半径
     */
    private int cirRadius;
    /**
     * 百分比
     */
    private int firstCount = 1;
    private boolean isAdd = true;
    private boolean isReduce;

    public WaveRipView(Context context) {
        this(context, null);
        // TODO Auto-generated constructor stub
    }

    public WaveRipView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        // TODO Auto-generated constructor stub
    }

    public WaveRipView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 1) {
                invalidate();
                angle++;
                if (angle == 360) {
                    angle = 0;
                }
                handler.sendEmptyMessageDelayed(1, 1);
            } else {
                // 判断是否达到最大，达到最大时往回缩
                if (firstCount > 0 && isAdd) {
                    firstCount++;
                }
                if (firstCount > 100 || isReduce) {
                    isAdd = false;
                    isReduce = true;
                    firstCount--;
                    if (firstCount <= 1) {
                        isAdd = true;
                        isReduce = false;
                    }
                }
                handler.sendEmptyMessageDelayed(2, 15);
            }
        };
    };

    private void initView() {
        cirRadius = 140;
        circlePaint = new Paint();
        circlePaint.setColor(Color.parseColor("#FDCFBF"));
        circlePaint.setStyle(Style.FILL);
        circlePaint.setAntiAlias(true);
        circlePaint.setAlpha(255);
        wavePaint = new Paint(circlePaint);
        wavePaint.setStyle(Style.FILL);
        containPaint = new Paint();
        containPaint.setStrokeWidth(10);
        containPaint.setAntiAlias(true);
        containPaint.setAlpha(50);
        containPaint.setStyle(Style.FILL);
        containPaint.setColor(Color.parseColor("#F76028"));
        handler.sendEmptyMessage(1);
        handler.sendEmptyMessage(2);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        int height = getHeight();
        int width = getWidth();
        angle++;
        // 最小半径
        int radius = 7 * cirRadius / 10;
        // 最多能扩大多少
        float f1 = 3 * cirRadius / 10;
        // 计算百分比距离
        float f2 = f1 * firstCount / 100.0F;
        // 扩散光圈效果
        canvas.drawCircle(width / 2, height / 2, radius + f2, circlePaint);
        // 最小圆形
//        canvas.drawCircle(width / 2, height / 2, radius, circlePaint);
//        circlePaint.setAlpha(120);
        // 第二层圆形
//        canvas.drawCircle(width / 2, height / 2, radius + 20, circlePaint);
//        circlePaint.setAlpha(180);
        // 第三层圆形
//        canvas.drawCircle(width / 2, height / 2, radius + 40, circlePaint);
        // 波浪的容器
        canvas.drawCircle(width / 2, height / 2, radius - 5, containPaint);
        Paint mTextPaint = new Paint();
        Paint.FontMetricsInt fm = mTextPaint.getFontMetricsInt();
        mTextPaint.setColor(Color.WHITE);
        mTextPaint.setTextSize(70);
        canvas.drawText("打卡",getWidth() / 2 - mTextPaint.measureText("打卡") / 2,
                getHeight() / 2 - fm.descent + (fm.bottom - fm.top)+10 ,mTextPaint);

        // 绘制一个扇形
//        RectF rectF = new RectF(width / 2 - radius, height / 2 - radius, width
//                / 2 + radius, height / 2 + radius);
//        canvas.drawArc(rectF, 0, 180, true, wavePaint);
//        double lineX = 0;
//        double lineY = 0;
        // 根据正弦绘制波浪
//        for (int i = width / 2 - radius; i < width / 2 + radius; i++) {
//            lineX = i;
//            lineY = 10 * Math.sin((i + angle) * Math.PI / 180) + getHeight()
//                    / 2 + 40;
//            canvas.drawLine((int) lineX, (int) (lineY - 70), (int) lineX + 1,
//                    (int) height / 2, wavePaint);
//        }

    }
}
