package com.shawn.newrollcall.ScanBLEModel.action;


import com.shawn.newrollcall.FluxCenter.action.FluxActionCreator;
import com.shawn.newrollcall.ScanBLEModel.BleDeviceItem;
import java.util.List;

/**
 * Created by Shawn Wu on 2017/12/24.
 *
 */

public class BleScannerCreator extends FluxActionCreator {

    public void updateFindNewDevice(List<BleDeviceItem> itemList,String deviceAddress,String deviceName) {
        addAction(newAction(BleScannerActionType.FIND_NEW_DEVICE,itemList,deviceAddress,deviceName));
    }

    public void resetDeviceName(String reset_device_name,String deviceAddress) {
        addAction(newAction(BleScannerActionType.RESET_DEVICE_NAME,reset_device_name,deviceAddress));
    }

    public void addDateSuccess() {
        addAction(newAction(BleScannerActionType.ADD_DATE_SUCCESS));
    }

}
