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

import com.ugo.android.weatherapp.MainActivity;
import com.ugo.android.weatherapp.R;
import com.ugo.android.weatherapp.models.City;
import com.ugo.android.weatherapp.models.MajorCities;
import com.ugo.android.weatherapp.response.CurrentWeatherResponse;

public class CurrentDayWeatherFragment extends Fragment {
    static AppCompatTextView feelslikeTemperature, humidity, wind, uvIndex, cityName, temperature, icon,
    description;
    CurrentWeatherResponse currentWeatherResponse;
    MajorCities majorCities;
    private City city;
    MainActivity mainActivity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_current_day_weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    public void initView(View view) {
        mainActivity = (MainActivity) getActivity();
        city = mainActivity.city;
        Bundle bundle = getArguments();
        if (bundle != null) {
            currentWeatherResponse = (CurrentWeatherResponse) bundle.getSerializable("current");
            majorCities = (MajorCities) bundle.getSerializable("key");
            city.setTemperature(currentWeatherResponse.getMain().getTemp());
        }

        feelslikeTemperature = view.findViewById(R.id.feelslikeTemperature);
        humidity = view.findViewById(R.id.humidity);
        wind = view.findViewById(R.id.wind);
        uvIndex = view.findViewById(R.id.uvIndex);
        temperature = view.findViewById(R.id.temperature);
        cityName = view.findViewById(R.id.cityName);
        icon = view.findViewById(R.id.icon);
        description = view.findViewById(R.id.description);

        cityName.setText(city.getCityName());

    }

    public static void displayWeatherData(AppCompatTextView feelslikeTemperature, AppCompatTextView humidity,
                                   AppCompatTextView wind, AppCompatTextView uvIndex, AppCompatTextView temperature,
                                          AppCompatTextView icon, AppCompatTextView description, CurrentWeatherResponse currentWeatherResponse) {

        feelslikeTemperature.setText(String.valueOf((int) currentWeatherResponse.getMain().getFeels_like()) + "\u2103");
        humidity.setText(String.valueOf(currentWeatherResponse.getMain().getHumidity()));
        wind.setText(String.valueOf(currentWeatherResponse.getWind().getSpeed()) + "km/h");
        uvIndex.setText(String.valueOf(currentWeatherResponse.getClouds().getAll()));
        temperature.setText(String.valueOf((int)currentWeatherResponse.getMain().getTemp()) + "\u2103");

        icon.setText(currentWeatherResponse.getWeather().get(0).getIcon());
        description.setText(currentWeatherResponse.getWeather().get(0).getDescription());


    }

}
