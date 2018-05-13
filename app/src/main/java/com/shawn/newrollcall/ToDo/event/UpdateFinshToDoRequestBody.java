package com.shawn.newrollcall.ToDo.event;

import com.shawn.newrollcall.FluxCenter.AbstractRequest;

/**
 * Created by Shawn Wu on 2017/12/14.
 *
 */

public class UpdateFinshToDoRequestBody extends AbstractRequest {

    private String account,password,todo_title;
    private int todo_isFinsh;


    public UpdateFinshToDoRequestBody(String account, String password, String todo_title, int todo_isFinsh) {
        this.account = account;
        this.password = password;
        this.todo_title = todo_title;
        this.todo_isFinsh = todo_isFinsh;
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

    public int getTodo_isFinsh() {
        return todo_isFinsh;
    }
}
