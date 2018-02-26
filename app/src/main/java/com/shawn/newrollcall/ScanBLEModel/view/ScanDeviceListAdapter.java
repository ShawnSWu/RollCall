package com.shawn.newrollcall.ScanBLEModel.view;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.shawn.newrollcall.R;
import com.shawn.newrollcall.ScanBLEModel.BleDeviceItem;

import java.util.LinkedList;
import java.util.List;


/**
 * Created by Shawn Wu on 2017/12/24
 * .
 */

public class ScanDeviceListAdapter extends RecyclerView.Adapter<ScanDeviceListViewHolder> {

    private List<BleDeviceItem> deviceItems;

    public ScanDeviceListAdapter(List<BleDeviceItem> deviceItems){
        this.deviceItems = deviceItems;
    }

    @Override
    public ScanDeviceListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_scan_device, parent, false);
        return new ScanDeviceListViewHolder(view);
    }

    public void updateView(List<BleDeviceItem> deviceItems){
        this.deviceItems = deviceItems;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(ScanDeviceListViewHolder holder, int position) {
        holder.setItem(deviceItems.get(position));
    }

    @Override
    public int getItemCount() {
        return deviceItems.size();
    }
}
