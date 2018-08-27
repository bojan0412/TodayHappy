package com.android.bojan.base.base.baseImpl;

import com.android.bojan.base.base.BasePresenter;
import com.android.bojan.base.base.BaseView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Create by bojan
 * on 2018/8/27
 */
public abstract class BasePresenterImpl<V extends BaseView> implements BasePresenter {
    protected V mView;
    private CompositeDisposable mCompositeDisposable;

    public BasePresenterImpl(V view) {
        this.mView = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void detach() {
        this.mView = null;
        unDisposable();
    }

    @Override
    public void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    /**
     * 统一解绑防止RX造成内存泄漏
     */
    @Override
    public void unDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }
}
