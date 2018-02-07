package com.shawn.newrollcall.MainView.GroupList.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.shawn.newrollcall.R;
import java.util.ArrayList;

/**
 * Created by Shawn Wu on 2017/12/5.
 *
 */

public class CreateGroupImageSelectorAdapter extends RecyclerView.Adapter<CreateGroupImageSelectorViewHolder>{

    private ArrayList<CreateGroupImageSelectorItem> imageList;

    public CreateGroupImageSelectorAdapter(ArrayList<CreateGroupImageSelectorItem> imageList) {
        this.imageList = imageList;
    }

    @Override
    public CreateGroupImageSelectorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_selector, parent, false);
        return new CreateGroupImageSelectorViewHolder(view);
    }

    public void update(){
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(CreateGroupImageSelectorViewHolder holder, int position) {
        holder.setItem(imageList,position);
        holder.setSelectRadioBtutton(position);
    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

}
