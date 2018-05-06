package com.shawn.newrollcall.ScanBLEModel.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shawn.newrollcall.FluxCenter.AppFluxCenter;
import com.shawn.newrollcall.R;
import com.shawn.newrollcall.ScanBLEModel.BleDeviceItem;

/**
 * Created by Shawn Wu on 2017/12/24.
 *
 */

public class ScanDeviceListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

    private BleDeviceItem deviceItem;

    private TextView deviceName,deviceAddress;

    private LinearLayout scanItemView;

    public ScanDeviceListViewHolder(View itemView) {
        super(itemView);
        deviceName = itemView.findViewById(R.id.device_name);
        deviceAddress = itemView.findViewById(R.id.device_address);
        scanItemView = itemView.findViewById(R.id.scan_item);

    }

    protected void setItem(BleDeviceItem deviceItem){
        this.deviceItem = deviceItem;
        deviceName.setText(deviceItem.getName());
        deviceAddress.setText(deviceItem.getAddress());
        scanItemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        AppFluxCenter
                .getActionCreator()
                .getRollCallDialogCreator()
                .showMaunalEditDailog(view.getContext(),
                        deviceName.getText().toString(),
                        deviceAddress.getText().toString());
    }
}
