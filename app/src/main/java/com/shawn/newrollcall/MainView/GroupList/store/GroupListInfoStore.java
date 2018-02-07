package com.shawn.newrollcall.MainView.GroupList.store;

import android.util.Log;

import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.store.Store;
import com.shawn.newrollcall.MainView.GroupList.action.GroupListInfoType;

/**
 * Created by Shawn Wu on 2018/2/3.
 *
 */

public class GroupListInfoStore extends Store {

    private static int select_image_position = 0;


    @Override
    public void onFluxActionHandling(FluxAction fluxAction) {

        switch (fluxAction.getType()){

            case GroupListInfoType.UPDATE_SELECT_IMAGE:
                select_image_position = (int)fluxAction.getData()[0];
                emitted(fluxAction);
                break;

            case GroupListInfoType.GET_GROUP_LIST_INFO_SUCCESS:
            case GroupListInfoType.CREATE_GROUP_FAIL:
            case GroupListInfoType.DELETE_GROUP_SUCCESS:
            case GroupListInfoType.CREATE_GROUP_SUCCESS:
            case GroupListInfoType.DELETE_GROUP:
            case GroupListInfoType.CREATE_GROUP:
                emitted(fluxAction);
                break;




        }

    }


    public int getSelectImagePosition() {
        return select_image_position;
    }


}
