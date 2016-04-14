package com.wells.carnet.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wells.carnet.data.entity.Order;
import com.wells.carnet.data.persistence.CarsDbHelper;
import com.wells.carnet.data.persistence.OrderPersistenceContract.OrderEntity;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Wells on 2016/4/11.
 */
public class OrderDataSourceImpl implements OrderDataSource {
    private CarsDbHelper mDbHelper;
    private static OrderDataSourceImpl mDataSource;

    public OrderDataSourceImpl(Context context) {
        checkNotNull(context);
        mDbHelper = CarsDbHelper.getInstance(context);
    }

    public static OrderDataSourceImpl getInstance(Context context) {
        if (mDataSource == null) {
            mDataSource = new OrderDataSourceImpl(context);
        }
        return mDataSource;
    }

    @Override
    public void addOrder(Order order) {
        checkNotNull(order);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(OrderEntity.COLUMN_NAME_BEGIN_TIME, order.getBegintime());
        values.put(OrderEntity.COLUMN_NAME_BACK_TIME, order.getBacktime());
        values.put(OrderEntity.COLUMN_NAME_CARID, order.getCarid());
        values.put(OrderEntity.COLUMN_NAME_DEST_PLACE, order.getDestPlace());
        values.put(OrderEntity.COLUMN_NAME_SRC_PLACE, order.getSrcPlace());
        values.put(OrderEntity.COLUMN_NAME_ORDER_ID, order.getOrderId());
        values.put(OrderEntity.COLUMN_NAME_ORDERMAN_NAME, order.getOrderman());
        values.put(OrderEntity.COLUMN_NAME_PHONENUM, order.getPhoneNum());
        values.put(OrderEntity.COLUMN_NAME_PROPERTY, order.getProperty());
        values.put(OrderEntity.COLUMN_NAME_REASON, order.getReason());
        values.put(OrderEntity.COLUMN_NAME_STATE, order.getState());
        values.put(OrderEntity.COLUMN_NAME_USEMAN, order.getUseman());
        values.put(OrderEntity.COLUMN_NAME_USEMAN_NUM, order.getUsemannum());
        db.insert(OrderEntity.TABLE_NAME, null, values);
        db.close();
    }

    @Override
    public void getOrderByState(int state, GetOrderCallback callback) {
        checkNotNull(state);
        checkNotNull(callback);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        if (state != Order.STATE_APPLY && state != Order.STATE_VERIFY) {
            callback.onDataNotAvailable();
            return;
        }
        String selection = OrderEntity.COLUMN_NAME_STATE + " = ?";
        String[] selectionArgs = {String.valueOf(state)};
        Cursor c = db.query(OrderEntity.TABLE_NAME, null, selection, selectionArgs, null, null, null);
        List<Order> orders = new ArrayList<>();
        c.moveToFirst();
        while (c != null && c.getCount() > 0) {
            Order order = null;
            String begintime = c.getString(c.getColumnIndexOrThrow(OrderEntity.COLUMN_NAME_BEGIN_TIME));
            String phonenum = c.getString(c.getColumnIndexOrThrow(OrderEntity.COLUMN_NAME_PHONENUM));
            String src = c.getString(c.getColumnIndexOrThrow(OrderEntity.COLUMN_NAME_SRC_PLACE));
            String dest = c.getString(c.getColumnIndexOrThrow(OrderEntity.COLUMN_NAME_DEST_PLACE));
            int property = c.getInt(c.getColumnIndexOrThrow(OrderEntity.COLUMN_NAME_PROPERTY));
            String useman = c.getString(c.getColumnIndexOrThrow(OrderEntity.COLUMN_NAME_USEMAN));
            int usemannum = c.getInt(c.getColumnIndexOrThrow(OrderEntity.COLUMN_NAME_USEMAN_NUM));
            String backtime = c.getString(c.getColumnIndexOrThrow(OrderEntity.COLUMN_NAME_BACK_TIME));
            String reason = c.getString(c.getColumnIndexOrThrow(OrderEntity.COLUMN_NAME_REASON));
            String carid = c.getString(c.getColumnIndexOrThrow(OrderEntity.COLUMN_NAME_CARID));
            int stateq = c.getInt(c.getColumnIndexOrThrow(OrderEntity.COLUMN_NAME_STATE));
            String orderman = c.getString(c.getColumnIndexOrThrow(OrderEntity.COLUMN_NAME_ORDERMAN_NAME));
            String orderid = c.getString(c.getColumnIndexOrThrow(OrderEntity.COLUMN_NAME_ORDER_ID));
            order = new Order(orderid, orderman, phonenum, src, dest, property, useman, usemannum, begintime, backtime, reason, carid, state);
            orders.add(order);
            if (c.isLast()) {
                break;
            } else {
                c.moveToNext();
            }
        }
        if (c != null) {
            c.close();
        }
        db.close();
        if (orders.size() > 0) {
            callback.onOrdersLoaded(orders);
        } else {
            callback.onDataNotAvailable();
        }
    }


}
