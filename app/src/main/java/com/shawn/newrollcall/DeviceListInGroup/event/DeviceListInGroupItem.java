package com.shawn.newrollcall.DeviceListInGroup.event;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shawn Wu on 2017/12/26.
 *
 */

public class DeviceListInGroupItem implements Parcelable {

    private String deviceName;

    private String deviceAddress;

    public DeviceListInGroupItem(String deviceName, String deviceAddress) {
        this.deviceName = deviceName;
        this.deviceAddress = deviceAddress;
    }

    protected DeviceListInGroupItem(Parcel in) {
        deviceName = in.readString();
        deviceAddress = in.readString();
    }

    public static final Creator<DeviceListInGroupItem> CREATOR = new Creator<DeviceListInGroupItem>() {
        @Override
        public DeviceListInGroupItem createFromParcel(Parcel in) {
            return new DeviceListInGroupItem(in);
        }

        @Override
        public DeviceListInGroupItem[] newArray(int size) {
            return new DeviceListInGroupItem[size];
        }
    };

    public String getDeviceName() {
        return deviceName;
    }

    public String getDeviceAddress() {
        return deviceAddress;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(deviceName);
        parcel.writeString(deviceAddress);
    }
}
