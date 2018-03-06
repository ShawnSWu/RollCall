package com.shawn.newrollcall.RollCall.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;

import com.shawn.newrollcall.DeviceListInGroup.event.DeviceListInGroupItem;
import com.shawn.newrollcall.FluxCenter.action.FluxAction;
import com.shawn.newrollcall.FluxCenter.view.AppBaseActivity;
import com.shawn.newrollcall.R;
import com.shawn.newrollcall.databinding.ActivityRollcallResultBinding;
import com.shinelw.library.ColorArcProgressBar;

import java.util.ArrayList;

/**
 * Created by Shawn Wu on 2017/12/27.
 *
 */

public class RollCallResultActivity extends AppBaseActivity{

    private ActivityRollcallResultBinding binding;

    public static final String PEOPLE_COUNT = "peopleCount";
    public static final String ABSENCE_PEOPLE = "absencePeople";
    public static final String REST_OF_PEOPLE = "restOfPeople";
    public static final String GROUP_LIST_NAME = "listName";

    private ArrayList<DeviceListInGroupItem> restOfPeople;
    private int peopleCount,absencePeople;
    private String listName;
    private RollCallResultAdapter rollCallResultAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_rollcall_result);
        Bundle bundle = this.getIntent().getExtras();
        assert bundle != null;
        listName = bundle.getString(GROUP_LIST_NAME);
        peopleCount =  bundle.getInt(PEOPLE_COUNT);
        absencePeople =  bundle.getInt(ABSENCE_PEOPLE);
        restOfPeople = bundle.getParcelableArrayList(REST_OF_PEOPLE);

        ColorArcProgressBar progressBar_outpeople = findViewById(R.id.present_people);
        progressBar_outpeople.setMaxValues(peopleCount);
        progressBar_outpeople.setCurrentValues(restOfPeople.size());

        binding.rollcallResultActivityToolbar.setTitle(listName);
        binding.absencePeopleText.setText(String.format(getString(R.string.absence_people),absencePeople));
        binding.totalPeopleText.setText(String.format(getString(R.string.total_people),peopleCount));

        setSupportActionBar(binding.rollcallResultActivityToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        rollCallResultAdapter = new RollCallResultAdapter(restOfPeople);
        binding.absencePeopleRecyclerview.setAdapter(rollCallResultAdapter);
        binding.absencePeopleRecyclerview.setItemAnimator(new DefaultItemAnimator());
        binding.absencePeopleRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));
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
