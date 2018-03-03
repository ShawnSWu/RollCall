package com.shawn.newrollcall.MainView.GroupList.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.shawn.newrollcall.FluxCenter.AppFluxCenter;
import com.shawn.newrollcall.R;
import com.shawn.newrollcall.DeviceListInGroup.view.DeviceListInGroupActivity;
import com.shawn.newrollcall.ScanBLEModel.view.ScanActivity;

/**
 * Created by Shawn Wu on 2017/12/2.
 *
 */

public class GroupCardItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private TextView groupListNameText,people_count;
    private ImageView defalut_image,card_setting,delete_group;
    private CardView groupCardView;
    private  AppCompatCheckBox checkBox;
    private String groupListName,defalut_image_Uri;
    private final int CHECKBOX = 0 ,GROUPCARDVIEW = 1,CARDSETTING = 2, DELETEGROUP = 3;

    private int selectPosition = -1;
    private Activity activity;
    private View itemView;


    public void setGroupListName(String groupListName) {
        this.groupListName = groupListName;
        this.groupListNameText.setText(groupListName);
    }

    public void setPeople_count(int people_count) {
        this.people_count.setText(String.valueOf(people_count));
    }

    public void setDefalut_image(String  defalut_image_Uri) {
        this.defalut_image_Uri = defalut_image_Uri;
        Glide.with(itemView).load(defalut_image_Uri).thumbnail(0.1f).into(defalut_image);
    }
//        AppFluxCenter.getStore().getGroupListInfoStore().getSelectGroupPosition()
    public void setCheckBox(Activity activity,String groupListName,int selectPosition){
        this.selectPosition = selectPosition;
        boolean isSelected = AppFluxCenter.getStore().getSharedPreferences().getGroupName(activity).equals(groupListName);
        checkBox.setChecked(isSelected);

    }

    public GroupCardItemViewHolder(View itemView, Activity activity) {
        super(itemView);
        this.itemView = itemView;
        this.activity = activity;
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
    public void onClick(final View view) {


        switch (view.getId()) {

            case CHECKBOX:
                AppFluxCenter.getActionCreator().getGroupListInfoCreator().updateSelectGroup(selectPosition);
                AppFluxCenter.getActionCreator().getSharedPreferencesCreator().saveGroupName(view.getContext(),groupListName);
                Snackbar.make(view,"點名團隊:"+groupListName,Snackbar.LENGTH_LONG).show();
                break;


            case GROUPCARDVIEW:
                Bundle bundle = new Bundle();
                bundle.putString(DeviceListInGroupActivity.GROUP_LIST_NAME, groupListName);
                bundle.putString(DeviceListInGroupActivity.IMAGE_URI, defalut_image_Uri);

                Intent i = new Intent();
                i.setClass(activity,DeviceListInGroupActivity.class);
                i.putExtras(bundle);
                activity.startActivity(i);
                break;


            case CARDSETTING:

                PopupMenu popupMenu = new PopupMenu(view.getContext(),card_setting);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        switch (menuItem.getItemId()) {

                            case R.id.add_quickly:
                                Bundle bundle = new Bundle();
                                bundle.putString(ScanActivity.GROUP_LIST_NAME, groupListName);
                                bundle.putString(ScanActivity.IMAGE_URI, defalut_image_Uri);

                                Intent i = new Intent();
                                i.setClass(activity,ScanActivity.class);
                                i.putExtras(bundle);
                                activity.startActivityForResult(i,ScanActivity.addDataRequestCode);
                                break;

                            case R.id.edit_manually:
                                Toast.makeText(view.getContext(),view.getResources().getString(R.string.coming_soon),Toast.LENGTH_SHORT).show();
                                break;

                        }



                        return true;
                    }
                });
                popupMenu.show();

                break;

            case DELETEGROUP:
                String account = AppFluxCenter.getStore().getSharedPreferences().getSavedAccount(view.getContext());
                AppFluxCenter.getActionCreator().getGroupListInfoCreator().deleteGroup(account,groupListName);
                break;


        }

    }

}
