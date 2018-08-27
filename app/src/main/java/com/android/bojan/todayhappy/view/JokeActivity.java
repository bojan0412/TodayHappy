package com.android.bojan.todayhappy.view;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.android.bojan.base.base.BaseActivity;
import com.android.bojan.todayhappy.R;
import com.android.bojan.todayhappy.adapter.JokeAdapter;
import com.android.bojan.todayhappy.bean.JokeBean;
import com.android.bojan.todayhappy.contract.JokeContract;
import com.android.bojan.todayhappy.presenter.JokePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class JokeActivity extends BaseActivity<JokePresenter> implements JokeContract.view {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private List<JokeBean.ResultBean> mDatas = new ArrayList<>();
    private JokeAdapter mAdapter;

    @Override
    protected void initView() {
        super.initView();
        mPresenter.getData();
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new JokeAdapter(mDatas);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public JokePresenter initPresenter() {
        return new JokePresenter(this);
    }

    @Override
    protected @LayoutRes
    int getLayout() {
        return R.layout.activity_joke;
    }

    @Override
    public void setData(List<JokeBean.ResultBean> dataList) {
        mDatas.addAll(dataList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadingDialog() {
        Toast.makeText(JokeActivity.this, "loading...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dismissLoadingDialog() {
        Toast.makeText(JokeActivity.this, "dismiss...", Toast.LENGTH_SHORT).show();
    }
}
