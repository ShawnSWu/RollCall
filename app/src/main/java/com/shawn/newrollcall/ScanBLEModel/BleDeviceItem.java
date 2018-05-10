package com.shawn.newrollcall.ScanBLEModel;

import android.bluetooth.BluetoothDevice;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Shawn.Wu on 2017/10/17.
 *
 */

public class BleDeviceItem implements Parcelable{

    private BluetoothDevice bluetoothDevice;

    private String deviceName,deviceAddress;

    private int rssi;

    public BleDeviceItem(BluetoothDevice bluetoothDevice) {
        this.bluetoothDevice = bluetoothDevice;
        this.deviceName = bluetoothDevice.getName();
        this.deviceAddress = bluetoothDevice.getAddress();
    }

    protected BleDeviceItem(Parcel in) {
        bluetoothDevice = in.readParcelable(BluetoothDevice.class.getClassLoader());
        deviceName = in.readString();
        deviceAddress = in.readString();
        rssi = in.readInt();
    }

    public static final Creator<BleDeviceItem> CREATOR = new Creator<BleDeviceItem>() {
        @Override
        public BleDeviceItem createFromParcel(Parcel in) {
            return new BleDeviceItem(in);
        }

        @Override
        public BleDeviceItem[] newArray(int size) {
            return new BleDeviceItem[size];
        }
    };

    public String getAddress() {
        return deviceAddress;
    }

    public String getName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setDeviceAddress(String deviceAddress) {
        this.deviceAddress = deviceAddress;
    }

    public void setRSSI(int rssi) {
        this.rssi = rssi;
    }

    public int getRSSI() {
        return rssi;
    }

    public BluetoothDevice getBluetoothDevice() {
        return bluetoothDevice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(bluetoothDevice, i);
        parcel.writeString(deviceName);
        parcel.writeString(deviceAddress);
        parcel.writeInt(rssi);
    }


}