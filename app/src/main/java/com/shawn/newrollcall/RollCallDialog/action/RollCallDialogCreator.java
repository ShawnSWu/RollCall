package com.shawn.newrollcall.RollCallDialog.action;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.shawn.newrollcall.AlarmClock.AlarmBroadcast;
import com.shawn.newrollcall.FluxCenter.AppFluxCenter;
import com.shawn.newrollcall.FluxCenter.action.FluxActionCreator;
import com.shawn.newrollcall.R;
import com.shawn.newrollcall.ScanBLEModel.BleDeviceItem;
import com.shawn.newrollcall.util.AlarmClockUtil;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Shawn Wu on 2017/12/24.
 *
 */

public class RollCallDialogCreator extends FluxActionCreator {

    private static AlertDialog rollCallDailog;

    public void showAddDataFromCreateGroup(final Activity activity, String title, String message,final String listName, final String imageUri) {
        addAction(newAction(RollCallDialogActionType.ADD_DATA_FROM_CREATE_GROUP));

        View dialogView = LayoutInflater.from(activity).inflate(R.layout.dialog_general_view,null);
        TextView dialogtitle = dialogView.findViewById(R.id.dialog_title);
        dialogtitle.setText(title);
        TextView dialogmessage = dialogView.findViewById(R.id.dialog_message);
        dialogmessage.setText(message);

        Button btnOk = dialogView.findViewById(R.id.ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppFluxCenter
                        .getActionCreator()
                        .getIntentCenterActionsCreator()
                        .startScanActivity(activity,listName,imageUri);

                rollCallDailog.dismiss();
            }
        });
        Button btnCancel = dialogView.findViewById(R.id.cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollCallDailog.dismiss();
            }
        });

        if(rollCallDailog == null) {
            rollCallDailog = new AlertDialog.Builder(activity)
                    .setView(dialogView)
                    .create();
        }
        if(!rollCallDailog.isShowing()) {
            rollCallDailog.show();
        }
    }

    public void showMaunalEditDailog(final Context activity, final String deviceName, final String deviceAddress) {
        addAction(newAction(RollCallDialogActionType.MAUNAL_EDIT_ADD));

        View dialogView = LayoutInflater.from(activity).inflate(R.layout.dialog_manual_edit,null);
        TextView dialogtitle = dialogView.findViewById(R.id.edit_device_title);
        dialogtitle.setText(activity.getString(R.string.edit_manually));
        final EditText reset_device_name = dialogView.findViewById(R.id.device_name_edit);
        reset_device_name.setHint(deviceName);
        TextView device_address = dialogView.findViewById(R.id.device_address);
        device_address.setText(String.format(activity.getString(R.string.device_address_message),deviceAddress));


        Button btnOk = dialogView.findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppFluxCenter
                        .getActionCreator()
                        .getBleScannerCreator()
                        .resetDeviceName(reset_device_name.getText().toString(),deviceAddress);
                rollCallDailog.dismiss();
            }
        });
        Button btnCancel = dialogView.findViewById(R.id.btn_close);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollCallDailog.dismiss();
            }
        });

        rollCallDailog = new AlertDialog.Builder(activity)
                .setView(dialogView)
                .create();
        rollCallDailog.show();
    }

    public void showMakeSureSetDeviceDailog(String title, String message, final Activity activity,
                                            final String chooseSeconds, final List<BleDeviceItem> deviceItems, final String groupName) {
        addAction(newAction(RollCallDialogActionType.ADD_DATA_FROM_CREATE_GROUP));

        View dialogView = LayoutInflater.from(activity).inflate(R.layout.dialog_general_view,null);
        TextView dialogtitle = dialogView.findViewById(R.id.dialog_title);
        dialogtitle.setText(title);
        TextView dialogmessage = dialogView.findViewById(R.id.dialog_message);
        dialogmessage.setText(message);

        Button btnOk = dialogView.findViewById(R.id.ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppFluxCenter.getActionCreator().getBleScannerCreator().writeDataToDevice();
                AppFluxCenter
                        .getActionCreator()
                        .getIntentCenterActionsCreator()
                        .startWriteDataToDeviceSerVice(activity,chooseSeconds,deviceItems);
                int time=Integer.parseInt(chooseSeconds.substring(1,chooseSeconds.length()-1))/1000;
                AppFluxCenter
                        .getActionCreator()
                        .getAlarmClockCreator()
                        .setAlarmClock(time,view.getContext(),AlarmBroadcast.class,groupName);

                rollCallDailog.dismiss();
            }
        });
        Button btnCancel = dialogView.findViewById(R.id.cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollCallDailog.dismiss();
            }
        });

        if(rollCallDailog == null) {
            rollCallDailog = new AlertDialog.Builder(activity)
                    .setView(dialogView)
                    .create();
        }
        if(!rollCallDailog.isShowing()) {
            rollCallDailog.show();
        }
    }


    public void showCreateToDoDailog(final Context context, final String account, final String password, final String createTime) {
        addAction(newAction(RollCallDialogActionType.CREATE_TODO));
        final View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_create_todo,null);
        TextView dialogtitle = dialogView.findViewById(R.id.createtime);
        dialogtitle.setText(createTime);
        final ImageView remindClock = dialogView.findViewById(R.id.remind_clock);
        final TranslateAnimation clockAnimation = new TranslateAnimation(0, 0, 5f, -20f);
        clockAnimation.setDuration(650);
        AppFluxCenter.getActionCreator().getAlarmClockCreator().saveUserSelectHourAndMinute(0,0);
        remindClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int systemHour =AlarmClockUtil.calendar.get(Calendar.HOUR_OF_DAY);
                int systemMinute = AlarmClockUtil.calendar.get(Calendar.MINUTE);
                new TimePickerDialog(context,R.style.timePickerStyle, new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker timePicker, int chooseHour, int chooseMinute) {
                        AppFluxCenter.getActionCreator().getAlarmClockCreator().saveUserSelectHourAndMinute(chooseHour,chooseMinute);
                    }
                }, systemHour, systemMinute, false).show();
            }
        });
        Button btnOk = dialogView.findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText todo_edit = dialogView.findViewById(R.id.todo_edit);
                if(todo_edit.getText().toString().equals("")) {
                    Toast.makeText(context,context.getString(R.string.content_can_not_null),Toast.LENGTH_SHORT).show();
                }else if (AppFluxCenter.getStore().getAlarmClockStore().getUserSelectTimeInMinute() == 0){
                    remindClock.startAnimation(clockAnimation);
                    Toast.makeText(context,context.getString(R.string.time_not_set_yet),Toast.LENGTH_SHORT).show();
                }else{
                    int chooseHour = AppFluxCenter.getStore().getAlarmClockStore().getUserSelectTimeInHour();
                    int chooseMinute = AppFluxCenter.getStore().getAlarmClockStore().getUserSelectTimeInMinute();
                    int time = AlarmClockUtil.getTimeInMillis(chooseHour,chooseMinute);
                    AppFluxCenter
                            .getActionCreator()
                            .getAlarmClockCreator()
                            .setAlarmClock(time,context
                                    ,AlarmBroadcast.class,todo_edit.getText().toString());
                    AppFluxCenter
                            .getActionCreator()
                            .getToDoCreator()
                            .createToDo(account, password, todo_edit.getText().toString(), createTime);
                    rollCallDailog.dismiss();
                }
            }
        });
        Button btnCancel = dialogView.findViewById(R.id.btn_close);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollCallDailog.dismiss();
            }
        });
        final LinearLayout edit_view = dialogView.findViewById(R.id.edit_view);
        edit_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  InputMethodManager imm = (InputMethodManager) edit_view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                  imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
            }
        });
        rollCallDailog = new AlertDialog.Builder(context)
                .setView(dialogView)
                .create();
        rollCallDailog.show();
    }

    public void showEditToDoContentDailog(final Context context, final String createTime, final String old_content) {
        rollCallDailog.dismiss();
        addAction(newAction(RollCallDialogActionType.UPDATE_TODO_CONTENT));
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_create_todo,null);
        TextView dialogTitle = dialogView.findViewById(R.id.edit_dialog_title);
        dialogTitle.setText(old_content);
        TextView createTime_text = dialogView.findViewById(R.id.createtime);
        createTime_text.setText(createTime);
        final EditText todo_edit = dialogView.findViewById(R.id.todo_edit);
        todo_edit.setText(old_content);

        Button btnOk = dialogView.findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = AppFluxCenter.getStore().getSharedPreferences().getSavedAccount(context);
                String password = AppFluxCenter.getStore().getSharedPreferences().getSavedPassword(context);
                AppFluxCenter
                        .getActionCreator()
                        .getToDoCreator()
                        .editToDoContent(account,password,todo_edit.getText().toString(),old_content,createTime);
                rollCallDailog.dismiss();
            }
        });
        Button btnCancel = dialogView.findViewById(R.id.btn_close);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollCallDailog.dismiss();
            }
        });
        final LinearLayout edit_view = dialogView.findViewById(R.id.edit_view);
        edit_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputMethodManager imm = (InputMethodManager) edit_view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
            }
        });
        rollCallDailog = new AlertDialog.Builder(context)
                .setView(dialogView)
                .create();
        rollCallDailog.show();
    }

    public void showToDoContentDailog(final Context context, String todo_content, String createTime) {
        addAction(newAction(RollCallDialogActionType.WATCH_TODO));
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_todo_content,null);
        TextView toDoContent = dialogView.findViewById(R.id.watch_todo_content);
        toDoContent.setText(todo_content);
        TextView toDoCreateTime = dialogView.findViewById(R.id.watch_todo_createtime);
        toDoCreateTime.setText(createTime);
        rollCallDailog = new AlertDialog.Builder(context)
                .setView(dialogView)
                .create();
        rollCallDailog.show();
    }

    public void showLongClickTodoDailog(final Context context, final String account, final String password, final String todo_content, final String currentTime) {
        addAction(newAction(RollCallDialogActionType.WATCH_TODO));
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_longclick_todo,null);
        dialogView.findViewById(R.id.edit_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEditToDoContentDailog(context,currentTime,todo_content);

            }
        });
        dialogView.findViewById(R.id.delete_todo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppFluxCenter
                        .getActionCreator()
                        .getToDoCreator()
                        .deleteToDo(account,password,todo_content);
                rollCallDailog.dismiss();
            }
        });
        rollCallDailog = new AlertDialog.Builder(context)
                .setView(dialogView)
                .create();
        rollCallDailog.show();
    }
}
