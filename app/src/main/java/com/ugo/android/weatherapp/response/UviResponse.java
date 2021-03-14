package com.ugo.android.weatherapp.response;

import java.io.Serializable;

public class UviResponse implements Serializable {
    private double lat;
    private double lon;
    private String date_iso;
    private long date;
    private double value;

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

    public String getDate_iso() {
        return date_iso;
    }

    public void setDate_iso(String date_iso) {
        this.date_iso = date_iso;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
