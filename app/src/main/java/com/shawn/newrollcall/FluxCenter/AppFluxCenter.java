package com.shawn.newrollcall.FluxCenter;
import com.shawn.newrollcall.FluxCenter.action.ActionCreatorCenter;
import com.shawn.newrollcall.FluxCenter.store.FluxStoreCenter;

/**
 * Created by Administrator on 2017/01/8.
 *
 */

public class AppFluxCenter {

    private static ActionCreatorCenter actionCreatorCenter = new ActionCreatorCenter();

    private static FluxStoreCenter fluxStoreCenter = new FluxStoreCenter();


    public static ActionCreatorCenter getActionCreator() {
        return actionCreatorCenter;
    }

    public static FluxStoreCenter getStore() {
        return fluxStoreCenter;
    }

}
