/*
   private int orderId
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

package com.wells.carnet.data.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CarsDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Cars.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String BOOLEAN_TYPE = " INTEGER";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_CAR_ENTRIES =
            "CREATE TABLE " + CarPersistenceContract.CarEntity.TABLE_NAME + " (" +
                    CarPersistenceContract.CarEntity._ID + TEXT_TYPE + " PRIMARY KEY," +
                    CarPersistenceContract.CarEntity.COLUMN_NAME_CAR_ID + TEXT_TYPE + COMMA_SEP +
                    CarPersistenceContract.CarEntity.COLUMN_NAME_CAR_POSITION + TEXT_TYPE + COMMA_SEP +
                    CarPersistenceContract.CarEntity.COLUMN_NAME_OWNER + TEXT_TYPE + COMMA_SEP +
                    CarPersistenceContract.CarEntity.COLUMN_NAME_ELECTRIC + BOOLEAN_TYPE + COMMA_SEP +
                    CarPersistenceContract.CarEntity.COLUMN_NAME_TOTALDISTANCE + BOOLEAN_TYPE + COMMA_SEP +
                    CarPersistenceContract.CarEntity.COLUMN_NAME_DRIVER + TEXT_TYPE + COMMA_SEP +
                    CarPersistenceContract.CarEntity.COLUMN_NAME_CARNUM + TEXT_TYPE + COMMA_SEP +
                    CarPersistenceContract.CarEntity.COLUMN_NAME_STATE + BOOLEAN_TYPE + COMMA_SEP +
                    CarPersistenceContract.CarEntity.COLUMN_NAME_DETAIL + TEXT_TYPE + COMMA_SEP +
                    CarPersistenceContract.CarEntity.COLUMN_NAME_OTHER + TEXT_TYPE + COMMA_SEP +
                    CarPersistenceContract.CarEntity.COLUMN_NAME_SPEED + BOOLEAN_TYPE +
                    " )";
    /*
   private int orderId
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
    private static final String SQL_CREATE_ORDER_ENTRIES =
            "CREATE TABLE " + OrderPersistenceContract.OrderEntity.TABLE_NAME + " (" +
                    OrderPersistenceContract.OrderEntity._ID + TEXT_TYPE + " PRIMARY KEY," +
                    OrderPersistenceContract.OrderEntity.COLUMN_NAME_ORDER_ID + TEXT_TYPE + COMMA_SEP +
                    OrderPersistenceContract.OrderEntity.COLUMN_NAME_ORDERMAN_NAME + TEXT_TYPE + COMMA_SEP +
                    OrderPersistenceContract.OrderEntity.COLUMN_NAME_PHONENUM + TEXT_TYPE + COMMA_SEP +
                    OrderPersistenceContract.OrderEntity.COLUMN_NAME_SRC_PLACE + TEXT_TYPE + COMMA_SEP +
                    OrderPersistenceContract.OrderEntity.COLUMN_NAME_DEST_PLACE + TEXT_TYPE + COMMA_SEP +
                    OrderPersistenceContract.OrderEntity.COLUMN_NAME_PROPERTY + BOOLEAN_TYPE + COMMA_SEP +
                    OrderPersistenceContract.OrderEntity.COLUMN_NAME_USEMAN + TEXT_TYPE + COMMA_SEP +
                    OrderPersistenceContract.OrderEntity.COLUMN_NAME_USEMAN_NUM + BOOLEAN_TYPE + COMMA_SEP +
                    OrderPersistenceContract.OrderEntity.COLUMN_NAME_BEGIN_TIME + TEXT_TYPE + COMMA_SEP +
                    OrderPersistenceContract.OrderEntity.COLUMN_NAME_BACK_TIME + TEXT_TYPE + COMMA_SEP +
                    OrderPersistenceContract.OrderEntity.COLUMN_NAME_REASON + TEXT_TYPE + COMMA_SEP +
                    OrderPersistenceContract.OrderEntity.COLUMN_NAME_CARID + TEXT_TYPE + COMMA_SEP +
                    OrderPersistenceContract.OrderEntity.COLUMN_NAME_STATE + BOOLEAN_TYPE +

                    " )";
    private static CarsDbHelper mHelper;

    public CarsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static CarsDbHelper getInstance(Context context) {
        if (mHelper == null) {
            mHelper = new CarsDbHelper(context);
        }
        return mHelper;
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_CAR_ENTRIES);
        db.execSQL(SQL_CREATE_ORDER_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }
}
