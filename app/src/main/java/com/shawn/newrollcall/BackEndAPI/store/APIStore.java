package com.shawn.newrollcall.BackEndAPI.store;

import com.shawn.newrollcall.BackEndAPI.action.APIType;
import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.store.Store;

/**
 * Created by Shawn Wu on 2017/12/13
 * .
 */

public class APIStore extends Store {
    @Override
    public void onFluxActionHandling(FluxAction fluxAction) {

        switch (fluxAction.getType()){
            case APIType.SERVER_ERROR:
                emitted(fluxAction);
                break;
        }
    }
}
