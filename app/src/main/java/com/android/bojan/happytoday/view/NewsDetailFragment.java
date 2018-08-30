package com.android.bojan.happytoday.view;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.android.bojan.base.base.BaseFragment;
import com.android.bojan.base.utils.ShareUtils;
import com.android.bojan.happytoday.R;
import com.android.bojan.happytoday.adapter.NewsDataAdapter;
import com.android.bojan.happytoday.contract.NewsContract;
import com.android.bojan.happytoday.model.entry.NewsBean;
import com.android.bojan.happytoday.presenter.NewsPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.xiawei.webviewlib.WebViewActivity;

import java.util.List;

import butterknife.BindView;

/**
 * Create by bojan
 * on 2018/8/29
 */
public class NewsDetailFragment extends BaseFragment<NewsContract.presenter> implements NewsContract.view {

    @BindView(R.id.rv_news_detail)
    RecyclerView mRvNewDetail;
    @BindView(R.id.srl)
    SwipeRefreshLayout mSrl;
    private NewsDataAdapter mAdapter;
    /**
     * 新闻类型
     */
    private String mType;

    @SuppressLint("ValidFragment")
    public NewsDetailFragment(String type) {
        this.mType = type;
    }

    public NewsDetailFragment() {
    }


    @Override
    protected NewsContract.presenter initPresenter() {
        return new NewsPresenter(this);
    }

    @Override
    protected void setLayoutView(ViewGroup container, View view) {
        mAdapter = new NewsDataAdapter();
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        mSrl.setColorSchemeColors(Color.RED, Color.RED);
        mSrl.setOnRefreshListener(this::fetchData);
        mRvNewDetail.setAdapter(mAdapter);
        mRvNewDetail.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRvNewDetail.addOnItemTouchListener(new SimpleClickListener(){
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            }

            @Override
            public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                String title = mAdapter.getItem(position).getTitle();
                String url   = mAdapter.getItem(position).getUrl();
                ShareUtils.share(getActivity(), title + "\n" + url);
            }

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                WebViewActivity.startUrl(getActivity(),
                        ((NewsBean.ResultBean.DataBean) adapter.getItem(
                                position)).getUrl());
            }

            @Override
            public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_news_detail;
    }

    @Override
    protected void fetchData() {
        mSrl.setRefreshing(true);
        mPresenter.getNewsData(mType);
    }

    @Override
    public void setData(List<NewsBean.ResultBean.DataBean> dataList) {
        mSrl.setRefreshing(false);
        mAdapter.setNewData(dataList);
    }

    @Override
    public void getJokeFail(Throwable throwable) {
        mSrl.setRefreshing(false);
    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }
}
