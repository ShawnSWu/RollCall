package com.shawn.newrollcall.SharedPreferences.action;

import android.app.Activity;
import android.content.Context;

import com.shawn.newrollcall.FluxCenter.action.FluxActionCreator;

/**
 * Created by Shawn Wu on 2017/11/30.
 *
 */

public class SharedPreferencesCreator extends FluxActionCreator {


    public void saveAccountInfo(Activity activity,String account, String password) {
        addAction(newAction(SharedPreferencesActionType.SAVE_ACCOUNT,activity,account,password));
    }

    public void saveGroupName(Context context, String groupName) {
        addAction(newAction(SharedPreferencesActionType.SAVEGROUPNAME,context,groupName));
    }

    public void deleteGroupName(Context context) {
        addAction(newAction(SharedPreferencesActionType.DELETEGROUPNAME,context));
    }

}
