package com.ugo.android.weatherapp.presentation;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ugo.android.weatherapp.MainActivity;
import com.ugo.android.weatherapp.R;
import com.ugo.android.weatherapp.adapters.WeeklyWeatherAdapter;
import com.ugo.android.weatherapp.interfaces.WeatherClickListener;
import com.ugo.android.weatherapp.models.City;
import com.ugo.android.weatherapp.models.Daily;
import com.ugo.android.weatherapp.models.MajorCities;
import com.ugo.android.weatherapp.network.ApiService;
import com.ugo.android.weatherapp.network.RetrofitFactory;
import com.ugo.android.weatherapp.response.CurrentWeatherResponse;
import com.ugo.android.weatherapp.response.WeeklyWeatherResponse;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class WeeklyWeatherFragment extends Fragment implements WeatherClickListener {
    static AppCompatTextView cityName, temperature, icon,
            description;
    CurrentWeatherResponse currentWeatherResponse;
    MajorCities majorCities;
    private ArrayList<MajorCities> majorCitiesList;
    private ArrayList<Daily> dailyList;
    private WeeklyWeatherAdapter weeklyWeatherAdapter;
    private RecyclerView weeklyweatherRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    WeeklyWeatherResponse weeklyWeatherResponse;
    MainActivity mainActivity;
    City city;
    String cityTemperature;
    private double lat;
    private double lon;
    private static final String UNIT = "metric";
    private static final String EXCLUDE = "minutely,hourly";
    private String apikey;
    private Retrofit retrofit;
    public static DisposableObserver<WeeklyWeatherResponse> weekRequest;
    Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        dailyList = new ArrayList<>();
        mainActivity = (MainActivity) getActivity();
        city = mainActivity.city;
        lat = city.getLat();
        lon = city.getLon();
        apikey = RetrofitFactory.API_KEY;
        bundle = getArguments();



        return inflater.inflate(R.layout.fragment_weekly_weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        fetchWeeklyWeatherData(lat, lon, apikey);
    }

    public void initView(View view) {
        weeklyweatherRecyclerView = view.findViewById(R.id.weekRecyclerView);
        weeklyWeatherAdapter = new WeeklyWeatherAdapter(requireContext(), weeklyWeatherResponse, dailyList);
        mLayoutManager = new LinearLayoutManager(getActivity());
        weeklyweatherRecyclerView.setLayoutManager(mLayoutManager);
        weeklyweatherRecyclerView.setHasFixedSize(true);
        weeklyweatherRecyclerView.addItemDecoration(new DividerItemDecoration(weeklyweatherRecyclerView.getContext(), DividerItemDecoration.VERTICAL));
        weeklyweatherRecyclerView.setAdapter(weeklyWeatherAdapter);
        Log.e("TAG", "initView: Recyclerview created: " + weeklyweatherRecyclerView);


        temperature = view.findViewById(R.id.temperature);
        cityName = view.findViewById(R.id.cityName);
        icon = view.findViewById(R.id.icon);
        description = view.findViewById(R.id.description);

        cityName.setText(city.getCityName());
//        cityTemperature = String.valueOf(currentWeatherResponse.getMain().getTemp());
//        temperature.setText(cityTemperature);

        //initializeRecyclerView(weeklyweatherRecyclerView);
        //fetchWeeklyWeatherData(lat, lon, apikey);
    }

    private void initializeRecyclerView(RecyclerView recyclerView) {
        fetchWeeklyWeatherData(lat, lon, apikey);
        recyclerView.setLayoutManager(new LinearLayoutManager(mainActivity));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        weeklyWeatherAdapter = new WeeklyWeatherAdapter(requireContext(), weeklyWeatherResponse, dailyList);
        recyclerView.setAdapter(weeklyWeatherAdapter);
    }

    public void displayWeatherData(WeeklyWeatherResponse weeklyWeatherResponse) {
        Log.e("TAG", "displayWeatherDataMISSING: " + weeklyWeatherResponse.getTimezone());

        if (bundle != null) {
            currentWeatherResponse = (CurrentWeatherResponse) bundle.getSerializable("current");
            Log.e("TAG", "displayWeatherDataMMM: " + currentWeatherResponse.getMain().getTemp());

            //weeklyWeatherResponse = (WeeklyWeatherResponse) bundle.getSerializable("weekly");
            majorCities = (MajorCities) bundle.getSerializable("key");

            cityTemperature = String.valueOf(currentWeatherResponse.getMain().getTemp());

        }


        temperature.setText(String.valueOf(Math.round(weeklyWeatherResponse.getCurrent().getTemp())) + "\u2103");


        Log.e("TAG", "initializeRecyclerView: " + dailyList.get(0).getClouds());
    }

    public void fetchWeeklyWeatherData(double lat, double lon, String apikey) {

        retrofit = RetrofitFactory.getRetrofit();
        ApiService apiService = retrofit.create(ApiService.class);

        weekRequest = apiService.getWeeklyWeatherResponse(lat, lon, EXCLUDE, apikey, UNIT)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<WeeklyWeatherResponse>() {
                    @Override
                    public void onNext(@NotNull WeeklyWeatherResponse response) {
                        Log.e("TAG", "onNext: AAAAH" + response.getCurrent().getTemp() );

                        onWeeklyWeatherDataRetrieved(response);

                        displayWeatherData(response);



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

    public void onWeeklyWeatherDataRetrieved(WeeklyWeatherResponse weeklyWeatherResponse) {
        this.weeklyWeatherResponse = weeklyWeatherResponse;
        dailyList.clear();
        dailyList.addAll(weeklyWeatherResponse.getDaily());

        //dailyList = weeklyWeatherResponse.getDaily();

        weeklyWeatherAdapter.setItems(dailyList);
        Log.e("TAG", "onWeeklyWeatherDataRetrieved_MIZZIN: " + dailyList.get(0).getPressure());

    }

    @Override
    public void onCityClicked(MajorCities majorCities) {

    }
}
