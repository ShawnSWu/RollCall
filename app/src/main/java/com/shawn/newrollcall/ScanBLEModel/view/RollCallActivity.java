package com.shawn.newrollcall.ScanBLEModel.view;


import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.shawn.newrollcall.DeviceListInGroup.action.DeviceListInGroupActionType;
import com.shawn.newrollcall.DeviceListInGroup.event.DeviceListInGroupItem;
import com.shawn.newrollcall.FluxCenter.AppFluxCenter;
import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.R;
import com.shawn.newrollcall.ScanBLEModel.action.BleScannerActionType;

import java.util.ArrayList;


/**
 * Created by Shawn Wu on 2017/12/24.
 *
 */

public class RollCallActivity extends ScanActivity {

    private ArrayList<DeviceListInGroupItem> deviceListInGroupItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding.btnok.setText(getString(R.string.rollcall_over));

        String account = AppFluxCenter.getStore().getSharedPreferences().getSavedAccount(this);
        AppFluxCenter.getActionCreator().getDeviceListInGroupCreator().getGroupDeviceData(account,listName);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menuText = findViewById(R.id.action_scan);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                return true;

            case R.id.action_scan:
                if(rollCallBLEScanner.isScaning()) {
                    showStopScanView();
                    binding.btnok.setEnabled(true);
                    binding.btnok.setBackgroundColor(getColor(R.color.theme_green));
                }else{
                    showStartScanView();
                    binding.btnok.setEnabled(false);
                    binding.btnok.setBackgroundColor(getColor(R.color.theme_gray));
                }
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected View.OnClickListener getBtnOkLintener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(deviceListInGroupItems == null)
                    return;
              showStopScanView();

              int peopleCount = deviceListInGroupItems.size();
              int absencePeople = deviceListInGroupItems.size() - rollCallBLEScanner.getDeviceList().size();

              ArrayList<DeviceListInGroupItem> castList = new ArrayList<>();
              for(int i=0;i<rollCallBLEScanner.getDeviceList().size();i++) {
                  String address = rollCallBLEScanner.getDeviceList().get(i).getAddress();
                  String name = rollCallBLEScanner.getDeviceList().get(i).getName();
                  castList.add(new DeviceListInGroupItem(name,address));
              }

              ArrayList<DeviceListInGroupItem> restOfPeople = getComputingList(castList);
              AppFluxCenter
                      .getActionCreator()
                      .getIntentCenterActionsCreator()
                      .startResultActivity(RollCallActivity.this,listName,peopleCount,absencePeople,restOfPeople);
              finish();
            }
        };
    }

    private ArrayList<DeviceListInGroupItem> getComputingList(ArrayList<DeviceListInGroupItem> scanItems){

        synchronized (this){
            for(int i =0;i< rollCallBLEScanner.getDeviceList().size();i++) {
                String listAddress = deviceListInGroupItems.get(i).getDeviceAddress();

                String scanAddress = scanItems.get(i).getDeviceAddress();

                if(listAddress.equals(scanAddress)) {
                    deviceListInGroupItems.remove(deviceListInGroupItems.get(i));
                }
            }
        }
        return deviceListInGroupItems;
    }


    @Override
    public void onFluxChanged(FluxAction fluxAction) {

        switch (fluxAction.getType()) {


            case BleScannerActionType.FIND_NEW_DEVICE:
                binding.searchview.setVisibility(View.GONE);
                binding.btnok.setEnabled(true);
                binding.btnok.setBackgroundColor(getColor(R.color.theme_green));

                for(int i =0;i<rollCallBLEScanner.getDeviceList().size();i++) {
                    String findAddress = rollCallBLEScanner.getDeviceList().get(i).getAddress();
                    String addressInGroup = deviceListInGroupItems.get(i).getDeviceAddress();
                    String insteadOfName = deviceListInGroupItems.get(i).getDeviceName();

                    if(findAddress.equals(addressInGroup)) {
                        rollCallBLEScanner.getDeviceList().get(i).setDeviceName(insteadOfName);
                    }
                }
                scanDeviceListAdapter.updateView(rollCallBLEScanner.getDeviceList());
                break;

            case DeviceListInGroupActionType.GET_GROUP_DEVICE_DATA_SUCCESS:
                ArrayList<DeviceListInGroupItem> deviceListInGroupItems
                        = (ArrayList<DeviceListInGroupItem>)fluxAction.getData()[0];
                this.deviceListInGroupItems = deviceListInGroupItems;
                break;

        }

    }

}
