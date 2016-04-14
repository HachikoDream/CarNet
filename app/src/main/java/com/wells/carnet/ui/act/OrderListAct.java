package com.wells.carnet.ui.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wells.carnet.R;
import com.wells.carnet.contract.OrderListContract;
import com.wells.carnet.data.OrderDataSourceImpl;
import com.wells.carnet.data.entity.Order;
import com.wells.carnet.presenter.OrderListPresenter;
import com.wells.carnet.ui.adapter.OrderAdapter;
import com.wells.carnet.utils.AlerDialogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Wells on 2016/4/9.
 */
public class OrderListAct extends BaseAct implements OrderListContract.View, OrderAdapter.OnOrderClickListener {
    private static final int ADD_ORDER_REQ = 12;
    public static final int APPLY_LIST = 13;
    public static final int VERIFY_LIST = 14;
    public static final String SOURCE = "COME_SOURCE";
    public static final String NEED_REFRESH = "NEED_REFRESH";
    @Bind(R.id.common_rv)
    RecyclerView commonRv;
    @Bind(R.id.error_msg_lv)
    LinearLayout errorMsgLv;
    private int source;
    private OrderAdapter mAdapter;
    private OrderListContract.Presenter mPresenter;
    private int state;

    @Override
    protected void init() {
        mAdapter = new OrderAdapter(new ArrayList<Order>(), this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        commonRv.setAdapter(mAdapter);
        commonRv.setLayoutManager(layoutManager);
        mAdapter.setListener(this);
        source = getIntent().getIntExtra(SOURCE, -1);
        switch (source) {
            case APPLY_LIST:
                state = Order.STATE_APPLY;
                getSupportActionBar().setTitle("申请列表");
                break;
            case VERIFY_LIST:
                state = Order.STATE_VERIFY;
                getSupportActionBar().setTitle("审核列表");
                break;
        }
        OrderListContract.Presenter presenter = new OrderListPresenter(OrderDataSourceImpl.getInstance(getApplicationContext()), this);
        presenter.getAllOrders(state);
    }


    @Override
    public int getLayoutId() {
        return R.layout.view_orderlist;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.orderlist_items, menu);
        if (source == VERIFY_LIST) {
            menu.clear();
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_add_order:
                readyGoForResult(AddOrderAct.class, ADD_ORDER_REQ);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_ORDER_REQ && resultCode == RESULT_OK) {
            Order order = data.getParcelableExtra(NEED_REFRESH);
            mAdapter.addOrder(order);
        }
    }

    @Override
    public void showOrders(List<Order> orders) {
        errorMsgLv.setVisibility(View.INVISIBLE);
        commonRv.setVisibility(View.VISIBLE);
        mAdapter.setOrders(orders);
    }

    @Override
    public void showOrderDetail(Order order) {
        View dialogView = buildView(order);
        AlerDialogUtils.showCustomDialog(this, dialogView, "订单详情", null, "确定", null);
    }

    /**
     * @param order
     * @return
     */
    private View buildView(Order order) {
        View v = LayoutInflater.from(this).inflate(R.layout.order_detail_view, null);
        ((TextView) v.findViewById(R.id.orderman_tv)).setText("申请人:" + order.getOrderman());
        ((TextView) v.findViewById(R.id.useman_tv)).setText("使用人:" + order.getUseman());
        ((TextView) v.findViewById(R.id.dateperiod_tv)).setText("使用时间段:" + order.getBegintime() + "-" + order.getBacktime());
        ((TextView) v.findViewById(R.id.placeperiod_tv)).setText("从" + order.getSrcPlace() + "到" + order.getDestPlace());
        ((TextView) v.findViewById(R.id.reason_tv)).setText("理由:" + order.getReason());
        String propertyStr = order.getProperty() == Order.PROPERTY_LONG ? "长途" : "短途";
        ((TextView) v.findViewById(R.id.property_tv)).setText("路途性质:" + propertyStr);
        ((TextView) v.findViewById(R.id.phonenum_tv)).setText("电话:" + order.getPhoneNum());
        return v;
    }

    @Override
    public void showEmptyMsg() {
        commonRv.setVisibility(View.INVISIBLE);
        errorMsgLv.setVisibility(View.VISIBLE);
    }

    @Override
    public void setPresenter(OrderListContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onOrderClick(View v, Order order) {
        showOrderDetail(order);
    }
}
