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
import com.ugo.android.weatherapp.models.MajorCities;
import com.ugo.android.weatherapp.network.RetrofitFactory;
import com.ugo.android.weatherapp.network.WeatherData;

import java.util.ArrayList;

public class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder> {
    private ArrayList<MajorCities> majorCitiesList;
    private WeatherClickListener weatherClickListener;


    public CountriesAdapter(WeatherClickListener weatherClickListener, ArrayList<MajorCities> majorCitiesList) {
        this.majorCitiesList = majorCitiesList;
        this.weatherClickListener = weatherClickListener;
    }

    @NonNull
    @Override
    public CountriesAdapter.CountriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.countries_list_item, parent, false);

        return new CountriesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CountriesAdapter.CountriesViewHolder holder, int position) {
        MajorCities majorCities = majorCitiesList.get(position);
        Log.e("TAG", "onBindViewHolder: " + majorCities.getCityName());
        holder.cityCountry.setText(String.format("%s, %s", majorCitiesList.get(position).getCityName(), majorCitiesList.get(position).getCountryName()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (weatherClickListener != null) {
                    weatherClickListener.onCityClicked(majorCities);

                    Log.e("TAG", "onBindViewHolder_CLICK: " + majorCities.getCityName());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return majorCitiesList !=null?majorCitiesList.size():0;
    }

    public class CountriesViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView cityCountry;
        public CountriesViewHolder(@NonNull View itemView) {
            super(itemView);
            cityCountry = itemView.findViewById(R.id.cityCountry);
        }
    }
}
