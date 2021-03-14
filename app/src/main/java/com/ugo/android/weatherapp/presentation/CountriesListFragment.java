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
        majorCitiesList.add(new MajorCities("Tokyo", "Japan", 35.689712, 139.692212));
        majorCitiesList.add(new MajorCities("Mumbai", "India", 18.966712, 72.833312));
        majorCitiesList.add(new MajorCities("New York", "United States", 40.694312, -73.924912));
        majorCitiesList.add(new MajorCities("Lagos", "Nigeria", 6.451012, 3.410102));
        majorCitiesList.add(new MajorCities("London", "United Kingdom", 51.507212, -0.127512));
        majorCitiesList.add(new MajorCities("Abuja", "Nigeria", 9.055612, 7.491412));
        majorCitiesList.add(new MajorCities("Dallas", "United States", 44.922212, -123.313112));
        majorCitiesList.add(new MajorCities("Beijing", "China", 39.905101, 116.391412));
        majorCitiesList.add(new MajorCities("Milan", "Italy", 45.466912, 9.191012));
        majorCitiesList.add(new MajorCities("Istanbul", "Turkey", 41.010101, 28.960312));
        majorCitiesList.add(new MajorCities("Moscow", "Russia", 55.755812, 37.617812));
        majorCitiesList.add(new MajorCities("Shenzhen", "China", 22.535101, 114.054101));
        majorCitiesList.add(new MajorCities("Paris", "France", 48.856612, 2.352212));
        majorCitiesList.add(new MajorCities("Rio de Janeiro", "Brazil", -22.908312, -43.196412));
        majorCitiesList.add(new MajorCities("New Delhi", "India", 28.710101, 77.210101));
        majorCitiesList.add(new MajorCities("Johannesburg", "South Africa", -26.204412, 28.041612));
        majorCitiesList.add(new MajorCities("Casablanca", "Morocco", 33.599212, -7.621012));
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
