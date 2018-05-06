package com.shawn.newrollcall.RollCallDialog.action;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shawn.newrollcall.FluxCenter.AppFluxCenter;
import com.shawn.newrollcall.FluxCenter.action.FluxActionCreator;
import com.shawn.newrollcall.R;

/**
 * Created by Shawn Wu on 2017/12/24.
 *
 */

public class RollCallDialogCreator extends FluxActionCreator {

    private static AlertDialog rollCallDailog;

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

                AppFluxCenter
                        .getActionCreator()
                        .getIntentCenterActionsCreator()
                        .startScanActivity(activity,listName,imageUri);

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

        if(rollCallDailog == null) {
            rollCallDailog = new AlertDialog.Builder(activity)
                    .setView(dialogView)
                    .create();
        }
        if(!rollCallDailog.isShowing()) {
            rollCallDailog.show();
        }
    }

    public void showMaunalEditDailog(final Context activity, final String deviceName, final String deviceAddress) {
        addAction(newAction(RollCallDialogActionType.MAUNAL_EDIT_ADD));

        View dialogView = LayoutInflater.from(activity).inflate(R.layout.dialog_manual_edit,null);
        TextView dialogtitle = dialogView.findViewById(R.id.edit_device_title);
        dialogtitle.setText(activity.getString(R.string.edit_manually));
        final EditText reset_device_name = dialogView.findViewById(R.id.device_name_edit);
        reset_device_name.setHint(deviceName);
        TextView device_address = dialogView.findViewById(R.id.device_address);
        device_address.setText(String.format(activity.getString(R.string.device_address_message),deviceAddress));


        Button btnOk = dialogView.findViewById(R.id.btn_ok);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppFluxCenter
                        .getActionCreator()
                        .getBleScannerCreator()
                        .resetDeviceName(reset_device_name.getText().toString(),deviceAddress);
                rollCallDailog.dismiss();
            }
        });
        Button btnCancel = dialogView.findViewById(R.id.btn_close);
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
