package com.shawn.newrollcall.ScanBLEModel.event;

import android.support.annotation.NonNull;
import com.shawn.newrollcall.BackEndAPI.BackEndAPI;
import com.shawn.newrollcall.FluxCenter.AbstractRequest;
import com.shawn.newrollcall.FluxCenter.AppFluxCenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shawn Wu on 2017/12/19.
 *
 */

public class GetSomeOneGroupListDataEvent extends BackEndAPI {

    public GetSomeOneGroupListDataEvent(String account,String listname) {
        run(requestBody(account,listname));
    }


    @Override
    protected void run(AbstractRequest abstractRequest) {

        GroupItemRequestBody groupItemRequestBody = (GroupItemRequestBody)abstractRequest;

        Call<GroupItemDataResponse> call = getBackEndService().getSomeOneGroupData(groupItemRequestBody);

        call.enqueue(new Callback<GroupItemDataResponse>() {
            @Override
            public void onResponse(@NonNull Call<GroupItemDataResponse> call, @NonNull Response<GroupItemDataResponse> response) {
                if(response.isSuccessful()) {
                    AppFluxCenter
                            .getActionCreator()
                            .getBleScannerCreator()
                            .getSomeOneGroupDataSuccess(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<GroupItemDataResponse> call, @NonNull Throwable t) {

            }
        });

    }


    public GroupItemRequestBody requestBody(String account,String listname){
        return new GroupItemRequestBody(account,listname);
    }

}
