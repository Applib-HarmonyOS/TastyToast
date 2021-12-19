package com.sdsmdg.tastytoast;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;
import ohos.agp.colors.RgbPalette;
import ohos.agp.components.Component;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.LayoutScatter;
import ohos.agp.components.Text;
import ohos.agp.components.element.ShapeElement;
import ohos.agp.utils.Color;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.ToastDialog;
import ohos.app.Context;

public class TastyToast {

    public static final int LENGTH_SHORT = 1000;
    public static final int LENGTH_LONG = 2000;


    public static final int SUCCESS = 1;
    public static final int WARNING = 2;
    public static final int ERROR = 3;
    public static final int INFO = 4;
    public static final int DEFAULT = 5;
    public static final int CONFUSING = 6;

    static SuccessToastView successToastView;
    static WarningToastView warningToastView;



    public static ToastDialog makeText(Context context, String msg, int length, int type) {

        ToastDialog toast = new ToastDialog(context);
        toast.setDuration(length);

        switch (type) {
            case 1: {
                Component layout = LayoutScatter.getInstance(context).
                        parse(ResourceTable.Layout_success_toast_layout, null, false);
                Text text = (Text) layout.findComponentById(ResourceTable.Id_toastMessage);
                text.setText(msg);
                successToastView = (SuccessToastView) layout.findComponentById(ResourceTable.Id_successView);
                successToastView.startAnim();
                text.setBackground(new ShapeElement(context, ResourceTable.Graphic_success_toast));
                text.setTextColor(new Color(RgbPalette.parse("#FFFFFF")));
                toast.setComponent(layout);
                break;
            }
            case 2: {
                Component layout = LayoutScatter.getInstance(context).
                        parse(ResourceTable.Layout_warning_toast_layout, null, false);
                Text text = (Text) layout.findComponentById(ResourceTable.Id_toastMessage);
                text.setText(msg);
                warningToastView = (WarningToastView) layout.findComponentById(ResourceTable.Id_warningView);
                SpringSystem springSystem = SpringSystem.create();
                final Spring spring = springSystem.createSpring();
                spring.setCurrentValue(1.8);
                SpringConfig config = new SpringConfig(40, 5);
                spring.setSpringConfig(config);
                spring.addListener(new SimpleSpringListener() {

                    @Override
                    public void onSpringUpdate(Spring spring) {
                        float value = (float) spring.getCurrentValue();
                        float scale = (float) (0.9f - (value * 0.5f));

                        warningToastView.setScaleX(scale);
                        warningToastView.setScaleY(scale);
                    }
                });
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                        }
                        spring.setEndValue(0.4f);
                    }
                });

                t.start();

                text.setBackground(new ShapeElement(context, ResourceTable.Graphic_warning_toast));
                text.setTextColor(new Color(RgbPalette.parse("#FFFFFF")));
                toast.setComponent(layout);
                break;
            }
        }

        toast.setAlignment(LayoutAlignment.BOTTOM);
        toast.show();
        return toast;
    }
}
