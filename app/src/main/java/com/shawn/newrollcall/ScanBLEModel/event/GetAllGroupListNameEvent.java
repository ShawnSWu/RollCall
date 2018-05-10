package com.shawn.newrollcall.ScanBLEModel.event;

import android.support.annotation.NonNull;
import com.shawn.newrollcall.BackEndAPI.BackEndAPI;
import com.shawn.newrollcall.FluxCenter.AbstractRequest;
import com.shawn.newrollcall.FluxCenter.AppFluxCenter;
import com.shawn.newrollcall.MainView.GroupList.event.GroupListRequestBody;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shawn Wu on 2017/12/19.
 *
 */

public class GetAllGroupListNameEvent extends BackEndAPI {

    public GetAllGroupListNameEvent(String account) {
        run(requestBody(account));
    }


    @Override
    protected void run(AbstractRequest abstractRequest) {

        GroupListRequestBody groupListRequestBody = (GroupListRequestBody)abstractRequest;

        Call<ArrayList<String>> call = getBackEndService().getAllGroupName(groupListRequestBody);

        call.enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<String>> call, @NonNull Response<ArrayList<String>> response) {
                if(response.isSuccessful()) {
                    AppFluxCenter
                            .getActionCreator()
                            .getBleScannerCreator()
                            .getAllGroupNameSuccess(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<String>> call, @NonNull Throwable t) {

            }
        });

    }


    public GroupListRequestBody requestBody(String account){
        return new GroupListRequestBody(account);
    }

}
