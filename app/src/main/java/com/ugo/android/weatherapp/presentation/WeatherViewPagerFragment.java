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

import com.google.android.material.tabs.TabLayout;
import com.ugo.android.weatherapp.MainActivity;
import com.ugo.android.weatherapp.R;
import com.ugo.android.weatherapp.adapters.WeatherViewPagerAdapter;
import com.ugo.android.weatherapp.models.Main;
import com.ugo.android.weatherapp.models.MajorCities;
import com.ugo.android.weatherapp.network.ApiService;
import com.ugo.android.weatherapp.network.RetrofitFactory;
import com.ugo.android.weatherapp.response.CurrentWeatherResponse;
import com.ugo.android.weatherapp.response.WeeklyWeatherResponse;
import com.ugo.android.weatherapp.utils.CustomViewPager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class WeatherViewPagerFragment extends Fragment implements View.OnClickListener {

    private MainActivity mainActivity;
    private CustomViewPager viewPager;
    private TabLayout tabLayout;
    private WeatherViewPagerAdapter weatherViewPagerAdapter;
    private ArrayList<Fragment> fragmentArrayList;
    private ArrayList<String> mFragmentTitleList;
    private Boolean editable;
    private MajorCities majorCities;
    private double lat;
    private double lon;
    private String apikey;
    private CurrentWeatherResponse currentWeatherResponse;
    private Bundle bundle;
    private Main main;
    private static Retrofit retrofit;
    public static DisposableObserver<CurrentWeatherResponse> request;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_current_weekly_weather_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        apikey = RetrofitFactory.API_KEY;
        bundle = getArguments();
        if (bundle != null) {
            majorCities = (MajorCities) bundle.getSerializable("key");
            lat = majorCities.getLatitude();
            lon = majorCities.getLongitude();
        }
        fetchCurrentWeatherData(lat, lon, apikey);
        fetchWeeklyWeatherData(lat,lon,apikey);
        initView(view);
        setClickListener();




    }

    private void initView(View view) {

        editable = false;
        viewPager = view.findViewById(R.id.viewpager);
        tabLayout = view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        fragmentArrayList = new ArrayList<>();
        mFragmentTitleList = new ArrayList<>();


        Bundle cityBundle = new Bundle();
        cityBundle.putSerializable("city", majorCities);
        CurrentDayWeatherFragment currentDayWeatherFragment = new CurrentDayWeatherFragment();
        currentDayWeatherFragment.setArguments(cityBundle);
        fragmentArrayList.add(currentDayWeatherFragment);
        mFragmentTitleList.add(getActivity().getResources().getString(R.string.today));

        WeeklyWeatherFragment weeklyWeatherFragment = new WeeklyWeatherFragment();
        fragmentArrayList.add(weeklyWeatherFragment);
        mFragmentTitleList.add(getActivity().getResources().getString(R.string.weekly));

        weatherViewPagerAdapter = new WeatherViewPagerAdapter(getChildFragmentManager(), fragmentArrayList, mFragmentTitleList);
        viewPager.setAdapter(weatherViewPagerAdapter);


    }

    public void sendDataToNextPage() {
        Log.e("TAG", "initView_ADAP_RESP: " + currentWeatherResponse);
        bundle.putSerializable("current", currentWeatherResponse);

        CurrentDayWeatherFragment currentDayWeatherFragment = new CurrentDayWeatherFragment();
        currentDayWeatherFragment.setArguments(bundle);
    }

    private void setClickListener() {
    }



    @Override
    public void onClick(View v) {

    }

    public void onCurrentWeatherDataRetrieved(CurrentWeatherResponse currentWeatherResponse) {
        this.currentWeatherResponse = currentWeatherResponse;
        main = currentWeatherResponse.getMain();

    }

    public void getCurrentWaetherData() {

    }


    public void fetchCurrentWeatherData(double lat, double lon, String apikey) {

        retrofit = RetrofitFactory.getRetrofit();
        ApiService apiService = retrofit.create(ApiService.class);
        String unit = "metric";

        request = apiService.getCurrentWeatherResponse(lat, lon, apikey, unit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<CurrentWeatherResponse>() {
                    @Override
                    public void onNext(@NotNull CurrentWeatherResponse response) {
                        if (response.getCod() == 200) {

                            AppCompatTextView feelslikeTemperature, humidity, wind, uvIndex, temperature,
                                    icon, description ;
                            feelslikeTemperature = CurrentDayWeatherFragment.feelslikeTemperature;
                            humidity = CurrentDayWeatherFragment.humidity;
                            wind = CurrentDayWeatherFragment.wind;
                            uvIndex = CurrentDayWeatherFragment.uvIndex;
                            temperature = CurrentDayWeatherFragment.temperature;
                            icon = CurrentDayWeatherFragment.icon;
                            description = CurrentDayWeatherFragment.description;
                            onCurrentWeatherDataRetrieved(response);
                            sendDataToNextPage();
                            Log.e("TAG", "onNext: " + response.getMain().getFeels_like());
                            CurrentDayWeatherFragment.displayWeatherData(feelslikeTemperature, humidity,
                                    wind, uvIndex, temperature, icon, description, response);
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

    }

    public void fetchWeeklyWeatherData(double lat, double lon, String apikey) {

        retrofit = RetrofitFactory.getRetrofit();
        ApiService apiService = retrofit.create(ApiService.class);
        String unit = "metric";

        request = apiService.getWeeklyWeatherResponse(lat, lon, apikey, unit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<WeeklyWeatherResponse>() {
                    @Override
                    public void onNext(@NotNull WeeklyWeatherResponse response) {
                        if (response.getCod() == 200) {

                            AppCompatTextView feelslikeTemperature, humidity, wind, uvIndex, temperature,
                                    icon, description ;
                            feelslikeTemperature = CurrentDayWeatherFragment.feelslikeTemperature;
                            humidity = CurrentDayWeatherFragment.humidity;
                            wind = CurrentDayWeatherFragment.wind;
                            uvIndex = CurrentDayWeatherFragment.uvIndex;
                            temperature = CurrentDayWeatherFragment.temperature;
                            icon = CurrentDayWeatherFragment.icon;
                            description = CurrentDayWeatherFragment.description;
                            onCurrentWeatherDataRetrieved(response);
                            sendDataToNextPage();
                            Log.e("TAG", "onNext: " + response.getMain().getFeels_like());
                            CurrentDayWeatherFragment.displayWeatherData(feelslikeTemperature, humidity,
                                    wind, uvIndex, temperature, icon, description, response);
                        }

                    }
                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


}
