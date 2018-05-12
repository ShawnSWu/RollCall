package com.shawn.newrollcall.MainView.Profile.view;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import com.shawn.newrollcall.BackEndAPI.action.APIType;
import com.shawn.newrollcall.FluxCenter.AppFluxCenter;
import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.view.AppBaseFragment;
import com.shawn.newrollcall.Intentcenter.action.IntentCenterActionsType;
import com.shawn.newrollcall.Intentcenter.action.IntentCenterRequestCode;
import com.shawn.newrollcall.MainView.Profile.event.UserInfo;
import com.shawn.newrollcall.MainView.Profile.action.AccountInfoType;
import com.shawn.newrollcall.R;
import com.shawn.newrollcall.databinding.FragmentProfileBinding;

import java.io.File;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Shawn Wu on 2017/1/31.
 *
 */

public class ProfileFragment extends AppBaseFragment implements View.OnClickListener{

    private FragmentProfileBinding binding;

    public final static String TAG = "ProfileFragment";

    private final int LOGOUT = 0, PROFILESETTING = 1, DRIVESETTING = 2, PROFILE_IMAGE = 3;

    private static ProfileFragment instance;

    private String account, password;


    public static ProfileFragment getInstance() {
        if (instance == null) {
            instance = new ProfileFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);

        binding.logout.setOnClickListener(this);
        binding.logout.setId(LOGOUT);
        binding.profileSettingLayout.setOnClickListener(this);
        binding.profileSettingLayout.setId(PROFILESETTING);
        binding.driveSettingLayout.setOnClickListener(this);
        binding.driveSettingLayout.setId(DRIVESETTING);
        binding.profileImage.setOnClickListener(this);
        binding.profileImage.setId(PROFILE_IMAGE);


        account = AppFluxCenter.getStore().getSharedPreferences().getSavedAccount(getContext());
        password = AppFluxCenter.getStore().getSharedPreferences().getSavedPassword(getContext());

        AppFluxCenter.getActionCreator().getAccountInfoCreator().getAccountInfomation(account,password);

        return binding.getRoot();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        AppFluxCenter.getActionCreator().getAccountInfoCreator().getProcfileGroupAndDeivceDataInfomation(account,password);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case LOGOUT:
                AppFluxCenter.getActionCreator().getSharedPreferencesCreator().saveAccountInfo(mActivity,"","");
                AppFluxCenter.getActionCreator().getIntentCenterActionsCreator().backLoginActivity(mActivity);
                break;

            case PROFILESETTING:
                Toast.makeText(getContext(),getResources().getString(R.string.coming_soon),Toast.LENGTH_SHORT).show();
                break;

            case DRIVESETTING:
                Toast.makeText(getContext(),getResources().getString(R.string.coming_soon),Toast.LENGTH_SHORT).show();
                break;

            case PROFILE_IMAGE:
                openGallery();
                break;
        }

    }

    private void openGallery() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, IntentCenterRequestCode.START_GALLERY);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            switch (requestCode) {

                case IntentCenterRequestCode.START_GALLERY:
                    if (data.getData() != null) {
                        Uri uri = data.getData();
                        File file = getFileByUri(uri, getContext());
                        AppFluxCenter.getActionCreator().getAccountInfoCreator().uploadProfileImageToImgur(file,mActivity,account, password);
                    }

                    break;
            }
        }
    }


    public File getFileByUri(Uri uri,Context context) {
        String path = null;
        if ("file".equals(uri.getScheme())) {
            path = uri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = context.getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=").append("'" + path + "'").append(")");
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[] { MediaStore.Images.ImageColumns._ID, MediaStore.Images.ImageColumns.DATA }, buff.toString(), null, null);
                int index = 0;
                int dataIdx = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    index = cur.getInt(index);
                    dataIdx = cur.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    path = cur.getString(dataIdx);
                }
                cur.close();
                if (index == 0) {
                } else {
                    Uri u = Uri.parse("content://media/external/images/media/" + index);
                    System.out.println("temp uri is :" + u);
                }
            }
            if (path != null) {
                return new File(path);
            }
        } else if ("content".equals(uri.getScheme())) {
            // 4.2.2以後
            String[] proj = { MediaStore.Images.Media.DATA };
            Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                path = cursor.getString(columnIndex);
            }
            cursor.close();

            return new File(path);
        } else {
            //Log.i(TAG, "Uri Scheme:" + uri.getScheme());
        }
        return null;
    }




    @Override
    public void onFluxChanged(FluxAction fluxAction) {

        switch (fluxAction.getType()){

            case AccountInfoType.GET_ACCOUNT_INFO_SUCCESS:
                UserInfo userInfo = (UserInfo)fluxAction.getData()[0];
                binding.email.setText(userInfo.getUserEmail());
                binding.username.setText(userInfo.getUserName());
                if(!userInfo.getUserProfileImage().equals("")) {
                    Glide.with(getContext()).load(userInfo.getUserProfileImage()).thumbnail(0.1f).into(binding.profileImage);
                }else{
                    binding.profileImage.setImageResource(R.mipmap.icon_profile_image);
                }
                break;

            case AccountInfoType.GET_ACCOUNT_INFO_FAIL:

                break;

            case AccountInfoType.UPLOAD_PROFILE_IMAGE_IMGUR:

                break;

            case IntentCenterActionsType.BACK_TO_LOGIN:
                mActivity.finish();
                break;

            case AccountInfoType.SAVE_IMGUR_URI_SUCCESS:
                AppFluxCenter.getActionCreator().getAccountInfoCreator().getAccountInfomation(account,password);
                break;

            case AccountInfoType.GET_ACCOUNT_GROUP_AND_DEVICE_DATA_SUCCESS:
                Integer groupCount =(Integer) fluxAction.getData()[0];
                Integer deviceCount =(Integer) fluxAction.getData()[1];
                binding.groupCount.setText(String.valueOf(groupCount));
                binding.deviceCount.setText(String.valueOf(deviceCount));
                break;

            case APIType.SERVER_ERROR:
                Toast.makeText(mActivity,getString(R.string.server_error),Toast.LENGTH_SHORT).show();
                break;
        }

    }

    @Override
    public void onFluxStoreRegistered() {
        AppFluxCenter.getStore().getAccountInfoStore().register(this);
        AppFluxCenter.getStore().getIntentCenterStore().register(this);
        AppFluxCenter.getStore().getAPIStore().register(this);
    }

    @Override
    public void onFluxStoreUnregistered() {
        AppFluxCenter.getStore().getAccountInfoStore().unRegister(this);
        AppFluxCenter.getStore().getIntentCenterStore().unRegister(this);
        AppFluxCenter.getStore().getAPIStore().unRegister(this);
    }

}
