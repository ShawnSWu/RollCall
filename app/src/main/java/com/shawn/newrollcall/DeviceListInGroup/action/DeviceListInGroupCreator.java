package com.shawn.newrollcall.DeviceListInGroup.action;

import com.shawn.newrollcall.DeviceListInGroup.event.DeviceListInGroupEvent;
import com.shawn.newrollcall.DeviceListInGroup.event.DeviceListInGroupItem;
import com.shawn.newrollcall.DeviceListInGroup.event.InsertNewDeviceDataEvent;
import com.shawn.newrollcall.FluxCenter.action.FluxActionCreator;


import java.util.ArrayList;

/**
 * Created by Shawn Wu on 2017/12/24.
 *
 */

public class DeviceListInGroupCreator  extends FluxActionCreator {

    public void insertNewDeviceDatatoGroup(String insert_type,String account,String list_name,String list_key,String list_value ,String group_image_uri,int itemPosition,int listsize) {
        addAction(newAction(DeviceListInGroupActionType.INSERT_NEW_DATA_TO_GROUP,new InsertNewDeviceDataEvent(insert_type,account,list_name,list_key,list_value,group_image_uri,itemPosition,listsize)));
    }

    public void insertNewDeviceDatatoGroupSuccess() {
        addAction(newAction(DeviceListInGroupActionType.INSERT_NEW_DATA_TO_GROUP_SUCCESS));
    }

    public void getGroupDeviceData(String account, String list_name) {
        addAction(newAction(DeviceListInGroupActionType.GET_GROUP_DEVICE_DATA,new DeviceListInGroupEvent(account,list_name)));
    }

    public void getGroupDeviceDataSuccess(ArrayList<DeviceListInGroupItem> deviceListInGroupItems) {
        addAction(newAction(DeviceListInGroupActionType.GET_GROUP_DEVICE_DATA_SUCCESS,deviceListInGroupItems));
    }
}
