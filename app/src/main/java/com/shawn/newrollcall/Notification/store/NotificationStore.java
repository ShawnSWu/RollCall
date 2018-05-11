package com.shawn.newrollcall.Notification.store;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.store.Store;
import com.shawn.newrollcall.Notification.action.NotificationType;
import com.shawn.newrollcall.R;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Shawn Wu on 2017/12/11.
 *
 */

public class NotificationStore extends Store {

    @Override
    public void onFluxActionHandling(FluxAction fluxAction) {

        Log.e("++789",""+fluxAction.getType());
        switch (fluxAction.getType()){

            case NotificationType.TODO_NOTIFICATION:
                Context context = (Context)fluxAction.getData()[0];
                int chennalID = (int)fluxAction.getData()[1];
                String title = (String)fluxAction.getData()[2];
                String content = (String)fluxAction.getData()[3];

                NotificationCompat.Builder notification = new NotificationCompat.Builder(context)
                        .setContentTitle(title)
                        .setContentText(content)
                        .setSmallIcon(R.mipmap.icon_notification)
                        .setVibrate(new long[]{0, 200, 200, 200})
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(content));

                NotificationManager notificationManager = (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(chennalID, notification.build());
                break;
        }

    }


}
