package com.shawn.newrollcall.MainView.GroupList.view;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shawn.newrollcall.FluxCenter.AppFluxCenter;
import com.shawn.newrollcall.R;

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
                Snackbar.make(view,view.getContext().getString(R.string.ready_rollcall_team)+groupListName,Snackbar.LENGTH_LONG).show();
                break;


            case GROUPCARDVIEW:
                AppFluxCenter
                        .getActionCreator()
                        .getIntentCenterActionsCreator()
                        .startGroupListInGroupActivity(activity,groupListName,defalut_image_Uri);
                break;


            case CARDSETTING:

                PopupMenu popupMenu = new PopupMenu(view.getContext(),card_setting);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {

                        switch (menuItem.getItemId()) {

                            case R.id.add_quickly:
                                AppFluxCenter
                                        .getActionCreator()
                                        .getIntentCenterActionsCreator()
                                        .startScanActivity(activity,groupListName,defalut_image_Uri);
                                break;

                            case R.id.edit_manually:
                                AppFluxCenter
                                        .getActionCreator()
                                        .getIntentCenterActionsCreator()
                                        .startManualEditActivity(activity,groupListName,defalut_image_Uri);
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
                AppFluxCenter.getActionCreator().getSharedPreferencesCreator().deleteGroupName(view.getContext());
                break;


        }

    }

}
