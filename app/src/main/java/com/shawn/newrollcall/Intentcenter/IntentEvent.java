package com.shawn.newrollcall.Intentcenter;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Shawn Wu on 2017/11/30.
 *
 */

public class IntentEvent {

    private final Activity startAcivity;
    private final Class targetClass;
    private final int requestCode,flag;


    private IntentEvent(Activity startAcivity, Class targetClass, int requestCode, int flag) {
        this.startAcivity = startAcivity;
        this.targetClass = targetClass;
        this.requestCode = requestCode;
        this.flag = flag;
    }


    public void doStartActivity(){
        Intent i = new Intent();
        i.setClass(startAcivity,targetClass);
        i.setFlags(flag);
        startAcivity.startActivity(i);
    }

    public void doStartActivityForResult(){
        Intent i = new Intent();
        i.setClass(startAcivity,targetClass);
        i.setFlags(flag);
        startAcivity.startActivityForResult(i,requestCode);
    }


    public Activity getStartAcivity() {
        return startAcivity;
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public int getFlag() {
        return flag;
    }

    public static class Builder {

        private Activity startAcivity;
        private Class targetClass;
        private int requestCode,flag;


        public Builder setStartAcivity(Activity startAcivity) {
            this.startAcivity = startAcivity;
            return this;
        }

        public Builder setTargetClass(Class targetClass) {
            this.targetClass = targetClass;
            return this;
        }

        public Builder setRequestCode(int requestCode) {
            this.requestCode = requestCode;
            return this;
        }

        public Builder setFlag(int flag) {
            this.flag = flag;
            return this;
        }

        public IntentEvent build(){
            return new IntentEvent(startAcivity,targetClass,requestCode,flag);
        }

    }

}
