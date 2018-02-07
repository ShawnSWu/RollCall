package com.shawn.newrollcall.Signup.store;

import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.store.Store;
import com.shawn.newrollcall.Signup.action.SignUpActionType;

/**
 * Created by Shawn Wu on 2017/11/30.
 *
 */

public class SignUpAPIStore extends Store {

    @Override
    public void onFluxActionHandling(FluxAction fluxAction) {

        switch (fluxAction.getType()) {

            case SignUpActionType.SIGN_UP:
                emitted(fluxAction);
                break;

            case SignUpActionType.SIGN_UP_SUCCESS:
                emitted(fluxAction);
                break;

            case SignUpActionType.SIGN_UP_FAIL:
                emitted(fluxAction);
                break;

            case SignUpActionType.SIGN_UP_ERROR:
                emitted(fluxAction);
                break;

        }

    }

}
