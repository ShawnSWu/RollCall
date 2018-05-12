package com.shawn.newrollcall.ToDo.event;

import android.support.annotation.NonNull;

import com.shawn.newrollcall.BackEndAPI.BackEndAPI;
import com.shawn.newrollcall.FluxCenter.AbstractRequest;
import com.shawn.newrollcall.FluxCenter.AppFluxCenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shawn Wu on 2017/12/12.
 *
 */

public class DeleteToDoEvent extends BackEndAPI {

    public DeleteToDoEvent(String account, String password, String todo_content) {
        run(requestBody(account,password,todo_content));
    }
    @Override
    protected void run(AbstractRequest abstractRequest) {

        DeleteToDoRequestBody deleteToDoRequestBody = (DeleteToDoRequestBody)abstractRequest;

        Call<Boolean> call = getToDoAPI().deleteToDo(deleteToDoRequestBody);

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(@NonNull Call<Boolean> call, @NonNull Response<Boolean> response) {
                if(response.isSuccessful()) {
                    if(response.body()) {
                        AppFluxCenter.getActionCreator().getToDoCreator().deleteToDoSuccess();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Boolean> call, @NonNull Throwable t) {

            }
        });
    }

    public DeleteToDoRequestBody requestBody(String account, String password, String todo_content){
        return new DeleteToDoRequestBody(account,password,todo_content);
    }

}
