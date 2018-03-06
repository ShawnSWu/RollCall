package com.shawn.newrollcall.Intentcenter.action;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.shawn.newrollcall.DeviceListInGroup.event.DeviceListInGroupItem;
import com.shawn.newrollcall.FluxCenter.action.FluxActionCreator;
import com.shawn.newrollcall.Intentcenter.IntentEvent;
import com.shawn.newrollcall.MainView.GroupList.view.CreateGroupActivity;
import com.shawn.newrollcall.MainView.MainActivity;
import com.shawn.newrollcall.RollCall.view.RollCallResultActivity;
import com.shawn.newrollcall.ScanBLEModel.view.RollCallActivity;
import com.shawn.newrollcall.ScanBLEModel.view.ScanActivity;
import com.shawn.newrollcall.login.view.LogInActivity;

import java.util.ArrayList;

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
                        .setFlag(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        .setTargetClass(CreateGroupActivity.class)
                        .build()));
    }

    public void startScanActivity(Activity activity,String listName,String imageUri) {
        Bundle bundle = new Bundle();
        bundle.putString(ScanActivity.GROUP_LIST_NAME, listName);
        bundle.putString(ScanActivity.IMAGE_URI, imageUri);
        addAction(newAction(
                IntentCenterActionsType.INTENT_SCAN_DEVICE,
                new IntentEvent.Builder()
                        .setStartAcivity(activity)
                        .setFlag(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        .setTargetClass(ScanActivity.class)
                        .build()
                ,bundle));
    }

    public void startRollCallActivity(Activity activity,String listName) {
        Bundle bundle = new Bundle();
        bundle.putString(RollCallActivity.GROUP_LIST_NAME, listName);
        addAction(newAction(
                IntentCenterActionsType.INTENT_ROLLCALL,
                new IntentEvent.Builder()
                        .setStartAcivity(activity)
                        .setFlag(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        .setTargetClass(RollCallActivity.class)
                        .build()
                ,bundle));
    }

    public void startResultActivity(Activity activity,String listName, int peopleCount, int absencePeople, ArrayList<DeviceListInGroupItem> restOfPeople) {
        Bundle bundle = new Bundle();
        bundle.putString(RollCallResultActivity.GROUP_LIST_NAME, listName);
        bundle.putInt(RollCallResultActivity.PEOPLE_COUNT, peopleCount);
        bundle.putInt(RollCallResultActivity.ABSENCE_PEOPLE, absencePeople);
        bundle.putParcelableArrayList(RollCallResultActivity.REST_OF_PEOPLE,restOfPeople);

        addAction(newAction(
                IntentCenterActionsType.INTENT_ROLLCALL_RESULT,
                new IntentEvent.Builder()
                        .setStartAcivity(activity)
                        .setFlag(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        .setTargetClass(RollCallResultActivity.class)
                        .build()
                ,bundle));
    }

}
