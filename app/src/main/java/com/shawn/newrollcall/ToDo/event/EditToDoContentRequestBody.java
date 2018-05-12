package com.shawn.newrollcall.ToDo.event;

import com.shawn.newrollcall.FluxCenter.AbstractRequest;

/**
 * Created by Shawn Wu on 2017/12/13.
 *
 */

public class EditToDoContentRequestBody extends AbstractRequest {

    private String account,password,new_todo_title,old_todo_title,new_todo_createtime;


    public EditToDoContentRequestBody(String account, String password, String new_todo_title, String old_todo_title, String new_todo_createtime) {
        this.account = account;
        this.password = password;
        this.new_todo_title = new_todo_title;
        this.old_todo_title = old_todo_title;
        this.new_todo_createtime = new_todo_createtime;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getNew_todo_title() {
        return new_todo_title;
    }

    public String getOld_todo_title() {
        return old_todo_title;
    }

    public String getNew_todo_createtime() {
        return new_todo_createtime;
    }
}
