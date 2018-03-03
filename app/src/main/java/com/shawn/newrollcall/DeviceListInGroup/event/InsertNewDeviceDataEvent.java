package com.shawn.newrollcall.DeviceListInGroup.event;

import android.support.annotation.NonNull;

import com.shawn.newrollcall.BackEndAPI.BackEndAPI;
import com.shawn.newrollcall.FluxCenter.AbstractRequest;
import com.shawn.newrollcall.FluxCenter.AppFluxCenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shawn Wu on 2017/12/26.
 *
 */

public class InsertNewDeviceDataEvent extends BackEndAPI {

    private int listsize ,itemPosition;

    public InsertNewDeviceDataEvent(String insert_type, String account, String list_name, String list_key, String list_value, String group_image_uri,int itemPosition,int listsize){
        this.listsize = listsize;
        this.itemPosition = itemPosition;
        run(requestBody(insert_type,account,list_name,list_key,list_value,group_image_uri));
    }

    @Override
    protected void run(AbstractRequest abstractRequest) {

        InsertNewDeviceDataRequestBody insertNewDeviceDataRequestBody = (InsertNewDeviceDataRequestBody)abstractRequest;

        Call<String> call = getBackEndService().insertNewDataToGroup(insertNewDeviceDataRequestBody);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                if(response.isSuccessful()) {
                    if (response.body().equals("insert Success")) {
                        if(itemPosition == listsize-1) {
                            AppFluxCenter.getActionCreator().getDeviceListInGroupCreator().insertNewDeviceDatatoGroupSuccess();
                        }

                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {

            }
        });

    }


    public InsertNewDeviceDataRequestBody requestBody(String insert_type, String account, String list_name, String list_key, String list_value, String group_image_uri){
        return new InsertNewDeviceDataRequestBody(insert_type,account,list_name,list_key,list_value,group_image_uri);
    }
}
