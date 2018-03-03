package com.shawn.newrollcall.MainView.GroupList.view;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.shawn.newrollcall.MainView.GroupList.event.GetGroupListResponse;
import com.shawn.newrollcall.R;

import java.util.ArrayList;

/**
 * Created by Shawn Wu on 2017/12/2.
 *
 */

public class GroupCardItemRecyclerViewAdapter extends RecyclerView.Adapter<GroupCardItemViewHolder> {

    private ArrayList<GetGroupListResponse> groupCardItems;

    private Activity activity;

    public GroupCardItemRecyclerViewAdapter(Activity activity){
        groupCardItems = new ArrayList<>();
        this.activity = activity;
    }


    public void updata(ArrayList<GetGroupListResponse> getGroupList) {
        this.groupCardItems = getGroupList;
        notifyDataSetChanged();
    }

    @Override
    public GroupCardItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_group_list, parent, false);
        return new GroupCardItemViewHolder(view,activity);
    }

    @Override
    public void onBindViewHolder(GroupCardItemViewHolder holder, int position) {
        GetGroupListResponse groupCardItem = groupCardItems.get(position);
        holder.setGroupListName(groupCardItem.getListname());
        holder.setPeople_count(groupCardItem.getPeople_count());
        holder.setDefalut_image(groupCardItem.getGroup_image_uri());
        holder.setCheckBox(activity,groupCardItem.getListname(),position);


    }

    @Override
    public int getItemCount() {
        return groupCardItems.size();
    }


}