package com.shawn.newrollcall.ScanBLEModel.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.shawn.newrollcall.FluxCenter.AppFluxCenter;
import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.view.AppBaseActivity;
import com.shawn.newrollcall.R;
import com.shawn.newrollcall.ScanBLEModel.action.BleScannerActionType;
import com.shawn.newrollcall.ScanBLEModel.event.GroupItemDataResponse;
import com.shawn.newrollcall.databinding.ActivitySetDeviceRemindBinding;

import java.util.ArrayList;

/**
 * Created by Shawn Wu on 2018/01/10.
 *
 */

public class SetDeviceRemindActivity extends AppBaseActivity{

    private ActivitySetDeviceRemindBinding binding;

    private ArrayList<String> allGroupList = new ArrayList<>();

    private static StringBuffer choose_sencond;

    private SetDeviceCardViewAdapter setDeviceCardViewAdapter;
    private String account;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_set_device_remind);

        account = AppFluxCenter.getStore().getSharedPreferences().getSavedAccount(this);
        AppFluxCenter.getActionCreator().getBleScannerCreator().getAllGroupName(account);


        binding.setDeviceRemindToolbar.setTitle(getString(R.string.setbledrive_title));
        setSupportActionBar(binding.setDeviceRemindToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        binding.secondsSpinner.setAdapter(ArrayAdapter.createFromResource(this, R.array.spinner_time, android.R.layout.simple_spinner_dropdown_item));
        binding.secondsSpinner.setOnItemSelectedListener(getSecondsSelectListenr());
        binding.btnok.setOnClickListener(getBtnOK());

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.groupDataRecyclerview.setLayoutManager(layoutManager);
        setDeviceCardViewAdapter = new SetDeviceCardViewAdapter();
        binding.groupDataRecyclerview.setAdapter(setDeviceCardViewAdapter);
    }

    private View.OnClickListener getBtnOK(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (binding.secondsSpinner.getSelectedItemPosition()) {

                    case 0:
                        choose_sencond=new StringBuffer("$10000#");
                        break;
                    case 1:
                        choose_sencond=new StringBuffer("$20000#");
                        break;
                    case 2:
                        choose_sencond=new StringBuffer("$1800000#");
                        break;
                    case 3:
                        choose_sencond=new StringBuffer("$2700000#");
                        break;
                    case 4:
                        choose_sencond=new StringBuffer("$3600000#");
                        break;
                    case 5:
                        choose_sencond=new StringBuffer("$5400000#");
                        break;
                    case 6:
                        choose_sencond=new StringBuffer("$7200000#");
                        break;
                }

                String groupName = binding.groupSpinner.getSelectedItem().toString();
                AppFluxCenter.getActionCreator().getDeviceListInGroupCreator().getGroupDeviceData(account,groupName);

                AppFluxCenter
                        .getActionCreator()
                        .getIntentCenterActionsCreator()
                        .startWriteDataToDeviceActivity(SetDeviceRemindActivity.this,groupName,choose_sencond);
            }
        };
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private Spinner.OnItemSelectedListener getGroupSelectListenr(){
        return new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i != 0) {
                    AppFluxCenter.getActionCreator().getBleScannerCreator().getSomeOneGroupData(account,allGroupList.get(i));
                    binding.btnok.setEnabled(true);
                    binding.btnok.setBackgroundColor(getColor(R.color.theme_green));
                }
                else {
                    binding.btnok.setEnabled(false);
                    binding.btnok.setBackgroundColor(getColor(R.color.theme_gray));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
    }

    private Spinner.OnItemSelectedListener getSecondsSelectListenr(){
        return new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                setDeviceCardViewAdapter.updateSetTime(binding.secondsSpinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
    }

    @Override
    public void onFluxChanged(FluxAction fluxAction) {

        switch (fluxAction.getType()){

            case BleScannerActionType.GET_ALL_GROUP_NAME_SUCCESS:
                allGroupList = (ArrayList<String>) fluxAction.getData()[0];
                allGroupList.set(0,getString(R.string.plz_choice_group));

                binding.groupSpinner.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,allGroupList));
                binding.groupSpinner.setOnItemSelectedListener(getGroupSelectListenr());


                break;


            case BleScannerActionType.GET_SOMEONE_GROUP_DATA_SUCCESS:
                GroupItemDataResponse groupItemDataResponse = (GroupItemDataResponse)fluxAction.getData()[0];
                setDeviceCardViewAdapter.updateSelectGroup(groupItemDataResponse);


                break;
        }

    }

    @Override
    public void onFluxStoreRegistered() {
        AppFluxCenter.getStore().getBleScannerStore().register(this);
    }

    @Override
    public void onFluxStoreUnregistered() {
        AppFluxCenter.getStore().getBleScannerStore().unRegister(this);
    }
}
