package com.shawn.newrollcall.ScanBLEModel.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.shawn.newrollcall.FluxCenter.AppFluxCenter;

/**
 * Created by Shawn Wu on 2018/5/10.
 *
 */

public class WriteDataToDeviceActivity extends RollCallActivity {

    public static final String GROUP_LIST_NAME = "listName";
    public  static final String CHOOSE_SECOND="choose_second";

    public  static final String DEVICE_ARRAYLIST="Device_ArrayList";

    private String chooseSeconds;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        chooseSeconds = bundle.getString(WriteDataToDeviceActivity.CHOOSE_SECOND);

    }

    @Override
    protected View.OnClickListener getBtnOkLintener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppFluxCenter
                        .getActionCreator()
                        .getIntentCenterActionsCreator()
                        .startWriteDataToDeviceSerVice(WriteDataToDeviceActivity.this,chooseSeconds,rollCallBLEScanner.getDeviceList());
            }
        };
    }
}
