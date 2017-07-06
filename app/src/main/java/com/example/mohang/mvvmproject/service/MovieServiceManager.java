package com.example.mohang.mvvmproject.service;


import com.example.mohang.mvvmproject.models.MoviesResponse;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieServiceManager {

    private final HttpLoggingInterceptor httpLoggingInterceptor;
//    private final ServiceConfig serviceConfig;
    private final MovieServices service;
    static String URL="http://api.themoviedb.org";



    public MovieServiceManager() {
        httpLoggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
        service = buildService();
    }

    public Observable<retrofit2.Response<MoviesResponse>> getMovies(String symbol) {
        return service.getMovies(symbol);
    }

    private MovieServices buildService() {
        return getDefaultRetrofitBuilder().build().create(MovieServices.class);
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
