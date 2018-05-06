package com.shawn.newrollcall.ScanBLEModel;

import android.bluetooth.BluetoothDevice;

/**
 * Created by Shawn.Wu on 2017/10/17.
 *
 */

public class BleDeviceItem {

    private BluetoothDevice bluetoothDevice;

    private String deviceName,deviceAddress;

    private int rssi;

    public BleDeviceItem(BluetoothDevice bluetoothDevice) {
        this.bluetoothDevice = bluetoothDevice;
        this.deviceName = bluetoothDevice.getName();
        this.deviceAddress = bluetoothDevice.getAddress();
    }

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
}