package com.example.kalpesh.interacter_mvp_dagger.mvp;

import android.util.Log;

import com.example.kalpesh.interacter_mvp_dagger.interacter.MoviesList_Interacter;
import com.example.kalpesh.interacter_mvp_dagger.model.Movie;
import com.example.kalpesh.interacter_mvp_dagger.model.MovieDetails;
import com.google.common.util.concurrent.AbstractScheduledService;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by Kazi on 7/May/17.
 */

public class MovieDetailPresenter implements iMovieDetailContract.IPresenter {

    @Inject
    MoviesList_Interacter moviesList_interacter;

    @Override
    public void getDetails(String apiKey, int id) {

        moviesList_interacter.getMovieDetails(id, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Movie>() {
                    @Override
                    public void onCompleted() {


                    }

                    @Override
                    public void onError(Throwable e) {


                    }

                    @Override
                    public void onNext(Movie moviesDetails) {
                        view.getAllDetails(moviesDetails);

                    }
                });

    }



    Scheduler observeScheduler;
    Scheduler subscribeScheduler;
    private iMovieDetailContract.IView view;


    @Inject
    public MovieDetailPresenter(MoviesList_Interacter interactor) {
        this.moviesList_interacter = interactor;
    }

    public MovieDetailPresenter(MoviesList_Interacter moviesList_interacter, Scheduler observeScheduler, Scheduler subscribeScheduler) {
        this.moviesList_interacter = moviesList_interacter;
        this.observeScheduler = observeScheduler;
        this.subscribeScheduler = subscribeScheduler;
    }

    @Override
    public void bind(iMovieDetailContract.IView view) {
        this.view = view;
    }

    @Override
    public void unBind() {
        this.view = null;
    }


}


