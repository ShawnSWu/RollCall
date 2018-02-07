package com.shawn.newrollcall.MainView.GroupList.event;

import com.shawn.newrollcall.FluxCenter.AbstractRequest;

/**
 * Created by Shawn Wu on 2017/12/6.
 *
 */

public class DeleteGroupRequestBody extends AbstractRequest {

    private String account;
    private String listname;

    public DeleteGroupRequestBody(String account, String listname) {
        this.account = account;
        this.listname = listname;
    }

    public String getAccount() {
        return account;
    }

    public String getListname() {
        return listname;
    }
}
