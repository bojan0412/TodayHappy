package com.android.bojan.happytoday.contract;

import com.android.bojan.base.base.BasePresenter;
import com.android.bojan.base.base.BaseView;
import com.android.bojan.happytoday.model.entry.TodayBean;

import java.util.List;

/**
 * Create by bojan
 * on 2018/8/30
 */
public interface TodayContract {
    interface view extends BaseView{
        void setData(List<TodayBean.ResultBean> dataList);
        void getToadyFail(Throwable throwable);
    }

    interface presenter extends BasePresenter {
        void getTodayOfHistoryData(String date);
    }
}
