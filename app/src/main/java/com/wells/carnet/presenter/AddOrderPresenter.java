package com.wells.carnet.presenter;

import com.wells.carnet.contract.AddOrderContract;
import com.wells.carnet.data.CarDataSource;
import com.wells.carnet.data.OrderDataSource;
import com.wells.carnet.data.entity.Car;
import com.wells.carnet.data.entity.Order;
import com.wells.carnet.ui.act.AddOrderAct;
import com.wells.carnet.utils.StringUtils;

import java.util.List;

/**
 * Created by Wells on 2016/4/14.
 */
public class AddOrderPresenter implements AddOrderContract.Presenter {
    private CarDataSource mCarDs;
    private OrderDataSource mOrderDs;
    private AddOrderContract.View mView;

    public AddOrderPresenter(AddOrderContract.View view, CarDataSource mCarDs, OrderDataSource mOrderDs) {
        this.mCarDs = mCarDs;
        this.mOrderDs = mOrderDs;
        this.mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void addOrder(int state, String orderman, String phoneNum,
                         String srcPlace, String destPlace,
                         String startDate, String endDate, String usename,
                         String type, String reason, String carId) {
        if (StringUtils.isEmpty(orderman)) {
            mView.showError("请输入申请人");
            return;
        }
        if (StringUtils.isEmpty(phoneNum)) {
            mView.showError("请输入手机号");
            return;
        }
        if (StringUtils.isEmpty(srcPlace)) {
            mView.showError("请输入乘车地点");
            return;
        }
        if (StringUtils.isEmpty(destPlace)) {
            mView.showError("请输入目的地");
            return;
        }
        if (StringUtils.isEmpty(startDate)) {
            mView.showError("请选择出发日期");
            return;
        }
        if (StringUtils.isEmpty(endDate)) {
            mView.showError("请选择返回日期");
            return;
        }
        if (StringUtils.isEmpty(usename)) {
            mView.showError("请输入乘车人");
            return;
        }
        if (StringUtils.isEmpty(reason)) {
            mView.showError("请输入申请理由");
            return;
        }
        if (StringUtils.isEmpty(type)) {
            mView.showError("请选择路途性质");
            return;
        }
        if (StringUtils.isEmpty(carId)) {
            mView.showError("请选择车辆");
        }
        int property;
        if (type.equals(AddOrderAct.types[0])) {
            property = Order.PROPERTY_SHORT;
        } else {
            property = Order.PROPERTY_LONG;
        }
        String[] usemans = usename.split(",");
        int usemannum = usemans.length;
        Order order = new Order(orderman, phoneNum, srcPlace, destPlace, property, usename, usemannum, startDate, endDate, reason, carId, state);
        mOrderDs.addOrder(order);
        mView.showSuccessDialog(order);

    }

    @Override
    public void getAllCars() {
        mCarDs.getCars(null, new CarDataSource.GetCarsCallback() {
            @Override
            public void onCarsLoaded(List<Car> cars) {
                mView.showCarListDialog(cars);
            }

            @Override
            public void onDataNotAvailable() {
                mView.showNoCarUsed();
            }
        });
    }

    @Override
    public void start() {

    }
}
