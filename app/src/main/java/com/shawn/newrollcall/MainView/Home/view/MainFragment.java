package com.shawn.newrollcall.MainView.Home.view;


import android.Manifest;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.shawn.newrollcall.FluxCenter.AppFluxCenter;
import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.view.AppBaseFragment;
import com.shawn.newrollcall.R;
import com.shawn.newrollcall.ScanBLEModel.RollCallBLEScanner;
import com.shawn.newrollcall.databinding.FragmentMainBinding;
import com.shawn.newrollcall.util.PermissionUtil;

import java.util.ArrayList;

/**
 * Created by Shawn Wu on 2017/11/31.
 *
 */

public class MainFragment extends AppBaseFragment implements View.OnClickListener{

    private FragmentMainBinding binding;
    public final static String TAG = "MainFragment";

    private static MainFragment instance;
    private String groupName;

    private final int ROLLCALL = 0 ,SETDRIVETIME = 1,TODO = 2;

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

        binding.rollcallButton.setOnClickListener(this);
        binding.rollcallButton.setId(ROLLCALL);
        binding.setBleDrive.setOnClickListener(this);
        binding.setBleDrive.setId(SETDRIVETIME);
        binding.todoButton.setOnClickListener(this);
        binding.todoButton.setId(TODO);


        return binding.getRoot();
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            groupName = AppFluxCenter.getStore().getSharedPreferences().getGroupName(getContext());
            binding.readyGroupName.setText(groupName);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        AppFluxCenter.getActionCreator().getSharedPreferencesCreator().saveGroupName(getContext(),mActivity.getResources().getString(R.string.rollcall_message2));
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case ROLLCALL:
                PermissionListener bluetoothpermission = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        Toast.makeText(getContext(),getResources().getString(R.string.coming_soon),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionDenied(ArrayList<String> deniedPermissions) {

                    }
                };
                PermissionUtil.setBlueToothPermission(getContext(),bluetoothpermission,getResources().getString(R.string.permission_message));
                break;

            case SETDRIVETIME:
                Toast.makeText(getContext(),getResources().getString(R.string.coming_soon),Toast.LENGTH_SHORT).show();
                break;

            case TODO:
                PermissionListener storegePermission = new PermissionListener() {
                @Override
                public void onPermissionGranted() {
                    Toast.makeText(getContext(),getResources().getString(R.string.coming_soon),Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onPermissionDenied(ArrayList<String> deniedPermissions) {

                }
            };

            PermissionUtil.setExternalStorage(view.getContext(),storegePermission,getResources().getString(R.string.permission_message));
                break;
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }


    @Override
    public void onFluxChanged(FluxAction fluxAction) {

    }

    @Override
    public void onFluxStoreRegistered() {

    }

    @Override
    public void onFluxStoreUnregistered() {

    }

}
