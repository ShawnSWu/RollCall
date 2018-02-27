package com.shawn.newrollcall.ScanBLEModel;

import android.bluetooth.BluetoothDevice;

/**
 * Created by Shawn.Wu on 2017/10/17.
 *
 */

public class BleDeviceItem {

    private BluetoothDevice bluetoothDevice;

    private int rssi;

    public BleDeviceItem(BluetoothDevice bluetoothDevice) {
        this.bluetoothDevice = bluetoothDevice;
    }

    public String getAddress() {
        return bluetoothDevice.getAddress();
    }

    public String getName() {
        return bluetoothDevice.getName();
    }

    public void setRSSI(int rssi) {
        this.rssi = rssi;
    }

    public int getRSSI() {
        return rssi;
    }
}