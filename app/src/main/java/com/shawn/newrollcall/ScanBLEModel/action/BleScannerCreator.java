package com.shawn.newrollcall.ScanBLEModel.action;


import com.shawn.newrollcall.FluxCenter.action.FluxActionCreator;
import com.shawn.newrollcall.ScanBLEModel.BleDeviceItem;
import com.shawn.newrollcall.ScanBLEModel.event.DeviceListInGroupEvent;
import com.shawn.newrollcall.ScanBLEModel.event.DeviceListInGroupItem;
import com.shawn.newrollcall.ScanBLEModel.event.InsertNewDeviceDataEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shawn Wu on 2017/12/24.
 *
 */

public class BleScannerCreator extends FluxActionCreator {

    public void updateFindNewDevice(List<BleDeviceItem> itemList) {
        addAction(newAction(BleScannerActionType.FIND_NEW_DEVICE,itemList));
    }

    public void insertNewDeviceDatatoGroup(String insert_type,String account,String list_name,String list_key,String list_value ,String group_image_uri,int itemPosition,int listsize) {
        addAction(newAction(BleScannerActionType.INSERT_NEW_DATA_TO_GROUP,new InsertNewDeviceDataEvent(insert_type,account,list_name,list_key,list_value,group_image_uri,itemPosition,listsize)));
    }

    public void insertNewDeviceDatatoGroupSuccess() {
        addAction(newAction(BleScannerActionType.INSERT_NEW_DATA_TO_GROUP_SUCCESS));
    }

    public void getGroupDeviceData(String account, String list_name) {
        addAction(newAction(BleScannerActionType.GET_GROUP_DEVICE_DATA,new DeviceListInGroupEvent(account,list_name)));
    }

    public void getGroupDeviceDataSuccess(ArrayList<DeviceListInGroupItem> deviceListInGroupItems) {
        addAction(newAction(BleScannerActionType.GET_GROUP_DEVICE_DATA_SUCCESS,deviceListInGroupItems));
    }
}
