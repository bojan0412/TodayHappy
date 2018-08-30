package com.android.bojan.happytoday.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.bojan.base.base.BaseFragment;
import com.android.bojan.happytoday.R;
import com.android.bojan.happytoday.adapter.GifAdapter;
import com.android.bojan.happytoday.contract.FunGifContract;
import com.android.bojan.happytoday.model.entry.GifBean;
import com.android.bojan.happytoday.presenter.FunGifPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * Create by bojan
 * on 2018/8/30
 */
public class FunGifFragment extends BaseFragment<FunGifContract.presenter> implements FunGifContract.view {
    @BindView(R.id.rv_gif)
    RecyclerView mGifRv;

    private GifAdapter mAdapter;

    @Override
    protected FunGifContract.presenter initPresenter() {
        return new FunGifPresenter(this);
    }

    @Override
    protected void setLayoutView(ViewGroup container, View view) {
        mGifRv.setLayoutManager(new LinearLayoutManager(getContext()));
        fetchData();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_gif;
    }

    @Override
    protected void fetchData() {
        mPresenter.getGifData("pic");
    }

    @Override
    public void setData(List<GifBean.ResultBean> dataList) {
        mAdapter = new GifAdapter(getContext(), dataList);
        mGifRv.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void getGifFail(Throwable throwable) {
        Toast.makeText(getActivity(), "获取数据失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }
}
