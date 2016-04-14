package com.wells.carnet.data.entity;

import java.util.UUID;

/**
 * Created by Wells on 2016/4/10.
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
public class Car {
    public String id;
    public String position;
    public String owner;
    public int electric;
    public int totalDistance;
    public String driver;
    public String carNum;
    public int state;
    public String detail;
    public String other;
    public int speed;
    public final static int STATE_RUN = 1;
    public final static int STATE_IDLE = 2;

    public Car(String id, String position, String owner, int electric,
               int totalDistance, String driver, String carNum,
               int state, String detail, String other, int speed) {
        this.id = id;
        this.position = position;
        this.owner = owner;
        this.electric = electric;
        this.totalDistance = totalDistance;
        this.driver = driver;
        this.carNum = carNum;
        this.state = state;
        this.detail = detail;
        this.other = other;
        this.speed = speed;
    }

    public Car(String position, String owner, int electric,
               int totalDistance, String driver, String carNum,
               int state, String detail, String other, int speed) {
        this.position = position;
        this.owner = owner;
        this.electric = electric;
        this.totalDistance = totalDistance;
        this.driver = driver;
        this.carNum = carNum;
        this.state = state;
        this.detail = detail;
        this.other = other;
        this.speed = speed;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getElectric() {
        return electric;
    }

    public void setElectric(int electric) {
        this.electric = electric;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(int totalDistance) {
        this.totalDistance = totalDistance;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", position='" + position + '\'' +
                ", owner='" + owner + '\'' +
                ", electric=" + electric +
                ", totalDistance=" + totalDistance +
                ", driver='" + driver + '\'' +
                ", carNum='" + carNum + '\'' +
                ", state=" + state +
                ", detail='" + detail + '\'' +
                ", other='" + other + '\'' +
                ", speed=" + speed +
                '}';
    }
}

