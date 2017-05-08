package com.example.kalpesh.interacter_mvp_dagger.interacter;

import com.example.kalpesh.interacter_mvp_dagger.model.Movie;
import com.example.kalpesh.interacter_mvp_dagger.model.MovieDetails;
import com.example.kalpesh.interacter_mvp_dagger.model.MoviesResponse;

import rx.Observable;

/**
 * Created by kalpesh on 11/03/2017.
 */

public interface MoviesList_Interacter {
    Observable<MoviesResponse> getTopRatedMovies(String apiKey);

    //New Observer
    Observable<Movie> getMovieDetails(int id, String apiKey) ;
}
