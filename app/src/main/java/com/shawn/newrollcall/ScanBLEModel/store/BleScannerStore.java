package com.shawn.newrollcall.ScanBLEModel.store;

import android.support.v7.app.AlertDialog;

import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.store.Store;
import com.shawn.newrollcall.ScanBLEModel.action.BleScannerActionType;

/**
 * Created by Shawn Wu on 2018/2/24.
 */

public class BleScannerStore extends Store {

    private AlertDialog rollCallDailog;

    @Override
    public void onFluxActionHandling(FluxAction fluxAction) {

        switch (fluxAction.getType()){

            case BleScannerActionType.FIND_NEW_DEVICE:
                emitted(fluxAction);
                break;
        }

    }

}
