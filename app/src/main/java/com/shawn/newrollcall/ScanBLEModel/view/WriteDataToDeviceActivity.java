package com.shawn.newrollcall.ScanBLEModel.view;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;

import com.shawn.newrollcall.FluxCenter.AppFluxCenter;
import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.R;
import com.shawn.newrollcall.ScanBLEModel.action.BleScannerActionType;

import dmax.dialog.SpotsDialog;

/**
 * Created by Shawn Wu on 2017/12/10.
 *
 */

public class WriteDataToDeviceActivity extends RollCallActivity {

    public static final String GROUP_LIST_NAME = "listName";
    public  static final String CHOOSE_SECOND="choose_second";

    public  static final String DEVICE_ARRAYLIST="Device_ArrayList";

    private String chooseSeconds,groupName;

    private static SpotsDialog spotsDialog = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        chooseSeconds = bundle.getString(WriteDataToDeviceActivity.CHOOSE_SECOND);
        groupName = bundle.getString(WriteDataToDeviceActivity.GROUP_LIST_NAME);
        binding.btnok.setText(getString(R.string.setting));
        spotsDialog = new SpotsDialog(WriteDataToDeviceActivity.this,R.style.connect_loding);
    }

    @Override
    protected View.OnClickListener getBtnOkLintener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppFluxCenter
                        .getActionCreator()
                        .getRollCallDialogCreator()
                        .showMakeSureSetDeviceDailog(getString(R.string.set_time),
                                String.format(getString(R.string.make_sure_set_device),rollCallBLEScanner.getDeviceList().size()),
                                WriteDataToDeviceActivity.this,chooseSeconds
                                ,rollCallBLEScanner.getDeviceList(),groupName);
            }
        };
    }


    @Override
    public void onFluxChanged(FluxAction fluxAction) {
        super.onFluxChanged(fluxAction);
        switch (fluxAction.getType()) {

            case BleScannerActionType.WRITETING_DATA_TO_DEVICE:
                if(spotsDialog==null) {
                    spotsDialog = new SpotsDialog(WriteDataToDeviceActivity.this,R.style.connect_loding);
                }
                spotsDialog.show();
                break;

            case BleScannerActionType.WRITE_DATA_TO_DEVICE_SUCCESS:
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run(){
                        if(spotsDialog!=null) {
                            spotsDialog.dismiss();
                        }
                        WriteDataToDeviceActivity.this.finish();
                        toast(getString(R.string.setting_finsh));
                    }
                }, 4000);
                break;

        }
    }

}
