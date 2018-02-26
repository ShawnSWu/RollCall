package com.shawn.newrollcall.RollCallDialog.action;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.shawn.newrollcall.FluxCenter.action.FluxActionCreator;
import com.shawn.newrollcall.R;
import com.shawn.newrollcall.ScanBLEModel.view.ScanActivity;


/**
 * Created by Shawn Wu on 2017/12/24.
 *
 */

public class RollCallDialogCreator extends FluxActionCreator {

    private AlertDialog rollCallDailog;

    public void showAddDataFromCreateGroup(final Activity activity, String title, String message,final String listName, final String imageUri) {
        addAction(newAction(RollCallDialogActionType.ADD_DATA_FROM_CREATE_GROUP));

        View dialogView = LayoutInflater.from(activity).inflate(R.layout.rollcall_dialog_view,null);
        TextView dialogtitle = dialogView.findViewById(R.id.dialog_title);
        dialogtitle.setText(title);
        TextView dialogmessage = dialogView.findViewById(R.id.dialog_message);
        dialogmessage.setText(message);

        Button btnOk = dialogView.findViewById(R.id.ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString(ScanActivity.listName_Tag, listName);
                bundle.putString(ScanActivity.imageUri_Tag, imageUri);

                Intent i = new Intent();
                i.setClass(activity,ScanActivity.class);
                i.putExtras(bundle);
                activity.startActivityForResult(i,ScanActivity.addDataRequestCode);

//                AppFluxCenter
//                        .getActionCreator()
//                        .getIntentCenterActionsCreator()
//                        .startScanActivity(activity,listName,imageUri);

                rollCallDailog.dismiss();
            }
        });
        Button btnCancel = dialogView.findViewById(R.id.cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollCallDailog.dismiss();
            }
        });

        rollCallDailog = new AlertDialog.Builder(activity)
                .setView(dialogView)
                .create();
        rollCallDailog.show();
    }

}
