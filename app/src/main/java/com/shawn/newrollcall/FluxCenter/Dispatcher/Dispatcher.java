package com.shawn.newrollcall.FluxCenter.Dispatcher;


import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.store.Store;

import java.util.LinkedList;

/**
 * Created by Shawn Wu on 2017/11/9.
 */

public class Dispatcher {

    private final static Dispatcher dispatcher = new Dispatcher();

    private LinkedList<Store> list = new LinkedList<>();

    private Dispatcher(){}

    public static Dispatcher getDispatcher() {
        return dispatcher;
    }

    public void send(FluxAction newAction) {
        pushToStroe(newAction);
    }

    public void register(Store store) {
        if(!list.contains(store)) {
            list.add(store);
        }
    }

    public void unRegister(Store store) {
        list.remove(store);
    }

    private void pushToStroe(FluxAction newAction) {
        for(Store store:list) {
            store.onFluxActionHandling(newAction);
        }
    }
}
