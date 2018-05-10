package com.shawn.newrollcall.ScanBLEModel;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothProfile;
import android.os.Handler;
import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2017/5/15.
 *
 */
public class BLECallBack {
    //  TxChar
    private final UUID ServerUUID= UUID.fromString("6e400001-b5a3-f393-e0a9-e50e24dcca9e");
    private final UUID CharacteristicUUID= UUID.fromString("6e400002-b5a3-f393-e0a9-e50e24dcca9e");
    private final UUID TxCharacteristicUUID= UUID.fromString("6e400003-b5a3-f393-e0a9-e50e24dcca9e");
    private final UUID DescriptorUUID= UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");

    static String cmd_3000;

    Handler delay_handle = new Handler();

    public BluetoothGatt bluetoothGatt;

    public BluetoothGattCharacteristic characteristic;
    public BluetoothGattService service;

    public List<BluetoothGattCharacteristic> listGattCharacteristic;
    public List<BluetoothGattService> supportedGattServices;



    public BLECallBack(String second) {
        cmd_3000= second;
    }

    public BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            super.onConnectionStateChange(gatt, status, newState);

            if (newState == BluetoothProfile.STATE_CONNECTED) {
                //連接成功後啟動服務發現

                Log.e("onConnectionStateChange", "啟動服務發現:");

                bluetoothGatt.discoverServices();

            }
        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status)
        {
            super.onCharacteristicWrite(gatt, characteristic, status);
            if (status == BluetoothGatt.GATT_SUCCESS)
            {
                Log.e("20170424", "寫入成功" +characteristic);
                Log.e("20170424", "onCharacteristicWrite:是"+ new String(characteristic.getValue()));
            }

        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            super.onServicesDiscovered(gatt, status);

            if (status == BluetoothGatt.GATT_SUCCESS) {

                supportedGattServices =bluetoothGatt.getServices();

                for(int i=0;i<supportedGattServices.size();i++){

                    Log.e("20170424","Service 的 UUID : BluetoothGattService UUID=:"+supportedGattServices.get(i).getUuid());
                    listGattCharacteristic=supportedGattServices.get(i).getCharacteristics();

                    for(int j=0;j<listGattCharacteristic.size();j++)
                    {
                        Log.e("20170424","Characteristic 的 UUID : BluetoothGattCharacteristic UUID=:"+listGattCharacteristic.get(j).getUuid());
                    }

                }

                delay_handle.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        write_Into();
                        enableTXNotification();
                    }
                }, 1000);

            }
        }
    };

    private void write_Into(){


        service = bluetoothGatt.getService(ServerUUID);


         characteristic = service.getCharacteristic(CharacteristicUUID);




        try {
            characteristic.setValue(cmd_3000.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        characteristic.setWriteType(BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE);
        bluetoothGatt.writeCharacteristic(characteristic);

        enableTXNotification();


    }

    public void enableTXNotification()
    {

        BluetoothGattService RxService = bluetoothGatt.getService(ServerUUID);
        if (RxService == null) {
            Log.e("FuckyouBLE","UART service not found!");

            return;
        }
        BluetoothGattCharacteristic TxChar = RxService.getCharacteristic(TxCharacteristicUUID);
        if (TxChar == null) {
            Log.e("FuckyouBLE","Tx charateristic not found!");
            return;
        }
        bluetoothGatt.setCharacteristicNotification(TxChar, true);

        BluetoothGattDescriptor descriptor = TxChar.getDescriptor(DescriptorUUID);
        descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        bluetoothGatt.writeDescriptor(descriptor);

    }
}
