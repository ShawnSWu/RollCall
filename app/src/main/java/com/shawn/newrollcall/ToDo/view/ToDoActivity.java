package com.shawn.newrollcall.ToDo.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.view.View;

import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.view.AppBaseActivity;
import com.shawn.newrollcall.R;
import com.shawn.newrollcall.databinding.ActivityTodoBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shawn Wu on 2017/12/11.
 *
 */

public class ToDoActivity extends AppBaseActivity {

    private ActivityTodoBinding binding;

    private ToDoAdapter doAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_todo);

        binding.todoToolbar.setTitle(getString(R.string.todo_message_title));
        setSupportActionBar(binding.todoToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.todoRecyclerview.setLayoutManager(layoutManager);

        binding.createGroupFab.attachToRecyclerView(binding.todoRecyclerview);
        binding.createGroupFab.setColorNormal(getResources().getColor(R.color.theme_green));
        binding.createGroupFab.setColorPressed(getResources().getColor(R.color.btn_press_green));
        binding.createGroupFab.setColorRipple(getResources().getColor(R.color.app_background_color));
        binding.createGroupFab.setOnClickListener(getCreateToDoNoteFabListener());

        List<ToDoNoteItem> toDoNoteItemList = new ArrayList<>();
        toDoNoteItemList.add(new ToDoNoteItem("雨傘","2017/07/05",true));
        toDoNoteItemList.add(new ToDoNoteItem("雨傘2","2017/07/05",true));
        toDoNoteItemList.add(new ToDoNoteItem("雨傘3","2017/07/05",false));
        toDoNoteItemList.add(new ToDoNoteItem("雨傘4","2017/07/05",true));


        doAdapter = new ToDoAdapter(toDoNoteItemList);
        binding.todoRecyclerview.setAdapter(doAdapter);
    }

    public View.OnClickListener getCreateToDoNoteFabListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        };
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onFluxChanged(FluxAction fluxAction) {

    }

    @Override
    public void onFluxStoreRegistered() {

    }

    @Override
    public void onFluxStoreUnregistered() {

    }
}
