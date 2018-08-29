package com.android.bojan.happytoday.presenter;

import com.android.bojan.base.retrofit.ExceptionHelper;
import com.android.bojan.happytoday.model.api.JokeApi;
import com.android.bojan.happytoday.model.api.JokeService;
import com.android.bojan.happytoday.model.entry.JokeBean;
import com.android.bojan.happytoday.contract.JokeContract;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Create by bojan
 * on 2018/8/27
 */
public class JokePresenter extends BasePresenterImpl<JokeContract.view> implements JokeContract.presenter {


    public JokePresenter(JokeContract.view view) {
        super(view);
    }


    @Override
    public void getJokeData(final boolean isLoadMore) {
        JokeApi.getInstance().getData(JokeService.APP_KEY).subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                addDisposable(disposable);
                if (!isLoadMore) {
                    mView.showLoadingDialog();
                }
            }
        }).delay(1, TimeUnit.SECONDS).map(new Function<JokeBean, List<JokeBean.ResultBean>>() {
            @Override
            public List<JokeBean.ResultBean> apply(JokeBean jokeBean) throws Exception {
                return jokeBean.getResult();
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<JokeBean.ResultBean>>() {
            @Override
            public void accept(List<JokeBean.ResultBean> resultBeans) throws Exception {
                getJokeSucess(resultBeans, isLoadMore);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                getJokeFail(throwable, isLoadMore);
            }
        });
    }

    @Override
    public void getJokeSucess(List<JokeBean.ResultBean> resultBeans, boolean isLoadMore) {
        mView.dismissLoadingDialog();
        mView.setData(resultBeans,isLoadMore);
        mView.resultForLoadMore(true);

    }

    @Override
    public void getJokeFail(Throwable throwable, boolean isLoadMore) {
        mView.dismissLoadingDialog();
        mView.resultForLoadMore(false);
        ExceptionHelper.handleException(throwable);
    }

}
