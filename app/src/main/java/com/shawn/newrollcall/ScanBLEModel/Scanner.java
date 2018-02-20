package com.shawn.newrollcall.ScanBLEModel;

import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Shawn Wu on 2017/10/18.
 */

public abstract class Scanner {

    public List<BleDeviceItem> deviceItemList = new LinkedList<>();

    public List<String> deviceAddressList = new LinkedList<>();

    public BluetoothLeScanner bluetoothLeScanner ;

    public ScanCallback scanCallback;

    public boolean isscaning = false;

    public abstract void startScan();

    public abstract void stopScan();

    public abstract void continueScan();

    public abstract List<BleDeviceItem> getDeviceList();
}
