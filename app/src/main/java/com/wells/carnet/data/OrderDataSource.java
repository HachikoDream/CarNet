package com.wells.carnet.data;

import com.wells.carnet.data.entity.Order;

import java.util.List;

/**
 * Created by Wells on 2016/4/11.
 */
public interface OrderDataSource {
    void addOrder(Order order);

    void getOrderByState(int state,GetOrderCallback callback);

    interface GetOrderCallback {
        void onOrdersLoaded(List<Order> orders);
        void onDataNotAvailable();
    }
}
