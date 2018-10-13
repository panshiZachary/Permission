package com.adyun.lib_permission.core;

import android.content.Context;

import android.util.Log;

import com.adyun.lib_permission.PermissionActivity;
import com.adyun.lib_permission.PermissionUtils;
import com.adyun.lib_permission.annotation.Permission;
import com.adyun.lib_permission.annotation.PermissionCanceled;
import com.adyun.lib_permission.annotation.PermissionDenied;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by Zachary
 * on 2018/10/12.
 */
@Aspect
public class PermissionAspect  {
    private static final String TAG = "PermissionAspect";

    // 切入点
    @Pointcut("execution(@com.adyun.lib_permission.annotation.Permission * *(..)) && @annotation(permission)")
    public void requestPermission(Permission permission){

    }
    @Around("requestPermission(permission)")
    public void aroundJoinPoint(final ProceedingJoinPoint joinPoint,Permission permission) throws  Throwable{

        // 初始化 context
        Context context =null;
        final Object object = joinPoint.getThis();
        if (joinPoint.getThis() instanceof  Context){
            context = (Context) object;
        }else if (joinPoint.getThis() instanceof android.support.v4.app.Fragment){
            context = ((android.support.v4.app.Fragment) object).getActivity();
        }else if (joinPoint.getThis() instanceof android.app.Fragment){
            context = ((android.app.Fragment) object).getActivity();
        }

        if ( context == null || permission ==null){
            Log.d(TAG, "aroundJoinPoint: err");
            return;
        }
        final Context finalContext = context;
        PermissionActivity.requestPermission(context,permission.value(),permission.requestCode(), new IPermission() {
            @Override
            public void ganted() {
                try {
                    joinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }

            @Override
            public void canceled() {
                PermissionUtils.invokAnnotation(object, PermissionCanceled.class);
            }

            @Override
            public void denied() {
                PermissionUtils.invokAnnotation(object, PermissionDenied.class);
                PermissionUtils.goToMenu(finalContext);
            }
        });





    }


}
