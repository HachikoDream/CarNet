package com.wells.carnet.presenter;

import com.wells.carnet.contract.CarShowContract;
import com.wells.carnet.data.CarDataSource;
import com.wells.carnet.data.entity.Car;
import com.wells.carnet.ui.fragment.BaseFragment;

import java.util.List;

/**
 * Created by Wells on 2016/4/16.
 */
public class CarShowPresenter implements CarShowContract.Presenter {
    private CarDataSource mCarDs;
    private CarShowContract.View mView;

    public CarShowPresenter(CarShowContract.View mView, CarDataSource mCarDs) {
        this.mCarDs = mCarDs;
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void getAllInfos() {
        if (mCarDs != null) {
            mCarDs.getCars(null, new CarDataSource.GetCarsCallback() {
                @Override
                public void onCarsLoaded(List<Car> cars) {
                    mView.showLocations(cars);
                }

                @Override
                public void onDataNotAvailable() {
                    mView.showNoCarInfo();
                }
            });
        }
    }

    @Override
    public void start() {

    }
}
