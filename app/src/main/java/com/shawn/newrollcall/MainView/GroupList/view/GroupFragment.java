package com.shawn.newrollcall.MainView.GroupList.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.shawn.newrollcall.FluxCenter.AppFluxCenter;
import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.view.AppBaseFragment;
import com.shawn.newrollcall.MainView.GroupList.action.GroupListInfoType;
import com.shawn.newrollcall.MainView.GroupList.event.GetGroupListResponse;
import com.shawn.newrollcall.R;
import com.shawn.newrollcall.ScanBLEModel.action.BleScannerActionType;
import com.shawn.newrollcall.databinding.FragmentGroupBinding;
import com.shawn.newrollcall.login.view.LodingFactory;

import java.util.ArrayList;


/**
 * Created by Shawn Wu on2017/11/30.
 *
 */

public class GroupFragment extends AppBaseFragment {

    private FragmentGroupBinding binding;

    private GroupCardItemRecyclerViewAdapter groupCardItemRecyclerViewAdapter;

    public final static String TAG = "GroupFragment";

    private  String account;

    private static GroupFragment instance;
    private static KProgressHUD lodingview;

    public static GroupFragment getInstance() {
        if (instance == null) {
            instance = new GroupFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);

        binding.swiperefreshLayout.setOnRefreshListener(getRefresh());
        binding.swiperefreshLayout.setColorSchemeResources(R.color.theme_green);

        account = AppFluxCenter.getStore().getSharedPreferences().getSavedAccount(getContext());

        AppFluxCenter.getActionCreator().getGroupListInfoCreator().getGroupListInfomation(account);

        groupCardItemRecyclerViewAdapter = new GroupCardItemRecyclerViewAdapter(mActivity);
        binding.groupListRecyclerview.setAdapter(groupCardItemRecyclerViewAdapter);
        binding.groupListRecyclerview.setItemAnimator(new DefaultItemAnimator());
        binding.groupListRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(1, OrientationHelper.VERTICAL));

        binding.createGroupFab.attachToRecyclerView(binding.groupListRecyclerview);
        binding.createGroupFab.setColorNormal(getResources().getColor(R.color.theme_green));
        binding.createGroupFab.setColorPressed(getResources().getColor(R.color.btn_press_green));
        binding.createGroupFab.setColorRipple(getResources().getColor(R.color.app_background_color));
        binding.createGroupFab.setOnClickListener(getCreateGroupFabListener());

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        lodingview = LodingFactory.getLodingAnimation(R.string.please_wait,getContext());

    }

    private SwipeRefreshLayout.OnRefreshListener getRefresh(){
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AppFluxCenter.getActionCreator().getGroupListInfoCreator().getGroupListInfomation(account);
                        binding.swiperefreshLayout.setRefreshing(false);
                    }
                },2000);

            }
        };
    }

    public View.OnClickListener getCreateGroupFabListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppFluxCenter.getActionCreator().getIntentCenterActionsCreator().startCreateGroupActivity(mActivity);
            }
        };
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            AppFluxCenter.getActionCreator().getGroupListInfoCreator().getGroupListInfomation(account);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_group;
    }



    @Override
    public void onFluxChanged(FluxAction fluxAction) {


        switch (fluxAction.getType()){

            case GroupListInfoType.GET_GROUP_LIST_INFO_SUCCESS:

                //更新cardview
                ArrayList<GetGroupListResponse> getGroupList = (ArrayList<GetGroupListResponse>) fluxAction.getData()[0];

                groupCardItemRecyclerViewAdapter.updata(getGroupList);

                if(getGroupList.isEmpty()){
                    binding.groupListRecyclerview.setVisibility(View.GONE);
                    binding.emptyview.setVisibility(View.VISIBLE);
                }else{
                    binding.groupListRecyclerview.setVisibility(View.VISIBLE);
                    binding.emptyview.setVisibility(View.GONE);
                }
                break;

            case GroupListInfoType.CREATE_GROUP_SUCCESS:
                String listName = (String)fluxAction.getData()[0];
                String imageUri = (String)fluxAction.getData()[1];

                AppFluxCenter
                        .getActionCreator()
                        .getRollCallDialogCreator()
                        .showAddDataFromCreateGroup(mActivity
                                ,getString(R.string.dialog_content_message1)
                                ,String.format(getString(R.string.dialog_content_message2),listName)
                                ,listName
                                ,imageUri);

                break;

            case GroupListInfoType.DELETE_GROUP_SUCCESS:
                AppFluxCenter.getActionCreator().getGroupListInfoCreator().getGroupListInfomation(account);
                lodingview.dismiss();
                if(!binding.createGroupFab.isVisible()){
                    binding.createGroupFab.show();
                }
                break;


            case GroupListInfoType.DELETE_GROUP:
                if(!mActivity.isFinishing()) {
                    lodingview.show();
                }
                break;

            case GroupListInfoType.UPDATE_SELECT_GROUP:
                groupCardItemRecyclerViewAdapter.notifyDataSetChanged();
                break;

            case BleScannerActionType.ADD_DATE_SUCCESS:
                AppFluxCenter.getActionCreator().getGroupListInfoCreator().getGroupListInfomation(account);
                break;
        }

    }

    @Override
    public void onFluxStoreRegistered() {
        AppFluxCenter.getStore().getGroupListInfoStore().register(this);
        AppFluxCenter.getStore().getBleScannerStore().register(this);
    }

    @Override
    public void onFluxStoreUnregistered() {
        AppFluxCenter.getStore().getGroupListInfoStore().unRegister(this);
        AppFluxCenter.getStore().getBleScannerStore().unRegister(this);
    }

}
