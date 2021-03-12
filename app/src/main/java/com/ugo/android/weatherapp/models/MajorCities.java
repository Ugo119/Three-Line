package com.ugo.android.weatherapp.models;

import androidx.fragment.app.Fragment;

import java.io.Serializable;

public class MajorCities implements Serializable {
    private String cityName;
    private String countryName;
    private int latitude;
    private int longitude;

    public MajorCities(String cityName, String countryName, int latitude, int longitude) {
        this.cityName = cityName;
        this.countryName = countryName;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }
}
