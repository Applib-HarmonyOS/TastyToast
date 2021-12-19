package com.sdsmdg.demoexample.slice;

import com.sdsmdg.demoexample.ResourceTable;
import com.sdsmdg.tastytoast.TastyToast;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;

public class MainAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        findComponentById(ResourceTable.Id_button).setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                TastyToast.makeText(getApplicationContext(), "Download Successful !", TastyToast.LENGTH_LONG,
                        TastyToast.SUCCESS);
            }
        });

        findComponentById(ResourceTable.Id_button2).setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                TastyToast.makeText(getApplicationContext(), "Are you sure ?", TastyToast.LENGTH_LONG,
                        TastyToast.WARNING);
            }
        });

        findComponentById(ResourceTable.Id_button3).setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                TastyToast.makeText(getApplicationContext(), "Downloading failed ! Try again later ", TastyToast.LENGTH_LONG,
                        TastyToast.ERROR);
            }
        });

        findComponentById(ResourceTable.Id_button4).setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                TastyToast.makeText(getApplicationContext(), "Searching for username : 'Rahul' ", TastyToast.LENGTH_LONG,
                        TastyToast.INFO);
            }
        });

        findComponentById(ResourceTable.Id_button5).setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                TastyToast.makeText(getApplicationContext(), "This is Default Toast", TastyToast.LENGTH_LONG,
                        TastyToast.DEFAULT);
            }
        });

        findComponentById(ResourceTable.Id_button6).setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                TastyToast.makeText(getApplicationContext(), "I don't Know !", TastyToast.LENGTH_LONG,
                        TastyToast.CONFUSING);
            }
        });
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
