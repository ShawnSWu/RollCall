package com.shawn.newrollcall.ToDo.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shawn.newrollcall.R;

/**
 * Created by Shawn Wu on 2017/12/12.
 *
 */

public class ToDoViewHolder extends RecyclerView.ViewHolder {

    private TextView toDoTitle,toDoCreateTime;
    private ImageView toDoIsFinshIcon;


    public ToDoViewHolder(View itemView) {
        super(itemView);
        toDoTitle = itemView.findViewById(R.id.todo_title);
        toDoCreateTime = itemView.findViewById(R.id.todo_createtime);
        toDoIsFinshIcon = itemView.findViewById(R.id.todo_isfinsh_icon);

    }

    public void setItem(ToDoNoteItem toDoNoteItem){
        toDoTitle.setText(toDoNoteItem.getToDoTitle());
        toDoCreateTime.setText(toDoNoteItem.getCreateTime());
        if(toDoNoteItem.isComplete())
            toDoIsFinshIcon.setImageResource(R.mipmap.icon_tick);
        else
            toDoIsFinshIcon.setImageResource(R.mipmap.icon_timer);
    }
}
