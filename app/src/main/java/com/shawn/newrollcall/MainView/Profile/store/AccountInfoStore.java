package com.shawn.newrollcall.MainView.Profile.store;

import com.shawn.newrollcall.MainView.Profile.action.AccountInfoType;
import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.store.Store;

/**
 * Created by Shawn Wu on 2017/11/31.
 *
 */

public class AccountInfoStore extends Store {


    @Override
    public void onFluxActionHandling(FluxAction fluxAction) {

        switch (fluxAction.getType()){

            case AccountInfoType.GET_ACCOUNT_GROUP_AND_DEVICE_DATA_SUCCESS:
            case AccountInfoType.GET_ACCOUNT_GROUP_AND_DEVICE_DATA:
            case AccountInfoType.UPLOAD_PROFILE_IMAGE_IMGUR:
            case AccountInfoType.GET_ACCOUNT_INFO_SUCCESS:
            case AccountInfoType.SAVE_IMGUR_URI_SUCCESS:
                emitted(fluxAction);
                break;


        }

    }


}
