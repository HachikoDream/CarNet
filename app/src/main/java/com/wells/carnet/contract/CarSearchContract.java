package com.wells.carnet.contract;

import com.wells.carnet.data.CarDataSource;
import com.wells.carnet.data.entity.Car;

import java.util.List;

/**
 * Created by Wells on 2016/4/12.
 */
public interface CarSearchContract {
    public interface View extends BaseView<Presenter> {
        void showSearchResult(List<Car> cars);

        void showEmptyMsg();

        void showDetailInfo(Car car);
    }

    public interface Presenter extends BasePresenter {
        void search(String carNum);
    }
}
