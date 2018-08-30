package com.android.bojan.base.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.bojan.base.utils.ActivityUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Create by bojan
 * on 2018/8/27
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView, Lifeful {
    protected P mPresenter;
    private boolean mIsViewCreated = false;  //isViewInitiated
    private boolean mIsViewVisible = false;  //isVisibleToUser
    private boolean mIsDataInitiated = false;
    public Context mContext;

    private Unbinder mUnbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter=initPresenter();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mIsViewCreated = true;
        prepareFetchData();
    }

    public void prepareFetchData() {
        if (mIsViewVisible && mIsViewCreated && (!mIsDataInitiated)) {
            fetchData();
            mIsDataInitiated = true;
        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    protected abstract P initPresenter();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutID(), container, false);
        mUnbinder = ButterKnife.bind(this, view);
        setLayoutView(container, view);
        return view;
    }

    abstract protected void setLayoutView(ViewGroup container, View view);


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mIsViewVisible = isVisibleToUser;
        prepareFetchData();
    }

    @Override
    public void onResume() {
        super.onResume();
        prepareFetchData();
    }

    /**
     * @return 返回布局文件id
     */
    protected abstract int getLayoutID();

    /**
     * 懒加载
     */
    abstract protected void fetchData();



    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detach();
        }
        mIsViewCreated = false;
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        super.onDestroyView();
    }

    @Override
    public boolean isAlive() {
        return activityIsAlive();
    }

    public boolean activityIsAlive() {
        return getActivity() != null && ActivityUtils.activityIsAlive(getActivity());
    }
}
