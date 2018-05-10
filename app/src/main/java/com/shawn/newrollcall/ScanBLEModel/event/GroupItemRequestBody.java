package com.shawn.newrollcall.ScanBLEModel.event;

import com.shawn.newrollcall.FluxCenter.AbstractRequest;

/**
 * Created by Shawn Wu on 2017/12/10.
 *
 */

public class GroupItemRequestBody extends AbstractRequest {

    private String account;

    private String list_name;

    public GroupItemRequestBody(String account, String list_name) {
        this.account = account;
        this.list_name = list_name;
    }

    public String getAccount() {
        return account;
    }

    public String getListName() {
        return list_name;
    }
}
