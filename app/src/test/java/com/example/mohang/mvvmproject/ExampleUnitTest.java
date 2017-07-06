package com.example.mohang.mvvmproject;

import android.support.annotation.NonNull;

import com.example.mohang.mvvmproject.models.Movie;
import com.example.mohang.mvvmproject.repo.MovieRepository;
import com.example.mohang.mvvmproject.viewmodel.LifeCycle;
import com.example.mohang.mvvmproject.viewmodel.MyViewModel;
import com.example.mohang.mvvmproject.viewmodel.contract.MovieContract;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {



    @Test
    public void test_observable_feild_should_contain_20_items() {



        MyViewModel myViewModel = new MyViewModel();
        myViewModel.loadMovies();
        Assert.assertEquals(20, myViewModel.observableList.size());

    }

    @Test
    public void onMovieClick_showMessage() {


        LifeCycle.View test = Mockito.mock(MovieContract.View.class);

        Movie movie=new Movie("title",1,null);
        MyViewModel myViewModel=spy(new MyViewModel());
        myViewModel.onViewAttached(test);
        myViewModel.onMovieClick(movie);
        verify(test).showMessage("title");


    }


    @Test
    public void test_seventh_item_is_whiplash() {


        MovieRepository movieRepository = new MovieRepository();

        TestObserver<List<Movie>> testSubscriber = new TestObserver<>();
        movieRepository.getMovies().subscribe(testSubscriber);
        testSubscriber.assertValue(new Predicate<List<Movie>>() {
            @Override
            public boolean test(@NonNull List<Movie> movies) throws Exception {
                return movies.size() == 20 && movies.get(6).getTitle().equalsIgnoreCase("Whiplash");
            }
        });
    }
}