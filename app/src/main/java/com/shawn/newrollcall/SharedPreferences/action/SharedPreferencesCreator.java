package com.shawn.newrollcall.SharedPreferences.action;

import android.app.Activity;

import com.shawn.newrollcall.FluxCenter.action.FluxActionCreator;

/**
 * Created by Shawn Wu on 2018/1/30.
 */

public class SharedPreferencesCreator extends FluxActionCreator {


    public void saveAccountInfo(Activity activity,String account, String password) {
        addAction(newAction(SharedPreferencesActionType.SAVE_ACCOUNT,activity,account,password));
    }

}
