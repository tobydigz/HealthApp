package com.digzdigital.healthapp;

import android.app.Application;

import com.digzdigital.healthapp.dagger.AppComponent;
import com.digzdigital.healthapp.dagger.AppModule;
import com.digzdigital.healthapp.dagger.DaggerAppComponent;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Digz on 16/03/2017.
 */

public class HealthApplication extends Application{

    private static HealthApplication instance = new HealthApplication();
    private static AppComponent appComponent;

    public static HealthApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        getAppComponent();
        FirebaseAuth.getInstance();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    public AppComponent getAppComponent(){
        if (appComponent ==null){
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule())
                    .build();
        }
        return appComponent;
    }
}
