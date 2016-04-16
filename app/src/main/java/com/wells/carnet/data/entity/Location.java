package com.wells.carnet.data.entity;

/**
 * Created by Wells on 2016/4/16.
 */
public class Location {
    private double longitude;
    private double latitude;

    public Location(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public static Location buildByStr(String locationInfo) {
        String[] linfos = locationInfo.split(",");
        double longitude = Double.parseDouble(linfos[0]);
        double latitude = Double.parseDouble(linfos[1]);
        return new Location(longitude, latitude);
    }
}
