package com.example.mohang.mvvmproject.viewmodel;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.databinding.ObservableList;

import com.example.mohang.mvvmproject.R;
import com.example.mohang.mvvmproject.models.Car;
import com.example.mohang.mvvmproject.models.Movie;
import com.example.mohang.mvvmproject.repo.MovieRepository;
import com.example.mohang.mvvmproject.viewmodel.contract.MovieContract;
import com.mohang.genericadapter.ActionHandler;
import com.mohang.genericadapter.ViewTypeHandler;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by mohang on 5/7/17.
 */


public class MyViewModel extends BaseViewModel implements ActionHandler {



    MovieRepository movieRepository;


    @Inject
    public MyViewModel(MovieRepository movieRepository) {
        this.movieRepository=movieRepository;
    }

    public ObservableList<Object> observableList = new ObservableArrayList<>();
    public ObservableField<String> testString=new ObservableField<>("");


    public ViewTypeHandler viewTypeHandler=new ViewTypeHandler() {
        @Override
        public int getViewType(int pos, Object item) {

            if(item instanceof Car){
                return R.layout.car_layout;
            }
            return R.layout.adapter_movie_item;
        }
    };


    public ObservableField<String> test=new ObservableField<>("a");
    public MovieContract.View movieContract;




    @Override
    public void onViewAttached(@android.support.annotation.NonNull LifeCycle.View viewCallback) {
        super.onViewAttached(viewCallback);

        if(viewCallback instanceof MovieContract.View) {
            movieContract = (MovieContract.View) viewCallback;
        }
    }


    public String getNAme(Car car){

        return car.getName();

    }

    public void onMovieClick(Movie movie){
        movieContract.showMessage(movie.getTitle());
        movieContract.getUserId();

    }

    @Override
    public void onViewResumed() {
        super.onViewResumed();
        loadMovies();
    }

    public void loadMovies() {

        handleObservable(movieRepository.getMovies())
                .doOnNext(new Consumer<List<Movie>>() {
                    @Override
                    public void accept(@NonNull List<Movie> movies) throws Exception {

                    }
                })
                .subscribe(new Observer<List<Movie>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<Movie> movies) {
                        testString.set(movieContract.getActivityContext().getString(R.string.app_name));
                        observableList.clear();
                        observableList.add(new Car("mohan"));
                        observableList.addAll(movies);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onViewDetached() {
        super.onViewDetached();
        movieContract=null;
    }
}
