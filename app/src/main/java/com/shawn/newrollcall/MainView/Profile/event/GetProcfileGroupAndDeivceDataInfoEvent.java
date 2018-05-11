package com.shawn.newrollcall.MainView.Profile.event;

import android.support.annotation.NonNull;
import com.shawn.newrollcall.BackEndAPI.BackEndAPI;
import com.shawn.newrollcall.FluxCenter.AbstractRequest;
import com.shawn.newrollcall.FluxCenter.AppFluxCenter;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shawn Wu on 2017/11/31.
 *
 */

public class GetProcfileGroupAndDeivceDataInfoEvent extends BackEndAPI {

    public GetProcfileGroupAndDeivceDataInfoEvent(String account, String passowrd) {
        run(requestBody(account,passowrd));
    }

    @Override
    protected void run(AbstractRequest abstractRequest) {

        GetAccountInfoRequestBody getAccountInfoRequestBody = (GetAccountInfoRequestBody)abstractRequest;

        Call<List<Integer>> call = getBackEndService().getProcfileGroupAndDeivceDataInfo(getAccountInfoRequestBody);

        call.enqueue(new Callback<List<Integer>>() {
            @Override
            public void onResponse(@NonNull Call<List<Integer>> call, @NonNull Response<List<Integer>> response) {
                if(response.isSuccessful()) {
                    List<Integer> list  = response.body();
                    int deviceGroup = 0;
                    for (int device:list) {
                        deviceGroup += device;
                    }
                    AppFluxCenter
                            .getActionCreator()
                            .getAccountInfoCreator()
                            .getProcfileGroupAndDeivceDataInfomationSuccess(list.size(),deviceGroup);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Integer>> call, @NonNull Throwable t) {

            }
        });

    }

    public GetAccountInfoRequestBody requestBody(String account,String passowrd){
        return new GetAccountInfoRequestBody(account,passowrd);
    }

}
