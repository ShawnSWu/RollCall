package com.shawn.newrollcall.ScanBLEModel.action;


import com.shawn.newrollcall.FluxCenter.action.FluxActionCreator;
import com.shawn.newrollcall.ScanBLEModel.BleDeviceItem;
import com.shawn.newrollcall.ScanBLEModel.event.GetAllGroupListNameEvent;
import com.shawn.newrollcall.ScanBLEModel.event.GetSomeOneGroupListDataEvent;
import com.shawn.newrollcall.ScanBLEModel.event.GroupItemDataResponse;

import java.util.ArrayList;
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

    public void getAllGroupName(String account) {
        addAction(newAction(BleScannerActionType.GET_ALL_GROUP_NAME,new GetAllGroupListNameEvent(account)));
    }

    public void getAllGroupNameSuccess(ArrayList<String> allGroupList) {
        addAction(newAction(BleScannerActionType.GET_ALL_GROUP_NAME_SUCCESS,allGroupList));
    }

    public void getSomeOneGroupData(String account,String listName) {
        addAction(newAction(BleScannerActionType.GET_SOMEONE_GROUP_DATA,new GetSomeOneGroupListDataEvent(account,listName)));
    }

    public void getSomeOneGroupDataSuccess(GroupItemDataResponse groupItemDataResponse) {
        addAction(newAction(BleScannerActionType.GET_SOMEONE_GROUP_DATA_SUCCESS,groupItemDataResponse));
    }

    public void writeDataToDevice() {
        addAction(newAction(BleScannerActionType.WRITETING_DATA_TO_DEVICE));
    }

    public void writeDataToDeviceSuccess() {
        addAction(newAction(BleScannerActionType.WRITE_DATA_TO_DEVICE_SUCCESS));
    }

}
