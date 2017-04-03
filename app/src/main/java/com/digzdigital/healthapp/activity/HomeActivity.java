package com.digzdigital.healthapp.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.digzdigital.healthapp.HealthApplication;
import com.digzdigital.healthapp.R;
import com.digzdigital.healthapp.data.FirebaseHelper;
import com.digzdigital.healthapp.data.model.appointment.Appointment;
import com.digzdigital.healthapp.fragment.antenatal.AntenatalTestsFragment;
import com.digzdigital.healthapp.fragment.appointment.create_edit.CreateAppointmentFragment;
import com.digzdigital.healthapp.fragment.appointment.view.AppointmentViewFragment;
import com.digzdigital.healthapp.fragment.immunization.ImmunizationFragment;
import com.digzdigital.healthapp.fragment.messages.MessagesFragment;
import com.digzdigital.healthapp.fragment.mother.MotherFragment;
import com.digzdigital.healthapp.fragment.nutrition.NutritionFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.inject.Inject;


public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{


    @Inject
    public FirebaseHelper firebaseHelper;
    private FragmentManager fragmentManager;
    private Fragment antenatalTestsFragment, immunizationFragment, nutritionFragment, appointmentFragment, motherFragment;
    private Boolean firstTime = null;
    private String jsonString;
    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;
    private String uid = "abc";
    private FirebaseAuth.AuthStateListener listener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            firebaseUser = firebaseAuth.getCurrentUser();
            if (firebaseUser == null) {
                switchActivity(LoginActivity.class);
            } else {
                uid = firebaseUser.getUid();
                FirebaseMessaging.getInstance().subscribeToTopic("reminders_" + firebaseUser.getUid());
            }
        }
    };

    private void switchActivity(Class classFile) {
        Intent intent = new Intent(this, classFile);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        auth = FirebaseAuth.getInstance();
        auth.addAuthStateListener(listener);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ((HealthApplication) getApplication()).getAppComponent().inject(this);
        fragmentManager = getFragmentManager();
        antenatalTestsFragment = AntenatalTestsFragment.newInstance("a", "b");
        fragmentManager.beginTransaction()
                .replace(R.id.contentFrame, getAntenatalTestsFragment())
                .addToBackStack(null)
                .commit();

        if (getIntent().getExtras() != null) {

        }
    }

    private boolean isFirstTime() {
        if (firstTime != null) return firstTime;
        SharedPreferences mPreferences = this.getSharedPreferences("health_app", Context.MODE_PRIVATE);
        firstTime = mPreferences.getBoolean("firstTime", true);
        if (!firstTime) return firstTime;
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean("firstTime", false);
        editor.commit();

        return firstTime;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_immunization) {
            switchFragmentToStack(getImmunizationFragment());
        } else if (id == R.id.nav_logout) {
            auth.signOut();
        } else if (id == R.id.nav_appointment) {
            switchFragmentToStack(getAppointmentFragment());
        } else if (id == R.id.nav_antenatal_tests) {
            switchFragmentToStack(getAntenatalTestsFragment());
        } else if (id == R.id.nav_nutrition) {
            switchFragmentToStack(getNutritionFragment());
        }else if (id == R.id.nav_mother){
            switchFragmentToStack(getMotherFragment());
        }else if (id == R.id.nav_messages){
            switchFragmentToStack(MessagesFragment.newInstance(firebaseUser.getUid()));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private Fragment getMotherFragment() {
        if (motherFragment == null)motherFragment = MotherFragment.newInstance(uid, "");
        return motherFragment;
    }


    public String readAssetsFile() {
        if (this.jsonString != null) return this.jsonString;
        AssetManager assetManager = getAssets();
        StringBuilder buf = new StringBuilder();
        InputStream json = null;
        try {
            json = assetManager.open("trimester.json");
            BufferedReader in =
                    new BufferedReader(new InputStreamReader(json, "UTF-8"));
            String str;
            while ((str = in.readLine()) != null) {
                buf.append(str);
            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        jsonString = buf.toString();
        return jsonString;


    }

    private void switchFragmentToStack(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.contentFrame, fragment)
                .addToBackStack(null)
                .commit();
    }

    private void switchFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.contentFrame, fragment)
                .commit();
    }

    public void onButtonClicked() {
        switchFragment(getAppointmentFragment());
    }

    public void onCreateAppointmentClicked() {
        Appointment appointment = new Appointment();
        appointment.setMotherId(uid);
        switchFragmentToStack(CreateAppointmentFragment.newInstance(appointment, true));
    }

    public FirebaseHelper getFirebaseHelper() {
        return firebaseHelper;
    }

    public Fragment getAntenatalTestsFragment() {
        if (antenatalTestsFragment == null) antenatalTestsFragment = AntenatalTestsFragment.newInstance("a", "b");
        return antenatalTestsFragment;
    }

    public Fragment getImmunizationFragment() {
        if (immunizationFragment == null) immunizationFragment = ImmunizationFragment.newInstance("a", "b");
        return immunizationFragment;
    }

    public Fragment getNutritionFragment() {
        if (nutritionFragment == null) nutritionFragment = new NutritionFragment();
        return nutritionFragment;
    }

    public Fragment getAppointmentFragment() {
        if (appointmentFragment == null) appointmentFragment = AppointmentViewFragment.newInstance(uid, "");
        return appointmentFragment;
    }
}
