package com.wells.carnet.data.entity;

/**
 * Created by Wells on 2016/4/10.
 */
public class User {
    public String userName;
    public String pwd;
    public int uid;

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", uid=" + uid +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
