package com.shawn.newrollcall.ToDo.action;

/**
 * Created by Shawn Wu on 2017/12/12.
 *
 */

public interface ToDoActionType {

    String GET_TODO_DATA = "GET_TODO_DATA";
    String GET_TODO_DATA_SUCCESS = "GET_TODO_DATA_SUCCESS";
    String CREATE_TODO = "CREATE_TODO";
    String CREATE_TODO_SUCCESS = "CREATE_TODO_SUCCESS";
    String UPDATE_FINSH_TODO = "UPDATE_FINSH_TODO";
    String UPDATE_FINSH_TODO_SUCCESS = "UPDATE_FINSH_TODO_SUCCESS";
    String UPDATE_TODO_CONTENT = "UPDATE_TODO_CONTENT";
    String UPDATE_TODO_CONTENT_SUCCESS = "UPDATE_TODO_CONTENT_SUCCESS";
    String DELETE_TODO = "DELETE_TODO";
    String DELETE_TODO_SUCCESS = "DELETE_TODO_SUCCESS";
}