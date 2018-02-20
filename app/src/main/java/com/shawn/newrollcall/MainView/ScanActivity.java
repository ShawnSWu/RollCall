package com.shawn.newrollcall.MainView;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.view.AppBaseActivity;
import com.shawn.newrollcall.ScanBLEModel.RollCallBLEScanner;

/**
 * Created by Shawn Wu on 2017/12/11.
 * 參考舊版
 */

public class ScanActivity extends AppBaseActivity{

    private RollCallBLEScanner rollCallBLEScanner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        rollCallBLEScanner = new RollCallBLEScanner
                .Builder()
                .build(this);

        rollCallBLEScanner.startScan();
    }




    @Override
    public void onFluxChanged(FluxAction fluxAction) {

    }

    @Override
    public void onFluxStoreRegistered() {

    }

    @Override
    public void onFluxStoreUnregistered() {

    }
}
