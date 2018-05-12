package com.shawn.newrollcall.BackEndAPI.action;

import com.shawn.newrollcall.FluxCenter.action.FluxActionCreator;

/**
 * Created by Shawn Wu on 2017/12/13.
 *
 */

public class APICreator extends FluxActionCreator {

    public void serverError() {
        addAction(newAction(APIType.SERVER_ERROR));
    }
}
