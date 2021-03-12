package com.ugo.android.weatherapp.response;

import com.ugo.android.weatherapp.models.Current;
import com.ugo.android.weatherapp.models.Daily;

import java.io.Serializable;
import java.util.ArrayList;

public class WeeklyWeatherResponse implements Serializable {
    private double lat;
    private double lon;
    private String timezone;
    private String timezone_offset;
    private Current current;
    private ArrayList<Daily> daily;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTimezone_offset() {
        return timezone_offset;
    }

    public void setTimezone_offset(String timezone_offset) {
        this.timezone_offset = timezone_offset;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public ArrayList<Daily> getDaily() {
        return daily;
    }

    public void setDaily(ArrayList<Daily> daily) {
        this.daily = daily;
    }
}
