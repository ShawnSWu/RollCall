package com.shawn.newrollcall.MainView.GroupList.event;

import android.support.annotation.NonNull;
import com.shawn.newrollcall.BackEndAPI.BackEndAPI;
import com.shawn.newrollcall.FluxCenter.AbstractRequest;
import com.shawn.newrollcall.FluxCenter.AppFluxCenter;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shawn Wu on 2017/12/3.
 *
 */

public class GetGroupListInfoEvent extends BackEndAPI {


    public GetGroupListInfoEvent(String account) {
        run(requestBody(account));
    }

    @Override
    protected void run(AbstractRequest abstractRequest) {

        GroupListRequestBody groupListRequestBody = (GroupListRequestBody)abstractRequest;

        Call<ArrayList<GetGroupListResponse>> call = getBackEndService().getGroupListIndo(groupListRequestBody);

        call.enqueue(new Callback<ArrayList<GetGroupListResponse>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<GetGroupListResponse>> call, @NonNull Response<ArrayList<GetGroupListResponse>> response) {

                if (response.isSuccessful()) {
                    ArrayList<GetGroupListResponse> groupListInfoList = response.body();
                    if(groupListInfoList == null){
                        groupListInfoList = new ArrayList<>();
                    }
                    AppFluxCenter.getActionCreator().getGroupListInfoCreator().getGroupListInfomationSuccess(groupListInfoList);
                }

            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<GetGroupListResponse>> call, @NonNull Throwable t) {

            }
        });

    }


    private GroupListRequestBody requestBody(String account){
        return new GroupListRequestBody(account);
    }

}
