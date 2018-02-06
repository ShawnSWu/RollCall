package com.shawn.newrollcall.login.store;

import android.util.Log;

import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.store.Store;
import com.shawn.newrollcall.login.action.LoginAPIActionType;

/**
 * Created by Shawn Wu on 2017/11/29.
 *
 */

public class LoginAPIStore extends Store {


    @Override
    public void onFluxActionHandling(FluxAction fluxAction) {

        switch (fluxAction.getType()) {

            case LoginAPIActionType.API_LOGIN:
                emitted(fluxAction);
                break;

            case LoginAPIActionType.API_LOGIN_SUCCESS:
                emitted(fluxAction);
                break;

            case LoginAPIActionType.API_LOGIN_FAIL:
                emitted(fluxAction);
                break;

        }



    }
}
