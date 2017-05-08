package com.example.kalpesh.interacter_mvp_dagger.mvp;

import com.example.kalpesh.interacter_mvp_dagger.model.Movie;
import com.example.kalpesh.interacter_mvp_dagger.model.MovieDetails;

/**
 * Created by Kazi on 7/May/17.
 */

public class iMovieDetailContract {

    public interface IView {
        public void getAllDetails(Movie movieDetails);

    }

    public interface IPresenter {
        void getDetails(String apiKey, int id);
        public void bind(IView view) ;
        public void unBind() ;

    }
}
