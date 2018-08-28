package com.android.bojan.happytoday.presenter;

import android.util.Log;

import com.android.bojan.base.base.baseImpl.BasePresenterImpl;
import com.android.bojan.base.retrofit.ExceptionHelper;
import com.android.bojan.happytoday.bean.JokeBean;
import com.android.bojan.happytoday.contract.JokeContract;
import com.android.bojan.happytoday.model.JokeApi;
import com.android.bojan.happytoday.model.JokeService;

import java.util.List;

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
    public void getData() {
        JokeApi.getInstance().getData(JokeService.APP_KEY).subscribeOn(Schedulers.io()).doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                addDisposable(disposable);
                mView.showLoadingDialog();
            }
        }).map(new Function<JokeBean, List<JokeBean.ResultBean>>() {
            @Override
            public List<JokeBean.ResultBean> apply(JokeBean jokeBean) throws Exception {
                return jokeBean.getResult();
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<List<JokeBean.ResultBean>>() {
            @Override
            public void accept(List<JokeBean.ResultBean> resultBeans) throws Exception {
                mView.dismissLoadingDialog();
                mView.setData(resultBeans);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mView.dismissLoadingDialog();
                ExceptionHelper.handleException(throwable);
            }
        });
    }
}
