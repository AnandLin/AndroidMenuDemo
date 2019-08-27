package com.anand.menudemo.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.anand.menudemo.R;

/**
 * @ProjectName: MenuDemo
 * @Package: com.anand.menudemo
 * @ClassName: CircleSeekBar
 * @Description: java类作用描述
 * @Author: AnandLin
 * @CreateDate: 2019/8/19 17:29
 * @UpdateUser: AnandLin
 * @UpdateDate: 2019/8/19 17:29
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CircleSeekBar extends AppCompatTextView {

    private int seekBarColor;
    private int seekBarBg;
    private int ringPadding;
    private int ringWidth;
    private int maxValue;
    private int progress;
    private ValueAnimator valueAnimator;
    private float mSelectRing = 0; //要显示的彩色区域(岁数值变化)

    private int width;
    private int height;
    private int radius;
    private RectF mRectF;


    public CircleSeekBar(Context context) {
        this(context,null,-1);
    }

    public CircleSeekBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public CircleSeekBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context,attrs);
    }

    private void initAttrs(Context context,AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleSeekBar);
        seekBarColor = typedArray.getColor(R.styleable.CircleSeekBar_seekBarColor,0XFF0066FF);
        seekBarBg = typedArray.getColor(R.styleable.CircleSeekBar_seekBarColor,0XFF00FFFF);
        ringPadding = typedArray.getDimensionPixelSize(R.styleable.CircleSeekBar_ringPadding,0);
        ringWidth = typedArray.getDimensionPixelSize(R.styleable.CircleSeekBar_ringWidth,2);
        progress = typedArray.getInteger(R.styleable.CircleSeekBar_progress,0);
        maxValue = typedArray.getInteger(R.styleable.CircleSeekBar_maxValue,100);
        typedArray.recycle();
        if(progress > maxValue){
            progress = maxValue;
        }
        mSelectRing = 360*(progress/(maxValue*1.0f));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //对View上的內容进行测量后得到的View內容占据的宽度
        width = getMeasuredWidth();
        //对View上的內容进行测量后得到的View內容占据的高度
        height = getMeasuredHeight();
        if(width < height){
            radius = width/2;
        }else {
            radius = height/2;
        }
        mRectF = new RectF(width/2 - radius + ringWidth/2,height/2 - radius + ringWidth/2,
                width/2 + radius - ringWidth/2,height/2 + radius - ringWidth/2);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        drawDisc(canvas);
        drawRing(canvas);
        drawSeek(canvas);
        super.onDraw(canvas);
    }

    /**
     * 绘制实心圆
     * @param canvas
     */
    private void drawDisc(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(seekBarBg);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(width / 2, height / 2, radius - ringPadding - ringWidth, paint);
    }

    private void drawRing(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(seekBarBg);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(ringWidth);
        canvas.drawArc(mRectF,360,360,false,paint);
    }

    private void drawSeek(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(seekBarColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(ringWidth);
        canvas.drawArc(mRectF,-90,mSelectRing,false,paint);
    }

    public void setProgress(int progress){
        if (progress > maxValue) {
            progress = maxValue;
        }
        this.progress = progress;
        int start = 0;
        int end = progress;
        startAnimator(start,end,2000);
    }

    private void startAnimator(int start, int end, long animTime) {
        valueAnimator = ValueAnimator.ofInt(start, end);
        valueAnimator.setDuration(animTime);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int i = Integer.valueOf(String.valueOf(animation.getAnimatedValue()));
                setText(i + "%");
                //每个单位长度占多少度
                mSelectRing=(int) (360 * (i / (maxValue * 1.0f)));
                invalidate();
            }
        });
        valueAnimator.start();
    }
}
