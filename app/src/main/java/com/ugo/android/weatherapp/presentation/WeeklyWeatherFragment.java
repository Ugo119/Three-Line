package com.ugo.android.weatherapp.presentation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ugo.android.weatherapp.R;
import com.ugo.android.weatherapp.adapters.CountriesAdapter;
import com.ugo.android.weatherapp.adapters.WeeklyWeatherAdapter;
import com.ugo.android.weatherapp.interfaces.WeatherClickListener;
import com.ugo.android.weatherapp.models.Daily;
import com.ugo.android.weatherapp.models.MajorCities;
import com.ugo.android.weatherapp.response.CurrentWeatherResponse;
import com.ugo.android.weatherapp.response.WeeklyWeatherResponse;

import java.util.ArrayList;

public class WeeklyWeatherFragment extends Fragment implements WeatherClickListener {
    static AppCompatTextView feelslikeTemperature, humidity, wind, uvIndex, cityName, temperature, icon,
            description;
    CurrentWeatherResponse currentWeatherResponse;
    MajorCities majorCities;
    private ArrayList<MajorCities> majorCitiesList;
    private ArrayList<Daily> dailyList;
    private WeeklyWeatherAdapter weeklyWeatherAdapter;
    private RecyclerView weeklyweatherRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weekly_weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    public void initView(View view) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            currentWeatherResponse = (CurrentWeatherResponse) bundle.getSerializable("current");
            majorCities = (MajorCities) bundle.getSerializable("city");
        }

        weeklyweatherRecyclerView = view.findViewById(R.id.weeklyweatherRecyclerView);
        weeklyWeatherAdapter = new WeeklyWeatherAdapter(WeeklyWeatherFragment.this, majorCitiesList, dailyList);
        mLayoutManager = new LinearLayoutManager(getActivity());
        weeklyweatherRecyclerView.setLayoutManager(mLayoutManager);
        weeklyweatherRecyclerView.setHasFixedSize(true);
        weeklyweatherRecyclerView.addItemDecoration(new DividerItemDecoration(weeklyweatherRecyclerView.getContext(), DividerItemDecoration.VERTICAL));
        weeklyweatherRecyclerView.setAdapter(weeklyWeatherAdapter);

        feelslikeTemperature = view.findViewById(R.id.feelslikeTemperature);
        humidity = view.findViewById(R.id.humidity);
        wind = view.findViewById(R.id.wind);
        uvIndex = view.findViewById(R.id.uvIndex);
        temperature = view.findViewById(R.id.temperature);
        cityName = view.findViewById(R.id.cityCountry);
        icon = view.findViewById(R.id.icon);
        description = view.findViewById(R.id.description);

        //cityName.setText(majorCities.getCityName());

//        if (currentWeatherResponse != null) {
//            Log.e("TAG", "initView_TEMP: " + currentWeatherResponse.getMain().getTemp());
//            temperature.setText(String.valueOf(currentWeatherResponse.getMain().getTemp()));
//            humidity.setText(String.valueOf(currentWeatherResponse.getMain().getHumidity()));
//            wind.setText(String.valueOf(currentWeatherResponse.getWind().getSpeed()));
//            uvIndex.setText(String.valueOf(currentWeatherResponse.getClouds().getAll()));
//        }

    }

    public static void displayWeatherData(AppCompatTextView temperature,
                                          AppCompatTextView icon, AppCompatTextView description, WeeklyWeatherResponse weeklyWeatherResponse,
                                          CurrentWeatherResponse currentWeatherResponse) {

//        feelslikeTemperature.setText(String.valueOf((int) currentWeatherResponse.getMain().getFeels_like()) + "\u2103");
//        humidity.setText(String.valueOf(currentWeatherResponse.getMain().getHumidity()));
//        wind.setText(String.valueOf(currentWeatherResponse.getWind().getSpeed()) + "km/h");
//        uvIndex.setText(String.valueOf(currentWeatherResponse.getClouds().getAll()));
        temperature.setText(String.valueOf((int)currentWeatherResponse.getMain().getTemp()) + "\u2103");
        icon.setText(currentWeatherResponse.getWeather().get(0).getIcon());
        description.setText(currentWeatherResponse.getWeather().get(0).getDescription());


    }

    /*public static void displayWeatherData(AppCompatTextView feelslikeTemperature, AppCompatTextView humidity,
                                          AppCompatTextView wind, AppCompatTextView uvIndex, AppCompatTextView temperature,
                                          AppCompatTextView icon, AppCompatTextView description, CurrentWeatherResponse currentWeatherResponse) {

        feelslikeTemperature.setText(String.valueOf((int) currentWeatherResponse.getMain().getFeels_like()) + "\u2103");
        humidity.setText(String.valueOf(currentWeatherResponse.getMain().getHumidity()));
        wind.setText(String.valueOf(currentWeatherResponse.getWind().getSpeed()) + "km/h");
        uvIndex.setText(String.valueOf(currentWeatherResponse.getClouds().getAll()));
        temperature.setText(String.valueOf((int)currentWeatherResponse.getMain().getTemp()) + "\u2103");
        icon.setText(currentWeatherResponse.getWeather().get(0).getIcon());
        description.setText(currentWeatherResponse.getWeather().get(0).getDescription());


    }*/

    @Override
    public void onCityClicked(MajorCities majorCities) {

    }
}
