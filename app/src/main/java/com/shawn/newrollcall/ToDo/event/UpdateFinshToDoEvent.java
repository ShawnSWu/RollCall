package com.shawn.newrollcall.ToDo.event;

import android.support.annotation.NonNull;

import com.shawn.newrollcall.BackEndAPI.BackEndAPI;
import com.shawn.newrollcall.FluxCenter.AbstractRequest;
import com.shawn.newrollcall.FluxCenter.AppFluxCenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shawn Wu on 2017/12/14.
 *
 */

public class UpdateFinshToDoEvent extends BackEndAPI {

    public UpdateFinshToDoEvent(String account, String password, String todo_title, int todo_isFinsh) {
        run(requestBody(account,password,todo_title,todo_isFinsh));
    }

    @Override
    protected void run(AbstractRequest abstractRequest) {

        UpdateFinshToDoRequestBody updateFinshToDoRequestBody = (UpdateFinshToDoRequestBody)abstractRequest;

        Call<Boolean> call = getToDoAPI().updateFinshToDo(updateFinshToDoRequestBody);

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(@NonNull Call<Boolean> call, @NonNull Response<Boolean> response) {
                if(response.isSuccessful()) {
                    AppFluxCenter
                            .getActionCreator()
                            .getToDoCreator()
                            .updateFinshToDoSuccess();
                }else{
                    AppFluxCenter.getActionCreator().getAPICreator().serverError();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Boolean> call, @NonNull Throwable t) {

            }
        });
    }


    public UpdateFinshToDoRequestBody requestBody(String account, String password, String todo_title, int todo_isFinsh){
        return new UpdateFinshToDoRequestBody(account,password,todo_title,todo_isFinsh);
    }
}
