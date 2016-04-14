package com.wells.carnet.data.persistence;

import android.provider.BaseColumns;

/**
 * Created by Wells on 2016/4/11.
 * public int id;
 * public String position;
 * public String owner;
 * public float electric;
 * public float totalDistance;
 * public String driver;
 * public String carNum;
 * public int state;
 * public String detail;
 * public String other;
 * public float speed;
 */
public class CarPersistenceContract {
    public static abstract class CarEntity implements BaseColumns {
        public static final String TABLE_NAME = "netcars";
        public static final String COLUMN_NAME_CAR_ID = "carid";
        public static final String COLUMN_NAME_CAR_POSITION = "position";
        public static final String COLUMN_NAME_OWNER = "owner";
        public static final String COLUMN_NAME_ELECTRIC = "electric";
        public static final String COLUMN_NAME_TOTALDISTANCE = "distance";
        public static final String COLUMN_NAME_DRIVER = "driver";
        public static final String COLUMN_NAME_CARNUM = "carnum";
        public static final String COLUMN_NAME_STATE = "state";
        public static final String COLUMN_NAME_DETAIL = "detail";
        public static final String COLUMN_NAME_OTHER = "other";
        public static final String COLUMN_NAME_SPEED = "speed";
    }
}
