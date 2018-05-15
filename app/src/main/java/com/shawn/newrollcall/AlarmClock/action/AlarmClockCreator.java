package com.shawn.newrollcall.AlarmClock.action;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.shawn.newrollcall.AlarmClock.AlarmBroadcast;
import com.shawn.newrollcall.FluxCenter.action.FluxActionCreator;
import com.shawn.newrollcall.R;
import com.shawn.newrollcall.util.AlarmClockUtil;

import java.util.Calendar;


/**
 * Created by Shawn Wu on 2017/12/13.
 *
 */

public class AlarmClockCreator extends FluxActionCreator {


    public void setAlarmClock(int timeSeconds,Context context,Class targetClass,String todo_content) {
        addAction(newAction(AlarmClockType.SET_ALARMCLOCK_FOR_TODO,timeSeconds,context,targetClass));

        Intent intent=new Intent(context,targetClass);
        intent.putExtra(AlarmBroadcast.TODO_TITLE_TAG,context.getString(R.string.todo_reminder));
        intent.putExtra(AlarmBroadcast.TODO_CONTENT_TAG,todo_content);

        AlarmManager manager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,1,intent,PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmClockUtil.calendar.add(Calendar.SECOND, timeSeconds);
        manager.set(AlarmManager.RTC_WAKEUP, AlarmClockUtil.calendar.getTimeInMillis(),pendingIntent);
    }

    public void saveUserSelectHourAndMinute(int userSelectTimeInHour,int userSelectTimeInMinute){
        addAction(newAction(AlarmClockType.SAVEA_HOUR_AND_MINUTE,userSelectTimeInHour,userSelectTimeInMinute));
    }
}
