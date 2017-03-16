package com.digzdigital.healthapp.dagger;

import com.digzdigital.healthapp.data.AppFirebaseHelper;
import com.digzdigital.healthapp.data.FirebaseHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Digz on 16/03/2017.
 */
@Module
public class AppModule {

    public AppModule(){

    }

    @Provides @Singleton
    public FirebaseHelper providesFirebaseHelper(){
        return new AppFirebaseHelper();
    }
}
