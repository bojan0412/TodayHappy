package com.android.bojan.happytoday.presenter;

import com.android.bojan.happytoday.contract.TodayContract;
import com.android.bojan.happytoday.model.api.JokeApi;
import com.android.bojan.happytoday.model.api.JokeService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Create by bojan
 * on 2018/8/30
 */
public class TodayPresenter extends BasePresenterImpl<TodayContract.view> implements TodayContract.presenter {
    public TodayPresenter(TodayContract.view view) {
        super(view);
    }

    @Override
    public void getTodayOfHistoryData(String date) {
        JokeApi.getInstance().getTodayOfHistoryData(JokeService.APP_KEY_TODAY_OF_HISTORY, date).subscribeOn(Schedulers.io()).doOnSubscribe(disposable -> {
            addDisposable(disposable);
            mView.showLoadingDialog();
        }).map(todayBean -> todayBean.getResult()).observeOn(AndroidSchedulers.mainThread()).subscribe(resultBeans -> mView.setData(resultBeans), throwable -> {
            mView.dismissLoadingDialog();
            mView.getToadyFail(throwable);
        });
    }
}
