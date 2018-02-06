package com.shawn.newrollcall.Signup.Event;

import com.shawn.newrollcall.FluxCenter.AbstractRequest;

/**
 * Created by Shawn Wu on 2017/11/30.
 *
 */

public class SignupRequestBody extends AbstractRequest {

    private String signup_account;

    private String signup_password;

    private String signup_name;

    private String profile_image = "";

    public SignupRequestBody(String signup_account, String signup_password, String signup_name) {
        this.signup_account = signup_account;
        this.signup_password = signup_password;
        this.signup_name = signup_name;
    }

    public String getSignup_account() {
        return signup_account;
    }

    public String getSignup_password() {
        return signup_password;
    }

    public String getSignup_name() {
        return signup_name;
    }

    public String getProfile_image() {
        return profile_image;
    }


}
