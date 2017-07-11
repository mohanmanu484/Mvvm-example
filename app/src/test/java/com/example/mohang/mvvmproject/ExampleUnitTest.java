package com.example.mohang.mvvmproject;

import android.content.Context;
import android.util.LruCache;

import com.example.mohang.mvvmproject.models.Movie;
import com.example.mohang.mvvmproject.repo.MovieRepository;
import com.example.mohang.mvvmproject.utils.Utility;
import com.example.mohang.mvvmproject.viewmodel.LifeCycle;
import com.example.mohang.mvvmproject.viewmodel.MyViewModel;
import com.example.mohang.mvvmproject.viewmodel.contract.MovieContract;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


public class ExampleUnitTest  {




    @Mock
    MovieContract.View view;

    @Mock
    MovieRepository movieRepository;

    @Mock
    Context context;

    MovieServiceTester movieServiceTester=new MovieServiceTester();


    @Before
    public void setUp() throws Exception {

        PowerMockito.mockStatic(Utility.class);
        PowerMockito.mock(LruCache.class);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void poerMOckitotest(){




        MovieRepository movieRepository= new MovieRepository(movieServiceTester.getMoviesService()){
            @Override
            public boolean runOnMainThread() {
                return true;
            }
        };


        movieRepository.getMovies().subscribe();

    }



    @Test
    public void test_observable_feild_should_contain_21_items() {

        MovieRepository movieRepository=new MovieRepository(movieServiceTester.getMoviesService()){
            @Override
            public boolean runOnMainThread() {
                return true;
            }
        };
        MyViewModel myViewModel = new MyViewModel(movieRepository);
        myViewModel.onViewAttached(view);

        when(view.getActivityContext()).thenReturn(context);
        when(context.getString(R.string.app_name)).thenReturn("app_name");

        myViewModel.loadMovies();
        Assert.assertEquals(21, myViewModel.observableList.size());

        Assert.assertEquals("app_name",myViewModel.testString.get());
    }

    @Test
    public void onMovieClick_showMessage() {


        LifeCycle.View test = Mockito.mock(MovieContract.View.class);

        Movie movie=new Movie("title",1,null);
        MyViewModel myViewModel=spy(new MyViewModel(movieRepository));
        myViewModel.onViewAttached(test);
        myViewModel.onMovieClick(movie);
        verify(test).showMessage("title");




    }


    @Test
    public void test_seventh_item_is_whiplash() {

        MovieRepository movieRepository = spy(new MovieRepository(movieServiceTester.getMoviesService()){
            @Override
            public boolean runOnMainThread() {
                return true;
            }
        });

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