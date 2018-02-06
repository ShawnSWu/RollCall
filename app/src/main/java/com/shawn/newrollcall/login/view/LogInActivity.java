package com.shawn.newrollcall.login.view;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.shawn.newrollcall.FluxCenter.AppFluxCenter;
import com.shawn.newrollcall.databinding.ActivityLoginBinding;
import com.shawn.newrollcall.login.action.LoginAPIActionType;
import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.view.AppBaseActivity;
import com.shawn.newrollcall.R;
import com.shawn.newrollcall.Signup.view.SignUpActivity;




/**
 * Created by Shawn Wu on 2017/11/21.
 *
 */

public class LogInActivity extends AppBaseActivity implements View.OnClickListener{

    private ActivityLoginBinding binding;

    private final int LOG_IN = 0,CREATE_ACCOUNT = 1,FORGOT_PASSWORD = 2 ;

    private KProgressHUD lodingview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        String loginAccount = AppFluxCenter.getStore().getSharedPreferences().getSavedAccount(this);

        if(!loginAccount.equals("")) {
            AppFluxCenter.getActionCreator().getIntentCenterActionsCreator().startMainActivity(this);
        }

        binding.loginButton.setOnClickListener(this);
        binding.loginButton.setId(LOG_IN);
        binding.createAccount.setOnClickListener(this);
        binding.createAccount.setId(CREATE_ACCOUNT);
        binding.forgotPassword.setOnClickListener(this);
        binding.forgotPassword.setId(FORGOT_PASSWORD);


    }

    @Override
    protected void onResume() {
        super.onResume();
        lodingview = LodingFactory.getLodingAnimation(R.string.please_wait,this);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case LOG_IN:
                //輸入檢查
                String account = binding.editAccount.getText().toString();
                String password = binding.editPassword.getText().toString();
                AppFluxCenter.getActionCreator().getLoginAPICreator().login(account,password);

                break;

            case CREATE_ACCOUNT:
                Intent intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                break;

            case FORGOT_PASSWORD:
                Toast.makeText(this,"Coming soon",Toast.LENGTH_SHORT).show();
                break;
        }

    }


    @Override
    public void onFluxChanged(FluxAction fluxAction) {


        switch (fluxAction.getType()) {

            case LoginAPIActionType.API_LOGIN:
                if(!isFinishing()) {
                    lodingview.show();
                }
                break;

            case LoginAPIActionType.API_LOGIN_SUCCESS:
                lodingview.dismiss();
                binding.layoutErrorMessage.setVisibility(View.GONE);
                String account = binding.editAccount.getText().toString();
                String password = binding.editPassword.getText().toString();
                AppFluxCenter.getActionCreator().getSharedPreferencesCreator().saveAccountInfo(this,account,password);
                AppFluxCenter.getActionCreator().getIntentCenterActionsCreator().startMainActivity(this);
                break;


            case LoginAPIActionType.API_LOGIN_FAIL:
                binding.layoutErrorMessage.setVisibility(View.VISIBLE);
                lodingview.dismiss();
                break;

            case LoginAPIActionType.API_LOGIN_ERROR:
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
                break;


        }

    }


    @Override
    public void onFluxStoreRegistered() {
        AppFluxCenter.getStore().getLoginAPI().register(this);
        AppFluxCenter.getStore().getSharedPreferences().register(this);
        AppFluxCenter.getStore().getIntentCenterStore().register(this);
    }

    @Override
    public void onFluxStoreUnregistered() {
        AppFluxCenter.getStore().getLoginAPI().unRegister(this);
        AppFluxCenter.getStore().getSharedPreferences().unRegister(this);
        AppFluxCenter.getStore().getIntentCenterStore().unRegister(this);
    }
}
