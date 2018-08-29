package com.android.bojan.happytoday.model;

import com.android.bojan.base.base.BasePresenter;

/**
 * Create by bojan
 * on 2018/8/29
 */
public abstract class BaseModel<T extends BasePresenter>

{
    T mPresenter;

    public BaseModel(T presenter) {
        this.mPresenter = presenter;
    }
}
