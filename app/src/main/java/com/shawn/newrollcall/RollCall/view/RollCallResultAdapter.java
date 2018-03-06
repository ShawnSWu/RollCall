package com.shawn.newrollcall.RollCall.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shawn.newrollcall.DeviceListInGroup.event.DeviceListInGroupItem;
import com.shawn.newrollcall.R;

import java.util.ArrayList;


/**
 * Created by Shawn Wu on 2017/12/27.
 *
 */

public class RollCallResultAdapter extends RecyclerView.Adapter<RollCallResultViewHolder>{

    private ArrayList<DeviceListInGroupItem> absenceList;


    public RollCallResultAdapter(ArrayList<DeviceListInGroupItem> absenceList){
        this.absenceList = absenceList;
    }

    @Override
    public RollCallResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_absence_list, parent, false);
        return new RollCallResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RollCallResultViewHolder holder, int position) {
        holder.setItem(absenceList.get(position));
    }

    @Override
    public int getItemCount() {
        return absenceList.size();
    }
}
