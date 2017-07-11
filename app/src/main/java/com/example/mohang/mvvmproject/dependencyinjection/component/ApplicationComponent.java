package com.example.mohang.mvvmproject.dependencyinjection.component;


import com.example.mohang.mvvmproject.dependencyinjection.module.AppModule;
import com.example.mohang.mvvmproject.dependencyinjection.module.NetworkModule;
import com.example.mohang.mvvmproject.repo.App;
import com.example.mohang.mvvmproject.repo.MovieRepository;
import com.example.mohang.mvvmproject.ui.MvvmFragmment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ankit on 26/12/16.
 */
/*@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})*/
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface ApplicationComponent {

    void inject(App application);


    void inject(MvvmFragmment mvvmFragmment);


    void inject(MovieRepository movieRepository);
}
