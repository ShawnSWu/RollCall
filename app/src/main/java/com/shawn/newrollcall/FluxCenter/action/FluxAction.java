package com.shawn.newrollcall.FluxCenter.action;


/**
 * Created by Shawn Wu on 2017/11/9.
 *
 */

public class FluxAction implements IFluxAction {

    private  String type;

    private Object[] data;

    public FluxAction(String type, Object... data){
        this.type = type;
        this.data = data;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Object[] getData() {
        return data;
    }

}
