package com.android.bojan.happytoday.contract;

import com.android.bojan.base.base.BasePresenter;
import com.android.bojan.base.base.BaseView;

/**
 * Create by bojan
 * on 2018/8/29
 */
public interface MainViewContract {
    interface View extends BaseView {


    }

    interface Persenter extends BasePresenter {

        void clearCache();

    }
}
