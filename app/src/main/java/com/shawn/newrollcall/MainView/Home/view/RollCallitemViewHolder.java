package com.shawn.newrollcall.MainView.Home.view;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.gun0912.tedpermission.PermissionListener;
import com.shawn.newrollcall.FluxCenter.AppFluxCenter;
import com.shawn.newrollcall.R;
import com.shawn.newrollcall.ScanBLEModel.BluetoothManager;
import com.shawn.newrollcall.util.PermissionUtil;

import java.util.ArrayList;


/**
 * Created by Shawn Wu on 2017/12/4.
 *
 */

public class RollCallitemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private RelativeLayout relativeLayout;
    private TextView titleName, otherDescription;
    private String groupName;
    private final int ROLLCALL = 0 , TODO = 1, SETDRIVETIME = 2;
    private Activity mActivity;


    public RollCallitemViewHolder(View itemView,Activity activity) {
        super(itemView);
        this.mActivity = activity;
        relativeLayout = itemView.findViewById(R.id.main_item);
        titleName = itemView.findViewById(R.id.main_title_text);
        otherDescription = itemView.findViewById(R.id.item_other_description);

    }

    public void setItem(String title,Integer image,String description,int position) {
        relativeLayout.setBackgroundResource(image);
        relativeLayout.getBackground().setAlpha(180);
        relativeLayout.setOnClickListener(this);
        titleName.setText(title);
        otherDescription.setText(description);
        relativeLayout.setId(position);

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case ROLLCALL:
                PermissionListener accessLocationPermission = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        if(BluetoothManager.checkIfTurnOnBluetooth(mActivity)) {
                            groupName = AppFluxCenter.getStore().getSharedPreferences().getGroupName(mActivity);
                            if(!groupName.equals("")) {
                                String account = AppFluxCenter.getStore().getSharedPreferences().getSavedAccount(mActivity);
                                AppFluxCenter.getActionCreator().getDeviceListInGroupCreator().getGroupDeviceDataCount(account,groupName);
                            }else{
                                Toast.makeText(mActivity,mActivity.getResources().getString(R.string.rollcall_message2),Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    }

                    @Override
                    public void onPermissionDenied(ArrayList<String> deniedPermissions) {

                    }
                };
                PermissionUtil.setAccessLocation(mActivity,accessLocationPermission,mActivity.getResources().getString(R.string.permission_message));
                break;

            case TODO:
                PermissionListener storegePermission = new PermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        Toast.makeText(mActivity,mActivity.getResources().getString(R.string.coming_soon),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionDenied(ArrayList<String> deniedPermissions) {

                    }
                };

                PermissionUtil.setExternalStorage(view.getContext(),storegePermission,mActivity.getResources().getString(R.string.permission_message));
                break;

            case SETDRIVETIME:
                AppFluxCenter.getActionCreator().getIntentCenterActionsCreator().startSetDeviceRemindActivity(mActivity);
                break;

                default:
                    Toast.makeText(mActivity,mActivity.getResources().getString(R.string.coming_soon),Toast.LENGTH_SHORT).show();
        }


    }


}
