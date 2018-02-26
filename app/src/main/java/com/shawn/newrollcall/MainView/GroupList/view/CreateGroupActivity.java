package com.shawn.newrollcall.MainView.GroupList.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.shawn.newrollcall.FluxCenter.AppFluxCenter;
import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.view.AppBaseActivity;
import com.shawn.newrollcall.MainView.GroupList.action.GroupListInfoType;
import com.shawn.newrollcall.R;
import com.shawn.newrollcall.databinding.ActivityCreateGroupBinding;
import com.shawn.newrollcall.login.view.LodingFactory;
import com.shawn.newrollcall.util.InputUtil;

import java.util.ArrayList;

/**
 * Created by Shawn Wu on 2017/12/14.
 *
 */

public class CreateGroupActivity extends AppBaseActivity {

    private ActivityCreateGroupBinding binding;
    private CreateGroupImageSelectorAdapter groupImageSelectorAdapter;
    private int selected_position = -1;
    private ArrayList<CreateGroupImageSelectorItem> list = new ArrayList<>();
    private static KProgressHUD lodingview;
    private String account;

    private ArrayList<CreateGroupImageSelectorItem> getImageList(){
        list.add(new CreateGroupImageSelectorItem(R.mipmap.defult_card_iamge ,"https://i.imgur.com/G16NNos.jpg"));
        list.add(new CreateGroupImageSelectorItem(R.mipmap.defult_card_iamge2 ,"https://i.imgur.com/F4cwNJT.jpg"));
        list.add(new CreateGroupImageSelectorItem(R.mipmap.defult_card_iamge3 ,"https://i.imgur.com/A4p9Tix.jpg"));
        list.add(new CreateGroupImageSelectorItem(R.mipmap.defult_card_iamge4 ,"https://i.imgur.com/oKTl5Ka.jpg"));
        return list;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_group);

        binding.createGroupToolbar.setTitle(getResources().getString(R.string.create_group_title));
        binding.createGroupToolbar.inflateMenu(R.menu.create_group_toolbar_menu);

        setSupportActionBar(binding.createGroupToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        account = AppFluxCenter.getStore().getSharedPreferences().getSavedAccount(this);


        groupImageSelectorAdapter = new CreateGroupImageSelectorAdapter(getImageList());
        binding.groupImageSelector.setAdapter(groupImageSelectorAdapter);
        binding.groupImageSelector.setItemAnimator(new DefaultItemAnimator());
        binding.groupImageSelector.setLayoutManager(new StaggeredGridLayoutManager(1, OrientationHelper.VERTICAL));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_group_toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        lodingview = LodingFactory.getLodingAnimation(R.string.please_wait,this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                return true;


            case R.id.action_create_group:
                String account = AppFluxCenter.getStore().getSharedPreferences().getSavedAccount(this);
                String listname = binding.editAccount.getText().toString();
                String group_image_uri;

                if(AppFluxCenter.getStore().getGroupListInfoStore().getSelectImagePosition() == -1) {
                    Toast.makeText(this,getResources().getString(R.string.please_select_image),Toast.LENGTH_SHORT).show();
                }else{
                    if(InputUtil.checkEmpty(listname)) {
                        group_image_uri = list.get(selected_position).getImage_uri();
                        AppFluxCenter.getActionCreator().getGroupListInfoCreator().createGroup(account,listname,group_image_uri);
                        AppFluxCenter.getActionCreator().getGroupListInfoCreator().setSelectImagePositionZero();
                    }else{
                        Toast.makeText(this,getResources().getString(R.string.group_name_not_allow_blank),Toast.LENGTH_SHORT).show();
                    }
                }
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFluxChanged(FluxAction fluxAction) {

        switch (fluxAction.getType()) {

            case GroupListInfoType.UPDATE_SELECT_IMAGE:
                selected_position = (int)fluxAction.getData()[0];
                groupImageSelectorAdapter.update();
                break;

            case GroupListInfoType.CREATE_GROUP:
                if(!isFinishing()) {
                    lodingview.show();
                }
                break;


            case GroupListInfoType.CREATE_GROUP_SUCCESS:
                lodingview.dismiss();
                Toast.makeText(this,getResources().getString(R.string.create_success),Toast.LENGTH_SHORT).show();
                finish();
                AppFluxCenter.getActionCreator().getGroupListInfoCreator().getGroupListInfomation(account);
                break;

            case GroupListInfoType.CREATE_GROUP_FAIL:
                lodingview.dismiss();
                Toast.makeText(this,getResources().getString(R.string.create_fail),Toast.LENGTH_SHORT).show();
                break;

        }
    }

    @Override
    public void onFluxStoreRegistered() {
        AppFluxCenter.getStore().getGroupListInfoStore().register(this);
        AppFluxCenter.getStore().getSharedPreferences().register(this);
    }

    @Override
    public void onFluxStoreUnregistered() {
        AppFluxCenter.getStore().getGroupListInfoStore().unRegister(this);
        AppFluxCenter.getStore().getSharedPreferences().unRegister(this);
    }
}
