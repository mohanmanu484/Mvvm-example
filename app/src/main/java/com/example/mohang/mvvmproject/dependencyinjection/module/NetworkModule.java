package com.example.mohang.mvvmproject.dependencyinjection.module;


import com.example.mohang.mvvmproject.BuildConfig;
import com.example.mohang.mvvmproject.service.MovieService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mohang on 11/7/2017.
 */

@Module
public class NetworkModule {


    @Provides
    Interceptor provideInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {

                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();

                HttpUrl url = originalHttpUrl.newBuilder()
                        .build();

                Request request = original.newBuilder()
                        .url(url)
                        .build();

                return chain.proceed(request);
            }
        };
    }


    @Provides
    OkHttpClient provideOkHttpClient(Interceptor interceptor) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
            client.addInterceptor(interceptor);
            client.addInterceptor(logging);
        return client.build();
    }


    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.API_URL)
                .client( okHttpClient)
                .build();
        return retrofit;
    }



    @Provides
    @Singleton
    MovieService providePropertyApiService(Retrofit retrofit) {
        return retrofit.create(MovieService.class);
    }

}
