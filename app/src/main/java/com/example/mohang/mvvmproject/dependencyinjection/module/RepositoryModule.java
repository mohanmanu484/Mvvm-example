package com.example.mohang.mvvmproject.dependencyinjection.module;


import com.example.mohang.mvvmproject.repo.MovieRepository;
import com.example.mohang.mvvmproject.service.MovieService;

import dagger.Module;
import dagger.Provides;


@Module
public class RepositoryModule {


    @Provides
    MovieRepository provideMovieRepository(MovieService movieService){
        return new MovieRepository(movieService);
    }

}
