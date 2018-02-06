package com.shawn.newrollcall.FluxCenter.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Shawn.Wu on 2017/11/8.
 */

//處理架構層的code
public abstract class BaseActivity extends AppCompatActivity implements onFluxChangedListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onFluxStoreRegistered();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onFluxStoreUnregistered();
    }


}
