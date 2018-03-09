package com.example.cesartorres.testmvpbus.presentation.mvp.utils;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

public class BusEventType {
    @Retention(SOURCE)
    @StringDef({USER_SELECTED})
    public @interface ListUpdate {}

    public static final String USER_SELECTED = "userSelected";
}
 