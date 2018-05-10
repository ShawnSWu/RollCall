package com.shawn.newrollcall.ScanBLEModel.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shawn.newrollcall.R;
import com.shawn.newrollcall.ScanBLEModel.event.GroupItemDataResponse;

/**
 * Created by Shawn Wu on 2018/01/10.
 *
 */

public class SetDeviceCardViewAdapter extends RecyclerView.Adapter<SetDeviceCardViewHolder>{

    private GroupItemDataResponse groupItemDataResponse;

    private String seconds = "10秒鐘";

    @Override
    public SetDeviceCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_set_device_cardview, parent, false);
        return new SetDeviceCardViewHolder(view);
    }

    public void updateSetTime(String seconds){
        this.seconds = seconds;
        notifyDataSetChanged();
    }

    public void updateSelectGroup(GroupItemDataResponse groupItemDataResponse){
        this.groupItemDataResponse = groupItemDataResponse;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(SetDeviceCardViewHolder holder, int position) {
        holder.setItem(groupItemDataResponse);
        holder.setTime(seconds);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

}
