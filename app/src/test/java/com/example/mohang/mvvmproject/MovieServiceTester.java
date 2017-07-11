package com.example.mohang.mvvmproject;


import com.example.mohang.mvvmproject.service.MovieService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieServiceTester {

    private final HttpLoggingInterceptor httpLoggingInterceptor;
//    private final ServiceConfig serviceConfig;
    private final MovieService service;
    static String URL="http://api.themoviedb.org";



    public MovieServiceTester() {
        httpLoggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
        service = buildService();
    }

    public MovieService getMoviesService() {
        return service;
    }

    private MovieService buildService() {
        return getDefaultRetrofitBuilder().build().create(MovieService.class);
    }

    private Retrofit.Builder getDefaultRetrofitBuilder() {
        return new Retrofit.Builder()
                .baseUrl(URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOkHttpClientBuilder().build())
                .addConverterFactory(GsonConverterFactory.create());
    }

    private OkHttpClient.Builder getOkHttpClientBuilder() {
        return new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain
                                .request()
                                .newBuilder()
                                .addHeader("Content-Type", "application/json")
                                .build();
                        return chain.proceed(request);
                    }
                });
    }


}
