package com.android.bojan.todayhappy.contract;

import com.android.bojan.base.base.BasePresenter;
import com.android.bojan.base.base.BaseView;
import com.android.bojan.todayhappy.bean.JokeBean;

import java.util.List;

/**
 * Create by bojan
 * on 2018/8/27
 */
public interface JokeContract {

    interface view extends BaseView {
        void setData(List<JokeBean.ResultBean> dataList);

    }

    interface presenter extends BasePresenter {
        void getData();
    }
}
