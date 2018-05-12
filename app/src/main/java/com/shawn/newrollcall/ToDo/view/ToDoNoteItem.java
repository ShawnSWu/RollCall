package com.shawn.newrollcall.ToDo.view;

/**
 * Created by Shawn Wu on 2017/12/12.
 *
 */

public class ToDoNoteItem {

    private String todo_title,todo_createtime;

    private int todo_isFinsh;

    public ToDoNoteItem(String toDoTitle, String createTime, int isComplete) {
        this.todo_title = toDoTitle;
        this.todo_createtime = createTime;
        this.todo_isFinsh = isComplete;
    }


    public String getToDoTitle() {
        return todo_title;
    }

    public String getCreateTime() {
        return todo_createtime;
    }

    public boolean isComplete() {
        return todo_isFinsh == 1;
    }
}
