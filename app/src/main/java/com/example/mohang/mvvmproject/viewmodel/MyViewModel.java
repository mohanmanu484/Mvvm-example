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

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by mohang on 5/7/17.
 */

public class MyViewModel extends BaseViewModel implements ActionHandler {



    MovieRepository movieRepository = new MovieRepository();

    // RecyclerConfiguration recyclerConfiguration=new RecyclerConfigurationImpl()

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

    public MyViewModel() {

       // Log.d(TAG, "MyViewModel: //////////// construtot ////////////////////");
    }


    @Override
    public void onViewAttached(@android.support.annotation.NonNull LifeCycle.View viewCallback) {
        super.onViewAttached(viewCallback);

        if(viewCallback instanceof MovieContract.View) {
            movieContract = (MovieContract.View) viewCallback;
        }
    }


    public String getNAme(Car car){

        return car.getNamel();

    }

    public void onMovieClick(Movie movie){
        movieContract.showMessage(movie.getTitle());

        movieContract.getUserId();

    }

    @Override
    public void onViewResumed() {
        super.onViewResumed();
        loadMovies();
      //  loadMovies();
    }

    public void loadMovies() {


        handleObservable(movieRepository.getMovies(),movieContract).subscribe(new Observer<List<Movie>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

              //  Log.d(TAG, "onSubscribe: onSubscribe");
            }

            @Override
            public void onNext(@NonNull List<Movie> moviesResponse) {

                testString.set(movieContract.getActivityContext().getString(R.string.app_name));
                observableList.clear();
                observableList.add(new Car("mohan"));
                observableList.addAll(moviesResponse);
            }

            @Override
            public void onError(@NonNull Throwable e) {

               // Log.d(TAG, "onError: onerror called");
            }

            @Override
            public void onComplete() {

              //  Log.d(TAG, "onComplete: oncomplete");

            }
        });

    }



}
