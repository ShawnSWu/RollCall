package com.shawn.newrollcall.ToDo.action;

import com.shawn.newrollcall.FluxCenter.action.FluxActionCreator;
import com.shawn.newrollcall.ToDo.event.CreateToDoEvent;
import com.shawn.newrollcall.ToDo.event.DeleteToDoEvent;
import com.shawn.newrollcall.ToDo.event.EditToDoContentEvent;
import com.shawn.newrollcall.ToDo.event.GetToDoDateEvent;
import com.shawn.newrollcall.ToDo.event.UpdateFinshToDoEvent;
import com.shawn.newrollcall.ToDo.view.ToDoNoteItem;


import java.util.List;

/**
 * Created by Shawn Wu on 2017/12/12.
 *
 */

public class ToDoCreator extends FluxActionCreator {

    public void getToDoData(String account,String password) {
        addAction(newAction(ToDoActionType.GET_TODO_DATA,new GetToDoDateEvent(account,password)));
    }

    public void getToDoDataSuccess(List<ToDoNoteItem> toDoNoteItems) {
        addAction(newAction(ToDoActionType.GET_TODO_DATA_SUCCESS,toDoNoteItems));
    }

    public void createToDo(String account,String password,String todo_content,String todo_createtime) {
        addAction(newAction(ToDoActionType.CREATE_TODO,new CreateToDoEvent(account,password,todo_content,todo_createtime)));
    }

    public void createToDoSuccess() {
        addAction(newAction(ToDoActionType.CREATE_TODO_SUCCESS));
    }

    public void updateFinshToDo(String account,String password,String todo_title,int  todo_isFinsh) {
        addAction(newAction(ToDoActionType.UPDATE_FINSH_TODO,new UpdateFinshToDoEvent(account,password,todo_title,todo_isFinsh)));
    }

    public void updateFinshToDoSuccess() {
        addAction(newAction(ToDoActionType.UPDATE_FINSH_TODO_SUCCESS));
    }

    public void deleteToDo(String account,String password,String content) {
        addAction(newAction(ToDoActionType.DELETE_TODO,new DeleteToDoEvent(account,password,content)));
    }

    public void deleteToDoSuccess() {
        addAction(newAction(ToDoActionType.DELETE_TODO_SUCCESS));
    }

    public void editToDoContent(String account,String password,String new_content,String old_content,String new_todo_createtime) {
        addAction(newAction(ToDoActionType.UPDATE_TODO_CONTENT,new EditToDoContentEvent(account,password,new_content,old_content,new_todo_createtime)));
    }

    public void editToDoContentSuccess() {
        addAction(newAction(ToDoActionType.UPDATE_TODO_CONTENT_SUCCESS));
    }

}
