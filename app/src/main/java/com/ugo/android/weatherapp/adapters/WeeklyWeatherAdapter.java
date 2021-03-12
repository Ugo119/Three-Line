package com.ugo.android.weatherapp.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.ugo.android.weatherapp.R;
import com.ugo.android.weatherapp.interfaces.WeatherClickListener;
import com.ugo.android.weatherapp.models.Daily;
import com.ugo.android.weatherapp.models.MajorCities;
import com.ugo.android.weatherapp.response.WeeklyWeatherResponse;

import java.util.ArrayList;

public class WeeklyWeatherAdapter extends RecyclerView.Adapter<WeeklyWeatherAdapter.WeeklyWeatherViewHolder> {
    private ArrayList<MajorCities> majorCitiesList;
    private WeatherClickListener weatherClickListener;
    private ArrayList<Daily> dailyList;
    private WeeklyWeatherResponse weeklyWeatherResponse;


    public WeeklyWeatherAdapter(WeeklyWeatherResponse weeklyWeatherResponse, ArrayList<MajorCities> majorCitiesList, ArrayList<Daily> dailyList) {
        this.majorCitiesList = majorCitiesList;
        this.weeklyWeatherResponse = weeklyWeatherResponse;
        this.dailyList = dailyList;
    }

    @NonNull
    @Override
    public WeeklyWeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.countries_list_item, parent, false);

        return new WeeklyWeatherViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklyWeatherViewHolder holder, int position) {
        MajorCities majorCities = majorCitiesList.get(position);
        Daily daily = weeklyWeatherResponse.getDaily().get(position);
        Log.e("TAG", "onBindViewHolder: " + majorCities.getCityName());
        holder.sunrise.setText(String.valueOf(daily.getSunrise()));
        holder.temperature.setText(String.format("%s, %s", daily.getTemp().getMin(), daily.getTemp().getMax()));
        holder.description.setText(daily.getWeather().get(0).getDescription());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (weatherClickListener != null) {
//                    weatherClickListener.onCityClicked(majorCities);
//
//                    Log.e("TAG", "onBindViewHolder_CLICK: " + majorCities.getCityName());
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return majorCitiesList !=null?majorCitiesList.size():0;
    }

    public class WeeklyWeatherViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView sunrise, temperature, description;
        public WeeklyWeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            sunrise = itemView.findViewById(R.id.sunrise);
            temperature = itemView.findViewById(R.id.temperature);
            description = itemView.findViewById(R.id.description);
        }
    }
}
