package com.sdsmdg.tastytoast;

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

public class WarningToastView extends Component implements Component.EstimateSizeListener, Component.DrawTask {

    RectFloat rectFOne = new RectFloat();
    RectFloat rectFTwo = new RectFloat();
    RectFloat rectFThree = new RectFloat();
    private Paint mPaint;
    private float mWidth = 0f;
    private float mHeight = 0f;
    private float mStrokeWidth = 0f;
    private float mPadding = 0f;
    private float mPaddingBottom = 0f;
    private float endAngle = 0f;

    public WarningToastView(Context context) {
        super(context);
        initialize();
    }

    public WarningToastView(Context context, AttrSet attrSet) {
        super(context, attrSet);
        initialize();
    }

    public WarningToastView(Context context, AttrSet attrSet, String styleName) {
        super(context, attrSet, styleName);
        initialize();
    }

    public WarningToastView(Context context, AttrSet attrSet, int resId) {
        super(context, attrSet, resId);
        initialize();
    }

    private void initialize() {
        mWidth = getWidth();
        mHeight = getHeight();
        mPadding = AttrHelper.vp2px(2, mContext);
        mPaddingBottom = mPadding * 2;
        mStrokeWidth = AttrHelper.vp2px(2, mContext);
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
        mPaint.setColor(new Color(RgbPalette.parse("#f0ad4e")));
        mPaint.setStrokeWidth(mStrokeWidth);
    }

    private void initRect() {
        rectFOne = new RectFloat(mPadding, 0, mWidth - mPadding, mWidth - mPaddingBottom);
        rectFTwo = new RectFloat((float) (1.5 * mPadding), AttrHelper.vp2px(6, mContext) + mPadding +
                mHeight / 3, mPadding + AttrHelper.vp2px(9, mContext), AttrHelper.vp2px(6, mContext) + mPadding + mHeight / 2);
        rectFThree = new RectFloat(mPadding + AttrHelper.vp2px(9, mContext), AttrHelper.vp2px(3, mContext) + mPadding +
                mHeight / 3, mPadding + AttrHelper.vp2px(18, mContext), AttrHelper.vp2px(3, mContext) + mPadding + mHeight / 2);
    }

    @Override
    public void onDraw(Component component, Canvas canvas) {
        mPaint.setStyle(Paint.Style.STROKE_STYLE);
        canvas.drawArc(rectFOne, new Arc(170, -144, false), mPaint);

        canvas.drawLine(mWidth - AttrHelper.vp2px(3, mContext) - mStrokeWidth, (float) (mPadding +
                        mHeight / 6), mWidth - AttrHelper.vp2px(3, mContext) - mStrokeWidth,
                mHeight - AttrHelper.vp2px(2, mContext) - mHeight / 4, mPaint);

        canvas.drawLine(mWidth - AttrHelper.vp2px(3, mContext) - mStrokeWidth - AttrHelper.vp2px(8, mContext), (float) (mPadding +
                        mHeight / 8.5), mWidth - AttrHelper.vp2px(3, mContext) - mStrokeWidth - AttrHelper.vp2px(8, mContext),
                (float) (mHeight - AttrHelper.vp2px(3, mContext) - mHeight / 2.5), mPaint);

        canvas.drawLine(mWidth - AttrHelper.vp2px(3, mContext) - mStrokeWidth - AttrHelper.vp2px(17, mContext), (float) (mPadding +
                        mHeight / 10), mWidth - AttrHelper.vp2px(3, mContext) - mStrokeWidth - AttrHelper.vp2px(17, mContext),
                (float) (mHeight - AttrHelper.vp2px(3, mContext) - mHeight / 2.5), mPaint);

        canvas.drawLine(mWidth - AttrHelper.vp2px(3, mContext) - mStrokeWidth - AttrHelper.vp2px(26, mContext), (float) (mPadding +
                        mHeight / 10), mWidth - AttrHelper.vp2px(3, mContext) - mStrokeWidth - AttrHelper.vp2px(26, mContext),
                (float) (mHeight - AttrHelper.vp2px(2, mContext) - mHeight / 2.5), mPaint);


        canvas.drawArc(rectFTwo, new Arc(170, 180, false), mPaint);
        canvas.drawArc(rectFThree, new Arc(175, -150, false), mPaint);
    }

}
