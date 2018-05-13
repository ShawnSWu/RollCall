package com.shawn.newrollcall.RollCallDialog.store;

import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.store.Store;
import com.shawn.newrollcall.RollCallDialog.action.RollCallDialogActionType;

/**
 * Created by Shawn Wu on 2017/12/04.
 *
 */

public class RollCallDialogStore  extends Store {

    private long timeSeconds = 0;

    @Override
    public void onFluxActionHandling(FluxAction fluxAction) {

        switch (fluxAction.getType()){

            case RollCallDialogActionType.CREATE_TODO:
                long seconds =(long)fluxAction.getData()[0];
                break;
        }

    }

    public long getTimeSeconds(){
        return timeSeconds;
    }


}
