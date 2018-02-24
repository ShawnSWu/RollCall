package com.shawn.newrollcall.ScanBLEModel.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.shawn.newrollcall.FluxCenter.AppFluxCenter;
import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.view.AppBaseActivity;
import com.shawn.newrollcall.R;
import com.shawn.newrollcall.ScanBLEModel.RollCallBLEScanner;
import com.shawn.newrollcall.databinding.ActivityScanBinding;
import com.shawn.newrollcall.util.InputUtil;

/**
 * Created by Shawn Wu on 2017/12/11.
 * 參考舊版
 */

public class ScanActivity extends AppBaseActivity{

    private RollCallBLEScanner rollCallBLEScanner;

    private ActivityScanBinding binding;
    private String listName,imageUri;

    public static final String listName_Tag = "listName";
    public static final String imageUri_Tag = "imageUri";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_scan);
                Bundle bundle = this.getIntent().getExtras();
        assert bundle != null;
        listName =  bundle.getString(listName_Tag);
        imageUri =  bundle.getString(imageUri_Tag);

        binding.scanActivityToolbar.setTitle(listName);

        setSupportActionBar(binding.scanActivityToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


//        rollCallBLEScanner = new RollCallBLEScanner
//                .Builder()
//                .build(this);
//        rollCallBLEScanner.startScan();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.scan_device_toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                finish();
                return true;

            case R.id.action_scan:


                return true;

        }
        return super.onOptionsItemSelected(item);
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
