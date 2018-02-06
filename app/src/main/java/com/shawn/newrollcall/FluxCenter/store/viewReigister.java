package com.shawn.newrollcall.FluxCenter.store;


import com.shawn.newrollcall.FluxCenter.view.BaseActivity;
import com.shawn.newrollcall.FluxCenter.view.onFluxChangedListener;

/**
 * Created by Shawn Wu on 2017/11/11.
 */

public interface viewReigister {

    void register(onFluxChangedListener activity);

    void unRegister(onFluxChangedListener activity);


}
