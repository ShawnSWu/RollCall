package com.shawn.newrollcall.SharedPreferences.store;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.store.Store;
import com.shawn.newrollcall.SharedPreferences.action.SharedPreferencesActionType;

import java.util.Set;

/**
 * Created by Shawn Wu on 2017/11/30.
 *
 */

public class SharedPreferencesStore extends Store {

    @Override
    public void onFluxActionHandling(FluxAction fluxAction) {

        switch (fluxAction.getType()) {

            case SharedPreferencesActionType.SAVE_ACCOUNT:
                Activity activity = (Activity) fluxAction.getData()[0];
                String account = (String)fluxAction.getData()[1];
                String password = (String)fluxAction.getData()[2];

                SharedPreferences setting = activity.getSharedPreferences(SharedPreferencesActionType.ACCOUNT,Context.MODE_PRIVATE);
                setting.edit()
                        .putString(SharedPreferencesActionType.SAVE_ACCOUNT,account)
                        .putString(SharedPreferencesActionType.SAVE_PASSWORD,password)
                        .apply();
                break;

        }

    }


    public String getSavedAccount(Context context){
        SharedPreferences setting = context.getSharedPreferences(SharedPreferencesActionType.ACCOUNT,Context.MODE_PRIVATE);
        return setting.getString(SharedPreferencesActionType.SAVE_ACCOUNT,"");
    }

    public String getSavedPassword(Context context){
        SharedPreferences setting = context.getSharedPreferences(SharedPreferencesActionType.ACCOUNT,Context.MODE_PRIVATE);
        return setting.getString(SharedPreferencesActionType.SAVE_PASSWORD,"");
    }

}
