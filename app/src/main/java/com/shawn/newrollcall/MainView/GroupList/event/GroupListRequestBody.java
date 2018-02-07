package com.shawn.newrollcall.MainView.GroupList.event;

import com.shawn.newrollcall.FluxCenter.AbstractRequest;

/**
 * Created by Shawn Wu on 2017/12/3.
 */

public class GroupListRequestBody extends AbstractRequest {

    private String account;

    public GroupListRequestBody(String account) {
        this.account = account;
    }

    public String getAccount() {
        return account;
    }
}
