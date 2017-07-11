package com.example.mohang.mvvmproject.repo;

import com.example.mohang.mvvmproject.models.Movie;
import com.example.mohang.mvvmproject.models.MoviesResponse;
import com.example.mohang.mvvmproject.service.MovieService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by mohang on 5/7/17.
 */

public  class MovieRepository extends BaseRepsitory {



    MovieService movieService;



    @Inject
    public MovieRepository(MovieService movieService) {
        this.movieService = movieService;
    }

    public List<Movie> movieList=new ArrayList<>();

    public Observable<List<Movie>> getMovies() {

        return handleApiObservable(movieService.getMovies("66731d2e5d5e953395193ec20af94cac")).map(new Function<MoviesResponse, List<Movie>>() {
            @Override
            public List<Movie> apply(@NonNull MoviesResponse moviesResponse) throws Exception {
                return moviesResponse.getResults();
            }
        });

    }


    public Observable<List<Movie>> getMoviesFromSqlite(){
        List<Movie> movieList=new ArrayList<>();
        Movie movies1=new Movie("title1",1,null);
        Movie movies2=new Movie("title2",2,null);
        movieList.add(movies1);
        movieList.add(movies2);

        return Observable.just(movieList)

                .map(new Function<List<Movie>, List<Movie>>() {
                    @Override
                    public List<Movie> apply(@NonNull List<Movie> movies) throws Exception {
                      //  Log.d(TAG, "apply: "+Thread.currentThread().getName());



                        return movies;
                    }
                });

    }


    @Override
    public boolean runOnMainThread() {
        return false;
    }
}
