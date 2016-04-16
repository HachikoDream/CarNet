package com.wells.carnet.contract;

import com.wells.carnet.data.entity.Car;

import java.util.List;

/**
 * Created by Wells on 2016/4/16.
 */
public interface CarShowContract {

    public interface Presenter extends BasePresenter {
        void getAllInfos();
    }

    public interface View extends BaseView<Presenter> {
        void showLocations(List<Car> carList);

        void showNoCarInfo();

        void showDetailInfo(Car car);
    }
}
