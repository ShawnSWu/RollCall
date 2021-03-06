package com.shawn.newrollcall.Intentcenter.store;

import android.os.Bundle;

import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.store.Store;
import com.shawn.newrollcall.Intentcenter.IntentEvent;
import com.shawn.newrollcall.Intentcenter.action.IntentCenterActionsType;

/**
 * Created by Shawn Wu on 2017/11/30.
 *
 */

public class IntentCenterStore extends Store {

    private IntentEvent event = null;

    private  Bundle bundle;

    @Override
    public void onFluxActionHandling(FluxAction fluxAction) {

        switch (fluxAction.getType()) {

            case IntentCenterActionsType.INTENT_SET_DEVICE_REMIND:
            case IntentCenterActionsType.INTENT_CREATE_GROUP:
            case IntentCenterActionsType.INTENT_MAIN_ACTIVITY:
            case IntentCenterActionsType.BACK_TO_LOGIN:
                event = (IntentEvent) fluxAction.getData()[0];
                event.doStartActivity();
                break;

            case IntentCenterActionsType.INTENT_OPEN_LIST_IN_GROUP:
            case IntentCenterActionsType.INTENT_WRITE_DATA_TO_DEVICE_ACTIVITY:
            case IntentCenterActionsType.INTENT_ROLLCALL_RESULT:
            case IntentCenterActionsType.INTENT_ROLLCALL:
            case IntentCenterActionsType.INTENT_SCAN_DEVICE:
                event = (IntentEvent) fluxAction.getData()[0];
                bundle = (Bundle)fluxAction.getData()[1];
                event.doStartActivityWithBundle(bundle);
                break;

            case IntentCenterActionsType.INTENT_WRITE_DATA_TO_DEVICE_SERVICE:
                event = (IntentEvent) fluxAction.getData()[0];
                bundle = (Bundle)fluxAction.getData()[1];
                event.doStartSerViceWithBundle(bundle);
                break;
        }
    }


}
