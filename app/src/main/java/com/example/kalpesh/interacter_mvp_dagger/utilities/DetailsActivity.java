package com.example.kalpesh.interacter_mvp_dagger.mvp;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kalpesh.interacter_mvp_dagger.R;
import com.example.kalpesh.interacter_mvp_dagger.interacter.MoviesList_InteracterImpl;
import com.example.kalpesh.interacter_mvp_dagger.model.Movie;
import com.example.kalpesh.interacter_mvp_dagger.model.MovieDetails;
import com.example.kalpesh.interacter_mvp_dagger.mvp.iMovieDetailContract;
import com.example.kalpesh.interacter_mvp_dagger.mvp.MovieDetailPresenter;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.example.kalpesh.interacter_mvp_dagger.model.Constants.API_KEY;

/**
 * Created by Kazi on 7/May/17.
 */

public class DetailsActivity extends AppCompatActivity implements iMovieDetailContract.IView {

    @Override
    public void getAllDetails(Movie movieDetails) {

    }

    iMovieDetailContract.IPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        Integer movieID = getIntent().getIntExtra("movieid", 1);
        Log.i("Details Activity", "id = " + movieID);
        presenter = new MovieDetailPresenter(new MoviesList_InteracterImpl(), AndroidSchedulers.mainThread(), Schedulers.io());
        presenter.bind(this);
        presenter.getDetails(API_KEY,movieID);
    }



}

