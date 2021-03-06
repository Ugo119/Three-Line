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

import com.google.android.material.tabs.TabLayout;
import com.ugo.android.weatherapp.MainActivity;
import com.ugo.android.weatherapp.R;
import com.ugo.android.weatherapp.adapters.WeatherViewPagerAdapter;
import com.ugo.android.weatherapp.models.City;
import com.ugo.android.weatherapp.models.Daily;
import com.ugo.android.weatherapp.models.Main;
import com.ugo.android.weatherapp.models.MajorCities;
import com.ugo.android.weatherapp.network.ApiService;
import com.ugo.android.weatherapp.network.RetrofitFactory;
import com.ugo.android.weatherapp.response.CurrentWeatherResponse;
import com.ugo.android.weatherapp.response.UviResponse;
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
    private int lat;
    private int lon;
    private String apikey;
    private CurrentWeatherResponse currentWeatherResponse;
    private UviResponse uviResponse;
    private WeeklyWeatherResponse weeklyWeatherResponse;
    private Bundle bundle;
    private Main main;
    private static Retrofit retrofit;
    public static DisposableObserver<CurrentWeatherResponse> request;
    public static DisposableObserver<UviResponse> uvirequest;
    public static DisposableObserver<WeeklyWeatherResponse> weekRequest;
    private ArrayList<Daily> dailyList;
    private City city;
    private static final String UNIT = "metric";
    private static final int TIME = 1586468027;
    private double uviValue = 0.00;
    private int[] tabIcons = {
            R.drawable.day_tab_icon,
            R.drawable.week_tab_icon
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dailyList = new ArrayList<>();

        return inflater.inflate(R.layout.fragment_current_weekly_weather_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainActivity = (MainActivity) getActivity();
        city = mainActivity.city;
        apikey = RetrofitFactory.API_KEY;
        bundle = getArguments();

        if (bundle != null) {
            majorCities = (MajorCities) bundle.getSerializable("key");
            lat = (int) Math.round(majorCities.getLatitude());
            lon = (int) Math.round(majorCities.getLongitude());
            city.setLat(majorCities.getLatitude());
            city.setLon(majorCities.getLongitude());

            city.setCityName(majorCities.getCityName());
        }

        fetchCurrentWeatherData(lat, lon, apikey);
        fetchUviData(lat, lon, apikey);
        initView(view);
        setClickListener();
        setupTabicons();

    }

    private void initView(View view) {

        editable = false;
        viewPager = view.findViewById(R.id.viewpager);
        tabLayout = view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        fragmentArrayList = new ArrayList<>();
        mFragmentTitleList = new ArrayList<>();

        bundle.putSerializable("key", majorCities);
        CurrentDayWeatherFragment currentDayWeatherFragment = new CurrentDayWeatherFragment();
        currentDayWeatherFragment.setArguments(bundle);
        fragmentArrayList.add(currentDayWeatherFragment);
        mFragmentTitleList.add(getActivity().getResources().getString(R.string.today));

        WeeklyWeatherFragment weeklyWeatherFragment = new WeeklyWeatherFragment();
        weeklyWeatherFragment.setArguments(bundle);
        fragmentArrayList.add(weeklyWeatherFragment);
        mFragmentTitleList.add(getActivity().getResources().getString(R.string.weekly));

        weatherViewPagerAdapter = new WeatherViewPagerAdapter(getChildFragmentManager(), fragmentArrayList, mFragmentTitleList);
        viewPager.setAdapter(weatherViewPagerAdapter);


    }

    private void setupTabicons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
    }

    public void sendDataToNextPage() {
        bundle.putSerializable("current", currentWeatherResponse);
        bundle.putSerializable("uvi", uviResponse);
        CurrentDayWeatherFragment currentDayWeatherFragment = new CurrentDayWeatherFragment();
        currentDayWeatherFragment.setArguments(bundle);

        WeeklyWeatherFragment weeklyWeatherFragment = new WeeklyWeatherFragment();
        weeklyWeatherFragment.setArguments(bundle);
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

    public void onUviRetrieved(UviResponse uviResponse) {
        this.uviResponse = uviResponse;
        uviValue = uviResponse.getValue();

    }

    public void onWeeklyWeatherDataRetrieved(WeeklyWeatherResponse weeklyWeatherResponse) {
        this.weeklyWeatherResponse = weeklyWeatherResponse;
        dailyList.clear();
        dailyList.addAll(weeklyWeatherResponse.getDaily());

    }

    public void fetchCurrentWeatherData(int lat, int lon, String apikey) {

        retrofit = RetrofitFactory.getRetrofit();
        ApiService apiService = retrofit.create(ApiService.class);

        request = apiService.getCurrentWeatherResponse(lat, lon, apikey, UNIT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<CurrentWeatherResponse>() {
                    @Override
                    public void onNext(@NotNull CurrentWeatherResponse response) {
                        if (response.getCod() == 200) {
                            city.setTemperature(response.getMain().getTemp());
                            AppCompatTextView feelslikeTemperature, humidity, wind, temperature,
                                     description ;
                            AppCompatImageView icon;
                            feelslikeTemperature = CurrentDayWeatherFragment.feelslikeTemperature;
                            humidity = CurrentDayWeatherFragment.humidity;
                            wind = CurrentDayWeatherFragment.wind;
                            //uvIndex = CurrentDayWeatherFragment.uvIndex;
                            temperature = CurrentDayWeatherFragment.temperature;
                            icon = CurrentDayWeatherFragment.icon;
                            description = CurrentDayWeatherFragment.description;
                            onCurrentWeatherDataRetrieved(response);
                            sendDataToNextPage();

                            CurrentDayWeatherFragment.displayWeatherData(feelslikeTemperature, humidity,
                                    wind, temperature, icon, description, response);
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

    public void fetchUviData(int lat, int lon, String apikey) {

        retrofit = RetrofitFactory.getRetrofit();
        ApiService apiService = retrofit.create(ApiService.class);

        uvirequest = apiService.getUviResponse(lat, lon, apikey, UNIT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<UviResponse>() {
                    @Override
                    public void onNext(@NotNull UviResponse response) {


                            AppCompatTextView uvIndex;

                            uvIndex = CurrentDayWeatherFragment.uvIndex;

                            onUviRetrieved(response);
                            sendDataToNextPage();

                            CurrentDayWeatherFragment.displayUvidata(uvIndex, response);


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
