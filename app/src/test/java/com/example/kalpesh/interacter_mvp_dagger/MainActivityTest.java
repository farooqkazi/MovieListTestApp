package com.example.kalpesh.interacter_mvp_dagger;

import com.example.kalpesh.interacter_mvp_dagger.interacter.MoviesList_Interacter;
import com.example.kalpesh.interacter_mvp_dagger.interacter.MoviesList_InteracterImpl;
import com.example.kalpesh.interacter_mvp_dagger.mvp.MovieList_Presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by kalpesh on 04/05/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class MainActivityTest {


    /**
     *
     */
    @Mock
    private MainActivity activity;

    //@InjectMocks
    @Mock
    MovieList_Presenter movieList_presenter;

    @Mock
    MoviesList_Interacter interactor;//repository


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        // activity = Robolectric.buildActivity(MainActivity.class).create().get();
        interactor = new MoviesList_InteracterImpl();
        movieList_presenter = new MovieList_Presenter(interactor);
        activity= new MainActivity();
//        activity.setPresenter(movieList_presenter);
    }

    @Test
    public void testLeaveView() throws Exception {
        movieList_presenter.unbind();
    }

}