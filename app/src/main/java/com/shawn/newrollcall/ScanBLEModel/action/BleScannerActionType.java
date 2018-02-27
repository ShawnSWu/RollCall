package com.shawn.newrollcall.ScanBLEModel.action;

/**
 * Created by Shawn Wu on 2017/12/24.
 *
 */

public interface BleScannerActionType {

    String FIND_NEW_DEVICE = "FIND_NEW_DEVICE";
    String INSERT_NEW_DATA_TO_GROUP = "INSERT_NEW_DATA_TO_GROUP";
    String INSERT_NEW_DATA_TO_GROUP_SUCCESS = "INSERT_NEW_DATA_TO_GROUP_SUCCESS";
    String GET_GROUP_DEVICE_DATA = "GET_GROUP_DEVICE_DATA";
    String GET_GROUP_DEVICE_DATA_SUCCESS = "GET_GROUP_DEVICE_DATA_SUCCESS";
}
