package com.shawn.newrollcall.ToDo.event;

import android.support.annotation.NonNull;

import com.shawn.newrollcall.BackEndAPI.BackEndAPI;
import com.shawn.newrollcall.FluxCenter.AbstractRequest;
import com.shawn.newrollcall.FluxCenter.AppFluxCenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shawn Wu on 2017/12/13.
 *
 */

public class EditToDoContentEvent extends BackEndAPI {


    public EditToDoContentEvent(String account, String password, String new_content, String old_content, String new_todo_createtime) {
        run(requestBody(account,password, new_content,old_content,new_todo_createtime));
    }

    @Override
    protected void run(AbstractRequest abstractRequest) {

        EditToDoContentRequestBody editToDoContentRequestBody = (EditToDoContentRequestBody)abstractRequest;

        Call<Boolean> call = getToDoAPI().updateToDoContent(editToDoContentRequestBody);

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(@NonNull Call<Boolean> call, @NonNull Response<Boolean> response) {
                if(response.isSuccessful()) {
                    if(response.body()) {
                        AppFluxCenter.getActionCreator().getToDoCreator().editToDoContentSuccess();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Boolean> call, @NonNull Throwable t) {

            }
        });

    }

    public EditToDoContentRequestBody requestBody(String account, String password, String new_todo_content,
                                                  String old_todo_content, String new_todo_createtime){
        return new EditToDoContentRequestBody(account,password,new_todo_content,old_todo_content,new_todo_createtime);
    }
}
