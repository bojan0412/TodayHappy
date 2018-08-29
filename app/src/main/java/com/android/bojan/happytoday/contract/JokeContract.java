package com.android.bojan.happytoday.contract;

import com.android.bojan.base.base.BasePresenter;
import com.android.bojan.base.base.BaseView;
import com.android.bojan.happytoday.model.entry.JokeBean;

import java.util.List;

/**
 * Create by bojan
 * on 2018/8/27
 */
public interface JokeContract {

    interface view extends BaseView {
        void setData(List<JokeBean.ResultBean> dataList, boolean isLoadMore);

        void resultForLoadMore(boolean isSucess);
    }

    interface presenter extends BasePresenter {
        void getJokeData(boolean isLoadMore);

        void getJokeSucess(List<JokeBean.ResultBean> resultBeans, boolean isLoadMore);

        void getJokeFail(Throwable throwable, boolean isloadMore);

    }
}
