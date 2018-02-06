package com.shawn.newrollcall.MainView.GroupList.event;

import com.shawn.newrollcall.FluxCenter.AbstractRequest;

/**
 * Created by Shawn Wu on 2018/2/6.
 *
 */

public class CreateGroupRequestBody extends AbstractRequest {

    private String account;
    private String listname;
    private String group_image_uri;

    public CreateGroupRequestBody(String account, String listname, String group_image_uri) {
        this.account = account;
        this.listname = listname;
        this.group_image_uri = group_image_uri;
    }

    public String getAccount() {
        return account;
    }

    public String getListname() {
        return listname;
    }

    public String getGroup_image_uri() {
        return group_image_uri;
    }
}
