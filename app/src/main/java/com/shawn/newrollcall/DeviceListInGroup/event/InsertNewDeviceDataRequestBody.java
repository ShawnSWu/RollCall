package com.shawn.newrollcall.DeviceListInGroup.event;

import com.shawn.newrollcall.FluxCenter.AbstractRequest;


/**
 * Created by Shawn Wu on 2017/12/26.
 *
 */

public class InsertNewDeviceDataRequestBody extends AbstractRequest {

    private String insert_type = "";

    private String account;

    private String list_name;

    private String list_key;

    private String list_value;

    private String group_image_uri;

    public InsertNewDeviceDataRequestBody(String insert_type, String account, String list_name, String list_key, String list_value, String group_image_uri) {
        this.insert_type = insert_type;
        this.account = account;
        this.list_name = list_name;
        this.list_key = list_key;
        this.list_value = list_value;
        this.group_image_uri = group_image_uri;
    }

    public String getInsert_type() {
        return insert_type;
    }

    public String getAccount() {
        return account;
    }

    public String getList_name() {
        return list_name;
    }

    public String getGroup_image_uri() {
        return group_image_uri;
    }

    public String getList_key() {
        return list_key;
    }

    public String getList_value() {
        return list_value;
    }
}
