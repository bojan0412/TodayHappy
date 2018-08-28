package com.android.bojan.base.utils;

import android.app.Activity;
import android.os.Build;

/**
 * Create by bojan
 * on 2018/8/28
 */


public class ActivityUtils {
    /**
     * 判断activity的生命周期
     *
     * @param currentActivity
     * @return activity是否处于可用状态
     */
    public static boolean activityIsAlive(Activity currentActivity) {
        if (currentActivity == null) return false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return !(currentActivity.isDestroyed() || currentActivity.isFinishing());
        } else {
            return !currentActivity.isFinishing();
        }
    }
}
