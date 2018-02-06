package com.shawn.newrollcall.MainView.Profile.event;

import android.support.annotation.NonNull;
import android.util.Log;

import com.shawn.newrollcall.BackEndAPI.BackEndAPI;
import com.shawn.newrollcall.FluxCenter.AbstractRequest;
import com.shawn.newrollcall.FluxCenter.AppFluxCenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shawn Wu on 2017/11/10.
 *
 */

public class SaveImgurUriEvent extends BackEndAPI {

    public SaveImgurUriEvent(String account,String password, String imgurUri) {
        run(requestBody(account,password,imgurUri));
    }

    @Override
    protected void run(AbstractRequest abstractRequest) {

        SaveImgurUriRequestBody saveImgurUriRequestBody = (SaveImgurUriRequestBody)abstractRequest;

        Call<Boolean> call = getBackEndService().saveProfileImage(saveImgurUriRequestBody);

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(@NonNull Call<Boolean> call, @NonNull Response<Boolean> response) {
                Log.e("update",""+response.body());
                if (response.isSuccessful())
                    if(response.body()){
                        AppFluxCenter.getActionCreator().getAccountInfoCreator().saveImgurUriSuccess();
                    }
            }

            @Override
            public void onFailure(@NonNull Call<Boolean> call, @NonNull Throwable t) {

            }
        });

    }

    private SaveImgurUriRequestBody requestBody(String account,String password,String imgurUri){
        return new SaveImgurUriRequestBody(account,password,imgurUri);
    }
}
