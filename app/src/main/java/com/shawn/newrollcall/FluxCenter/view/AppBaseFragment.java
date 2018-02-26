package com.shawn.newrollcall.FluxCenter.view;


import android.app.Activity;
import android.content.Context;

/**
 * Created by Shawn Wu on 2017/01/31.
 *
 */
//處理View層的code
public abstract class AppBaseFragment extends BaseFragment {

    protected Activity mActivity;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity)context;
    }


    protected abstract int getLayoutId();


}
