package com.shawn.newrollcall.ScanBLEModel.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.shawn.newrollcall.FluxCenter.AppFluxCenter;
import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.view.AppBaseActivity;
import com.shawn.newrollcall.R;
import com.shawn.newrollcall.ScanBLEModel.BleDeviceItem;
import com.shawn.newrollcall.ScanBLEModel.RollCallBLEScanner;
import com.shawn.newrollcall.ScanBLEModel.action.BleScannerActionType;
import com.shawn.newrollcall.databinding.ActivityScanBinding;
import com.shawn.newrollcall.login.view.LodingFactory;

import java.util.List;

/**
 * Created by Shawn Wu on 2017/12/11.
 * 參考舊版
 */

public class ScanActivity extends AppBaseActivity{

    private RollCallBLEScanner rollCallBLEScanner;

    private KProgressHUD lodingview;

    private ActivityScanBinding binding;
    private String listName,imageUri;

    private ScanDeviceListAdapter scanDeviceListAdapter;

    public static final String IMAGE_URI = "listName";
    public static final String GROUP_LIST_NAME = "imageUri";
    private ImageView scanLoading;

    public static int addDataRequestCode = 6666;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_scan);
                Bundle bundle = this.getIntent().getExtras();
        assert bundle != null;
        listName =  bundle.getString(GROUP_LIST_NAME);
        imageUri =  bundle.getString(IMAGE_URI);

        binding.scanActivityToolbar.setTitle(listName);
        binding.btnok.setOnClickListener(getBtnOkLintener());

        setSupportActionBar(binding.scanActivityToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        rollCallBLEScanner = new RollCallBLEScanner
                .Builder()
                .build(this);
        rollCallBLEScanner.startScan();
        setDeviceListRecyclerView();
        setSearchAAnimation();

    }

    private View.OnClickListener getBtnOkLintener(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollCallBLEScanner.stopScan();
                List<BleDeviceItem> deviceItems = rollCallBLEScanner.getDeviceList();

                String account = AppFluxCenter.getStore().getSharedPreferences().getSavedAccount(getBaseContext());

                for(int i =0; i < deviceItems.size();i++) {
                    String address = deviceItems.get(i).getAddress();
                    String name = deviceItems.get(i).getName();
                    AppFluxCenter
                            .getActionCreator()
                            .getBleScannerCreator()
                            .insertNewDeviceDatatoGroup("extra_add",account,listName,address,name,imageUri,i,deviceItems.size());
                }

            }
        };
    }

    private void setDeviceListRecyclerView() {
        scanDeviceListAdapter = new ScanDeviceListAdapter(rollCallBLEScanner.getDeviceList());
        binding.deviceListRecyclerview.setAdapter(scanDeviceListAdapter);
        binding.deviceListRecyclerview.setItemAnimator(new DefaultItemAnimator());
        binding.deviceListRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(1, OrientationHelper.VERTICAL));

    }

    private Animation getAnimation(){
        Animation animation = new AlphaAnimation(0.1f, 1.0f);
        animation.setDuration(1000);
        animation.setRepeatCount(-1);
        return animation;
    }

    private Animation getNullAnimation(){
        Animation animation = new AlphaAnimation(0, 0);
        animation.setDuration(1000);
        animation.setRepeatCount(-1);
        return animation;
    }

    private void setSearchAAnimation(){
        binding.searchview.setVisibility(View.VISIBLE);
        binding.searchIcon.setAnimation(getAnimation());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.scan_device_toolbar_menu, menu);
        MenuItem scanLoadingItem = menu.findItem(R.id.action_scanning_loading);

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        scanLoading = (ImageView)inflater.inflate(R.layout.scan_device_icon, null);
        scanLoading.setAnimation(getAnimation());
        scanLoadingItem.setActionView(scanLoading);

        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        lodingview = LodingFactory.getLodingAnimation(R.string.please_wait,this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        TextView menuText = findViewById(R.id.action_scan);

        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                return true;

            case R.id.action_scan:
                if(rollCallBLEScanner.isScaning()) {
                    rollCallBLEScanner.stopScan();
                    if (rollCallBLEScanner.getDeviceList().isEmpty()){
                        setNotFoundView();
                    }
                    menuText.setText(getString(R.string.scan));
                    scanLoading.setAnimation(getNullAnimation());
                }else{
                    clearView();
                    setSearchView();
                    menuText.setText(getString(R.string.scanner_stop));
                    rollCallBLEScanner.startScan();
                    scanLoading.setAnimation(getAnimation());

                }

                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void clearView(){
        rollCallBLEScanner.clearDeviceList();
        scanDeviceListAdapter.updateView(rollCallBLEScanner.getDeviceList());
        binding.btnok.setEnabled(false);
        binding.btnok.setBackgroundColor(getColor(R.color.theme_gray));
    }

    private void setNotFoundView(){
        binding.searchNotFoundDeviceView.setVisibility(View.VISIBLE);
        binding.searchview.setVisibility(View.GONE);
    }

    private void setSearchView(){
        binding.searchNotFoundDeviceView.setVisibility(View.GONE);
        binding.searchview.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFluxChanged(FluxAction fluxAction) {

        switch (fluxAction.getType()) {

            case BleScannerActionType.FIND_NEW_DEVICE:
                binding.searchview.setVisibility(View.GONE);
                binding.btnok.setEnabled(true);
                binding.btnok.setBackgroundColor(getColor(R.color.theme_green));

                scanDeviceListAdapter.updateView(rollCallBLEScanner.getDeviceList());
                break;


            case BleScannerActionType.INSERT_NEW_DATA_TO_GROUP:
                lodingview.show();
                break;

            case BleScannerActionType.INSERT_NEW_DATA_TO_GROUP_SUCCESS:
                lodingview.dismiss();
                Toast.makeText(this,getString(R.string.add_success),Toast.LENGTH_SHORT).show();
                finish();

                break;
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        rollCallBLEScanner.stopScan();
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
