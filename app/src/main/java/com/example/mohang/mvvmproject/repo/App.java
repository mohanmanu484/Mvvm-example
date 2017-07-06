package com.example.mohang.mvvmproject.repo;

import android.app.Application;

/**
 * Created by mohang on 6/7/17.
 */

public class App extends Application {

    private static App application;

    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
    }

    public static App getInstance() {
        return application;
    }
}
