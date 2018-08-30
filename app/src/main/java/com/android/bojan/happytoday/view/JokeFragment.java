package com.android.bojan.happytoday.view;

import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.bojan.base.base.BaseFragment;
import com.android.bojan.base.utils.ShareUtils;
import com.android.bojan.happytoday.R;
import com.android.bojan.happytoday.adapter.JokeQuickAdapter;
import com.android.bojan.happytoday.contract.JokeContract;
import com.android.bojan.happytoday.model.entry.JokeBean;
import com.android.bojan.happytoday.presenter.JokePresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Create by bojan
 * on 2018/8/29
 */
public class JokeFragment extends BaseFragment<JokeContract.presenter> implements JokeContract.view {

    @BindView(R.id.rv_joke)
    RecyclerView mRvJoke;
    @BindView(R.id.srl_joke)
    SwipeRefreshLayout mSrlJoke;
    @BindView(R.id.ll_loading)
    LinearLayout mLlLoading;
    @BindView(R.id.ll_error)
    LinearLayout mLlError;
    private JokeQuickAdapter mAdapter;


    @Override
    protected int getLayoutID() {
        return R.layout.fragment_joke;
    }

    @Override
    protected void fetchData() {

    }

    @Override
    protected JokeContract.presenter initPresenter() {
        return new JokePresenter(this);
    }

    @Override
    protected void setLayoutView(ViewGroup container, View view) {

        mAdapter = new JokeQuickAdapter();
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);

        //设置下拉刷新
        mSrlJoke.setColorSchemeColors(Color.RED, Color.GREEN, Color.YELLOW);
        mSrlJoke.setOnRefreshListener(() -> mPresenter.getJokeData(true));

        mRvJoke.setLayoutManager(new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL,
                false));
        mRvJoke.setAdapter(mAdapter);
        mRvJoke.addOnItemTouchListener(new OnItemLongClickListener() {
            @Override
            public void onSimpleItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                if (mAdapter.getItem(position) == null) return;
                String content = mAdapter.getItem(position).getContent();
                ShareUtils.share(getActivity(), content);
            }
        });
        mAdapter.setOnLoadMoreListener(() -> mPresenter.getJokeData(true), mRvJoke);
        updateView();
    }

    private void updateView() {
        mSrlJoke.setVisibility(View.VISIBLE);
        mLlLoading.setVisibility(View.VISIBLE);
        mLlError.setVisibility(View.GONE);
        mSrlJoke.setRefreshing(true);

        mPresenter.getJokeData(false);
    }

    @Override
    public void setData(List<JokeBean.ResultBean> dataList, boolean isLoadMore) {
        if (isLoadMore) {
            mAdapter.addData(dataList);
        } else {
            mLlLoading.setVisibility(View.GONE);
            mLlError.setVisibility(View.GONE);
            mAdapter.setNewData(dataList);
            mSrlJoke.setRefreshing(false);

        }

    }

    @Override
    public void resultForLoadMore(boolean isSucess) {
        if (isSucess) {
            mAdapter.loadMoreComplete();
        } else {
            mAdapter.loadMoreFail();
            mLlError.setVisibility(View.VISIBLE);
            mLlLoading.setVisibility(View.GONE);
            mSrlJoke.setVisibility(View.GONE);
        }
        mSrlJoke.setRefreshing(false);

    }

    @Override
    public void showLoadingDialog() {
        Toast.makeText(getContext(), "loading...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dismissLoadingDialog() {
        Toast.makeText(getContext(), "dismiss...", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.tv_joke_load_again)
    public void onClick() {
        updateView();
    }
}
