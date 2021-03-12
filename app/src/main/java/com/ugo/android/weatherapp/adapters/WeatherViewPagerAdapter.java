package com.ugo.android.weatherapp.adapters;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import java.util.ArrayList;

public class WeatherViewPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<Fragment> fragmentArrayList;
    private final ArrayList<String> mFragmentTitleList;
    private Bundle fragmentBundle;

    public WeatherViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragmentArrayList, ArrayList<String> mFragmentTitleList) {
        super(fm);
        this.fragmentArrayList = fragmentArrayList;
        this.mFragmentTitleList = mFragmentTitleList;
    }

    @Override
    public Fragment getItem(int i) {
        fragmentArrayList.get(i).setArguments(this.fragmentBundle);
        return fragmentArrayList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}
