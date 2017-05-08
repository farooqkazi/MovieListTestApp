package com.example.kalpesh.interacter_mvp_dagger.module;

import com.example.kalpesh.interacter_mvp_dagger.interacter.MoviesList_Interacter;
import com.example.kalpesh.interacter_mvp_dagger.interacter.MoviesList_InteracterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by kalpesh on 11/03/2017.
 */

@Module
public class Movielist_Module {

    @Provides
    public MoviesList_Interacter providesBooksInteractor() {
       return new MoviesList_InteracterImpl();
    }
}
