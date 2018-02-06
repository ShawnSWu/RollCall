package com.shawn.newrollcall.Signup.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.shawn.newrollcall.FluxCenter.AppFluxCenter;
import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.view.AppBaseActivity;
import com.shawn.newrollcall.R;
import com.shawn.newrollcall.SharedPreferences.action.SharedPreferencesActionType;
import com.shawn.newrollcall.Signup.action.SignUpActionType;
import com.shawn.newrollcall.databinding.ActivitySignupBinding;

import com.shawn.newrollcall.login.view.LodingFactory;
import com.shawn.newrollcall.util.InputUtil;

/**
 * Created by Shawn Wu on 2017/11/30.
 *
 */

public class SignUpActivity extends AppBaseActivity {

    private ActivitySignupBinding binding;

    private KProgressHUD lodingview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup);
        binding.signupButton.setOnClickListener(getSignUpButton());

        lodingview = LodingFactory.getLodingAnimation(R.string.please_wait,this);
    }

    private  View.OnClickListener getSignUpButton() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.signupEmail.getText().toString();
                String password = binding.signupPassword.getText().toString();
                String confirm_password = binding.signupConfirmPassword.getText().toString();
                String username = binding.signupName.getText().toString();

                if(!InputUtil.checkEmail(email)){
                    showErrorMessage(getResources().getString(R.string.email_format_error));
                }else if(!InputUtil.checkEmptyAndLength(password,8)) {
                    showErrorMessage(getResources().getString(R.string.password_format_error));
                }else if(!confirm_password.equals(password)) {
                    showErrorMessage(getResources().getString(R.string.confirm_password_error));
                }else if(!InputUtil.checkEmptyAndLength(username,3)) {
                    showErrorMessage(getResources().getString(R.string.username_format_error));
                }else{
                    AppFluxCenter.getActionCreator().getSignupAPICreator().signup(email, password, username);
                }
            }
        };
    }

    private void showErrorMessage(String message) {
        binding.textviewErrorMessage.setText(message);
        binding.layoutErrorMessage.setVisibility(View.VISIBLE);
    }


    @Override
    public void onFluxChanged(FluxAction fluxAction) {

        switch (fluxAction.getType()) {

            case SignUpActionType.SIGN_UP:
                binding.layoutErrorMessage.setVisibility(View.GONE);
                if(!isFinishing()) {
                    lodingview.show();
                }
                break;

            case SignUpActionType.SIGN_UP_SUCCESS:
                lodingview.dismiss();
                binding.layoutErrorMessage.setVisibility(View.GONE);
                Toast.makeText(this,getResources().getString(R.string.sign_up_success),Toast.LENGTH_SHORT).show();
                finish();
                break;

            case SignUpActionType.SIGN_UP_FAIL:
                lodingview.dismiss();
                binding.layoutErrorMessage.setVisibility(View.VISIBLE);
                break;


            case SharedPreferencesActionType.SAVE_ACCOUNT:

                break;

        }

    }

    @Override
    public void onFluxStoreRegistered() {
        AppFluxCenter.getStore().getSignUpAPI().register(this);

    }

    @Override
    public void onFluxStoreUnregistered() {
        AppFluxCenter.getStore().getSignUpAPI().unRegister(this);
    }

}
