package com.android.bojan.happytoday.contract;

import com.android.bojan.base.base.BasePresenter;
import com.android.bojan.base.base.BaseView;
import com.android.bojan.happytoday.model.entry.JokeBean;
import com.android.bojan.happytoday.model.entry.NewsBean;

import java.util.List;

/**
 * Create by bojan
 * on 2018/8/29
 */
public interface NewsContract {
    interface view extends BaseView {
        void setData(List<NewsBean.ResultBean.DataBean> dataList);
        void getJokeFail(Throwable throwable);

    }

    interface presenter extends BasePresenter {
        void getNewsData(String type);



    }
}
