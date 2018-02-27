package com.shawn.newrollcall.ScanBLEModel.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.shawn.newrollcall.R;
import com.shawn.newrollcall.ScanBLEModel.event.DeviceListInGroupItem;

/**
 * Created by Shawn Wu on 2017/12/26.
 *
 */

public class DeviceListInGroupViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private DeviceListInGroupItem deviceListInGroupItem;

    private TextView deviceName,deviceAddress;


    public DeviceListInGroupViewHolder(View itemView) {
        super(itemView);
        deviceName = itemView.findViewById(R.id.device_name);
        deviceAddress = itemView.findViewById(R.id.device_address);
    }


    public void setItem(DeviceListInGroupItem deviceListInGroupItem){
        this.deviceListInGroupItem = deviceListInGroupItem;
        deviceName.setText(deviceListInGroupItem.getDeviceName());
        deviceAddress.setText(deviceListInGroupItem.getDeviceAddress());
    }

    @Override
    public void onClick(View view) {

    }
}
