package com.android.bojan.base.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.android.bojan.base.utils.ActivityUtils;

import butterknife.ButterKnife;

/**
 * Create by bojan
 * on 2018/8/27
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView,Lifeful {

    protected P mPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mPresenter = initPresenter();
        parpareBar();
        initView();
    }

    protected abstract void initView();

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detach();
            mPresenter = null;
        }
        super.onDestroy();
    }

    public abstract P initPresenter();

    protected abstract @LayoutRes int getLayout();

    protected void parpareBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            //window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        ButterKnife.bind(this);
    }

    public boolean activityIsAlive() {
        return ActivityUtils.activityIsAlive(this);
    }

    @Override
    public boolean isAlive() {
        return activityIsAlive();
    }
}
