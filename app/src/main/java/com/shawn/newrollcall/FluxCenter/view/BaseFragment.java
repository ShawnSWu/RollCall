package com.shawn.newrollcall.FluxCenter.view;

import android.support.v4.app.Fragment;


/**
 * Created by Shawn Wu on 2017/11/30.
 *
 */
//處理架構層的code
public abstract class BaseFragment extends Fragment implements onFluxChangedListener {

    @Override
    public void onStart() {
        super.onStart();
        onFluxStoreRegistered();
    }

    @Override
    public void onPause() {
        super.onPause();
        onFluxStoreUnregistered();
    }

}
