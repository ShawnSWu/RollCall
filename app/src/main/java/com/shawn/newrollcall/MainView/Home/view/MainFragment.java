package com.shawn.newrollcall.MainView.Home.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.shawn.newrollcall.DeviceListInGroup.action.DeviceListInGroupActionType;
import com.shawn.newrollcall.FluxCenter.AppFluxCenter;
import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.view.AppBaseFragment;
import com.shawn.newrollcall.R;
import com.shawn.newrollcall.databinding.FragmentMainBinding;
import com.shawn.newrollcall.login.view.LodingFactory;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Shawn Wu on 2017/11/31.
 *
 */

public class MainFragment extends AppBaseFragment {

    private FragmentMainBinding binding;
    public final static String TAG = "MainFragment";

    private static MainFragment instance;
    private String groupName;

    private RollCallitemAdapter rollCallitemAdapter;

    private List<String> titleList;

    private List<Integer> imageList;

    private List<String> descriptionList;

    private static KProgressHUD lodingview;

    public static MainFragment getInstance() {
        if (instance == null) {
            instance = new MainFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        lodingview = LodingFactory.getLodingAnimation(R.string.please_wait,getContext());
        titleList = Arrays.asList(getString(R.string.start_rollcall),
                getString(R.string.todo_message),
                getString(R.string.setbledrive),
                getString(R.string.calender));

        imageList = Arrays.asList(R.mipmap.rollcall_item_bg
                ,R.mipmap.main_todo_item
                ,R.mipmap.main_alarmclock_item
                ,R.mipmap.main_calendar_item);

        descriptionList = Arrays.asList(getString(R.string.rollcall_message2),"","","");

        rollCallitemAdapter = new RollCallitemAdapter(titleList,imageList,descriptionList,mActivity);

        binding.mainItemRecyclerview.setAdapter(rollCallitemAdapter);
        binding.mainItemRecyclerview.setItemAnimator(new DefaultItemAnimator());
        binding.mainItemRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(1, OrientationHelper.VERTICAL));

        return binding.getRoot();
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            lodingview = LodingFactory.getLodingAnimation(R.string.please_wait,getContext());
            groupName = AppFluxCenter.getStore().getSharedPreferences().getGroupName(getContext());
            if(!groupName.equals("")){
                descriptionList.set(0,groupName);
            }else{
                descriptionList.set(0,getString(R.string.rollcall_message2));
            }
            rollCallitemAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppFluxCenter.getActionCreator().getSharedPreferencesCreator().deleteGroupName(getContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }


    @Override
    public void onFluxChanged(FluxAction fluxAction) {

        switch (fluxAction.getType()){

            case DeviceListInGroupActionType.GET_GROUP_DEVICE_COUNT:
                if(lodingview != null) {
                    lodingview.show();
                }
                break;

            case DeviceListInGroupActionType.GET_GROUP_DEVICE_COUNT_SUCCESS:
                if(lodingview != null) {
                    lodingview.dismiss();
                }
                Integer listCount = (Integer) fluxAction.getData()[0];
                if(listCount != 0){
                    AppFluxCenter.getActionCreator().getIntentCenterActionsCreator().startRollCallActivity(mActivity, groupName);
                }else{
                    Toast.makeText(getContext(),getResources().getString(R.string.not_found_device_in_group),Toast.LENGTH_SHORT).show();
                }

                break;
        }

    }

    @Override
    public void onFluxStoreRegistered() {
        AppFluxCenter.getStore().getDeviceListInGroupStore().register(this);
    }

    @Override
    public void onFluxStoreUnregistered() {
        AppFluxCenter.getStore().getDeviceListInGroupStore().unRegister(this);
    }

}
