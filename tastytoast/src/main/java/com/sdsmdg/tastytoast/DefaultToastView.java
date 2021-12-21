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
import ohos.app.Context;

public class DefaultToastView extends Component implements Component.EstimateSizeListener, Component.DrawTask {

    ValueAnimator valueAnimator;
    float mAnimatedValue = 0f;
    private Paint mPaint, mSpikePaint;
    private float mWidth = 0f;
    private float mPadding = 0f;
    private float mSpikeLength;

    public DefaultToastView(Context context) {
        super(context);
        initialize();
    }

    public DefaultToastView(Context context, AttrSet attrSet) {
        super(context, attrSet);
        initialize();
    }

    public DefaultToastView(Context context, AttrSet attrSet, String styleName) {
        super(context, attrSet, styleName);
        initialize();
    }

    public DefaultToastView(Context context, AttrSet attrSet, int resId) {
        super(context, attrSet, resId);
        initialize();
    }

    private void initialize() {
        mWidth = getWidth();
        mPadding = AttrHelper.vp2px(5, mContext);
        setEstimateSizeListener(this);
        addDrawTask(this);
    }


    @Override
    public boolean onEstimateSize(int i, int i1) {
        initPaint();
        return false;
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE_STYLE);
        mPaint.setColor(new Color(RgbPalette.parse("#222222")));
        mPaint.setStrokeWidth(AttrHelper.vp2px(2, mContext));

        mSpikePaint = new Paint();
        mSpikePaint.setAntiAlias(true);
        mSpikePaint.setStyle(Paint.Style.STROKE_STYLE);
        mSpikePaint.setColor(new Color(RgbPalette.parse("#222222")));
        mSpikePaint.setStrokeWidth(AttrHelper.vp2px(4, mContext));

        mSpikeLength = AttrHelper.vp2px(4, mContext);
    }

    @Override
    public void onDraw(Component component, Canvas canvas) {
        canvas.save();
        canvas.drawCircle(mWidth / 2, mWidth / 2, mWidth / 4, mPaint);

        for (int i = 0; i < 360; i += 40) {

            int angle = (int) (mAnimatedValue * 40 + i);
            float initialX = (float) ((mWidth / 4) * Math.cos(angle * Math.PI / 180));
            float initialY = (float) ((mWidth / 4) * Math.sin(angle * Math.PI / 180));
            float finalX = (float) ((mWidth / 4 + mSpikeLength) * Math.cos(angle * Math.PI / 180));
            float finalY = (float) ((mWidth / 4 + mSpikeLength) * Math.sin(angle * Math.PI / 180));
            canvas.drawLine(mWidth / 2 - initialX, mWidth / 2 - initialY, mWidth / 2 - finalX,
                    mWidth / 2 - finalY, mSpikePaint);
        }
        canvas.restore();
    }

    public void startAnim() {
        stopAnim();
        startViewAnim(0f, 1f, 2000);
    }

    public void stopAnim() {
        if (valueAnimator != null) {
            valueAnimator.end();
            invalidate();
        }
    }

    private ValueAnimator startViewAnim(float startF, final float endF, long time) {
        valueAnimator = ValueAnimator.ofFloat(startF, endF);
        valueAnimator.setDuration(time);
        valueAnimator.setCurveType(Animator.CurveType.LINEAR);
        valueAnimator.setLoopedCount(ValueAnimator.INFINITE);

        valueAnimator.setValueUpdateListener(new AnimatorValue.ValueUpdateListener() {
            @Override
            public void onUpdate(AnimatorValue animatorValue, float v) {
                mAnimatedValue = v;
                invalidate();
            }
        });

        if (!valueAnimator.isRunning()) {
            valueAnimator.start();

        }
        return valueAnimator;
    }
}
