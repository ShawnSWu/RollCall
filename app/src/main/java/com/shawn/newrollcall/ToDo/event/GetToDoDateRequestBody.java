package com.shawn.newrollcall.ToDo.event;

import com.shawn.newrollcall.FluxCenter.AbstractRequest;

/**
 * Created by Shawn Wu on 2017/11/29.
 *
 */

public class GetToDoDateRequestBody extends AbstractRequest {

    private String account;

    private String password;

    public GetToDoDateRequestBody(String account, String password) {
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
