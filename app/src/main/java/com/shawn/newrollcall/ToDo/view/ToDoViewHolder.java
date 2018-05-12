package com.shawn.newrollcall.ToDo.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shawn.newrollcall.FluxCenter.AppFluxCenter;
import com.shawn.newrollcall.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Shawn Wu on 2017/12/12.
 *
 */

public class ToDoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{

    private TextView toDoTitle,toDoCreateTime;
    private ImageView toDoIsFinshIcon;
    private ToDoNoteItem toDoNoteItem;

    public ToDoViewHolder(View itemView) {
        super(itemView);
        toDoTitle = itemView.findViewById(R.id.todo_title);
        toDoCreateTime = itemView.findViewById(R.id.todo_createtime);
        toDoIsFinshIcon = itemView.findViewById(R.id.todo_isfinsh_icon);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    public void setItem(ToDoNoteItem toDoNoteItem){
        this.toDoNoteItem = toDoNoteItem;
        toDoTitle.setText(toDoNoteItem.getToDoTitle());
        toDoCreateTime.setText(toDoNoteItem.getCreateTime());
        if(toDoNoteItem.isComplete())
            toDoIsFinshIcon.setImageResource(R.mipmap.icon_tick);
        else
            toDoIsFinshIcon.setImageResource(R.mipmap.icon_timer);
    }

    @Override
    public void onClick(View view) {
        AppFluxCenter
                .getActionCreator()
                .getRollCallDialogCreator()
                .showToDoContentDailog(view.getContext()
                        ,toDoNoteItem.getToDoTitle()
                        ,toDoNoteItem.getCreateTime());
    }


    @Override
    public boolean onLongClick(View view) {
        String account = AppFluxCenter.getStore().getSharedPreferences().getSavedAccount(view.getContext());
        String password = AppFluxCenter.getStore().getSharedPreferences().getSavedPassword(view.getContext());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String currentTime = formatter.format(new Date(System.currentTimeMillis()));
        AppFluxCenter
                .getActionCreator()
                .getRollCallDialogCreator()
                .showLongClickTodoDailog(view.getContext(),account,password,
                        toDoNoteItem.getToDoTitle(),currentTime);
        return true;
    }
}
