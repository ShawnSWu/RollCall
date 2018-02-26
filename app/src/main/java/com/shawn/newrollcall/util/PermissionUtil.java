package com.shawn.newrollcall.util;

import android.Manifest;
import android.content.Context;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;


/**
 * Created by Shawn Wu on 2017/12/21.
 * 未封裝完
 */

public class PermissionUtil {

    public static void setAccessLocation(Context context, PermissionListener permissionListener,String deniedMessage) {
        TedPermission.with(context)
                .setPermissionListener(permissionListener)
                .setDeniedMessage(deniedMessage)
                .setPermissions(Manifest.permission.BLUETOOTH, Manifest.permission.BLUETOOTH_ADMIN, Manifest.permission.ACCESS_COARSE_LOCATION)
                .check();
    }

    public static void setExternalStorage(Context context, PermissionListener permissionListener,String deniedMessage) {
        TedPermission.with(context)
                .setPermissionListener(permissionListener)
                .setDeniedMessage(deniedMessage)
                .setPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();
    }
}
