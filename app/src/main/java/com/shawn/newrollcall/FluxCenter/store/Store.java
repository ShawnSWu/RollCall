package com.shawn.newrollcall.FluxCenter.store;

import com.shawn.newrollcall.FluxCenter.Dispatcher.Dispatcher;
import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.view.onFluxChangedListener;

import java.util.LinkedList;

/**
 * Created by Shawn Wu on 2017/11/10.
 *
 */

public abstract class Store implements viewReigister {


    private LinkedList<onFluxChangedListener> list  = new LinkedList<>();

     public Store(){
         Dispatcher.getDispatcher().register(this);
     }

    public abstract void onFluxActionHandling(FluxAction fluxAction);


    public void emitted(FluxAction fluxAction) {
        for(onFluxChangedListener o: list) {
            o.onFluxChanged(fluxAction);
        }
    }

    @Override
    public void register(onFluxChangedListener onFluxChangedListener) {
        list.add(onFluxChangedListener);
    }

    @Override
    public void unRegister(onFluxChangedListener onFluxChangedListener) {
        Dispatcher.getDispatcher().unRegister(this);
        list.remove(onFluxChangedListener);
    }


}
