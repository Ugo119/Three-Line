package com.ugo.android.weatherapp.models;

import androidx.fragment.app.Fragment;

import java.io.Serializable;

public class MajorCities implements Serializable {
    private String cityName;
    private String countryName;
    private double latitude;
    private double longitude;

    public MajorCities(String cityName, String countryName, double latitude, double longitude) {
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
