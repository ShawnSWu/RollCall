package com.shawn.newrollcall.ScanBLEModel.store;

import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.store.Store;
import com.shawn.newrollcall.ScanBLEModel.action.BleScannerActionType;

/**
 * Created by Shawn Wu on 2017/12/24.
 *
 */

public class BleScannerStore extends Store {

    @Override
    public void onFluxActionHandling(FluxAction fluxAction) {

        switch (fluxAction.getType()){

            case BleScannerActionType.WRITETING_DATA_TO_DEVICE:
            case BleScannerActionType.WRITE_DATA_TO_DEVICE_SUCCESS:
            case BleScannerActionType.GET_SOMEONE_GROUP_DATA_SUCCESS:
            case BleScannerActionType.GET_ALL_GROUP_NAME_SUCCESS:
            case BleScannerActionType.RESET_DEVICE_NAME:
            case BleScannerActionType.FIND_NEW_DEVICE:
                emitted(fluxAction);
                break;

        }

    }

}
