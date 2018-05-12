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

public class CreateToDoEvent extends BackEndAPI {


    public CreateToDoEvent(String account, String password, String todo_content, String todo_createtime) {
        run(requestBody(account,password,todo_content,todo_createtime));
    }

    @Override
    protected void run(AbstractRequest abstractRequest) {

        CreateToDoRequestBody createToDoRequestBody = (CreateToDoRequestBody)abstractRequest;

        Call<Boolean> call = getToDoAPI().createNewToDo(createToDoRequestBody);

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(@NonNull Call<Boolean> call, @NonNull Response<Boolean> response) {
                if(response.isSuccessful()) {
                    if(response.body()) {
                        AppFluxCenter.getActionCreator().getToDoCreator().createToDoSuccess();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Boolean> call, @NonNull Throwable t) {

            }
        });

    }

    public CreateToDoRequestBody requestBody(String account, String password, String todo_content, String todo_createtime){
        return new CreateToDoRequestBody(account,password,todo_content,todo_createtime);
    }
}
