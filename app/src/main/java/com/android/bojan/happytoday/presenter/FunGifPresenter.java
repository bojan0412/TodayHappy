package com.android.bojan.happytoday.presenter;

import com.android.bojan.happytoday.contract.FunGifContract;
import com.android.bojan.happytoday.model.api.JokeApi;
import com.android.bojan.happytoday.model.api.JokeService;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Create by bojan
 * on 2018/8/30
 */
public class FunGifPresenter extends BasePresenterImpl<FunGifContract.view> implements FunGifContract.presenter {
    public FunGifPresenter(FunGifContract.view view) {
        super(view);
    }

    @Override
    public void getGifData(String type) {
        JokeApi.getInstance().getGIfData(JokeService.APP_KEY_JOKE, type).subscribeOn(Schedulers.io()).doOnSubscribe(disposable -> {
            addDisposable(disposable);
            mView.showLoadingDialog();
        }).map(gifBean -> gifBean.getResult()).observeOn(AndroidSchedulers.mainThread()).subscribe(resultBeans -> mView.setData(resultBeans), throwable -> {
            mView.dismissLoadingDialog();
            mView.getGifFail(throwable);
        });
    }

}
