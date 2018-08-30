package com.android.bojan.happytoday.presenter;

import com.android.bojan.happytoday.contract.NewsContract;
import com.android.bojan.happytoday.model.api.JokeApi;
import com.android.bojan.happytoday.model.api.JokeService;
import com.android.bojan.happytoday.model.entry.NewsBean;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Create by bojan
 * on 2018/8/29
 */
public class NewsPresenter extends BasePresenterImpl<NewsContract.view> implements NewsContract.presenter {
    public NewsPresenter(NewsContract.view view) {
        super(view);
    }

    @Override
    public void getNewsData(String type) {
        JokeApi.getInstance().getNewsTopData(type, JokeService.APP_KEY_NEWS).subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                addDisposable(disposable);
                mView.showLoadingDialog();
            }
        }).map(new Function<NewsBean, List<NewsBean.ResultBean.DataBean>>() {
            @Override
            public List<NewsBean.ResultBean.DataBean> apply(NewsBean newsBean) throws Exception {
                return newsBean.getResult().getData();
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<NewsBean.ResultBean.DataBean>>() {
            @Override
            public void accept(List<NewsBean.ResultBean.DataBean> resultBeans) throws Exception {
                mView.setData(resultBeans);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mView.dismissLoadingDialog();
                mView.getJokeFail(throwable);
            }
        });
    }
}
