package com.example.mohang.mvvmproject.dependencyinjection.module;

import android.content.Context;

import com.example.mohang.mvvmproject.dependencyinjection.qualifier.ForApplication;
import com.example.mohang.mvvmproject.repo.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {RepositoryModule.class, ThirdPartyModule.class, ViewModelModule.class})
public class AppModule {

    private final App application;

    public AppModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    @ForApplication
    Context provideApplicationContext() {
        return application;
    }


}
