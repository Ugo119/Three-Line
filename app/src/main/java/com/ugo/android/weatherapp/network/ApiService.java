package com.ugo.android.weatherapp.network;


import com.ugo.android.weatherapp.response.CurrentWeatherResponse;
import com.ugo.android.weatherapp.response.WeeklyWeatherResponse;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {
    @GET("weather")
    Observable<CurrentWeatherResponse> getCurrentWeatherResponse(@Query("lat") int lat, @Query("lon") int lon,
                                                                 @Query("appid") String apikey,
                                                                 @Query("units") String metric);

    @GET("onecall")
    Observable<WeeklyWeatherResponse> getWeeklyWeatherResponse(@Query("lat") double lat, @Query("lon") double lon,
                                                         @Query("exclude") String exclude,
                                                         @Query("appid") String apikey,
                                                         @Query("units") String metric);
}
