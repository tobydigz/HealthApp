package com.digzdigital.healthapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.digzdigital.healthapp.fragment.ImmunizationFragment;
import com.digzdigital.healthapp.fragment.NutritionFragment;
import com.digzdigital.healthapp.fragment.PrescriptionFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import io.realm.Realm;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;
    private Fragment prescriptionFragment, immunizationFragment, nutritionFragment;
    private Boolean firstTime = null;
    Realm realm;
    private String jsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // TODO: 04/12/2016 Emergency is fab
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

        fragmentManager = getSupportFragmentManager();
        prescriptionFragment = PrescriptionFragment.newInstance("a", "b");
        fragmentManager.beginTransaction()
                .replace(R.id.contentFrame, prescriptionFragment)
                .addToBackStack(null)
                .commit();
        // realm = Realm.getDefaultInstance();
        if (isFirstTime())setupDbStuff();

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
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_immunization) {

            if (immunizationFragment == null)
                immunizationFragment = ImmunizationFragment.newInstance("a", "b");
            fragmentManager.beginTransaction()
                    .replace(R.id.contentFrame, immunizationFragment)
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.nav_hospital_talk) {

        } else if (id == R.id.nav_logout) {

        } else if (id == R.id.nav_prescription) {
            if (prescriptionFragment == null)
                prescriptionFragment = PrescriptionFragment.newInstance("a", "b");
            fragmentManager.beginTransaction()
                    .replace(R.id.contentFrame, prescriptionFragment)
                    .addToBackStack(null)
                    .commit();
        } else if (id == R.id.nav_nutrition) {

            if (nutritionFragment == null)
                nutritionFragment =new NutritionFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.contentFrame, nutritionFragment)
                    .addToBackStack(null)
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setupDbStuff() {

        setupPrescription();
        setupImmunization();



    }

    private void setupImmunization() {

    }

    private void setupPrescription() {

    }
    public String readAssetsFile() {
        if (this.jsonString != null)return this.jsonString;
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


}
