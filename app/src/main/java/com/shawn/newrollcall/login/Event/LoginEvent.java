package com.shawn.newrollcall.login.Event;

import android.support.annotation.NonNull;
import android.util.Log;

import com.shawn.newrollcall.FluxCenter.AppFluxCenter;
import com.shawn.newrollcall.BackEndAPI.BackEndAPI;
import com.shawn.newrollcall.FluxCenter.AbstractRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shawn Wu on 2017/11/29.
 *
 */

public class LoginEvent extends BackEndAPI {


    public LoginEvent(String account,String passowrd) {
        run(requestBody(account,passowrd));
    }

    protected void run(AbstractRequest abstractRequest) {

        LoginRequestBody loginRequestBody = (LoginRequestBody)abstractRequest;

        Call<Boolean> call = getBackEndService().login(loginRequestBody);

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(@NonNull Call<Boolean> call, @NonNull Response<Boolean> response) {
                try {
                    if (response.body()) {
                        AppFluxCenter.getActionCreator().getLoginAPICreator().loginSuccess();
                    } else {
                        AppFluxCenter.getActionCreator().getLoginAPICreator().loginFail();
                    }
                }catch (NullPointerException e){
                    Log.e("shawn_LoginEvent",e.getMessage());
                    e.printStackTrace();
            }

            }

            @Override
            public void onFailure(@NonNull Call<Boolean> call, @NonNull Throwable t) {
                AppFluxCenter.getActionCreator().getLoginAPICreator().loginError();
            }
        });
    }

    private LoginRequestBody requestBody(String account,String passowrd){
        return new LoginRequestBody(account,passowrd);
    }

}
