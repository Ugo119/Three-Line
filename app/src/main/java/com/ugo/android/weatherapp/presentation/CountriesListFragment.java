package com.ugo.android.weatherapp.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ugo.android.weatherapp.MainActivity;
import com.ugo.android.weatherapp.R;
import com.ugo.android.weatherapp.adapters.CountriesAdapter;
import com.ugo.android.weatherapp.interfaces.WeatherClickListener;
import com.ugo.android.weatherapp.models.MajorCities;

import java.util.ArrayList;

public class CountriesListFragment extends Fragment implements WeatherClickListener {
    private RecyclerView countriesRecyclerView;
    private CountriesAdapter countriesAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<MajorCities> majorCitiesList;
    private MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        mainActivity = (MainActivity) getActivity();
        getList();
        return inflater.inflate(R.layout.fragment_countries, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        countriesRecyclerView = view.findViewById(R.id.countriesRecyclerView);
        countriesAdapter = new CountriesAdapter(CountriesListFragment.this, majorCitiesList);
        mLayoutManager = new LinearLayoutManager(getActivity());
        countriesRecyclerView.setLayoutManager(mLayoutManager);
        countriesRecyclerView.setHasFixedSize(true);
        countriesRecyclerView.addItemDecoration(new DividerItemDecoration(countriesRecyclerView.getContext(), DividerItemDecoration.VERTICAL));

        countriesRecyclerView.setAdapter(countriesAdapter);

    }

    public void getList() {
        majorCitiesList = new ArrayList<>();
        majorCitiesList.add(new MajorCities("Tokyo", "Japan", 36, 140));
        majorCitiesList.add(new MajorCities("Mumbai", "India", 19, 73));
        majorCitiesList.add(new MajorCities("New York", "United States", 41, -74));
        majorCitiesList.add(new MajorCities("Lagos", "Nigeria", 6, 3));
        majorCitiesList.add(new MajorCities("London", "United Kingdom", 52, -1));
    }

    @Override
    public void onCityClicked(MajorCities majorCities) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("key", majorCities);
        WeatherViewPagerFragment weatherViewPagerFragment = new WeatherViewPagerFragment();
        weatherViewPagerFragment.setArguments(bundle);
        mainActivity.replaceFragment(weatherViewPagerFragment, true);
    }
}
