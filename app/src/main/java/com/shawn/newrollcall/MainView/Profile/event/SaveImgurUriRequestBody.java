package com.shawn.newrollcall.MainView.Profile.event;

import com.shawn.newrollcall.FluxCenter.AbstractRequest;

/**
 * Created by Shawn Wu on 2018/2/1.
 */

public class SaveImgurUriRequestBody extends AbstractRequest {

    private String account;

    private String password;

    private String profile_image;

    public SaveImgurUriRequestBody(String account, String password,String profile_image) {
        this.account = account;
        this.password = password;
        this.profile_image = profile_image;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }
}


