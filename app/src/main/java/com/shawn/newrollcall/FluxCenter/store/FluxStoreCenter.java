package com.shawn.newrollcall.FluxCenter.store;


import com.shawn.newrollcall.DeviceListInGroup.store.DeviceListInGroupStore;
import com.shawn.newrollcall.MainView.GroupList.store.GroupListInfoStore;
import com.shawn.newrollcall.MainView.Profile.store.AccountInfoStore;
import com.shawn.newrollcall.Intentcenter.store.IntentCenterStore;
import com.shawn.newrollcall.RollCallDialog.store.RollCallDialogStore;
import com.shawn.newrollcall.ScanBLEModel.store.BleScannerStore;
import com.shawn.newrollcall.SharedPreferences.store.SharedPreferencesStore;
import com.shawn.newrollcall.Signup.store.SignUpAPIStore;
import com.shawn.newrollcall.login.store.LoginAPIStore;

/**
 * Created by Shawn Wu on 2017/11/11.
 *
 */

public class FluxStoreCenter {

    private Store STORE_POOL(String type) {
        Store store = null;

        switch (type){


            case FluxStoreType.LOGIN_API_STORE:
                store = new LoginAPIStore();
                break;

            case FluxStoreType.SIGNUP_API_STORE:
                store = new SignUpAPIStore();
                break;


            case FluxStoreType.SHAREDPREFERENCES_STORE:
                store = new SharedPreferencesStore();
                break;

            case FluxStoreType.INTENT_CENTER:
                store = new IntentCenterStore();
                break;

            case FluxStoreType.ACCOUNT_INFO:
                store = new AccountInfoStore();
                break;

            case FluxStoreType.GROUPLIST_INFO:
                store = new GroupListInfoStore();
                break;

            case FluxStoreType.BLE_SCANNER_MODEL:
                store = new BleScannerStore();
                break;

            case FluxStoreType.ROLLCALLDIALOG:
                store = new RollCallDialogStore();
                break;

            case FluxStoreType.DEVICELISTINGROUP:
                store = new DeviceListInGroupStore();
            break;

        }


        return store;
    }

    private  <T extends Store> T getStore(String type) {
        Store store = STORE_POOL(type);
        return (T)store;
    }

    public LoginAPIStore getLoginAPI() {
        return getStore(FluxStoreType.LOGIN_API_STORE);
    }

    public SignUpAPIStore getSignUpAPI() {
        return getStore(FluxStoreType.SIGNUP_API_STORE);
    }

    public SharedPreferencesStore getSharedPreferences() {
        return getStore(FluxStoreType.SHAREDPREFERENCES_STORE);
    }

    public IntentCenterStore getIntentCenterStore() {
        return getStore(FluxStoreType.INTENT_CENTER);
    }

    public AccountInfoStore getAccountInfoStore() {
        return getStore(FluxStoreType.ACCOUNT_INFO);
    }

    public GroupListInfoStore getGroupListInfoStore(){
        return getStore(FluxStoreType.GROUPLIST_INFO);
    }

    public BleScannerStore getBleScannerStore(){
        return getStore(FluxStoreType.BLE_SCANNER_MODEL);
    }

    public RollCallDialogStore getRollCallDialogStore(){
        return getStore(FluxStoreType.ROLLCALLDIALOG);
    }

    public DeviceListInGroupStore getDeviceListInGroupStore(){
        return getStore(FluxStoreType.DEVICELISTINGROUP);
    }
}
