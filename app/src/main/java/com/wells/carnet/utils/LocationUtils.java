package com.wells.carnet.utils;

import android.util.Log;

import com.wells.carnet.data.entity.Car;
import com.wells.carnet.data.entity.Location;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wells on 2016/4/16.
 */
public class LocationUtils {

    static final int TYPE_LONGITUDE = 1;
    static final int TYPE_LATITUDE = 2;
    static final double RANGE_ADD = 0.1;

    public static class Range {
        public double minLatitude;
        public double maxLatitude;
        public double minLongitude;
        public double maxLongitude;

        @Override
        public String toString() {
            return "Range{" +
                    "maxLongitude=" + maxLongitude +
                    ", minLongitude=" + minLongitude +
                    ", maxLatitude=" + maxLatitude +
                    ", minLatitude=" + minLatitude +
                    '}';
        }
    }

    public static List<Location> transfer(List<Car> cars) {
        List<Location> locations = new ArrayList<>(cars.size());
        for (Car car : cars) {
            Location location = Location.buildByStr(car.getPosition());
            locations.add(location);
        }
        return locations;
    }

    public static Car findCarByPos(List<Car> cars, Location location) {
        for (Car car : cars) {
            String pos = location.getLongitude() + "," + location.getLatitude();
            if (car.getPosition().equals(pos)) {
                return car;
            }
        }
        return null;
    }

    public static Range getMapRange(List<Car> cars) {
        List<Location> locations = transfer(cars);
        Range range = new Range();
        range.maxLatitude = findMax(locations, TYPE_LATITUDE) + RANGE_ADD;
        range.maxLongitude = findMax(locations, TYPE_LONGITUDE) + RANGE_ADD;
        range.minLongitude = findMin(locations, TYPE_LONGITUDE) - RANGE_ADD;
        range.minLatitude = findMin(locations, TYPE_LATITUDE) - RANGE_ADD;
        Log.i("info", range.toString());
        return range;
    }

    public static double findMin(List<Location> locations, int type) {
        double min;
        if (type == TYPE_LONGITUDE) {
            min = locations.get(0).getLongitude();
            for (int i = 1; i < locations.size(); i++) {
                if (locations.get(i).getLongitude() < min) {
                    min = locations.get(i).getLongitude();
                }
            }
            return min;
        } else {
            min = locations.get(0).getLatitude();
            for (int i = 1; i < locations.size(); i++) {
                if (locations.get(i).getLatitude() < min) {
                    min = locations.get(i).getLatitude();
                }
            }
            return min;
        }
    }

    public static double findMax(List<Location> locations, int type) {
        double max;
        if (type == TYPE_LONGITUDE) {
            max = 0;
            for (int i = 0; i < locations.size(); i++) {
                if (locations.get(i).getLongitude() > max) {
                    max = locations.get(i).getLongitude();
                }
            }
            return max;
        } else {
            max = 0;
            for (int i = 0; i < locations.size(); i++) {
                if (locations.get(i).getLatitude() > max) {
                    max = locations.get(i).getLatitude();
                }
            }
            return max;
        }
    }
}
