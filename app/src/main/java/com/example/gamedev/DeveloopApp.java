package com.example.gamedev;

import android.app.Application;

import androidx.appcompat.app.AppCompatDelegate;

public class DeveloopApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.getDefaultNightMode());
    }
}
