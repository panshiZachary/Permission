package com.adyun.lib_permission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

import com.adyun.lib_permission.core.IPermission;

/**
 * Created by Zachary
 * on 2018/10/12.
 * 透明的Activity
 */
public class PermissionActivity  extends Activity{

    private static final String PARAM_PERMISSION = "param_permission";
    private static final String PARAM_REQUEST_CODE = "param_request_code";

    private String[] permissions;
    private int mRequestCode;
    private static IPermission permissionListener;

    /**
     *  请求权限
     * @param context   上下文
     * @param permissions  权限
     * @param requestCode  请求码
     * @param iPermission  回调接口
     */
    public static void requestPermission(Context context, String[] permissions, int requestCode, IPermission iPermission) {
        permissionListener = iPermission;
        Intent intent = new Intent(context,PermissionActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle = new Bundle();
        bundle.putStringArray(PARAM_PERMISSION,permissions);
        bundle.putInt(PARAM_REQUEST_CODE,requestCode);
        intent.putExtras(bundle);
        context.startActivity(intent);
        if (context instanceof Activity){
            ((Activity) context).overridePendingTransition(0,0);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_apply);
        // 权限申请
        this.permissions = getIntent().getStringArrayExtra(PARAM_PERMISSION);
        this.mRequestCode = getIntent().getIntExtra(PARAM_REQUEST_CODE, -1);

        if (permissions == null || mRequestCode < 0 || permissionListener == null){
            finish();
            return;
        }
        // 检查是否已授权
        if (PermissionUtils.hasPermission(this,permissions)){
           permissionListener.ganted();
           finish();
           return;
        }
        // 授权
        ActivityCompat.requestPermissions(this,this.permissions,this.mRequestCode);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // 请求权限成功
        if (PermissionUtils.verifyPermission(this,grantResults)){
           permissionListener.ganted();
           finish();
           return;
        }
        // 用户点击了不在提醒
        if (!PermissionUtils.shouldShowRequestPermissionRationale(this,permissions)){
            permissionListener.denied();
            finish();
            return;
        }
        // 用户取消
        permissionListener.canceled();
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0,0);
    }
}
