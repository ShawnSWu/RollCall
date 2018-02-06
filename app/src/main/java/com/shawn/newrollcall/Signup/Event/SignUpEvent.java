package com.shawn.newrollcall.Signup.Event;

import android.support.annotation.NonNull;
import android.util.Log;

import com.shawn.newrollcall.BackEndAPI.BackEndAPI;
import com.shawn.newrollcall.FluxCenter.AbstractRequest;
import com.shawn.newrollcall.FluxCenter.AppFluxCenter;
import com.shawn.newrollcall.Signup.action.SignUpActionType;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shawn Wu on 2018/1/30.
 */

public class SignUpEvent extends BackEndAPI {


    public SignUpEvent(String email,String password,String username){
        run(requestBody(email,password,username));

    }

    @Override
    protected void run(AbstractRequest abstractRequest) {

        SignupRequestBody signupRequestBody = (SignupRequestBody)abstractRequest;

        Call<String> call = getBackEndService().signup(signupRequestBody);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {

                try{
                    switch (response.body()) {

                        case SignUpResponseType.SignUpSuccess:
                            AppFluxCenter.getActionCreator().getSignupAPICreator().signupSuccess();
                            break;

                        case SignUpResponseType.SignUpRepeatAccount:
                            AppFluxCenter.getActionCreator().getSignupAPICreator().signupFail(SignUpResponseType.SignUpRepeatAccount);
                            break;

                        case SignUpResponseType.SignUpFail:
                            AppFluxCenter.getActionCreator().getSignupAPICreator().signupFail(SignUpResponseType.SignUpFail);
                            break;
                    }
                }catch (NullPointerException e){
                    Log.e("SignUpEvent",e.getMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                AppFluxCenter.getActionCreator().getSignupAPICreator().signupError();
            }
        });

    }

    private SignupRequestBody requestBody(String email,String password,String username){
        return new SignupRequestBody(email,password,username);
    }
}
