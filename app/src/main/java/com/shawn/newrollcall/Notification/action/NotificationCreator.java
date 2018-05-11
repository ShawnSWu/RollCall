package com.shawn.newrollcall.Notification.action;

import android.content.Context;

import com.shawn.newrollcall.FluxCenter.action.FluxActionCreator;

/**
 * Created by Shawn Wu on 2017/12/11.
 *
 */

public class NotificationCreator extends FluxActionCreator {

    public void toDoNotification(Context context,int chennalID,String title, String content) {
        addAction(newAction(NotificationType.TODO_NOTIFICATION,context,chennalID,title,content));
    }

}
