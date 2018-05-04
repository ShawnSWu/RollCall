package com.shawn.newrollcall.MainView.Home.view;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shawn.newrollcall.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shawn Wu on 2017/12/4.
 *
 */

public class RollCallitemAdapter extends RecyclerView.Adapter<RollCallitemViewHolder> {

    private List<String> titleList = new ArrayList<>();
    private List<Integer> imageList = new ArrayList<>();
    private List<String> descriptionList = new ArrayList<>();
    private Activity activity;


    public RollCallitemAdapter(List<String> titleList, List<Integer> imageList, List<String> descriptionList, Activity activity){
        this.titleList = titleList;
        this.imageList = imageList;
        this.descriptionList = descriptionList;
        this.activity = activity;
    }

    @Override
    public RollCallitemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        return new RollCallitemViewHolder(view,activity);
    }

    @Override
    public void onBindViewHolder(RollCallitemViewHolder holder, int position) {
        holder.setItem(titleList.get(position),imageList.get(position),descriptionList.get(position),position);
    }

    @Override
    public int getItemCount() {
        return titleList.size();
    }

}
