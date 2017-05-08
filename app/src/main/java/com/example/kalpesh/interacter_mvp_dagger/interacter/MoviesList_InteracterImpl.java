package com.example.kalpesh.interacter_mvp_dagger.interacter;

import com.example.kalpesh.interacter_mvp_dagger.model.Constants;
import com.example.kalpesh.interacter_mvp_dagger.model.Movie;
import com.example.kalpesh.interacter_mvp_dagger.model.MovieDetails;
import com.example.kalpesh.interacter_mvp_dagger.model.MoviesResponse;
import com.example.kalpesh.interacter_mvp_dagger.service.ApiInterface;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by kalpesh on 11/03/2017.
 */

public class MoviesList_InteracterImpl implements MoviesList_Interacter {

    private ApiInterface service;

    public MoviesList_InteracterImpl() {
        // Configure Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                // Base URL can change for endpoints (dev, staging, live..)
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        service = retrofit.create(ApiInterface.class);
    }

    @Override
    public Observable<MoviesResponse> getTopRatedMovies(String apiKey) {
        return service.getTopRatedMovies(apiKey);

    }

    @Override
    public Observable<Movie> getMovieDetails(int id, String apiKey) {
        return null;
    }


}
