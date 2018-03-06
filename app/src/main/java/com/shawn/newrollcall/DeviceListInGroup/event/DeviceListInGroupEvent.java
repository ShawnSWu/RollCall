package com.shawn.newrollcall.DeviceListInGroup.event;

import android.support.annotation.NonNull;

import com.shawn.newrollcall.BackEndAPI.BackEndAPI;
import com.shawn.newrollcall.FluxCenter.AbstractRequest;
import com.shawn.newrollcall.FluxCenter.AppFluxCenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shawn Wu on 2017/12/27.
 *
 */

public class DeviceListInGroupEvent extends BackEndAPI {

    public DeviceListInGroupEvent(String account, String list_name) {
        run(requestBody(account,list_name));
    }

    @Override
    protected void run(AbstractRequest abstractRequest) {

        DeviceListInGroupRequestBody deviceListInGroupRequestBody = (DeviceListInGroupRequestBody)abstractRequest;

        Call<HashMap<String,String>> call = getBackEndService().getListDataInGroup(deviceListInGroupRequestBody);

        call.enqueue(new Callback<HashMap<String, String>>() {
            @Override
            public void onResponse(@NonNull Call<HashMap<String, String>> call, @NonNull Response<HashMap<String, String>> response) {

                if(response.isSuccessful()) {
                    HashMap<String,String> responseHashMap= response.body();
                    ArrayList<DeviceListInGroupItem> deviceListInGroupItems = new ArrayList<>();

                    assert responseHashMap != null;
                    for (String key : responseHashMap.keySet()) {
                        String value = responseHashMap.get(key);
                        if(!Objects.equals(key, "") && !Objects.equals(value, "")) {
                            deviceListInGroupItems.add(new DeviceListInGroupItem(value,key));
                        }
                    }
                    AppFluxCenter.getActionCreator().getDeviceListInGroupCreator().getGroupDeviceDataSuccess(deviceListInGroupItems);
                }
            }

            @Override
            public void onFailure(@NonNull Call<HashMap<String, String>> call, @NonNull Throwable t) {

            }
        });
    }


    public DeviceListInGroupRequestBody requestBody(String account, String list_name){
        return new DeviceListInGroupRequestBody(account,list_name);
    }
}
