package com.example.mohang.mvvmproject.repo;

import android.app.Application;

import com.example.mohang.mvvmproject.dependencyinjection.component.ApplicationComponent;
import com.example.mohang.mvvmproject.dependencyinjection.component.DaggerApplicationComponent;
import com.example.mohang.mvvmproject.dependencyinjection.module.AppModule;

/**
 * Created by mohang on 6/7/17.
 */

public class App extends Application {

    private static App application;

    private ApplicationComponent component;


    @Override
    public void onCreate() {
        super.onCreate();
        application=this;
        component = DaggerApplicationComponent.builder()
                .appModule(new AppModule(this))
                .build();
        component().inject(this);
    }

    public static App getInstance() {
        return application;
    }

    public ApplicationComponent component() {
        return component;
    }
}
