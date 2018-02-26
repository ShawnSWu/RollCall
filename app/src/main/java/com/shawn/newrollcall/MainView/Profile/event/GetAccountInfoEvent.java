package com.shawn.newrollcall.MainView.Profile.event;

import android.support.annotation.NonNull;

import com.shawn.newrollcall.BackEndAPI.BackEndAPI;
import com.shawn.newrollcall.FluxCenter.AbstractRequest;
import com.shawn.newrollcall.FluxCenter.AppFluxCenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shawn Wu on 2017/11/31.
 *
 */

public class GetAccountInfoEvent extends BackEndAPI {


    public GetAccountInfoEvent(String account,String passowrd) {
        run(requestBody(account,passowrd));
    }

    @Override
    protected void run(AbstractRequest abstractRequest) {

        GetAccountInfoRequestBody getAccountInfoRequestBody = (GetAccountInfoRequestBody)abstractRequest;

        Call<UserInfo> call = getBackEndService().getAccountInfo(getAccountInfoRequestBody);

        call.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(@NonNull Call<UserInfo> call, @NonNull Response<UserInfo> response) {

                if(response.isSuccessful()) {
                    UserInfo userInfo  = response.body();
                    AppFluxCenter.getActionCreator().getAccountInfoCreator().getAccountInfomationSuccess(userInfo);
                }

            }

            @Override
            public void onFailure(@NonNull Call<UserInfo> call, @NonNull Throwable t) {
                AppFluxCenter.getActionCreator().getAccountInfoCreator().getAccountInfomationFail();
            }
        });


    }

    public GetAccountInfoRequestBody requestBody(String account,String passowrd){
        return new GetAccountInfoRequestBody(account,passowrd);
    }

}
