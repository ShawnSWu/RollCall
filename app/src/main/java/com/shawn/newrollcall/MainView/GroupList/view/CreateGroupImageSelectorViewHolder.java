package com.shawn.newrollcall.MainView.GroupList.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.shawn.newrollcall.FluxCenter.AppFluxCenter;
import com.shawn.newrollcall.R;

import java.util.ArrayList;

/**
 * Created by Shawn Wu on 2017/12/5.
 *
 */

public class CreateGroupImageSelectorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ImageView item_image;
    private ArrayList<CreateGroupImageSelectorItem> list;
    private RadioButton select_radiobutton;
    private int position;

    public CreateGroupImageSelectorViewHolder(View itemView) {
        super(itemView);

        item_image = itemView.findViewById(R.id.seletor_iamge_item);
        select_radiobutton = itemView.findViewById(R.id.radio_select);
        item_image.setOnClickListener(this);
    }

    public void setItem(ArrayList<CreateGroupImageSelectorItem> list,int position) {
        this.position = position;
        this.list = list;
        item_image.setImageResource(list.get(position).getResorce_image());
    }

    public void setSelectRadioBtutton(int position) {
        if(AppFluxCenter.getStore().getGroupListInfoStore().getSelectImagePosition() == position){
            select_radiobutton.setChecked(true);
        }else{
            select_radiobutton.setChecked(false);
        }
    }

    @Override
    public void onClick(View view) {
        AppFluxCenter.getActionCreator().getGroupListInfoCreator().updateSelectImage(position);
    }
}
