package com.example.mohang.mvvmproject;

import android.content.Context;

import com.example.mohang.mvvmproject.models.Movie;
import com.example.mohang.mvvmproject.repo.MovieRepository;
import com.example.mohang.mvvmproject.service.MovieServiceTester;
import com.example.mohang.mvvmproject.utils.Utility;
import com.example.mohang.mvvmproject.viewmodel.LifeCycle;
import com.example.mohang.mvvmproject.viewmodel.MyViewModel;
import com.example.mohang.mvvmproject.viewmodel.contract.MovieContract;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({Utility.class})
public class ExampleUnitTest  {


    private MovieServiceTester movieServiceTester=new MovieServiceTester();



    @Before
    public void setUp() throws Exception {

        PowerMockito.mockStatic(Utility.class);
        MockitoAnnotations.initMocks(this);





    }

    @Test
    public void poerMOckitotest(){


       MovieRepository movieRepository=new MovieRepository(movieServiceTester.getMoviesService());

        TestObserver<List<Movie>> testSubscriber = new TestObserver<>();
        movieRepository.getMovies().subscribe(testSubscriber);
        testSubscriber.assertValue(new Predicate<List<Movie>>() {
            @Override
            public boolean test(@NonNull List<Movie> movies) throws Exception {
                return movies.size() == 20 && movies.get(6).getTitle().equalsIgnoreCase("Whiplash");
            }
        });


      /*  MovieRepository baseRepsitory=spy(new MovieRepository(new MovieSe));
        when(Utility.isNetworkConnected()).thenReturn(false);


       // PowerMockito.when(App.getInstance()).thenReturn(app);
       // when(baseRepsitory.isNetworkConnected()).thenReturn(true);
        Assert.assertTrue(BaseRepsitory.isNetworkConnected());
*/


    }



    //@Test
    public void test_observable_feild_should_contain_20_items() {


        MovieContract.View view=Mockito.mock(MovieContract.View.class);
        Context context=Mockito.mock(Context.class);
        MovieRepository movieRepository=null;//new MovieRepository();


        MyViewModel myViewModel = new MyViewModel(movieRepository);
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
        MyViewModel myViewModel=null;//spy(new MyViewModel(new MovieRepository()));
        myViewModel.onViewAttached(test);
        myViewModel.onMovieClick(movie);
        verify(test).showMessage("title");


    }


   // @Test
    public void test_seventh_item_is_whiplash() {




     //   MovieRepository movieRepository = spy(new MovieRepository());

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