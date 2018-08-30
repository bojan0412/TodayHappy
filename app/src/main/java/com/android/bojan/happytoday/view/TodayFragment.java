package com.android.bojan.happytoday.view;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.bojan.base.base.BaseFragment;
import com.android.bojan.happytoday.R;
import com.android.bojan.happytoday.adapter.TodayAdapter;
import com.android.bojan.happytoday.contract.TodayContract;
import com.android.bojan.happytoday.model.entry.TodayBean;
import com.android.bojan.happytoday.presenter.TodayPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Create by bojan
 * on 2018/8/30
 */
public class TodayFragment extends BaseFragment<TodayContract.presenter> implements TodayContract.view {
    @OnClick(R.id.fab)
    public void fabClick() {
        mRecyclerView.smoothScrollToPosition(0);
    }

    @BindView(R.id.rv_today)
    RecyclerView mRecyclerView;
    private TodayAdapter mAdapter;

    @Override
    protected TodayContract.presenter initPresenter() {
        return new TodayPresenter(this);
    }

    @Override
    protected void setLayoutView(ViewGroup container, View view) {
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mAdapter = new TodayAdapter();
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        mRecyclerView.setAdapter(mAdapter);
        fetchData();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_today;
    }

    @Override
    protected void fetchData() {
        mPresenter.getTodayOfHistoryData(getDate());
    }

    /**
     * 获得当前日期
     *
     * @return
     */
    private String getDate() {
        Calendar calendar = Calendar.getInstance();
        final int month = calendar.get(Calendar.MONTH) + 1;
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        return month + "/" + day;
    }

    @Override
    public void setData(List<TodayBean.ResultBean> dataList) {
        mAdapter.setNewData(dataList);
    }

    @Override
    public void getToadyFail(Throwable throwable) {
        Toast.makeText(getContext(), "获取数据失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }
}
