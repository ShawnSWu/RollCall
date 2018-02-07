package com.shawn.newrollcall.login.action;

import com.shawn.newrollcall.login.Event.LoginEvent;
import com.shawn.newrollcall.FluxCenter.action.FluxActionCreator;

/**
 * Created by Shawn Wu on 2017/11/29.
 *
 */

public class LoginAPICreator extends FluxActionCreator {

    public void login(String account,String password) {
        addAction(newAction(LoginAPIActionType.API_LOGIN,new LoginEvent(account,password)));
    }

    public void loginSuccess() {
        addAction(newAction(LoginAPIActionType.API_LOGIN_SUCCESS));
    }

    public void loginFail() {
        addAction(newAction(LoginAPIActionType.API_LOGIN_FAIL));
    }

    public void loginError() {
        addAction(newAction(LoginAPIActionType.API_LOGIN_ERROR));
    }

}
