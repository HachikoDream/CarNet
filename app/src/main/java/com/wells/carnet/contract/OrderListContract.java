package com.wells.carnet.contract;

import com.wells.carnet.data.entity.Car;
import com.wells.carnet.data.entity.Order;

import java.util.List;

/**
 * Created by Wells on 2016/4/14.
 */
public interface OrderListContract {
    interface Presenter extends BasePresenter {
        void getAllOrders(int state);
    }

    interface View extends BaseView<Presenter> {
        void showOrders(List<Order> orders);

        void showOrderDetail(Order order);

        void showEmptyMsg();
    }

}
