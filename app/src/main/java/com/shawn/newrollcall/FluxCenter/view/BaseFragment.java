package com.shawn.newrollcall.FluxCenter.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;


/**
 * Created by Shawn Wu on 2018/1/30.
 *
 */
//處理架構層的code
public abstract class BaseFragment extends Fragment implements onFluxChangedListener {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onFluxStoreRegistered();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        onFluxStoreUnregistered();
    }

}
