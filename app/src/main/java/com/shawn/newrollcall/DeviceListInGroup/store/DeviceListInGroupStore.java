package com.shawn.newrollcall.DeviceListInGroup.store;

import com.shawn.newrollcall.DeviceListInGroup.action.DeviceListInGroupActionType;
import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.store.Store;

/**
 * Created by Shawn Wu on 2017/12/24.
 * *
 */

public class DeviceListInGroupStore extends Store {

    @Override
    public void onFluxActionHandling(FluxAction fluxAction) {
        switch (fluxAction.getType()) {

            case DeviceListInGroupActionType.GET_GROUP_DEVICE_DATA:
            case DeviceListInGroupActionType.GET_GROUP_DEVICE_DATA_SUCCESS:
            case DeviceListInGroupActionType.INSERT_NEW_DATA_TO_GROUP:
            case DeviceListInGroupActionType.INSERT_NEW_DATA_TO_GROUP_SUCCESS:
                emitted(fluxAction);
                break;
        }
    }

}
