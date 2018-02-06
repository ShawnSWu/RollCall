package com.shawn.newrollcall.MainView.Home.view;



import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.view.AppBaseFragment;
import com.shawn.newrollcall.R;
import com.shawn.newrollcall.databinding.FragmentMainBinding;

/**
 * Created by Shawn Wu on 2018/1/31.
 */

public class MainFragment extends AppBaseFragment {

    private FragmentMainBinding binding;
    public final static String TAG = "MainFragment";

    private static MainFragment instance;

    public static MainFragment getInstance() {
        if (instance == null) {
            instance = new MainFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);


        return binding.getRoot();
    }



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }


    @Override
    public void onFluxChanged(FluxAction fluxAction) {

    }

    @Override
    public void onFluxStoreRegistered() {

    }

    @Override
    public void onFluxStoreUnregistered() {

    }

}
