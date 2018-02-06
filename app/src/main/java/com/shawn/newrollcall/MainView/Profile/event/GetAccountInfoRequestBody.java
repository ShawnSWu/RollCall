package com.shawn.newrollcall.MainView.Profile.event;

import com.shawn.newrollcall.FluxCenter.AbstractRequest;

/**
 * Created by Shawn Wu on 2018/1/29.
 */

public class GetAccountInfoRequestBody extends AbstractRequest {

    private String account;

    private String password;

    public GetAccountInfoRequestBody(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }
}
