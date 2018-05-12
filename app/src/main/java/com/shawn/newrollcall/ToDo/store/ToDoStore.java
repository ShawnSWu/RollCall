package com.shawn.newrollcall.ToDo.store;

import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.store.Store;
import com.shawn.newrollcall.ToDo.action.ToDoActionType;

/**
 * Created by Shawn Wu on 2017/12/12.
 *
 */

public class ToDoStore extends Store {


    @Override
    public void onFluxActionHandling(FluxAction fluxAction) {


        switch (fluxAction.getType()){

            case ToDoActionType.UPDATE_TODO_CONTENT_SUCCESS:
            case ToDoActionType.DELETE_TODO_SUCCESS:
            case ToDoActionType.CREATE_TODO_SUCCESS:
            case ToDoActionType.GET_TODO_DATA_SUCCESS:
                emitted(fluxAction);
                break;
        }

    }

}
