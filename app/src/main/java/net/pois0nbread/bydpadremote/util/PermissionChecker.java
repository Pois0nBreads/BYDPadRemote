package net.pois0nbread.bydpadremote.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

public class PermissionChecker {

    public static final String[] SYSTEM_PERMISSIONS = {
            Manifest.permission.BLUETOOTH,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.BLUETOOTH_ADMIN,
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    public static final String[] BYD_PERMISSIONS = {
        BydManifest.permission.BYDAUTO_BODYWORK_COMMON,
        BydManifest.permission.BYDAUTO_AC_COMMON,
        BydManifest.permission.BYDAUTO_PANORAMA_COMMON,
        BydManifest.permission.BYDAUTO_PANORAMA_GET,
        BydManifest.permission.BYDAUTO_SETTING_COMMON,
        BydManifest.permission.BYDAUTO_INSTRUMENT_COMMON,
        BydManifest.permission.BYDAUTO_DOOR_LOCK_COMMON,
//            "android.permission.BYDAUTO_ADAS_COMMON",
    };

    public static boolean isBydAutoPermissionGranted(Context Context) {
        boolean isAllGranted = true;
        for (String perm : BYD_PERMISSIONS) {
            if (Context.checkSelfPermission(perm) != PackageManager.PERMISSION_GRANTED) {
                isAllGranted = false;
                break;
            }
        }
        return isAllGranted;
    }

    public static boolean isSystemPermissionGranted(Context Context) {
        boolean isAllGranted = true;
        for (String perm : SYSTEM_PERMISSIONS) {
            if (Context.checkSelfPermission(perm) != PackageManager.PERMISSION_GRANTED) {
                isAllGranted = false;
                break;
            }
        }
        return isAllGranted;
    }
}
