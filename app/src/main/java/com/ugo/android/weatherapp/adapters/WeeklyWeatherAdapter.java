package com.ugo.android.weatherapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.ugo.android.weatherapp.R;
import com.ugo.android.weatherapp.interfaces.WeatherClickListener;
import com.ugo.android.weatherapp.models.Daily;
import com.ugo.android.weatherapp.models.MajorCities;
import com.ugo.android.weatherapp.response.WeeklyWeatherResponse;
import com.ugo.android.weatherapp.utils.DateUtil;

import java.util.ArrayList;
import java.util.Date;

public class WeeklyWeatherAdapter extends RecyclerView.Adapter<WeeklyWeatherAdapter.WeeklyWeatherViewHolder> {
    private ArrayList<MajorCities> majorCitiesList;
    private ArrayList<Daily> dailyList;
    private WeeklyWeatherResponse weeklyWeatherResponse;
    private Context context;


    public WeeklyWeatherAdapter(WeeklyWeatherResponse weeklyWeatherResponse, ArrayList<MajorCities> majorCitiesList, ArrayList<Daily> dailyList) {
        this.majorCitiesList = majorCitiesList;
        this.weeklyWeatherResponse = weeklyWeatherResponse;
        this.dailyList = dailyList;
    }

    public WeeklyWeatherAdapter(Context context, WeeklyWeatherResponse weeklyWeatherResponse, ArrayList<Daily> dailyList) {
        this.context = context;
        this.weeklyWeatherResponse = weeklyWeatherResponse;
        this.dailyList = dailyList;
    }

    public void setItems(ArrayList<Daily> dailyList) {
        this.dailyList = dailyList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WeeklyWeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weekly_weather_list_item, parent, false);
        return new WeeklyWeatherViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklyWeatherViewHolder holder, int position) {
        String iconText = dailyList.get(position).getWeather().get(0).getIcon();
        switch (iconText) {
            case "01d":
                holder.icon.setImageResource(R.drawable._01d);
                break;
            case "01n":
                holder.icon.setImageResource(R.drawable._01n);
                break;
            case "02d":
                holder.icon.setImageResource(R.drawable._02d);
                break;
            case "02n":
                holder.icon.setImageResource(R.drawable._02n);
            case "03n":
                holder.icon.setImageResource(R.drawable._03n);
                break;
            case "03d":
                holder.icon.setImageResource(R.drawable._03d);
                break;
            case "04d":
                holder.icon.setImageResource(R.drawable._04d);
                break;
            case "04n":
                holder.icon.setImageResource(R.drawable._04n);
                break;
            case "09d":
                holder.icon.setImageResource(R.drawable._09d);
                break;
            case "09n":
                holder.icon.setImageResource(R.drawable._09n);
                break;
            case "10d":
                holder.icon.setImageResource(R.drawable._10d);
                break;
            case "10n":
                holder.icon.setImageResource(R.drawable._10n);
            case "11d":
                holder.icon.setImageResource(R.drawable._11d);
                break;
            case "11n":
                holder.icon.setImageResource(R.drawable._11n);
                break;
            case "13d":
                holder.icon.setImageResource(R.drawable._13d);
                break;
            case "13n":
                holder.icon.setImageResource(R.drawable._13n);
                break;
            case "50d":
                holder.icon.setImageResource(R.drawable._50d);
                break;
            case "50n":
                holder.icon.setImageResource(R.drawable._50n);
                break;
        }
        String dayForSunrise = DateUtil.getDayFromEpoch(dailyList.get(position).getDt());
        Date date = DateUtil.getDate(dailyList.get(position).getDt());
        String day = DateUtil.getDayOfWeek(date);
        holder.sunrise.setText(dayForSunrise);
        holder.temperature.setText(String.format("%s,%s", Math.round(dailyList.get(position).getTemp().getMax())+"\u2103", "/" + Math.round(dailyList.get(position).getTemp().getMin())+"\u2103"));
        holder.description.setText(dailyList.get(position).getWeather().get(0).getDescription());
    }

    @Override
    public int getItemCount() {
        return dailyList !=null?dailyList.size():0;
    }

    public class WeeklyWeatherViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView sunrise, temperature, description;
        AppCompatImageView icon;
        public WeeklyWeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            sunrise = itemView.findViewById(R.id.sunrise);
            temperature = itemView.findViewById(R.id.temperature);
            description = itemView.findViewById(R.id.description);
            icon = itemView.findViewById(R.id.icon);
        }
    }
}
