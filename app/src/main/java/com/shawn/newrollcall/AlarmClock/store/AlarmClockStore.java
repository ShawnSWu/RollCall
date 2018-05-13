package com.shawn.newrollcall.AlarmClock.store;

import com.shawn.newrollcall.AlarmClock.action.AlarmClockType;
import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.store.Store;

/**
 * Created by Shawn Wu on 2017/12/13.
 *
 */

public class AlarmClockStore extends Store {

    private static int userSelectTimeInHour = 0,userSelectTimeInMinute = 0;

    @Override
    public void onFluxActionHandling(FluxAction fluxAction) {


        switch (fluxAction.getType()){

            case AlarmClockType.SAVEA_HOUR_AND_MINUTE:
                int userSelectTimeInHour = (int)fluxAction.getData()[0];
                int userSelectTimeInMinute = (int)fluxAction.getData()[1];
                this.userSelectTimeInHour = userSelectTimeInHour;
                this.userSelectTimeInMinute = userSelectTimeInMinute;
                break;


        }

    }


    public int getUserSelectTimeInHour() {
        return userSelectTimeInHour;
    }

    public int getUserSelectTimeInMinute() {
        return userSelectTimeInMinute;
    }

}
