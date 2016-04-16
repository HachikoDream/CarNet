package com.wells.carnet.data;

import com.wells.carnet.data.entity.Car;
import com.wells.carnet.data.entity.Location;

import java.util.List;

/**
 * Created by Wells on 2016/4/11.
 */
public interface CarDataSource {

    public void addCar(Car car);

    public void getCars(String carNum, GetCarsCallback carsCallback);

    public void getLocations(GetLocationCallback callback);
    interface GetCarsCallback {
        void onCarsLoaded(List<Car> cars);

        void onDataNotAvailable();
    }

    interface GetLocationCallback {
        void onLocationsLoaded(List<Location> locations);

        void onDataNotAvailable();
    }

}
