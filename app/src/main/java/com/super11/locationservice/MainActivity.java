package com.super11.locationservice;

import android.Manifest;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.super11.locationservice.Utils.LocationUtility;
import com.super11.locationservice.Utils.PermissionUtils;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    //LocationUtility mLocationUtility;

    private static final int REQUEST_INTERNET = 0;
    private static final int WRITE_EXTERNAL_STORAGE = 1;
    private static final int ACCESS_FINE_LOCATION = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       // mLocationUtility = new LocationUtility(this);


       /* if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (PermissionUtils.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)) {

                *//* Permission Alreadly granted

                 *//*
                Log.d("Abhishek", "Permission Alreadly granted ");

            } else {
                Log.d("Abhishek", "Permission don't have ");
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        1);
            }

        }*/
        checkPermissionsAndProceed();
    }


    private void checkPermissionsAndProceed() {
        if (PermissionUtils.isInternetGranted(this)){
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, REQUEST_INTERNET);
        }

        if (PermissionUtils.isReadStorageGranted(this)){
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length > 0) {
            if (PermissionUtils.evaluateGrantResults(grantResults)) {
                Log.d("Abhishek", "Permission callback ");
                checkPermissionsAndProceed();
            } else {
                showPermissionDeniedAndCloseApp();
            }
        }


    }

    private void showPermissionDeniedAndCloseApp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please Grants permissions")
                .setTitle("Permission !");

        AlertDialog dialog = builder.create();
        dialog.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                finish();
            }
        }, 2000);
    }
}
