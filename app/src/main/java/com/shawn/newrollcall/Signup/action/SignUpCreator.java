package com.shawn.newrollcall.Signup.action;

import com.shawn.newrollcall.FluxCenter.action.FluxActionCreator;
import com.shawn.newrollcall.Signup.Event.SignUpEvent;

/**
 * Created by Shawn Wu on 2017/11/30.
 *
 */

public class SignUpCreator extends FluxActionCreator {

    public void signup(String email,String password,String username) {
        addAction(newAction(SignUpActionType.SIGN_UP,new SignUpEvent(email,password,username)));
    }

    public void signupSuccess() {
        addAction(newAction(SignUpActionType.SIGN_UP_SUCCESS));
    }

    public void signupFail(String fail_type) {
        addAction(newAction(SignUpActionType.SIGN_UP_FAIL,fail_type));
    }

    public void signupError() {
        addAction(newAction(SignUpActionType.SIGN_UP_ERROR));
    }

}
