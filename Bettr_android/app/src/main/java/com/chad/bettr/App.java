package com.chad.bettr;

import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;

import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_AUTO;

public class App extends Application {

    static {
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_AUTO);
    }

    private static Context CONTEXT;

    @Override
    public void onCreate() {
        super.onCreate();

        CONTEXT = getApplicationContext();

    }

    public static Context getContext() {
        return CONTEXT;
    }


}
