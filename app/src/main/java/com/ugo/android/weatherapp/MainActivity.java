package com.ugo.android.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.ugo.android.weatherapp.models.City;
import com.ugo.android.weatherapp.presentation.CountriesListFragment;

public class MainActivity extends AppCompatActivity {
    FragmentManager fragmentManager;
    CountriesListFragment countriesListFragment;
    public City city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countriesListFragment = new CountriesListFragment();
        city = new City();
        replaceFragment(countriesListFragment, true);
//        fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.container, countriesListFragment)
//                .commit();
    }

    public void replaceFragment(Fragment fragment, boolean addToBackstack) {
        String fragment_name = fragment.getClass().getSimpleName();
        Fragment frag = getSupportFragmentManager().findFragmentByTag(fragment_name);
        if (frag != null && frag.isVisible())
            return;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
        if (addToBackstack) {
            transaction.addToBackStack(fragment_name);
        }
        transaction.replace(R.id.container, fragment, fragment_name);
        transaction.commit();
//        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
//            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
//        }
    }

}