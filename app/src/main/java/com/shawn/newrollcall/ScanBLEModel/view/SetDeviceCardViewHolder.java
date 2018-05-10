package com.shawn.newrollcall.ScanBLEModel.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shawn.newrollcall.R;
import com.shawn.newrollcall.ScanBLEModel.event.GroupItemDataResponse;

/**
 * Created by Shawn Wu on 2018/01/10.
 *
 */

public class SetDeviceCardViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageUri;
    private TextView peopleCount , setTime;

    public SetDeviceCardViewHolder(View itemView) {
        super(itemView);
        imageUri = itemView.findViewById(R.id.set_device_image);
        peopleCount = itemView.findViewById(R.id.group_people_count);
        setTime = itemView.findViewById(R.id.settime);
    }

    public void setItem(GroupItemDataResponse groupItemDataResponse){
        if(groupItemDataResponse != null){
            Glide.with(itemView).load(groupItemDataResponse.getGroup_image_uri()).thumbnail(0.1f).into(imageUri);
            peopleCount.setText(String.valueOf(groupItemDataResponse.getPeople_count()));
        }

    }

    public void setTime(String seconds){
        setTime.setText(seconds);
    }
}
