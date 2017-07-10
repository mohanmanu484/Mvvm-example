package com.example.mohang.mvvmproject.repo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.mohang.mvvmproject.exceptions.NoInternetException;
import com.example.mohang.mvvmproject.exceptions.ServerRuntimeException;
import com.example.mohang.mvvmproject.exceptions.UrlNotFoundException;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.net.HttpRetryException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiPredicate;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class NetworkManager {


    private boolean isRequesting;
    public static final String TAG = "NetworkManager";

    private int DEFAULT_RETRY_ATTEMPT = 3;


    protected <T> Observable<T> handleApiObservable(Observable<Response<T>> t) {

        return t.doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(@NonNull Disposable disposable) throws Exception {
               /* if (!NetworkManager.this.isNetworkConnected()) {
                    throw new NoInternetException("Please check internet connection.");
                }*/

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(new BiPredicate<Integer, Throwable>() {
            @Override
            public boolean test(@NonNull Integer integer, @NonNull Throwable throwable) throws Exception {
                return DEFAULT_RETRY_ATTEMPT < 3;
            }
        }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Response<T>>>() {
            @Override
            public ObservableSource<? extends Response<T>> apply(@NonNull Throwable throwable) throws Exception {
                return handleHttpError(throwable);
            }
        }).filter(new Predicate<Response<T>>() {
            @Override
            public boolean test(@NonNull Response<T> tResponse) throws Exception {
                return tResponse.isSuccessful() && tResponse.body()!=null;
            }
        }).map(new Function<Response<T>, T>() {
            @Override
            public T apply(@NonNull Response<T> tResponse) throws Exception {
                return tResponse.body();
            }
        });
    }





    protected <T> Observable<T> handleApiObservable( Observable<Response<T>> t, String tag, boolean cache) {



        return CacheManager.chacheObserver(tag,t.doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(@NonNull Disposable disposable) throws Exception {
               /* if (!NetworkManager.this.isNetworkConnected()) {
                    throw new NoInternetException("Please check internet connection.");
                }*/

            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .retry(new BiPredicate<Integer, Throwable>() {
            @Override
            public boolean test(@NonNull Integer integer, @NonNull Throwable throwable) throws Exception {
                return DEFAULT_RETRY_ATTEMPT < 3;
            }
        }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Response<T>>>() {
            @Override
            public ObservableSource<? extends Response<T>> apply(@NonNull Throwable throwable) throws Exception {
                return handleHttpError(throwable);
            }
        }).filter(new Predicate<Response<T>>() {
            @Override
            public boolean test(@NonNull Response<T> tResponse) throws Exception {
                return tResponse.isSuccessful() && tResponse.body()!=null;
            }
        }).map(new Function<Response<T>, T>() {
            @Override
            public T apply(@NonNull Response<T> tResponse) throws Exception {
                return tResponse.body();
            }
        }),cache);

    }

    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) App.getInstance()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }



    private <T> ObservableSource<T> handleHttpError(Throwable throwable) throws RuntimeException {

        if (throwable instanceof HttpException) {
            int status = ((HttpRetryException) throwable).responseCode();

            if (status >= 400 && status < 500)
                throw new UrlNotFoundException("Url Not Found. Status : " + status);
            else
                throw new ServerRuntimeException("Server runtime exception");
        } else if (throwable instanceof NoInternetException) {
            throw new NoInternetException("Please check your internet connection." /*+ throwable.toString()*/);

        } else {
            throw new IllegalArgumentException("Something went wrong. Please try again");
        }
    }
}



//return true;





