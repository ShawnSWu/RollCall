package com.shawn.newrollcall.ToDo.event;

import com.shawn.newrollcall.FluxCenter.AbstractRequest;

/**
 * Created by Shawn Wu on 2017/12/12.
 *
 */

public class DeleteToDoRequestBody extends AbstractRequest {

    private String account,password,todo_title;


    public DeleteToDoRequestBody(String account, String password, String todo_title) {
        this.account = account;
        this.password = password;
        this.todo_title = todo_title;
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
}
