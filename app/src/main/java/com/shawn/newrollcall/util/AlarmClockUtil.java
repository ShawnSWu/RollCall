package com.shawn.newrollcall.util;

import java.util.Calendar;

/**
 * Created by Shawn Wu on 2017/12/13.
 *
 */

public final class AlarmClockUtil {

    public final static Calendar calendar = Calendar.getInstance();

    public static int getTimeInMillis(int userSelectTimeInHour,int userSelectTimeInMinute){
        int systemHour = calendar.get(Calendar.HOUR_OF_DAY);
        int systemMinute = calendar.get(Calendar.MINUTE);

        int hour = userSelectTimeInHour-systemHour;
        int minute = userSelectTimeInMinute-systemMinute;
        int time = ((hour*60*60) + (minute*60));
        return time;

    }


}
