package com.example.mohang.mvvmproject.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.example.mohang.mvvmproject.models.Movie;
import com.example.mohang.mvvmproject.repo.MovieRepository;
import com.example.mohang.mvvmproject.ui.adapters.ActionHandler;
import com.example.mohang.mvvmproject.viewmodel.contract.MovieContract;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by mohang on 5/7/17.
 */

public class MyViewModel extends BaseViewModel implements ActionHandler {



    MovieRepository movieRepository = new MovieRepository();

    // RecyclerConfiguration recyclerConfiguration=new RecyclerConfigurationImpl()

    public ObservableList<Movie> observableList = new ObservableArrayList<>();

    public ObservableField<String> test=new ObservableField<>("a");
    public MovieContract.View movieContract;


    @Override
    public void onCreate() {
        super.onCreate();
        loadMovies();
    }


    @Override
    public void onViewAttached(@android.support.annotation.NonNull LifeCycle.View viewCallback) {
        super.onViewAttached(viewCallback);

        if(viewCallback instanceof MovieContract.View) {
            movieContract = (MovieContract.View) viewCallback;
        }
    }

    public void onMovieClick(Movie movie){
        movieContract.showMessage(movie.getTitle());

    }




    public void loadMovies() {



       // Log.d(TAG, "loadMovies: "+Thread.currentThread().getName());

        handleObservable(movieRepository.getMovies()).subscribe(new Observer<List<Movie>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull List<Movie> moviesResponse) {

                observableList.clear();
                observableList.addAll(moviesResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }



}
