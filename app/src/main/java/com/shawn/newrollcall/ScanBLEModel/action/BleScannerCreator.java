package com.shawn.newrollcall.ScanBLEModel.action;


import com.shawn.newrollcall.FluxCenter.action.FluxActionCreator;
import com.shawn.newrollcall.ScanBLEModel.BleDeviceItem;

import java.util.List;

/**
 * Created by Shawn Wu on 2017/12/24.
 *
 */

public class BleScannerCreator extends FluxActionCreator {

    public void updateFindNewDevice(List<BleDeviceItem> itemList) {
        addAction(newAction(BleScannerActionType.FIND_NEW_DEVICE,itemList));
    }

}
