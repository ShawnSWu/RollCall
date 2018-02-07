package com.shawn.newrollcall.MainView.GroupList.view;

import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.shawn.newrollcall.FluxCenter.AppFluxCenter;
import com.shawn.newrollcall.MainView.GroupList.event.DeleteGroupEvent;
import com.shawn.newrollcall.R;

/**
 * Created by Shawn Wu on 2018/2/2.
 */

public class GroupCardItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private TextView groupListNameText,people_count;
    private ImageView defalut_image,card_setting,delete_group;
    private CardView groupCardView;
    private  AppCompatCheckBox checkBox;
    private String groupListName;
    private final int CHECKBOX = 0 ,GROUPCARDVIEW = 1,CARDSETTING = 2, DELETEGROUP = 3;

    private int selectPosition = -1;

    private View itemView;


    public void setGroupListName(String groupListName) {
        this.groupListName = groupListName;
        this.groupListNameText.setText(groupListName);
    }

    public void setPeople_count(int people_count) {
        this.people_count.setText(String.valueOf(people_count));
    }

    public void setDefalut_image(String  defalut_image_Uri) {
        Glide.with(itemView).load(defalut_image_Uri).thumbnail(0.1f).into(defalut_image);
    }

    public void setCheckBox(int selectPosition){
        this.selectPosition = selectPosition;
        if(AppFluxCenter.getStore().getGroupListInfoStore().getSelectGroupPosition() == selectPosition){
            checkBox.setChecked(true);
        }else{
            checkBox.setChecked(false);
        }

    }

    public GroupCardItemViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        groupListNameText = itemView.findViewById(R.id.group_list_name);
        people_count = itemView.findViewById(R.id.people_count);
        defalut_image = itemView.findViewById(R.id.group_list_image);

        checkBox = itemView.findViewById(R.id.checkbox_select_group);
        groupCardView = itemView.findViewById(R.id.group_card_view);
        card_setting = itemView.findViewById(R.id.card_setting);
        delete_group = itemView.findViewById(R.id.delete_group);

        checkBox.setOnClickListener(this);
        checkBox.setId(CHECKBOX);
        groupCardView.setOnClickListener(this);
        groupCardView.setId(GROUPCARDVIEW);
        card_setting.setOnClickListener(this);
        card_setting.setId(CARDSETTING);
        delete_group.setOnClickListener(this);
        delete_group.setId(DELETEGROUP);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case CHECKBOX:
                Toast.makeText(view.getContext(),view.getResources().getString(R.string.coming_soon),Toast.LENGTH_SHORT).show();
                AppFluxCenter.getActionCreator().getGroupListInfoCreator().updateSelectGroup(selectPosition);

                AppFluxCenter.getActionCreator().getSharedPreferencesCreator().saveGroupName(view.getContext(),groupListName);
                break;


            case GROUPCARDVIEW:
                Toast.makeText(view.getContext(),view.getResources().getString(R.string.coming_soon),Toast.LENGTH_SHORT).show();
                break;


            case CARDSETTING:
                Toast.makeText(view.getContext(),view.getResources().getString(R.string.coming_soon),Toast.LENGTH_SHORT).show();
                break;

            case DELETEGROUP:
                String account = AppFluxCenter.getStore().getSharedPreferences().getSavedAccount(view.getContext());
                AppFluxCenter.getActionCreator().getGroupListInfoCreator().deleteGroup(account,groupListName);
                break;


        }

    }

}
