package com.shawn.newrollcall.ScanBLEModel;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.widget.Toast;

import com.shawn.newrollcall.R;

/**
 * Created by Shawn Wu on 2017/12/01.
 *
 */

public class BluetoothManager {

    public static boolean isBluetoothSupported() {
        return BluetoothAdapter.getDefaultAdapter() != null;
    }

    public static boolean isBluetoothEnabled() {
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter
                .getDefaultAdapter();

        if (bluetoothAdapter != null) {
            return bluetoothAdapter.isEnabled();
        }

        return false;
    }

    public static void turnOnBluetooth(Activity activity) {
        Intent requestBluetoothOn = new Intent(
                BluetoothAdapter.ACTION_REQUEST_ENABLE);

        activity.startActivity(requestBluetoothOn);
    }


    public static boolean checkIfTurnOnBluetooth(Activity activity) {

        if (isBluetoothSupported()) {
            if (!isBluetoothEnabled()) {
                turnOnBluetooth(activity);
                return false;
            }
        }
        return true;
    }

}
