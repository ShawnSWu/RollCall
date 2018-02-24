package com.shawn.newrollcall.Intentcenter.action;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.shawn.newrollcall.FluxCenter.action.FluxActionCreator;
import com.shawn.newrollcall.Intentcenter.IntentEvent;
import com.shawn.newrollcall.MainView.GroupList.view.CreateGroupActivity;
import com.shawn.newrollcall.MainView.MainActivity;
import com.shawn.newrollcall.ScanBLEModel.view.ScanActivity;
import com.shawn.newrollcall.login.view.LogInActivity;

/**
 * Created by Shawn Wu on 2017/11/30.
 *
 */

public class IntentCenterActionsCreator extends FluxActionCreator {

    public void startMainActivity(Activity activity) {
        addAction(newAction(
                IntentCenterActionsType.INTENT_MAIN_ACTIVITY,
                new IntentEvent.Builder()
                        .setStartAcivity(activity)
                        .setFlag(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        .setTargetClass(MainActivity.class)
                        .build()));
    }

    public void backLoginActivity(Activity activity) {
        addAction(newAction(
                IntentCenterActionsType.BACK_TO_LOGIN,
                new IntentEvent.Builder()
                        .setStartAcivity(activity)
                        .setFlag(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        .setTargetClass(LogInActivity.class)
                        .build()));
    }

    public void startCreateGroupActivity(Activity activity) {
        addAction(newAction(
                IntentCenterActionsType.INTENT_CREATE_GROUP,
                new IntentEvent.Builder()
                        .setStartAcivity(activity)
                        .setFlag(0)
                        .setTargetClass(CreateGroupActivity.class)
                        .build()));
    }

    public void startScanActivity(Activity activity,String listName,String imageUri) {
        Bundle bundle = new Bundle();
        bundle.putString(ScanActivity.listName_Tag, listName);
        bundle.putString(ScanActivity.imageUri_Tag, imageUri);
        addAction(newAction(
                IntentCenterActionsType.INTENT_SCAN_DEVICE,
                new IntentEvent.Builder()
                        .setStartAcivity(activity)
                        .setFlag(0)
                        .setTargetClass(ScanActivity.class)
                        .build()
                ,bundle));
    }

}
