package com.shawn.newrollcall.ScanBLEModel;

import android.Manifest;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.support.annotation.RequiresPermission;

import com.shawn.newrollcall.FluxCenter.AppFluxCenter;

import java.util.List;

/**
 * Created by Shawn.Wu on 2017/10/17.
 *
 */

public class RollCallBLEScanner extends Scanner {

    private final String ROLLCALL = "RollCall";
    private static ScanSettings scanSettings = null;

    private static List<ScanFilter> scanFilter = null;

    private RollCallBLEScanner(Context context) {
        final BluetoothManager bluetoothManager =
                (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        bluetoothLeScanner = bluetoothManager.getAdapter().getBluetoothLeScanner();
    }

    private ScanCallback callback() {
        scanCallback = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                super.onScanResult(callbackType, result);

                BleDeviceItem bluetoothDevice = new BleDeviceItem(result.getDevice());
                String deviceAddress = bluetoothDevice.getAddress();

                if(!deviceAddressList.contains(deviceAddress)) {
                    if(bluetoothDevice.getName().contains(ROLLCALL)) {
                        deviceAddressList.add(deviceAddress);
                        deviceItemList.add(bluetoothDevice);
                        AppFluxCenter.getActionCreator().getBleScannerCreator().updateFindNewDevice(deviceItemList);
                    }
                }
            }
        };
        return scanCallback;
    }

    private void scan() {
        if(!isscaning) {
            if(scanSettings != null) {
                bluetoothLeScanner.startScan(scanFilter,scanSettings,callback());
            }else {
                bluetoothLeScanner.startScan(callback());
            }
            isscaning = true;
        }
    }

    @RequiresPermission(Manifest.permission.BLUETOOTH_ADMIN)
    @Override
    public void startScan() {
        if(!isscaning){
            scan();
            deviceItemList.clear();
        }

    }

    public boolean isScaning(){
        return isscaning;
    }

    @Override
    public void stopScan() {
        if(isscaning) {
            bluetoothLeScanner.stopScan(callback());
            isscaning = false;
        }
    }

    @Override
    public void continueScan() {
        if(!isscaning) {
            scan();
        }
    }


    public static class Builder {

        public Builder setScanSettings(ScanSettings scanSettings) {
            RollCallBLEScanner.scanSettings = scanSettings;
            return this;
        }

        public Builder setScanFilter(List<ScanFilter> scanFilter) {
            RollCallBLEScanner.scanFilter = scanFilter;
            return this;
        }

        public RollCallBLEScanner build(Context context) {
            return new RollCallBLEScanner(context);
        }

    }

}