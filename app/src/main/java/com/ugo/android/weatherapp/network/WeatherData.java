package com.ugo.android.weatherapp.network;

import android.os.Bundle;
import android.util.Log;

import com.ugo.android.weatherapp.R;
import com.ugo.android.weatherapp.models.Weather;
import com.ugo.android.weatherapp.presentation.WeatherViewPagerFragment;
import com.ugo.android.weatherapp.response.CurrentWeatherResponse;

import org.jetbrains.annotations.NotNull;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WeatherData {
    private static Retrofit retrofit;
    public Weather weather = new Weather();
    private WeatherViewPagerFragment weatherViewPagerFragment = new WeatherViewPagerFragment();
    public static DisposableObserver<CurrentWeatherResponse> request;


    /*public static void fetchCurrentWeatherData(double lat, double lon, String apikey) {
        retrofit = RetrofitFactory.getRetrofit();
        ApiService apiService = retrofit.create(ApiService.class);

        Call<CurrentWeatherResponse> call = apiService.getCurrentWeatherResponse(lat, lon, apikey);
        call.enqueue(new Callback<CurrentWeatherResponse>() {
            @Override
            public void onResponse(Call<CurrentWeatherResponse> call, Response<CurrentWeatherResponse> response) {
                if (response != null) {
                    WeatherViewPagerFragment weatherViewPagerFragment = new WeatherViewPagerFragment();
                    weatherViewPagerFragment.onCurrentWeatherDataRetrieved(call);
                }

            }

            @Override
            public void onFailure(Call<CurrentWeatherResponse> call, Throwable t) {

            }
        });

    }*/

    /*public static void fetchCurrentWeatherData(double lat, double lon, String apikey) {

        retrofit = RetrofitFactory.getRetrofit();
        ApiService apiService = retrofit.create(ApiService.class);

        request = apiService.getCurrentWeatherResponse(lat, lon, apikey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<CurrentWeatherResponse>() {
                    @Override
                    public void onNext(@NotNull CurrentWeatherResponse response) {
                        if (response.getCod() == 200) {
                            WeatherViewPagerFragment weatherViewPagerFragment = new WeatherViewPagerFragment();
                            weatherViewPagerFragment.onCurrentWeatherDataRetrieved(response);
                        }
//                        if (response != null) {
//
//                        }

                    }
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }*/

    public static void fetchWeeklyWeatherData() {

    }
}
