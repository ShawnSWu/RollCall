package com.shawn.newrollcall.MainView.GroupList.event;

import android.support.annotation.NonNull;
import android.util.Log;

import com.shawn.newrollcall.BackEndAPI.BackEndAPI;
import com.shawn.newrollcall.FluxCenter.AbstractRequest;
import com.shawn.newrollcall.FluxCenter.AppFluxCenter;

import java.util.ConcurrentModificationException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shawn Wu on 2018/2/6.
 *
 */

public class DeleteGroupEvent extends BackEndAPI {

    public DeleteGroupEvent(String account, String listname) {
        run(requestBody(account,listname));
    }


    @Override
    protected void run(AbstractRequest abstractRequest) {

        DeleteGroupRequestBody deleteGroupRequestBody = (DeleteGroupRequestBody)abstractRequest;

        Call<Boolean> call = getBackEndService().deleteGroup(deleteGroupRequestBody);

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(@NonNull Call<Boolean> call, @NonNull Response<Boolean> response) {
                    if (response.isSuccessful()) {
                        if (response.body()) {
                            AppFluxCenter.getActionCreator().getGroupListInfoCreator().deleteGroupSuccess();

                        } else {
                            AppFluxCenter.getActionCreator().getGroupListInfoCreator().createGroupFail();
                        }
                    }
                }

            @Override
            public void onFailure(@NonNull Call<Boolean> call, @NonNull Throwable t) {

            }
        });

    }

    private DeleteGroupRequestBody requestBody(String account, String listname){
        return new DeleteGroupRequestBody(account,listname);
    }


}
