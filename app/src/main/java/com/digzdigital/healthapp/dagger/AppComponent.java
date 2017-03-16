package com.digzdigital.healthapp.dagger;

import com.digzdigital.healthapp.activity.HomeActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Digz on 16/03/2017.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(HomeActivity activity);
}
