package com.shawn.newrollcall.ScanBLEModel.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;
import android.view.View;

import com.shawn.newrollcall.FluxCenter.AppFluxCenter;
import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.view.AppBaseActivity;
import com.shawn.newrollcall.R;
import com.shawn.newrollcall.ScanBLEModel.action.BleScannerActionType;
import com.shawn.newrollcall.ScanBLEModel.event.DeviceListInGroupItem;
import com.shawn.newrollcall.databinding.ActivityDeviceListInGroupBinding;

import java.util.ArrayList;

/**
 * Created by Shawn Wu on 2017/12/26.
 *
 */

public class DeviceListInGroupActivity extends AppBaseActivity {

    private ActivityDeviceListInGroupBinding binding;

    private DeviceListInGroupAdapter deviceListInGroupAdapter;

    private String listName,imageUri;

    public static String IMAGE_URI = "imageUri", GROUP_LIST_NAME = "listName";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_device_list_in_group);

        binding.deviceListActivityToolbar.setTitle(listName);

        setSupportActionBar(binding.deviceListActivityToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Bundle bundle = this.getIntent().getExtras();
        assert bundle != null;
        imageUri =  bundle.getString(IMAGE_URI);
        listName =  bundle.getString(GROUP_LIST_NAME);

        binding.progressView.setVisibility(View.VISIBLE);
        binding.notFoundDeviceInGroupView.setVisibility(View.GONE);



        AppFluxCenter
                .getActionCreator()
                .getBleScannerCreator()
                .getGroupDeviceData(AppFluxCenter.getStore().getSharedPreferences().getSavedAccount(this),listName);

        deviceListInGroupAdapter = new DeviceListInGroupAdapter();
        binding.deviceListRecyclerview.setAdapter(deviceListInGroupAdapter);
        binding.deviceListRecyclerview.setItemAnimator(new DefaultItemAnimator());
        binding.deviceListRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(1, OrientationHelper.VERTICAL));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFluxChanged(FluxAction fluxAction) {

        switch (fluxAction.getType()){

            case BleScannerActionType.GET_GROUP_DEVICE_DATA:
                binding.progressView.startAnimation();

                break;

            case BleScannerActionType.GET_GROUP_DEVICE_DATA_SUCCESS:
                ArrayList<DeviceListInGroupItem> deviceListInGroupItems =(ArrayList<DeviceListInGroupItem>)fluxAction.getData()[0];
                binding.progressView.setVisibility(View.GONE);
                if(deviceListInGroupItems.size() == 0){
                    binding.notFoundDeviceInGroupView.setVisibility(View.VISIBLE);
                }
                deviceListInGroupAdapter.update(deviceListInGroupItems);
                break;
        }

    }

    @Override
    public void onFluxStoreRegistered() {
        AppFluxCenter.getStore().getBleScannerStore().register(this);

    }

    @Override
    public void onFluxStoreUnregistered() {
        AppFluxCenter.getStore().getBleScannerStore().unRegister(this);
    }
}
