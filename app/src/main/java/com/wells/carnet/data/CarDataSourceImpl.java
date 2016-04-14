package com.wells.carnet.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wells.carnet.data.entity.Car;
import com.wells.carnet.data.persistence.CarPersistenceContract.CarEntity;
import com.wells.carnet.data.persistence.CarsDbHelper;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Wells on 2016/4/11.
 */
public class CarDataSourceImpl implements CarDataSource {
    private static CarDataSourceImpl mDateSource;
    private CarsDbHelper mDbHelper;

    public static CarDataSourceImpl getInstance(Context context) {
        checkNotNull(context);
        if (mDateSource == null) {
            mDateSource = new CarDataSourceImpl(context);
        }
        return mDateSource;
    }

    private CarDataSourceImpl(Context context) {
        checkNotNull(context);
        mDbHelper = CarsDbHelper.getInstance(context);
    }

    @Override
    public void addCar(Car car) {
        checkNotNull(car);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CarEntity.COLUMN_NAME_CAR_ID, car.getId());
        values.put(CarEntity.COLUMN_NAME_CAR_POSITION, car.getPosition());
        values.put(CarEntity.COLUMN_NAME_CARNUM, car.getCarNum());
        values.put(CarEntity.COLUMN_NAME_DETAIL, car.getDetail());
        values.put(CarEntity.COLUMN_NAME_DRIVER, car.getDriver());
        values.put(CarEntity.COLUMN_NAME_TOTALDISTANCE, car.getTotalDistance());
        values.put(CarEntity.COLUMN_NAME_ELECTRIC, car.getElectric());
        values.put(CarEntity.COLUMN_NAME_OTHER, car.getOther());
        values.put(CarEntity.COLUMN_NAME_SPEED, car.getSpeed());
        values.put(CarEntity.COLUMN_NAME_OWNER, car.getOwner());
        values.put(CarEntity.COLUMN_NAME_STATE, car.getState());
        db.insert(CarEntity.TABLE_NAME, null, values);
        db.close();
    }

    @Override
    public void getCars(String carNum, GetCarsCallback carsCallback) {
        checkNotNull(carsCallback);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String selection = CarEntity.COLUMN_NAME_CARNUM + " LIKE ?";
        if (carNum != null && carNum.length() > 0) {
            carNum = "%" + carNum + "%";
        }
        String[] selectionArgs = {carNum};
        Cursor c = null;
        if (carNum == null) {
            c = db.query(CarEntity.TABLE_NAME, null, null, null, null, null, null);
        } else {
            c = db.query(CarEntity.TABLE_NAME, null, selection, selectionArgs, null, null, null);

        }
        List<Car> cars = new ArrayList<>();
        c.moveToFirst();
        while (c != null && c.getCount() > 0) {
            Car car = null;
            String carId = c.getString(c.getColumnIndexOrThrow(CarEntity.COLUMN_NAME_CAR_ID));
            String carNumber = c.getString(c.getColumnIndexOrThrow(CarEntity.COLUMN_NAME_CARNUM));
            int speed = c.getInt(c.getColumnIndexOrThrow(CarEntity.COLUMN_NAME_SPEED));
            int state = c.getInt(c.getColumnIndexOrThrow(CarEntity.COLUMN_NAME_STATE));
            int distance = c.getInt(c.getColumnIndexOrThrow(CarEntity.COLUMN_NAME_TOTALDISTANCE));
            String detail = c.getString(c.getColumnIndexOrThrow(CarEntity.COLUMN_NAME_DETAIL));
            String other = c.getString(c.getColumnIndexOrThrow(CarEntity.COLUMN_NAME_OTHER));
            String driver = c.getString(c.getColumnIndexOrThrow(CarEntity.COLUMN_NAME_DRIVER));
            String owner = c.getString(c.getColumnIndexOrThrow(CarEntity.COLUMN_NAME_OWNER));
            int electric = c.getInt(c.getColumnIndexOrThrow(CarEntity.COLUMN_NAME_ELECTRIC));
            String position = c.getString(c.getColumnIndexOrThrow(CarEntity.COLUMN_NAME_CAR_POSITION));
            car = new Car(carId, position, owner, electric, distance, driver, carNumber, state, detail, other, speed);
            cars.add(car);
            if (c.isLast()) {
                break;
            } else {
                c.moveToNext();
            }

        }
        if (c != null) {
            c.close();
        }
        db.close();
        if (cars.size() > 0) {
            carsCallback.onCarsLoaded(cars);
        } else {
            carsCallback.onDataNotAvailable();
        }

    }
}
