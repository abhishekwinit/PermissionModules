package com.super11.locationservice.Utils;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by abhishek on 25/6/16.
 */
public final class PermissionUtils {
    private PermissionUtils() {
    }

    public static boolean evaluateGrantResults(int[] grantResults){
        int grantedSum = 0;
        for (int i : grantResults) if (i == PackageManager.PERMISSION_GRANTED) grantedSum++;{
            return grantedSum == grantResults.length;
        }
    }

    public static boolean isInternetGranted(Context context){
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isReadStorageGranted(Context context){
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED&&
        ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isLocationGranted(Context context){
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }
}
