package com.shawn.newrollcall.FluxCenter.action;


import com.shawn.newrollcall.MainView.GroupList.action.GroupListInfoCreator;
import com.shawn.newrollcall.MainView.Profile.action.AccountInfoCreator;
import com.shawn.newrollcall.Intentcenter.action.IntentCenterActionsCreator;
import com.shawn.newrollcall.RollCallDialog.action.RollCallDialogCreator;
import com.shawn.newrollcall.ScanBLEModel.action.BleScannerCreator;
import com.shawn.newrollcall.SharedPreferences.action.SharedPreferencesCreator;
import com.shawn.newrollcall.Signup.action.SignUpCreator;
import com.shawn.newrollcall.login.action.LoginAPICreator;

/**
 * Created by Shawn.Wu on 2017/11/8.
 */

public class ActionCreatorCenter {

    public LoginAPICreator getLoginAPICreator(){
        return new LoginAPICreator();
    }

    public SignUpCreator getSignupAPICreator(){
        return new SignUpCreator();
    }

    public SharedPreferencesCreator getSharedPreferencesCreator(){
        return new SharedPreferencesCreator();
    }

    public IntentCenterActionsCreator getIntentCenterActionsCreator(){
        return new IntentCenterActionsCreator();
    }

    public AccountInfoCreator getAccountInfoCreator(){
        return new AccountInfoCreator();
    }

    public GroupListInfoCreator getGroupListInfoCreator(){
        return new GroupListInfoCreator();
    }

    public BleScannerCreator getBleScannerCreator(){
        return new BleScannerCreator();
    }

    public RollCallDialogCreator getRollCallDialogCreator(){
        return new RollCallDialogCreator();
    }

}
