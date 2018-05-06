package com.shawn.newrollcall.ScanBLEModel.view;

import com.shawn.newrollcall.FluxCenter.AppFluxCenter;
import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.ScanBLEModel.BleDeviceItem;
import com.shawn.newrollcall.ScanBLEModel.action.BleScannerActionType;


/**
 * Created by Shawn Wu on 2018/01/06.
 *
 */

public class ManualEditActivity extends ScanActivity {


    @Override
    public void onFluxChanged(FluxAction fluxAction) {
        super.onFluxChanged(fluxAction);

        switch (fluxAction.getType()){

            case BleScannerActionType.FIND_NEW_DEVICE:
                String deviceAddress = (String)fluxAction.getData()[1];
                String deviceName = (String)fluxAction.getData()[2];

                AppFluxCenter
                        .getActionCreator()
                        .getRollCallDialogCreator()
                        .showMaunalEditDailog(this,deviceName,deviceAddress);

                break;

            case BleScannerActionType.RESET_DEVICE_NAME:
                String reset_device_name =  (String)fluxAction.getData()[0];
                String device_address =  (String)fluxAction.getData()[1];

                for(BleDeviceItem item :rollCallBLEScanner.getDeviceList()){
                    if(device_address.equals(item.getAddress())){
                        item.setDeviceName(reset_device_name);
                    }
                }
                scanDeviceListAdapter.notifyDataSetChanged();
                break;

        }
    }
}
