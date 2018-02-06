package com.shawn.newrollcall.FluxCenter.view;


import com.shawn.newrollcall.FluxCenter.action.FluxAction;

/**
 * Created by Shawn.Wu on 2017/11/8.
 */

public interface onFluxChangedListener {

    void onFluxChanged(FluxAction fluxAction);

    void onFluxStoreRegistered();

    void onFluxStoreUnregistered();

}
