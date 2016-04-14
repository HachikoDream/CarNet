package com.wells.carnet.data.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

/**
 * Created by Wells on 2016/4/10.
 * orderman
 * phoneNum
 * srcPlace
 * destPlace
 * property(long short)
 * useman
 * usemannum
 * begintime
 * backtime
 * reason
 * carid
 * state(审核还是)
 */
public class Order implements Parcelable {
    private String orderId;
    private String orderman;
    private String phoneNum;
    private String srcPlace;
    private String destPlace;
    private int property;
    private String useman;
    private int usemannum;
    private String begintime;
    private String backtime;
    private String reason;
    private String carid;
    private int state;
    public static final int STATE_APPLY = 1;
    public static final int STATE_VERIFY = 2;
    public static final int PROPERTY_SHORT = 3;
    public static final int PROPERTY_LONG = 4;


    public Order(String orderId, String orderman,
                 String phoneNum, String srcPlace,
                 String destPlace, int property, String useman, int usemannum,
                 String begintime, String backtime, String reason, String carid, int state) {
        this.orderId = orderId;
        this.orderman = orderman;
        this.phoneNum = phoneNum;
        this.srcPlace = srcPlace;
        this.destPlace = destPlace;
        this.property = property;
        this.useman = useman;
        this.usemannum = usemannum;
        this.begintime = begintime;
        this.backtime = backtime;
        this.reason = reason;
        this.carid = carid;
        this.state = state;
    }

    public Order(String orderman, String phoneNum,
                 String srcPlace, String destPlace,
                 int property, String useman, int usemannum, String begintime,
                 String backtime, String reason, String carid, int state) {
        this.orderman = orderman;
        this.phoneNum = phoneNum;
        this.srcPlace = srcPlace;
        this.destPlace = destPlace;
        this.property = property;
        this.useman = useman;
        this.usemannum = usemannum;
        this.begintime = begintime;
        this.backtime = backtime;
        this.reason = reason;
        this.carid = carid;
        this.state = state;
        this.orderId = UUID.randomUUID().toString();
    }

    protected Order(Parcel in) {
        orderId = in.readString();
        orderman = in.readString();
        phoneNum = in.readString();
        srcPlace = in.readString();
        destPlace = in.readString();
        property = in.readInt();
        useman = in.readString();
        usemannum = in.readInt();
        begintime = in.readString();
        backtime = in.readString();
        reason = in.readString();
        carid = in.readString();
        state = in.readInt();
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {
        @Override
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        @Override
        public Order[] newArray(int size) {
            return new Order[size];
        }
    };

    public String getOrderman() {
        return orderman;
    }

    public void setOrderman(String orderman) {
        this.orderman = orderman;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getSrcPlace() {
        return srcPlace;
    }

    public void setSrcPlace(String srcPlace) {
        this.srcPlace = srcPlace;
    }

    public String getDestPlace() {
        return destPlace;
    }

    public void setDestPlace(String destPlace) {
        this.destPlace = destPlace;
    }

    public int getProperty() {
        return property;
    }

    public void setProperty(int property) {
        this.property = property;
    }

    public String getUseman() {
        return useman;
    }

    public void setUseman(String useman) {
        this.useman = useman;
    }

    public int getUsemannum() {
        return usemannum;
    }

    public void setUsemannum(int usemannum) {
        this.usemannum = usemannum;
    }

    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    public String getBacktime() {
        return backtime;
    }

    public void setBacktime(String backtime) {
        this.backtime = backtime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getCarid() {
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderman='" + orderman + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", srcPlace='" + srcPlace + '\'' +
                ", destPlace='" + destPlace + '\'' +
                ", property=" + property +
                ", useman='" + useman + '\'' +
                ", usemannum='" + usemannum + '\'' +
                ", begintime='" + begintime + '\'' +
                ", backtime='" + backtime + '\'' +
                ", reason='" + reason + '\'' +
                ", carid=" + carid +
                ", state=" + state +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(orderId);
        dest.writeString(orderman);
        dest.writeString(phoneNum);
        dest.writeString(srcPlace);
        dest.writeString(destPlace);
        dest.writeInt(property);
        dest.writeString(useman);
        dest.writeInt(usemannum);
        dest.writeString(begintime);
        dest.writeString(backtime);
        dest.writeString(reason);
        dest.writeString(carid);
        dest.writeInt(state);
    }
}
