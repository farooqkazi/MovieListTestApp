package com.example.kalpesh.interacter_mvp_dagger;

import android.view.View;

import com.example.kalpesh.interacter_mvp_dagger.interacter.MoviesList_Interacter;
import com.example.kalpesh.interacter_mvp_dagger.model.Movie;
import com.example.kalpesh.interacter_mvp_dagger.model.MovieDetails;
import com.example.kalpesh.interacter_mvp_dagger.mvp.MovieDetailPresenter;
import com.example.kalpesh.interacter_mvp_dagger.mvp.iMovieDetailContract;
import com.example.kalpesh.interacter_mvp_dagger.service.ApiInterface;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import javax.inject.Inject;

import rx.Observable;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

/**
 * Created by Kazi on 8/May/17.
 */

public class MovieDetailsPresenterTest {

    @Mock
    @Inject
    ApiInterface apiInterface;

    @Mock
    iMovieDetailContract.IView view;

    @Mock
    MoviesList_Interacter moviesList_interacter;

    @Mock
    Movie movie;


    @InjectMocks
    MovieDetailPresenter movieDetailPresenter;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

        movie = new Movie("", true, null, null, null, null,
                "Doctor Strange", null, null, null, null,
                null, null, null);

        view = mock(iMovieDetailContract.IView.class);

        movieDetailPresenter = new MovieDetailPresenter(moviesList_interacter);



        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });
    }

    @Test
    public void movieDetailsShouldLoadIntoView(){

        int id = 0;
        String key = "";
        view = mock(iMovieDetailContract.IView.class);


        movieDetailPresenter.bind(view);

        when(moviesList_interacter.getMovieDetails(id, key))
                .thenReturn(Observable.just(movie));

        movieDetailPresenter.getDetails(key, id);
        Mockito.verify(view).getAllDetails(movie);

    }

    @After
    public void tearDown() {
        RxAndroidPlugins.getInstance().reset();
    }



}








