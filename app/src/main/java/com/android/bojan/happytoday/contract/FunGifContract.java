package com.android.bojan.happytoday.contract;

import com.android.bojan.base.base.BasePresenter;
import com.android.bojan.base.base.BaseView;
import com.android.bojan.happytoday.model.entry.GifBean;
import com.android.bojan.happytoday.model.entry.NewsBean;

import java.util.List;

/**
 * Create by bojan
 * on 2018/8/30
 */
public interface FunGifContract {

        interface view extends BaseView {
            void setData(List<GifBean.ResultBean> dataList);
            void getGifFail(Throwable throwable);
        }

        interface presenter extends BasePresenter {
            void getGifData(String type);
        }

}
