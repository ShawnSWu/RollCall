package com.shawn.newrollcall.MainView.GroupList.store;

import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.store.Store;
import com.shawn.newrollcall.MainView.GroupList.action.GroupListInfoType;

/**
 * Created by Shawn Wu on 2017/12/3.
 *
 */

public class GroupListInfoStore extends Store {

    private static int select_image_position = -1;

    private static int select_group_position = -1;


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

            case GroupListInfoType.SETSELECTIMAGEPOSITIONZERO:
                select_image_position = -1;
                break;

            case GroupListInfoType.UPDATE_SELECT_GROUP:
                select_group_position = (int)fluxAction.getData()[0];
                emitted(fluxAction);
                break;


        }

    }


    public int getSelectImagePosition() {
        return select_image_position;
    }

    public int getSelectGroupPosition() {
        return select_group_position;
    }

}
