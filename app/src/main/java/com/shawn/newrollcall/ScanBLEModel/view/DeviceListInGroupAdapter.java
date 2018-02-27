package com.shawn.newrollcall.ScanBLEModel.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shawn.newrollcall.R;
import com.shawn.newrollcall.ScanBLEModel.event.DeviceListInGroupItem;

import java.util.ArrayList;

/**
 * Created by Shawn Wu on 2017/12/26.
 *
 */

public class DeviceListInGroupAdapter extends RecyclerView.Adapter<DeviceListInGroupViewHolder>{

    private ArrayList<DeviceListInGroupItem> deviceListInGroupItems;

    public DeviceListInGroupAdapter(){
        this.deviceListInGroupItems = new ArrayList<>();
    }

    @Override
    public DeviceListInGroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_device_in_group, parent, false);
        return new DeviceListInGroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DeviceListInGroupViewHolder holder, int position) {
        holder.setItem(deviceListInGroupItems.get(position));
    }

    public void update(ArrayList<DeviceListInGroupItem> deviceListInGroupItems){
        this.deviceListInGroupItems = deviceListInGroupItems;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return deviceListInGroupItems.size();
    }
}
