package com.adyun.lib_permission.menu;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import com.adyun.lib_permission.menu.base.IMenu;

/**
 * Created by Zachary
 * on 2018/10/12.
 */
public class Default implements IMenu{
    @Override
    public Intent getMenuIntent(Context context) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.fromParts("package",context.getPackageName(),null));
        return intent;
    }
}
