package com.shawn.newrollcall.BackEndAPI.ImgurAPI;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Shawn Wu on 2017/12/1.
 *
 */

public interface ImgurAPI {

    String SERVER="https://api.imgur.com";
    String AUTH= "5ffff3c618a9e30";

    @Headers("Authorization: Client-ID " + AUTH)
    @POST("/3/image/")
    Call<UploadImageRespose> postImage(
            @Body RequestBody image
    );
}
