package com.shawn.newrollcall.DeviceListInGroup.event;

import android.support.annotation.NonNull;

import com.shawn.newrollcall.BackEndAPI.BackEndAPI;
import com.shawn.newrollcall.FluxCenter.AbstractRequest;
import com.shawn.newrollcall.FluxCenter.AppFluxCenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shawn Wu on 2017/12/27.
 *
 */

public class GetCountDeviceListInGroupEvent extends BackEndAPI {

    public GetCountDeviceListInGroupEvent(String account, String list_name) {
        run(requestBody(account,list_name));
    }

    @Override
    protected void run(AbstractRequest abstractRequest) {

        DeviceListInGroupRequestBody deviceListInGroupRequestBody = (DeviceListInGroupRequestBody)abstractRequest;

        Call<Integer> call = getBackEndService().getCountListDataInGroup(deviceListInGroupRequestBody);

        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(@NonNull Call<Integer> call, @NonNull Response<Integer> response) {
                if(response.isSuccessful()) {
                    AppFluxCenter
                            .getActionCreator()
                            .getDeviceListInGroupCreator()
                            .getGroupDeviceDataCountSuccess(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Integer> call, @NonNull Throwable t) {

            }
        });
    }


    public DeviceListInGroupRequestBody requestBody(String account, String list_name){
        return new DeviceListInGroupRequestBody(account,list_name);
    }
}
