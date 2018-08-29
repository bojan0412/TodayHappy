package com.android.bojan.base.base;

import io.reactivex.disposables.Disposable;

/**
 * Create by bojan
 * on 2018/8/27
 */
public interface BasePresenter {

    /**
     * 执行默认的初始化
     */
    void start();

    /**
     * 执行关闭前的置空任务,解绑view
     */
    void detach();

    /**
     * 收集disposable，退出时一并销毁
     */
    void addDisposable(Disposable disposable);

    /**
     * 注销所有请求
     */
    void unDisposable();

}
