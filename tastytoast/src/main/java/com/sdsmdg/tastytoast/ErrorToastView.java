package com.sdsmdg.tastytoast;

import ohos.agp.animation.Animator;
import ohos.agp.animation.AnimatorValue;
import ohos.agp.colors.RgbPalette;
import ohos.agp.components.AttrHelper;
import ohos.agp.components.AttrSet;
import ohos.agp.components.Component;
import ohos.agp.render.Arc;
import ohos.agp.render.Canvas;
import ohos.agp.render.Paint;
import ohos.agp.utils.Color;
import ohos.agp.utils.RectFloat;
import ohos.app.Context;

public class ErrorToastView extends Component implements Component.EstimateSizeListener, Component.DrawTask {

    RectFloat rectF = new RectFloat();
    RectFloat leftEyeRectF = new RectFloat();
    RectFloat rightEyeRectF = new RectFloat();
    ValueAnimator valueAnimator;
    float mAnimatedValue = 0f;
    private Paint mPaint;
    private float mWidth = 0f;
    private float mEyeWidth = 0f;
    private float mPadding = 0f;
    private float endAngle = 0f;
    private boolean isJustVisible = false;
    private boolean isSad = false;

    public ErrorToastView(Context context) {
        super(context);
        initialize();
    }

    public ErrorToastView(Context context, AttrSet attrSet) {
        super(context, attrSet);
        initialize();
    }

    public ErrorToastView(Context context, AttrSet attrSet, String styleName) {
        super(context, attrSet, styleName);
        initialize();
    }

    public ErrorToastView(Context context, AttrSet attrSet, int resId) {
        super(context, attrSet, resId);
        initialize();
    }

    private void initialize() {
        mWidth = getWidth();
        mPadding = AttrHelper.vp2px(10, mContext);
        mEyeWidth = AttrHelper.vp2px(3, mContext);
        setEstimateSizeListener(this);
        addDrawTask(this);
    }


    @Override
    public boolean onEstimateSize(int i, int i1) {
        initPaint();
        initRect();
        return false;
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE_STYLE);
        mPaint.setColor(new Color(RgbPalette.parse("#d9534f")));
        mPaint.setStrokeWidth(AttrHelper.vp2px(2, mContext));
    }

    private void initRect() {
        rectF = new RectFloat(mPadding / 2, ((mWidth) / 2), mWidth - (mPadding / 2), ((3 * mWidth / 2)));
        leftEyeRectF = new RectFloat(mPadding + mEyeWidth - mEyeWidth, mWidth / 3 -
                mEyeWidth, mPadding + mEyeWidth + mEyeWidth, mWidth / 3 + mEyeWidth);
        rightEyeRectF = new RectFloat(mWidth - mPadding - 5 * mEyeWidth / 2, mWidth / 3 -
                mEyeWidth, mWidth - mPadding - mEyeWidth / 2, mWidth / 3 + mEyeWidth);
    }

    @Override
    public void onDraw(Component component, Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE_STYLE);
        canvas.drawArc(rectF, new Arc(210, endAngle, false), mPaint);

        mPaint.setStyle(Paint.Style.FILL_STYLE);
        if (isJustVisible) {
            canvas.drawCircle(mPadding + mEyeWidth + mEyeWidth / 2, mWidth / 3, mEyeWidth, mPaint);
            canvas.drawCircle(mWidth - mPadding - mEyeWidth - mEyeWidth / 2, mWidth / 3, mEyeWidth, mPaint);
        }
        if (isSad) {
            canvas.drawArc(leftEyeRectF, new Arc(160, -220, false), mPaint);
            canvas.drawArc(rightEyeRectF, new Arc(20, 220, false), mPaint);
        }
    }

    public void startAnim() {
        stopAnim();
        startViewAnim(0f, 1f, 2000);
    }

    public void stopAnim() {
        if (valueAnimator != null) {
            isSad = false;
            endAngle = 0f;
            isJustVisible = false;
            mAnimatedValue = 0f;
            valueAnimator.end();
        }
    }

    private ValueAnimator startViewAnim(float startF, final float endF, long time) {
        valueAnimator = ValueAnimator.ofFloat(startF, endF);
        valueAnimator.setDuration(time);
        valueAnimator.setCurveType(Animator.CurveType.LINEAR);

        valueAnimator.setValueUpdateListener(new AnimatorValue.ValueUpdateListener() {
            @Override
            public void onUpdate(AnimatorValue animatorValue, float v) {
                mAnimatedValue = v;
                if (mAnimatedValue < 0.5) {
                    isSad = false;
                    isJustVisible = false;
                    endAngle = 240 * (mAnimatedValue);
                    isJustVisible = true;
                } else if (mAnimatedValue > 0.55 && mAnimatedValue < 0.7) {
                    endAngle = 120;
                    isSad = false;
                    isJustVisible = true;
                } else {
                    endAngle = 120;
                    isSad = true;
                    isJustVisible = false;
                }

                invalidate();
            }
        });

        if (!valueAnimator.isRunning()) {
            valueAnimator.start();

        }
        return valueAnimator;
    }
}
