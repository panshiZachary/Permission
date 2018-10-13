package com.adyun.lib_permission.annotation;

import com.adyun.lib_permission.PermissionUtils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Zachary
 * on 2018/10/12.
 */
@Retention(RetentionPolicy.RUNTIME)   //  保留到运行时
@Target(ElementType.METHOD)  //作用在方法上
public @interface PermissionCanceled {

    int requestCode() default PermissionUtils.DEFAULT_REQUEST_CODE;

}
