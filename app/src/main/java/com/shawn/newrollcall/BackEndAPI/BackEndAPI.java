package com.shawn.newrollcall.BackEndAPI;


import com.shawn.newrollcall.FluxCenter.AbstractRequest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Shawn Wu on 2017/1/28.
 *
 */

public abstract class BackEndAPI {

    private String baseUrl = "http://rollcallbackend.herokuapp.com";

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private BackEndService backEndService = retrofit.create(BackEndService.class);

    private ToDoAPI toDoAPI = retrofit.create(ToDoAPI.class);

    protected BackEndService getBackEndService() {
        return backEndService;
    }

    public ToDoAPI getToDoAPI() {
        return toDoAPI;
    }

    protected abstract void run(AbstractRequest abstractRequest);

}
