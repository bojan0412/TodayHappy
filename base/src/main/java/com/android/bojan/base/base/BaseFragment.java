package com.android.bojan.base.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Create by bojan
 * on 2018/8/27
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {
    protected P mPresenter;
    private boolean mIsViewCreate = false;
    private boolean mIsViewVisible = false;
    public Context mContext;
    private boolean mIsFirstLoad = true;
    private Unbinder mUnbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mIsViewCreate = true;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    private P initPresenter() {
        return null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutID(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        setLayoutView(container, view);
        return view;
    }

    abstract void setLayoutView(ViewGroup container, View view);


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mIsViewVisible = isVisibleToUser;
        if (isVisibleToUser && mIsViewCreate) {
            visibleToUser();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mIsViewVisible) {
            visibleToUser();
        }
    }

    /**
     * @return 返回布局文件id
     */
    protected abstract int getLayoutID();

    /**
     * 懒加载，第一次加载
     */
    abstract protected void firstLoad();

    /**
     * 懒加载
     * 用户可见
     */
    protected void visibleToUser() {
        if (mIsFirstLoad) {
            firstLoad();
            mIsFirstLoad = false;
        }
    }

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detach();
        }
        mIsViewCreate = false;
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        super.onDestroyView();
    }

}
