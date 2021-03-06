package com.shawn.newrollcall.BackEndAPI;


import com.shawn.newrollcall.DeviceListInGroup.event.DeviceListInGroupRequestBody;
import com.shawn.newrollcall.DeviceListInGroup.event.InsertNewDeviceDataRequestBody;
import com.shawn.newrollcall.MainView.GroupList.event.CreateGroupRequestBody;
import com.shawn.newrollcall.MainView.GroupList.event.DeleteGroupRequestBody;
import com.shawn.newrollcall.MainView.GroupList.event.GetGroupListResponse;
import com.shawn.newrollcall.MainView.GroupList.event.GroupListRequestBody;
import com.shawn.newrollcall.MainView.Profile.event.GetAccountInfoRequestBody;
import com.shawn.newrollcall.MainView.Profile.event.SaveImgurUriRequestBody;
import com.shawn.newrollcall.MainView.Profile.event.UserInfo;

import com.shawn.newrollcall.ScanBLEModel.event.GroupItemDataResponse;
import com.shawn.newrollcall.ScanBLEModel.event.GroupItemRequestBody;
import com.shawn.newrollcall.Signup.Event.SignupRequestBody;
import com.shawn.newrollcall.login.Event.LoginRequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Shawn Wu on 2017/10/26.
 *
 */

public interface BackEndService {

    @Headers("Content-Type: application/json")
    @POST("/account/login")
    Call<Boolean> login(@Body LoginRequestBody loginRequestBody);


    @Headers("Content-Type: application/json")
    @POST("/account/signup")
    Call<String> signup(@Body SignupRequestBody signupRequestBody);


    @Headers("Content-Type: application/json")
    @POST("/account/getprocfiledata")
    Call<UserInfo> getAccountInfo(@Body GetAccountInfoRequestBody getAccountInfoRequestBody);

    @Headers("Content-Type: application/json")
    @POST("/account/getprocfilegroupdevicedate")
    Call<List<Integer>> getProcfileGroupAndDeivceDataInfo(@Body GetAccountInfoRequestBody getAccountInfoRequestBody);

    @Headers("Content-Type: application/json")
    @POST("/account/saveprofileimage")
    Call<Boolean> saveProfileImage(@Body SaveImgurUriRequestBody saveImgurUriRequestBody);

    @Headers("Content-Type: application/json")
    @POST("/list/getalllistdata")
    Call<ArrayList<GetGroupListResponse>> getGroupListIndo(@Body GroupListRequestBody groupListRequestBody);

    @Headers("Content-Type: application/json")
    @POST("/list/creategroup")
    Call<Boolean> createGroup(@Body CreateGroupRequestBody createGroupRequestBody);

    @Headers("Content-Type: application/json")
    @POST("/list/deletegroup")
    Call<Boolean> deleteGroup(@Body DeleteGroupRequestBody deleteGroupRequestBody);

    @Headers("Content-Type: application/json")
    @POST("/list/insertnewdatatolist")
    Call<Boolean> insertNewDataToGroup(@Body InsertNewDeviceDataRequestBody insertNewDeviceDataRequestBody);

    @Headers("Content-Type: application/json")
    @POST("/list/getlistdata")
    Call<HashMap<String,String>> getListDataInGroup(@Body DeviceListInGroupRequestBody deviceListInGroupRequestBody);

    @Headers("Content-Type: application/json")
    @POST("/list/listcount")
    Call<Integer> getCountListDataInGroup(@Body DeviceListInGroupRequestBody deviceListInGroupRequestBody);

    @Headers("Content-Type: application/json")
    @POST("/list/alllistname")
    Call<ArrayList<String>> getAllGroupName(@Body GroupListRequestBody groupListRequestBody);

    @Headers("Content-Type: application/json")
    @POST("/list/getsomegrouplistdata")
    Call<GroupItemDataResponse> getSomeOneGroupData(@Body GroupItemRequestBody groupListRequestBody);

}
