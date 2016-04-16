package com.wells.carnet.ui;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.wells.carnet.data.CarDataSource;
import com.wells.carnet.data.CarDataSourceImpl;
import com.wells.carnet.data.TempData;
import com.wells.carnet.data.entity.Car;
import com.wells.carnet.data.persistence.CarsDbHelper;
import com.wells.carnet.utils.PreferenceUtils;

/**
 * Created by Wells on 2016/4/11.
 */
public class CarApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);
        CarsDbHelper.getInstance(this);
        if (!PreferenceUtils.hasKey(this, PreferenceUtils.Key.OPEN_STATE)) {
            PreferenceUtils.putString(this, PreferenceUtils.Key.OPEN_STATE, "OPEN");
            TempData.buildTempData(this);
        }
    }

}
