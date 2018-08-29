package com.android.bojan.happytoday.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.android.bojan.base.base.Lifeful;
import com.android.bojan.happytoday.contract.MainViewContract;
import com.android.bojan.happytoday.model.api.JokeApi;
import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.io.File;
import java.lang.ref.WeakReference;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Create by bojan
 * on 2018/8/29
 */
public class MainViewPresenter extends BasePresenterImpl<MainViewContract.View> implements MainViewContract.Persenter {

    private MyHandler mHandler;
    private String mDirSize = "";
    public static final int SUCESS = 0;
    public static final int FAILED = 1;

    static class MyHandler extends Handler {
        WeakReference<Lifeful> mLifeful;

        MyHandler(Lifeful lifeful) {
            mLifeful = new WeakReference<>(lifeful);
        }

        @Override
        public void handleMessage(Message msg) {
            Lifeful theLifeful = mLifeful.get();
            if (!theLifeful.isAlive()) {
                return;
            }

            switch (msg.what) {
                case SUCESS:
                    ToastUtils.showShort("清理成功");
                    break;
                case FAILED:
                    ToastUtils.showShort("清理失败");
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void start() {
        super.start();
        new Thread(() -> mDirSize = FileUtils.getDirSize(getCacheDir())).start();

    }

    private File getCacheDir() {

        return ((Activity) mView).getCacheDir();
    }

    public MainViewPresenter(MainViewContract.View view) {
        super(view);
        mHandler = new MyHandler((Lifeful) mView);

    }



    @Override
    public void clearCache() {
        new AlertDialog.Builder((Context) mView).setTitle("确定要清理缓存")
                .setMessage("缓存大小：" + mDirSize)
                .setPositiveButton("清理",
                        (dialog, which) -> new Thread(new Runnable() {
                            @Override
                            public void run() {
                                FileUtils.deleteDir(getCacheDir());
                                mHandler.sendEmptyMessage(
                                        SUCESS);
                            }
                        }).start())
                .setNegativeButton("取消", null)
                .show();
    }

    @Override
    public void detach() {
        super.detach();
        mHandler = null;
    }
}
