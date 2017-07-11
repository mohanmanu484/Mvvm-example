package com.example.mohang.mvvmproject.dependencyinjection.module;

import com.example.mohang.mvvmproject.repo.MovieRepository;
import com.example.mohang.mvvmproject.viewmodel.MyViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by mohang on 11/7/17.
 */

@Module
public class ViewModelModule {


    @Provides
    public MyViewModel provideSignInViewModel(MovieRepository movieRepository) {
        return new MyViewModel(movieRepository);
    }

}
