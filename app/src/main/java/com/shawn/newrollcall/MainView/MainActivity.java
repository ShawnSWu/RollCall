package com.shawn.newrollcall.MainView;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.MenuItem;

import com.shawn.newrollcall.FluxCenter.AppFluxCenter;
import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.view.AppBaseActivity;
import com.shawn.newrollcall.FluxCenter.view.AppBaseFragment;
import com.shawn.newrollcall.MainView.GroupList.view.GroupFragment;
import com.shawn.newrollcall.MainView.Home.view.MainFragment;
import com.shawn.newrollcall.MainView.Profile.view.ProfileFragment;
import com.shawn.newrollcall.R;
import com.shawn.newrollcall.ScanBLEModel.view.ScanActivity;
import com.shawn.newrollcall.databinding.ActivityMainBinding;


public class MainActivity extends AppBaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private ActivityMainBinding binding;
    private AppBaseFragment currentFragment;

    private void initFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.main_FrameLayout, ProfileFragment.getInstance());
        fragmentTransaction.add(R.id.main_FrameLayout, GroupFragment.getInstance());
        fragmentTransaction.add(R.id.main_FrameLayout, MainFragment.getInstance());
        fragmentTransaction.hide(GroupFragment.getInstance());
        fragmentTransaction.hide(ProfileFragment.getInstance());
        fragmentTransaction.commit();

        currentFragment = MainFragment.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initFragment();
        binding.bottomNavigation.setOnNavigationItemSelectedListener(this);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        int a = ScanActivity.addDataRequestCode;


        switch (requestCode){

            case  6666:
                String account = AppFluxCenter.getStore().getSharedPreferences().getSavedAccount(this);
                AppFluxCenter.getActionCreator().getGroupListInfoCreator().getGroupListInfomation(account);
                break;
        }
    }

    private FragmentTransaction changeFragment(AppBaseFragment targetFragment) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!targetFragment.isAdded()) {
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.main_FrameLayout, targetFragment,targetFragment.getClass().getName());

        } else {
            transaction.hide(currentFragment).show(targetFragment);
        }
        currentFragment = targetFragment;
        return transaction;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_home:
                changeFragment(MainFragment.getInstance()).commit();
                break;

            case R.id.action_group:
                changeFragment(GroupFragment.getInstance()).commit();
                break;

            case R.id.action_profile:
                changeFragment(ProfileFragment.getInstance()).commit();
                break;
        }
        return true;
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
