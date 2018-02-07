package com.shawn.newrollcall.MainView.GroupList.event;

import android.support.annotation.NonNull;
import com.shawn.newrollcall.BackEndAPI.BackEndAPI;
import com.shawn.newrollcall.FluxCenter.AbstractRequest;
import com.shawn.newrollcall.FluxCenter.AppFluxCenter;
import java.util.ConcurrentModificationException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shawn Wu on 2017/12/6.
 *
 */

public class CreateGroupEvent extends BackEndAPI {

    public CreateGroupEvent(String account, String listname, String group_image_uri) {
        run(requestBody(account,listname,group_image_uri));
    }


    @Override
    protected void run(AbstractRequest abstractRequest) {

        CreateGroupRequestBody createGroupRequestBody = (CreateGroupRequestBody)abstractRequest;

        Call<Boolean> call = getBackEndService().createGroup(createGroupRequestBody);

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(@NonNull Call<Boolean> call, @NonNull Response<Boolean> response) {
                    if (response.isSuccessful()) {
                        if (response.body()) {
                            try {
                                AppFluxCenter.getActionCreator().getGroupListInfoCreator().createGroupSuccess();
                            }catch (ConcurrentModificationException ignored){

                            }

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

    private CreateGroupRequestBody requestBody(String account, String listname, String group_image_uri){
        return new CreateGroupRequestBody(account,listname,group_image_uri);
    }


}
