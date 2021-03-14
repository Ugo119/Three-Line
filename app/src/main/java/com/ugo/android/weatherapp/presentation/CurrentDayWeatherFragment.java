package com.ugo.android.weatherapp.presentation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.ugo.android.weatherapp.MainActivity;
import com.ugo.android.weatherapp.R;
import com.ugo.android.weatherapp.models.City;
import com.ugo.android.weatherapp.models.MajorCities;
import com.ugo.android.weatherapp.response.CurrentWeatherResponse;
import com.ugo.android.weatherapp.response.UviResponse;

public class CurrentDayWeatherFragment extends Fragment {
    static AppCompatTextView feelslikeTemperature, humidity, wind, uvIndex, cityName, temperature,
    description;
    static AppCompatImageView icon;
    CurrentWeatherResponse currentWeatherResponse;
    UviResponse uviResponse;
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
            uviResponse = (UviResponse) bundle.getSerializable("uvi");
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
                                   AppCompatTextView wind, AppCompatTextView temperature,
                                          AppCompatImageView icon, AppCompatTextView description, CurrentWeatherResponse currentWeatherResponse) {

        feelslikeTemperature.setText(String.valueOf((int) currentWeatherResponse.getMain().getFeels_like()) + "\u2103");
        humidity.setText(String.valueOf(currentWeatherResponse.getMain().getHumidity()));
        wind.setText(String.valueOf(currentWeatherResponse.getWind().getSpeed()) + "km/h");

        temperature.setText(String.valueOf((int)currentWeatherResponse.getMain().getTemp()) + "\u2103");

        String iconText = currentWeatherResponse.getWeather().get(0).getIcon();

        switch (iconText) {
            case "01d":
                icon.setImageResource(R.drawable._01d);
                break;
            case "01n":
                icon.setImageResource(R.drawable._01n);
                break;
            case "02d":
                icon.setImageResource(R.drawable._02d);
                break;
            case "02n":
                icon.setImageResource(R.drawable._02n);
            case "03n":
                icon.setImageResource(R.drawable._03n);
                break;
            case "03d":
                icon.setImageResource(R.drawable._03d);
                break;
            case "04d":
                icon.setImageResource(R.drawable._04d);
                break;
            case "04n":
                icon.setImageResource(R.drawable._04n);
                break;
            case "09d":
                icon.setImageResource(R.drawable._09d);
                break;
            case "09n":
                icon.setImageResource(R.drawable._09n);
                break;
            case "10d":
                icon.setImageResource(R.drawable._10d);
                break;
            case "10n":
                icon.setImageResource(R.drawable._10n);
            case "11d":
                icon.setImageResource(R.drawable._11d);
                break;
            case "11n":
                icon.setImageResource(R.drawable._11n);
                break;
            case "13d":
                icon.setImageResource(R.drawable._13d);
                break;
            case "13n":
                icon.setImageResource(R.drawable._13n);
                break;
            case "50d":
                icon.setImageResource(R.drawable._50d);
                break;
            case "50n":
                icon.setImageResource(R.drawable._50n);
                break;
        }

        description.setText(currentWeatherResponse.getWeather().get(0).getDescription());


    }

    public static void displayUvidata(AppCompatTextView uvIndex, UviResponse uviResponse) {
        uvIndex.setText(String.valueOf(uviResponse.getValue()));
    }

}
