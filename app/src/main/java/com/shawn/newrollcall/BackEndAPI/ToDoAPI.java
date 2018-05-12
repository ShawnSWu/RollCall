package com.shawn.newrollcall.BackEndAPI;

import com.shawn.newrollcall.ToDo.event.CreateToDoRequestBody;
import com.shawn.newrollcall.ToDo.event.DeleteToDoRequestBody;
import com.shawn.newrollcall.ToDo.event.EditToDoContentRequestBody;
import com.shawn.newrollcall.ToDo.event.GetToDoDateRequestBody;
import com.shawn.newrollcall.ToDo.view.ToDoNoteItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Shawn Wu on 2017/12/12.
 *
 */

public interface ToDoAPI {


    @Headers("Content-Type: application/json")
    @POST("/todo/gettododata")
    Call<ArrayList<ToDoNoteItem>> getToDoDate(@Body GetToDoDateRequestBody getToDoDateRequestBody);

    @Headers("Content-Type: application/json")
    @POST("/todo/createnewtodo")
    Call<Boolean> createNewToDo(@Body CreateToDoRequestBody getToDoDateRequestBody);

    @Headers("Content-Type: application/json")
    @POST("/todo/updatetodocontent")
    Call<Boolean> updateToDoContent(@Body EditToDoContentRequestBody editToDoContentRequestBody);

    @Headers("Content-Type: application/json")
    @POST("/todo/deletetodo")
    Call<Boolean> deleteToDo(@Body DeleteToDoRequestBody deleteToDoRequestBody);
}
