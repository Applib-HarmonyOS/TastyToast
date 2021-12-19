package com.sdsmdg.tastytoast;

import ohos.agp.animation.Animator;
import ohos.agp.animation.AnimatorValue;
import ohos.agp.colors.RgbPalette;
import ohos.agp.components.AttrHelper;
import ohos.agp.components.AttrSet;
import ohos.agp.components.Component;
import ohos.agp.render.*;
import ohos.agp.utils.Color;
import ohos.agp.utils.RectFloat;
import ohos.app.Context;
import ohos.media.image.PixelMap;
import ohos.media.image.common.PixelFormat;
import ohos.media.image.common.Size;

public class ConfusingToastView extends Component implements Component.EstimateSizeListener, Component.DrawTask {

    PixelMap eye;
    ValueAnimator valueAnimator;
    float angle = 0f;
    private Paint mPaint;
    private float mWidth = 0f;
    private float mHeight = 0f;

    public ConfusingToastView(Context context) {
        super(context);
        initialize();
    }

    public ConfusingToastView(Context context, AttrSet attrSet) {
        super(context, attrSet);
        initialize();
    }

    public ConfusingToastView(Context context, AttrSet attrSet, String styleName) {
        super(context, attrSet, styleName);
        initialize();
    }

    public ConfusingToastView(Context context, AttrSet attrSet, int resId) {
        super(context, attrSet, resId);
        initialize();
    }

    private void initialize() {
        mWidth = getWidth();
        mHeight = getHeight();
        setEstimateSizeListener(this);
        addDrawTask(this);
    }


    @Override
    public boolean onEstimateSize(int i, int i1) {
        initPaint();
        initPath();
        return false;
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE_STYLE);
        mPaint.setColor(new Color(RgbPalette.parse("#FE9D4D")));
    }

    private void initPath() {
        Path mPath = new Path();
        RectFloat rectF = new RectFloat(mWidth / 2f - dip2px(1.5f), mHeight / 2f - dip2px(1.5f)
                , mWidth / 2f + dip2px(1.5f), mHeight / 2f + dip2px(1.5f));
        mPath.addArc(rectF, 180f, 180f);
        rectF.modify(rectF.left - dip2px(3), rectF.top - dip2px(1.5f), rectF.right, rectF.bottom + dip2px(1.5f));
        mPath.addArc(rectF, 0f, 180f);
        rectF.modify(rectF.left, rectF.top - dip2px(1.5f), rectF.right + dip2px(3), rectF.bottom + dip2px(1.5f));
        mPath.addArc(rectF, 180f, 180f);
        rectF.modify(rectF.left - dip2px(3), rectF.top - dip2px(1.5f), rectF.right, rectF.bottom + dip2px(1.5f));
        mPath.addArc(rectF, 0f, 180f);


        PixelMap.InitializationOptions options = new PixelMap.InitializationOptions();
        options.size = new Size((int) mWidth, (int) mHeight);
        options.pixelFormat = PixelFormat.ARGB_8888;

        eye = PixelMap.create(options);
        Canvas c = new Canvas(new Texture(eye));
        mPaint.setStrokeWidth(dip2px(1.7f));
        c.drawPath(mPath, mPaint);
    }

    public float dip2px(float dpValue) {
        return AttrHelper.vp2px(dpValue, mContext);
    }

    @Override
    public void onDraw(Component component, Canvas canvas) {
        canvas.save();
        canvas.rotate(angle, mWidth / 4f, mHeight * 2f / 5f);
        canvas.drawPixelMapHolder(new PixelMapHolder(eye), mWidth / 4f - (eye.getImageInfo().size.width / 2f),
                mHeight * 2f / 5f - (eye.getImageInfo().size.height / 2f), mPaint);

        canvas.restore();
        canvas.save();
        canvas.rotate(angle, mWidth * 3f / 4f, mHeight * 2f / 5f);
        canvas.drawPixelMapHolder(new PixelMapHolder(eye), mWidth * 3f / 4f - (eye.getImageInfo().size.width / 2f),
                mHeight * 2f / 5f - (eye.getImageInfo().size.height / 2f), mPaint);
        canvas.restore();

        mPaint.setStrokeWidth(dip2px(2f));
        canvas.drawLine(mWidth / 4f, mHeight * 3f / 4f, mWidth * 3f / 4f, mHeight * 3f / 4f, mPaint);
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
                angle += 4;
                invalidate();
            }
        });

        if (!valueAnimator.isRunning()) {
            valueAnimator.start();

        }
        return valueAnimator;
    }
}
