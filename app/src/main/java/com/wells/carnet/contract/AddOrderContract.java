package com.wells.carnet.contract;

import com.wells.carnet.data.CarDataSource;
import com.wells.carnet.data.entity.Car;
import com.wells.carnet.data.entity.Order;

import java.util.List;

/**
 * Created by Wells on 2016/4/13.
 */
public interface AddOrderContract {
    public interface View extends BaseView<Presenter> {
        void showError(String info);

        void showSuccessDialog(Order order);

        void showOrderList(Order order);

        void showCarListDialog(List<Car> cars);

        void showNoCarUsed();

    }

    public interface Presenter extends BasePresenter {
        /**
         * orderman = ordermanEv.getText().toString();
         * phoneNum = phonenumEv.getText().toString();
         * srcPlace = srcplaceEv.getText().toString();
         * destPlace = destplaceEv.getText().toString();
         * startDate = startDateEv.getText().toString();
         * endDate = enddateEv.getText().toString();
         * userName = usenameEv.getText().toString();
         * tripType = triptypeEv.getText().toString();
         * reason = reasonEv.getText().toString();
         */
        void addOrder(int state, String orderman, String phoneNum,
                      String srcPlace, String destPlace,
                      String startDate, String endDate, String usename, String type, String reason, String carId);

        void getAllCars();
    }

}
