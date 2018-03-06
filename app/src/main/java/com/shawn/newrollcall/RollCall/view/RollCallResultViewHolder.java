package com.shawn.newrollcall.RollCall.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shawn.newrollcall.DeviceListInGroup.event.DeviceListInGroupItem;
import com.shawn.newrollcall.R;

/**
 * Created by Shawn Wu on 2017/12/27.
 *
 */

public class RollCallResultViewHolder extends RecyclerView.ViewHolder {

    private ImageView iconPerson;
    private TextView deviceName;


    public RollCallResultViewHolder(View itemView) {
        super(itemView);
        iconPerson = itemView.findViewById(R.id.absence_list_icon);
        deviceName = itemView.findViewById(R.id.absence_list_devicename);
    }

    public void setItem(DeviceListInGroupItem deviceListInGroupItem){
        deviceName.setText(deviceListInGroupItem.getDeviceName());
    }

}
