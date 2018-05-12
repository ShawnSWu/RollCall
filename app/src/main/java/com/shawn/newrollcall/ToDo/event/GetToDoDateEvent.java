package com.shawn.newrollcall.ToDo.event;

import android.support.annotation.NonNull;
import com.shawn.newrollcall.BackEndAPI.BackEndAPI;
import com.shawn.newrollcall.FluxCenter.AbstractRequest;
import com.shawn.newrollcall.FluxCenter.AppFluxCenter;
import com.shawn.newrollcall.ToDo.view.ToDoNoteItem;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shawn Wu on 2017/12/12.
 *
 */

public class GetToDoDateEvent extends BackEndAPI {


    public GetToDoDateEvent(String account,String password ){
        run(requestBody(account,password));
    }

    @Override
    protected void run(AbstractRequest abstractRequest) {

        GetToDoDateRequestBody groupListRequestBody = (GetToDoDateRequestBody)abstractRequest;

        Call<ArrayList<ToDoNoteItem>> call = getToDoAPI().getToDoDate(groupListRequestBody);

        call.enqueue(new Callback<ArrayList<ToDoNoteItem>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<ToDoNoteItem>> call, @NonNull Response<ArrayList<ToDoNoteItem>> response) {
                if(response.isSuccessful()) {
                    AppFluxCenter
                            .getActionCreator()
                            .getToDoCreator()
                            .getToDoDataSuccess(response.body());
                }else{
                    AppFluxCenter.getActionCreator().getAPICreator().serverError();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<ToDoNoteItem>> call, @NonNull Throwable t) {

            }
        });

    }

    public GetToDoDateRequestBody requestBody(String account,String password){
        return new GetToDoDateRequestBody(account,password);
    }

}
