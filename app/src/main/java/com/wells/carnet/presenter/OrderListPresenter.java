package com.wells.carnet.presenter;

import com.wells.carnet.contract.OrderListContract;
import com.wells.carnet.data.OrderDataSource;
import com.wells.carnet.data.entity.Order;

import java.util.List;

/**
 * Created by Wells on 2016/4/15.
 */
public class OrderListPresenter implements OrderListContract.Presenter {
    private OrderDataSource mDs;
    private OrderListContract.View mView;

    public OrderListPresenter(OrderDataSource mDs, OrderListContract.View mView) {
        this.mDs = mDs;
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void getAllOrders(int state) {
        mDs.getOrderByState(state, new OrderDataSource.GetOrderCallback() {
            @Override
            public void onOrdersLoaded(List<Order> orders) {
                mView.showOrders(orders);
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
