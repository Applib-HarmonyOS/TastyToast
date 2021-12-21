package com.sdsmdg.tastytoast;

import ohos.agp.animation.Animator;
import ohos.agp.animation.AnimatorValue;
import ohos.agp.colors.RgbPalette;
import ohos.agp.components.AttrHelper;
import ohos.agp.components.AttrSet;
import ohos.agp.components.Component;
import ohos.agp.render.Canvas;
import ohos.agp.render.Paint;
import ohos.agp.utils.Color;
import ohos.agp.utils.RectFloat;
import ohos.app.Context;

public class InfoToastView extends Component implements Component.EstimateSizeListener, Component.DrawTask {

    RectFloat rectF = new RectFloat();
    ValueAnimator valueAnimator;
    float mAnimatedValue = 0f;
    private String TAG = "com.sdsmdg.tastytoast";
    private Paint mPaint;
    private float mWidth = 0f;
    private float mEyeWidth = 0f;
    private float mPadding = 0f;
    private float endPoint = 0f;
    private boolean isEyeLeft = false;
    private boolean isEyeRight = false;
    private boolean isEyeMiddle = false;

    public InfoToastView(Context context) {
        super(context);
        initialize();
    }

    public InfoToastView(Context context, AttrSet attrSet) {
        super(context, attrSet);
        initialize();
    }

    public InfoToastView(Context context, AttrSet attrSet, String styleName) {
        super(context, attrSet, styleName);
        initialize();
    }

    public InfoToastView(Context context, AttrSet attrSet, int resId) {
        super(context, attrSet, resId);
        initialize();
    }

    private void initialize() {
        mWidth = getWidth();
        mPadding = AttrHelper.vp2px(10, mContext);
        mEyeWidth = AttrHelper.vp2px(3, mContext);
        endPoint = mPadding;
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
        mPaint.setColor(new Color(RgbPalette.parse("#337ab7")));
        mPaint.setStrokeWidth(AttrHelper.vp2px(2, mContext));
    }

    private void initRect() {
        rectF = new RectFloat(mPadding, mPadding, mWidth - mPadding, mWidth - mPadding);
    }

    @Override
    public void onDraw(Component component, Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE_STYLE);
        canvas.drawLine(mPadding, mWidth - 3 * mPadding / 2, endPoint, mWidth - 3 * mPadding / 2, mPaint);

        mPaint.setStyle(Paint.Style.FILL_STYLE);
        if (isEyeLeft) {
            canvas.drawCircle(mPadding + mEyeWidth, mWidth / 3, mEyeWidth, mPaint);
            canvas.drawCircle(mWidth - mPadding - 2 * mEyeWidth, mWidth / 3, mEyeWidth, mPaint);
        }
        if (isEyeMiddle) {
            canvas.drawCircle(mPadding + (3 * mEyeWidth / 2), mWidth / 3, mEyeWidth, mPaint);
            canvas.drawCircle(mWidth - mPadding - (5 * mEyeWidth / 2), mWidth / 3, mEyeWidth, mPaint);
        }
        if (isEyeRight) {
            canvas.drawCircle(mPadding + 2 * mEyeWidth, mWidth / 3, mEyeWidth, mPaint);
            canvas.drawCircle(mWidth - mPadding - mEyeWidth, mWidth / 3, mEyeWidth, mPaint);
        }
    }

    public void startAnim() {
        stopAnim();
        startViewAnim(0f, 1f, 2000);
    }

    public void stopAnim() {
        if (valueAnimator != null) {
            isEyeLeft = false;
            isEyeMiddle = false;
            isEyeRight = false;
            endPoint = mPadding;
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
                if (mAnimatedValue < 0.90) {
                    endPoint = ((2 * (mWidth) - (4 * mPadding)) * (mAnimatedValue / 2)) + mPadding;
                } else {
                    endPoint = mWidth - 5 * mPadding / 4;
                }

                if (mAnimatedValue < 0.16) {
                    isEyeRight = true;
                    isEyeLeft = false;
                } else if (mAnimatedValue < 0.32) {
                    isEyeRight = false;
                    isEyeLeft = true;
                } else if (mAnimatedValue < 0.48) {
                    isEyeRight = true;
                    isEyeLeft = false;
                } else if (mAnimatedValue < 0.64) {
                    isEyeRight = false;
                    isEyeLeft = true;
                } else if (mAnimatedValue < 0.80) {
                    isEyeRight = true;
                    isEyeLeft = false;
                } else if (mAnimatedValue < 0.96) {
                    isEyeRight = false;
                    isEyeLeft = true;
                } else {
                    isEyeLeft = false;
                    isEyeMiddle = true;
                    isEyeRight = false;
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