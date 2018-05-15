package com.shawn.newrollcall.ToDo.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.view.View;

import com.shawn.newrollcall.BackEndAPI.action.APIType;
import com.shawn.newrollcall.FluxCenter.AppFluxCenter;
import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.view.AppBaseActivity;
import com.shawn.newrollcall.Notification.action.NotificationType;
import com.shawn.newrollcall.R;
import com.shawn.newrollcall.ToDo.action.ToDoActionType;
import com.shawn.newrollcall.databinding.ActivityTodoBinding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Shawn Wu on 2017/12/11.
 *
 */

public class ToDoActivity extends AppBaseActivity {

    private ActivityTodoBinding binding;

    private ToDoAdapter doAdapter;
    private String account,password;

    // 獲取當前時間
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
    private Date curDate = new Date(System.currentTimeMillis());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_todo);

        binding.todoToolbar.setTitle(getString(R.string.todo_message_title));
        setSupportActionBar(binding.todoToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        binding.todoSwiperefreshLayout.setOnRefreshListener(getRefresh());
        binding.todoSwiperefreshLayout.setColorSchemeResources(R.color.theme_green);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.todoRecyclerview.setLayoutManager(layoutManager);

        binding.createTodoFab.attachToRecyclerView(binding.todoRecyclerview);
        binding.createTodoFab.setColorNormal(getResources().getColor(R.color.theme_green));
        binding.createTodoFab.setColorPressed(getResources().getColor(R.color.btn_press_green));
        binding.createTodoFab.setColorRipple(getResources().getColor(R.color.app_background_color));
        binding.createTodoFab.setOnClickListener(getCreateToDoNoteFabListener());

        doAdapter = new ToDoAdapter();
        binding.todoRecyclerview.setAdapter(doAdapter);

        account = AppFluxCenter.getStore().getSharedPreferences().getSavedAccount(this);
        password = AppFluxCenter.getStore().getSharedPreferences().getSavedPassword(this);
        AppFluxCenter.getActionCreator().getToDoCreator().getToDoData(account,password);

    }

    private SwipeRefreshLayout.OnRefreshListener getRefresh(){
        return new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        AppFluxCenter.getActionCreator().getToDoCreator().getToDoData(account,password);
                        binding.todoSwiperefreshLayout.setRefreshing(false);
                    }
                },2000);

            }
        };
    }

    public View.OnClickListener getCreateToDoNoteFabListener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String currentTime = formatter.format(curDate);
                AppFluxCenter
                        .getActionCreator()
                        .getRollCallDialogCreator()
                        .showCreateToDoDailog(ToDoActivity.this,account,password,currentTime);

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

        switch (fluxAction.getType()){

            case ToDoActionType.GET_TODO_DATA_SUCCESS:
                List<ToDoNoteItem> toDoNoteItems = (List<ToDoNoteItem>)fluxAction.getData()[0];
                doAdapter.update(toDoNoteItems);
                doAdapter.notifyDataSetChanged();
                if(!binding.createTodoFab.isVisible()){
                    binding.createTodoFab.show();
                }
                if(toDoNoteItems.isEmpty())
                    binding.emptyview.setVisibility(View.VISIBLE);
                else
                    binding.emptyview.setVisibility(View.GONE);
                break;

            case ToDoActionType.CREATE_TODO_SUCCESS:
                toast(getString(R.string.add_success));
                AppFluxCenter.getActionCreator().getToDoCreator().getToDoData(account,password);
                break;

            case ToDoActionType.DELETE_TODO_SUCCESS:
                toast(getString(R.string.delete_success));
                AppFluxCenter.getActionCreator().getToDoCreator().getToDoData(account,password);
                break;

            case ToDoActionType.UPDATE_TODO_CONTENT_SUCCESS:
                toast(getString(R.string.modify_success));
                AppFluxCenter.getActionCreator().getToDoCreator().getToDoData(account,password);
                break;

            case APIType.SERVER_ERROR:
                toast(getString(R.string.server_error));
                break;

            case ToDoActionType.UPDATE_FINSH_TODO_SUCCESS:
                AppFluxCenter.getActionCreator().getToDoCreator().getToDoData(account,password);
                break;

            case NotificationType.TODO_NOTIFICATION:
                AppFluxCenter.getActionCreator().getToDoCreator().getToDoData(account,password);
                break;

        }

    }

    @Override
    public void onFluxStoreRegistered() {
        AppFluxCenter.getStore().getToDoStore().register(this);
        AppFluxCenter.getStore().getAPIStore().register(this);
        AppFluxCenter.getStore().getAlarmClockStore().register(this);
        AppFluxCenter.getStore().getNotificationStore().register(this);
    }

    @Override
    public void onFluxStoreUnregistered() {
        AppFluxCenter.getStore().getToDoStore().unRegister(this);
        AppFluxCenter.getStore().getAPIStore().unRegister(this);
        AppFluxCenter.getStore().getAlarmClockStore().register(this);
        AppFluxCenter.getStore().getNotificationStore().unRegister(this);
    }
}
