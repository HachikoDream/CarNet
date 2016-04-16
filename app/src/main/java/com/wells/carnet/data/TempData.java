package com.wells.carnet.data;

import android.content.Context;

import com.wells.carnet.data.entity.Car;

/**
 * Created by Wells on 2016/4/12.
 * id
 * position（x,y坐标）
 * owner车主
 * electric 电瓶
 * totalDistance 行驶历程
 * driver 司机
 * carNum 车牌号
 * state( 熄火还是运行中)
 * detail（具体车型信息）
 * other
 * speed（km/h）
 */
public class TempData {
    public static String[] positions = {
            "117.137344,36.673902",
            "117.122971,36.682469",
            "117.185637,36.64773",
            "117.040758,36.63522",
            "116.997352,36.701916"};
    public static String[] carNums = {"鲁A1256E", "鲁A1156E", "鲁A126YA", "鲁B223EF", "鲁E1256A"};
    public static int[] speeds = {70, 60, 90, 87, 32};
    public static int[] states = {Car.STATE_IDLE, Car.STATE_IDLE, Car.STATE_RUN, Car.STATE_RUN, Car.STATE_RUN};
    public static String[] drivers = {"李爽", "王翔", "李伟", "张涛", "张思萌"};
    public static String[] details = {"奔驰600", "宝马300", "帕萨特230", "奥迪A1", "长城231"};
    public static int[] electrics = {12, 32, 15, 67, 90};
    public static int[] distances = {12000, 32000, 25000, 6070, 9000};
    public static String[] other = {"车状态良好", "车状态良好", "车状态良好", "车状态良好", "车状态良好"};
    public static String[] owner = {"张三", "李四", "马云", "相敬", "贾围"};
    public static String userName = "admin";
    public static String pwd = "admin";

    public static void buildTempData(Context context) {
        CarDataSource carDataSource = CarDataSourceImpl.getInstance(context);
        for (int i = 0; i < TempData.carNums.length; i++) {
            Car car = new Car(positions[i], owner[i],
                    electrics[i], distances[i],
                    drivers[i], carNums[i],
                    states[i], details[i],
                    other[i], speeds[i]);
            carDataSource.addCar(car);
        }

    }

}
