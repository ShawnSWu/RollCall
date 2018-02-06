package com.shawn.newrollcall.MainView.Profile.action;

import android.app.Activity;

import com.shawn.newrollcall.BackEndAPI.ImgurAPI.UploadAsyncTask;
import com.shawn.newrollcall.MainView.Profile.event.GetAccountInfoEvent;
import com.shawn.newrollcall.MainView.Profile.event.SaveImgurUriEvent;
import com.shawn.newrollcall.MainView.Profile.event.UserInfo;
import com.shawn.newrollcall.FluxCenter.action.FluxActionCreator;

import java.io.File;


/**
 * Created by Shawn Wu on 2018/1/31.
 *
 */

public class AccountInfoCreator extends FluxActionCreator {

    public void getAccountInfomation(String account,String password) {
        addAction(newAction(AccountInfoType.GET_ACCOUNT_INFO,new GetAccountInfoEvent(account,password)));
    }

    public void getAccountInfomationSuccess(UserInfo userInfo) {
        addAction(newAction(AccountInfoType.GET_ACCOUNT_INFO_SUCCESS,userInfo));
    }

    public void getAccountInfomationFail() {
        addAction(newAction(AccountInfoType.GET_ACCOUNT_INFO_FAIL));
    }


    public void uploadProfileImageToImgur(File profile_image,Activity activity,String account,String password) {
        addAction(newAction(AccountInfoType.UPLOAD_PROFILE_IMAGE_IMGUR,new UploadAsyncTask(profile_image,activity,account,password).execute()));
    }

    public void saveImgurUri(String account,String password,String imgurUri) {
        addAction(newAction(AccountInfoType.SAVE_IMGUR_URI,new SaveImgurUriEvent(account,password,imgurUri)));
    }

    public void saveImgurUriSuccess() {
        addAction(newAction(AccountInfoType.SAVE_IMGUR_URI_SUCCESS));
    }


}
