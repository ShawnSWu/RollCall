package com.shawn.newrollcall.ToDo.view;

/**
 * Created by Shawn Wu on 2017/12/12.
 *
 */

public class ToDoNoteItem {

    private String toDoTitle,createTime;

    private boolean isComplete;

    public ToDoNoteItem(String toDoTitle, String createTime, boolean isComplete) {
        this.toDoTitle = toDoTitle;
        this.createTime = createTime;
        this.isComplete = isComplete;
    }


    public String getToDoTitle() {
        return toDoTitle;
    }

    public String getCreateTime() {
        return createTime;
    }

    public boolean isComplete() {
        return isComplete;
    }
}
