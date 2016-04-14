package com.wells.carnet.presenter;

import com.wells.carnet.contract.CarSearchContract;
import com.wells.carnet.data.CarDataSource;
import com.wells.carnet.data.entity.Car;

import java.util.List;

/**
 * Created by Wells on 2016/4/12.
 */
public class CarSearchPresenter implements CarSearchContract.Presenter {
    private CarSearchContract.View mView;
    private CarDataSource mDataSource;

    public CarSearchPresenter(CarSearchContract.View mView, CarDataSource mDataSource) {
        this.mView = mView;
        this.mDataSource = mDataSource;
        mView.setPresenter(this);
    }

    @Override
    public void search(String carNum) {
        mDataSource.getCars(carNum, new CarDataSource.GetCarsCallback() {
            @Override
            public void onCarsLoaded(List<Car> cars) {
                mView.showSearchResult(cars);
            }

            @Override
            public void onDataNotAvailable() {
                mView.showEmptyMsg();
            }
        });
    }

    @Override
    public void start() {

    }
}
