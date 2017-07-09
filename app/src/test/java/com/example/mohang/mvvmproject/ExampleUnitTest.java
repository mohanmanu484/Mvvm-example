package com.example.mohang.mvvmproject;

import android.content.Context;

import com.example.mohang.mvvmproject.models.Movie;
import com.example.mohang.mvvmproject.repo.MovieRepository;
import com.example.mohang.mvvmproject.viewmodel.LifeCycle;
import com.example.mohang.mvvmproject.viewmodel.MyViewModel;
import com.example.mohang.mvvmproject.viewmodel.contract.MovieContract;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {



    //@Test
    public void test_observable_feild_should_contain_20_items() {


        MovieContract.View view=Mockito.mock(MovieContract.View.class);
        Context context=Mockito.mock(Context.class);


        MyViewModel myViewModel = new MyViewModel();
        myViewModel.onViewAttached(view);
        when(context.getString(R.string.app_name)).thenReturn("app_name");
        when(view.getActivityContext()).thenReturn(context);
        myViewModel.loadMovies();
        Assert.assertEquals(21, myViewModel.observableList.size());

        Assert.assertEquals("app_name",myViewModel.testString.get());
    }

  //  @Test
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




        MovieRepository movieRepository = spy(new MovieRepository());

       /* TestObserver<List<Movie>> testSubscriber = new TestObserver<>();
        movieRepository.getMovies().subscribe(testSubscriber);
        testSubscriber.assertValue(new Predicate<List<Movie>>() {
            @Override
            public boolean test(@NonNull List<Movie> movies) throws Exception {
                return movies.size() == 20 && movies.get(6).getTitle().equalsIgnoreCase("Whiplash");
            }
        });*/
    }
}