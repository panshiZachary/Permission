package com.adyun.lib_permission.menu;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;

import com.adyun.lib_permission.menu.base.IMenu;

/**
 * Created by Zachary
 * on 2018/10/12.
 */
public class VIVO implements IMenu {
    @Override
    public Intent getMenuIntent(Context context) {
        Intent appIntent = context.getPackageManager().getLaunchIntentForPackage("com.iqoo.secure");
        if (appIntent !=null && Build.VERSION.SDK_INT < 23){
            context.startActivity(appIntent);
            return null;
        }
        Intent vIntent = new Intent();
        vIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        vIntent.setAction(Settings.ACTION_SETTINGS);
        return vIntent;
    }
}
