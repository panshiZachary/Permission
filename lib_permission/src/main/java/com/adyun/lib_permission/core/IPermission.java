package com.adyun.lib_permission.core;

/**
 * Created by Zachary
 * on 2018/10/12.
 *
 */
public interface IPermission {
    /**
     * 已经授权了
     */
    void ganted();

    /**
     *  已经取消
     */
    void canceled();

    /**
     * 点击拒绝后 不在提醒
     */
    void denied();
}
