package com.shawn.newrollcall.BackEndAPI.ImgurAPI;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.shawn.newrollcall.FluxCenter.AppFluxCenter;
import com.shawn.newrollcall.R;
import com.shawn.newrollcall.login.view.LodingFactory;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Shawn Wu on 2017/12/1.
 *
 */

public class UploadAsyncTask extends AsyncTask<String,Void,String> {

    private File file;
    private Activity activity;
    private String account;
    private String password;
    private KProgressHUD lodingview;


    public UploadAsyncTask(File file, Activity activity,String account,String password) {
        this.file = file;
        this.activity = activity;
        this.account = account;
        this.password = password;
        lodingview = LodingFactory.getLodingAnimation(R.string.please_wait,activity);
    }

    @Override
    protected String doInBackground(String... strings) {
        String imageUrl = null;

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ImgurAPI.SERVER)
                .build();

        ImgurAPI api=retrofit.create(ImgurAPI.class);
        try {

            RequestBody request = RequestBody.create(MediaType.parse("image/*"), file);
            Call<UploadImageRespose> call =  api.postImage(request);
            Response<UploadImageRespose> res = call.execute();

            if(res.isSuccessful()) {
                Log.e("TAG","Success link :"+res.body().data.link);
                imageUrl = res.body().data.getImageLink();


                AppFluxCenter.getActionCreator().getAccountInfoCreator().saveImgurUri(account,password,imageUrl);
               //存到後端
            } else {

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageUrl;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        startProgressDialog();
    }

    @Override
    protected void onPostExecute(String o) {
        super.onPostExecute(o);
        stopProgressDialog();
    }

    private void startProgressDialog() {
        if(!activity.isFinishing()) {
            lodingview.show();
        }
    }

    private void stopProgressDialog() {
        if(!activity.isFinishing()) {
            lodingview.dismiss();
        }
    }
}