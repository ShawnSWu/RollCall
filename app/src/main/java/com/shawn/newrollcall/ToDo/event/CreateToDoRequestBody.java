package com.shawn.newrollcall.ToDo.event;

import com.shawn.newrollcall.FluxCenter.AbstractRequest;

/**
 * Created by Shawn Wu on 2017/12/12.
 *
 */

public class CreateToDoRequestBody extends AbstractRequest {

    private String account,password,todo_title,todo_createtime;

    public CreateToDoRequestBody(String account, String password, String todo_title, String todo_createtime) {
        this.account = account;
        this.password = password;
        this.todo_title = todo_title;
        this.todo_createtime = todo_createtime;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getTodo_title() {
        return todo_title;
    }

    public String getTodo_createtime() {
        return todo_createtime;
    }
}
