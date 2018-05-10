package com.shawn.newrollcall.ScanBLEModel.view;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.shawn.newrollcall.ScanBLEModel.BLECallBack;
import com.shawn.newrollcall.ScanBLEModel.BleDeviceItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/15.
 *
 */
public class WriteDataToDeviceService extends Service {

    private BLECallBack[] ble_callBack;
    private BleDeviceItem device;

    private List<BleDeviceItem> arrayList;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {

        Bundle bundle = intent.getExtras();

        String chooseSecond= bundle.getString(WriteDataToDeviceActivity.CHOOSE_SECOND);

        arrayList= (ArrayList<BleDeviceItem>) bundle.getSerializable(WriteDataToDeviceActivity.DEVICE_ARRAYLIST);


        ble_callBack = new BLECallBack[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            ble_callBack[i] = new BLECallBack(chooseSecond);
            device = arrayList.get(i);
            ble_callBack[i].bluetoothGatt = device.getBluetoothDevice().connectGatt(this, false, ble_callBack[i].mGattCallback);
        }

        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
