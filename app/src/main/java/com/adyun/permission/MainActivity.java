package com.adyun.permission;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.adyun.lib_permission.annotation.Permission;
import com.adyun.lib_permission.annotation.PermissionCanceled;
import com.adyun.lib_permission.annotation.PermissionDenied;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_two_permission).setOnClickListener(this);


        // 支持在 fragment  和 service 申请权限


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_two_permission:
                requestTwoPermission();
                break;
        }

    }

//    /**
//     *  requestCode = 200 可以默认不写
//     */
//    @Permission(value = {Manifest.permission.ACCESS_FINE_LOCATION})
//    private void requestTwoPermission() {
//        Toast.makeText(this, "请求两个权限成功（写和相机）", Toast.LENGTH_SHORT).show();
//    }
    @Permission(value = {Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE})
    private void requestTwoPermission() {
        Toast.makeText(this, "请求两个权限成功（写和相机）", Toast.LENGTH_SHORT).show();
    }
    @PermissionCanceled()
    private void cancel() {
        Log.i(TAG, "writeCancel: " );
        Toast.makeText(this, "cancel", Toast.LENGTH_SHORT).show();
    }

    @PermissionDenied()
    private void deny() {
        Log.i(TAG, "writeDeny:");
        Toast.makeText(this, "deny", Toast.LENGTH_SHORT).show();
    }
}
