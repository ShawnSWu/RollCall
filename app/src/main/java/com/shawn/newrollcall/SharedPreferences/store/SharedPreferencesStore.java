package com.shawn.newrollcall.SharedPreferences.store;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.store.Store;
import com.shawn.newrollcall.SharedPreferences.action.SharedPreferencesActionType;

/**
 * Created by Shawn Wu on 2017/11/30.
 *
 */

public class SharedPreferencesStore extends Store {

    private Activity activity;
    private Context context;
    private SharedPreferences setting;
    private String groupName;

    @Override
    public void onFluxActionHandling(FluxAction fluxAction) {

        switch (fluxAction.getType()) {

            case SharedPreferencesActionType.SAVE_ACCOUNT:
                activity = (Activity) fluxAction.getData()[0];
                String account = (String)fluxAction.getData()[1];
                String password = (String)fluxAction.getData()[2];

                SharedPreferences setting = activity.getSharedPreferences(SharedPreferencesActionType.ACCOUNT,Context.MODE_PRIVATE);
                setting.edit()
                        .putString(SharedPreferencesActionType.SAVE_ACCOUNT,account)
                        .putString(SharedPreferencesActionType.SAVE_PASSWORD,password)
                        .apply();
                break;


            case SharedPreferencesActionType.SAVEGROUPNAME:
                context = (Context) fluxAction.getData()[0];
                groupName = (String)fluxAction.getData()[1];

                setting = context.getSharedPreferences(SharedPreferencesActionType.ACCOUNT,Context.MODE_PRIVATE);
                setting.edit()
                        .putString(SharedPreferencesActionType.SAVEGROUPNAME,groupName)
                        .apply();
                break;

            case SharedPreferencesActionType.DELETEGROUPNAME:
                context = (Context) fluxAction.getData()[0];
                groupName = "";

                setting = context.getSharedPreferences(SharedPreferencesActionType.ACCOUNT,Context.MODE_PRIVATE);
                setting.edit()
                        .putString(SharedPreferencesActionType.SAVEGROUPNAME,groupName)
                        .apply();

                break;

        }

    }


    public String getSavedAccount(Context context){
        return getSharedPreferencesData(context,SharedPreferencesActionType.SAVE_ACCOUNT);
    }

    public String getSavedPassword(Context context){
        return getSharedPreferencesData(context,SharedPreferencesActionType.SAVE_PASSWORD);
    }

    public String getGroupName(Context context){
        return getSharedPreferencesData(context,SharedPreferencesActionType.SAVEGROUPNAME);
    }

    private String getSharedPreferencesData(Context context,String type){
        SharedPreferences setting = context.getSharedPreferences(SharedPreferencesActionType.ACCOUNT,Context.MODE_PRIVATE);
        return setting.getString(type,"");
    }
}
