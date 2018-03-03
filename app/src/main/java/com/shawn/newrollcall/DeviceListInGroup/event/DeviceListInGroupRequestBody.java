package com.shawn.newrollcall.DeviceListInGroup.event;

import com.shawn.newrollcall.FluxCenter.AbstractRequest;

/**
 * Created by Shawn Wu on 2017/12/27.
 *
 */

public class DeviceListInGroupRequestBody  extends AbstractRequest {

    private String account;

    private String list_name;

    public DeviceListInGroupRequestBody(String account, String list_name) {
        this.account = account;
        this.list_name = list_name;
    }

    public String getAccount() {
        return account;
    }

    public String getList_name() {
        return list_name;
    }
}
