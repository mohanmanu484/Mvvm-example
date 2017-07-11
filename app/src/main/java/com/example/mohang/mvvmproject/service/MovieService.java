package com.example.mohang.mvvmproject.service;


import com.example.mohang.mvvmproject.models.MoviesResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface MovieService {
        @GET()
        Call<String> universalCall(@Url String url);

        @GET("3/movie/top_rated")
        Call<String> test(@Query("api_key") String apiKey);

        @GET("3/movie/top_rated")
        Observable<Response<MoviesResponse>> getMovies(@Query("api_key") String apiKey);

        @GET("3/movie/top_rated")
        Observable<String> getMoviesList(@Query("api_key") String apiKey);
    }