package com.shawn.newrollcall.BackEndAPI;


import com.shawn.newrollcall.MainView.GroupList.event.CreateGroupRequestBody;
import com.shawn.newrollcall.MainView.GroupList.event.DeleteGroupRequestBody;
import com.shawn.newrollcall.MainView.GroupList.event.GetGroupListResponse;
import com.shawn.newrollcall.MainView.GroupList.event.GroupListRequestBody;
import com.shawn.newrollcall.MainView.Profile.event.GetAccountInfoRequestBody;
import com.shawn.newrollcall.MainView.Profile.event.SaveImgurUriRequestBody;
import com.shawn.newrollcall.MainView.Profile.event.UserInfo;
import com.shawn.newrollcall.Signup.Event.SignupRequestBody;
import com.shawn.newrollcall.login.Event.LoginRequestBody;

import java.util.ArrayList;

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

}
