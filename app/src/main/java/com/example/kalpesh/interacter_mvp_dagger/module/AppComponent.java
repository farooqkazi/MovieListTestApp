package com.example.kalpesh.interacter_mvp_dagger.module;


import com.example.kalpesh.interacter_mvp_dagger.MainActivity;
import com.example.kalpesh.interacter_mvp_dagger.mvp.DetailsActivity;

import dagger.Component;

/**
 * Created by Miquel Beltran on 11/18/16
 * More on http://beltran.work
 */
@Component(dependencies = {Movielist_Module.class})
public interface AppComponent {

    void inject(MainActivity activity);
    void inject(DetailsActivity detailsActivity);

}
