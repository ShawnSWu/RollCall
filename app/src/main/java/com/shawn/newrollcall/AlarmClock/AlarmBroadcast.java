package com.shawn.newrollcall.AlarmClock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.shawn.newrollcall.FluxCenter.AppFluxCenter;



/**
 * Created by Shawn Wu on 2017/12/13.
 *
 */

public class AlarmBroadcast extends BroadcastReceiver {

    public static String TODO_TITLE_TAG = "TODO_TITLE_TAG";
    public static String TODO_CONTENT_TAG = "TODO_CONTENT_TAG";


    @Override
    public void onReceive(Context context, Intent intent) {

        synchronized (this) {
            String title= intent.getStringExtra(TODO_TITLE_TAG);
            String content= intent.getStringExtra(TODO_CONTENT_TAG);
            AppFluxCenter
                    .getActionCreator()
                    .getNotificationCreator()
                    .toDoNotification(context,content.hashCode(),title,content);
            String account = AppFluxCenter.getStore().getSharedPreferences().getSavedAccount(context);
            String password = AppFluxCenter.getStore().getSharedPreferences().getSavedPassword(context);
            AppFluxCenter.getActionCreator().getToDoCreator().updateFinshToDo(account,password,content,1);
        }
    }



}
