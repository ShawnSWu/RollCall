package com.shawn.newrollcall.Notification.store;
import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.store.Store;
import com.shawn.newrollcall.Notification.action.NotificationType;
/**
 * Created by Shawn Wu on 2017/12/11.
 *
 */

public class NotificationStore extends Store {

    @Override
    public void onFluxActionHandling(FluxAction fluxAction) {

        switch (fluxAction.getType()){

            case NotificationType.TODO_NOTIFICATION:
                emitted(fluxAction);
                break;
        }

    }


}
