package com.example.kalpesh.interacter_mvp_dagger;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.kalpesh.interacter_mvp_dagger.adapter.MoviesAdapter;
import com.example.kalpesh.interacter_mvp_dagger.interacter.MoviesList_Interacter;
import com.example.kalpesh.interacter_mvp_dagger.interacter.MoviesList_InteracterImpl;
import com.example.kalpesh.interacter_mvp_dagger.model.Constants;
import com.example.kalpesh.interacter_mvp_dagger.model.Movie;
import com.example.kalpesh.interacter_mvp_dagger.model.MoviesResponse;
import com.example.kalpesh.interacter_mvp_dagger.module.App;
import com.example.kalpesh.interacter_mvp_dagger.mvp.IMovieList_View;
import com.example.kalpesh.interacter_mvp_dagger.mvp.MovieList_Presenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity  implements IMovieList_View {
    private List<Movie> movies;
    MoviesList_Interacter interacter;

    public List<Movie> getMovies() {
        return movies;
    }

    @Override
    public void onFetchDataStarted() {
        Log.i("Main Activity","Data started");

    }


    @Override
    public void onFetchDataSuccess(MoviesResponse response) {
        Log.i("Main Activity","Data fetched");

    }


    @Override
    public void onFetchDataCompleted() {

    }



    @Override
    public void onFetchDataError(Throwable e) {

    }

    //1 Presenter
    @Inject
    MovieList_Presenter presenter;

    @BindView(R.id.movies_recycler_view)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ButterKnife.bind(this);
        interacter = new MoviesList_InteracterImpl();


        //2  Dagger inject
        ((App) getApplication()).getAppComponent().inject(this);

        // 3 Initialize the View
        //presenter = new MovieList_Presenter(interacter, Schedulers.io(), AndroidSchedulers.mainThread());
        presenter.bind(this);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));



        if (isInternetConnectionAvailable()) {
            presenter.performSearch(Constants.API_KEY);
        } else {
            Toast.makeText(MainActivity.this,"No Internet Connection",
                    Toast.LENGTH_SHORT).show();
        }
    }

    //3 Extend IView and implement methods
    @Override
    public void updateUi(List<Movie> books) {
        if (books.isEmpty()) {
            Toast.makeText(MainActivity.this,"No Movies Found",
                    Toast.LENGTH_SHORT).show();
            // if no books found, show a message
           // textNoDataFound.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(MainActivity.this,"Movies Found",
                    Toast.LENGTH_SHORT).show();
           // textNoDataFound.setVisibility(View.GONE);
        }
       // adapter.clear();

        recyclerView.setAdapter(new MoviesAdapter(books, R.layout.list_item_movie, getApplicationContext()));
    }

    @Override
    public void getAllDetails(String movieName) {
       // recyclerView.setAdapter(new MoviesAdapter(movieName, R.layout.list_item_movie, getApplicationContext()));
    }

    // 4 Inject Presenter
    @Inject
    public void setPresenter(MovieList_Presenter presenter) {
        this.presenter = presenter;
        presenter.bind(this);

    }

    @Override
    protected void onDestroy() {
       presenter.unbind();
        super.onDestroy();
    }

    private boolean isInternetConnectionAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork.isConnectedOrConnecting();
    }

}
