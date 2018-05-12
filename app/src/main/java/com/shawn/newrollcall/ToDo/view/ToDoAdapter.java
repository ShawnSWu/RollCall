package com.shawn.newrollcall.ToDo.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shawn.newrollcall.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shawn Wu on 2017/12/12.
 *
 */

public class ToDoAdapter extends RecyclerView.Adapter<ToDoViewHolder> {

    private List<ToDoNoteItem> toDoNoteItemList=  new ArrayList<>();

    @Override
    public ToDoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_todo_recyclerview, parent, false);
        return new ToDoViewHolder(view);
    }

    public void update(List<ToDoNoteItem> toDoNoteItemList) {
        this.toDoNoteItemList = toDoNoteItemList;
    }

    @Override
    public void onBindViewHolder(ToDoViewHolder holder, int position) {
        holder.setItem(toDoNoteItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return toDoNoteItemList.size();
    }


}
