package com.wells.carnet.data.persistence;

import android.provider.BaseColumns;

/**
 * Created by Wells on 2016/4/11.
 * private int orderId
 * private String orderman;
 * private String phoneNum;
 * private String srcPlace;
 * private String destPlace;
 * private int property;
 * private String useman;
 * private String usemannum;
 * private String begintime;
 * private String backtime;
 * private String reason;
 * private int carid;
 * private int state;
 */
public class OrderPersistenceContract {
    public static abstract class OrderEntity implements BaseColumns {
        public static final String TABLE_NAME = "carorders";
        public static final String COLUMN_NAME_ORDER_ID = "orderid";
        public static final String COLUMN_NAME_ORDERMAN_NAME = "orderman";
        public static final String COLUMN_NAME_PHONENUM = "phonenum";
        public static final String COLUMN_NAME_SRC_PLACE = "src";
        public static final String COLUMN_NAME_DEST_PLACE = "dest";
        public static final String COLUMN_NAME_PROPERTY = "property";
        public static final String COLUMN_NAME_USEMAN = "useman";
        public static final String COLUMN_NAME_USEMAN_NUM = "usemannum";
        public static final String COLUMN_NAME_BEGIN_TIME = "begintime";
        public static final String COLUMN_NAME_BACK_TIME = "backtime";
        public static final String COLUMN_NAME_REASON = "reason";
        public static final String COLUMN_NAME_CARID = "carid";
        public static final String COLUMN_NAME_STATE = "state";
    }
}
