package com.android.bojan.base.retrofit;

import android.util.Log;

import com.google.gson.GsonBuilder;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Create by bojan
 * on 2018/8/27
 */
public class BaseApiImpl implements BaseApi {
    private volatile static Retrofit sRetrofit = null;
    protected Retrofit.Builder sRetrofitBuilder = new Retrofit.Builder();
    protected OkHttpClient.Builder sOkHttpClientBuilder = new OkHttpClient.Builder();


    protected BaseApiImpl(String baseUrL) {
        sRetrofitBuilder.addConverterFactory(ScalarsConverterFactory.create()).addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).client(sOkHttpClientBuilder.addInterceptor(getLoggerInterceptor()).build())
                .baseUrl(baseUrL);
    }

    /**
     * 构建retroft
     *
     * @return Retrofit对象
     */
    @Override
    public Retrofit getRetrofit() {
        if (sRetrofit == null) {
            //锁定代码块
            synchronized (BaseApiImpl.class) {
                if (sRetrofit == null) {
                    sRetrofit = sRetrofitBuilder.build(); //创建retrofit对象
                }
            }
        }
        return sRetrofit;

    }

    /**
     * 日志拦截器
     * 访问的接口信息
     *
     * @return 拦截器
     */
    public HttpLoggingInterceptor getLoggerInterceptor() {
        //日志显示级别
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.HEADERS;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("ApiUrl", "--->" + message);
            }
        });
        loggingInterceptor.setLevel(level);
        return loggingInterceptor;
    }


    @Override
    public OkHttpClient.Builder setInterceptor(Interceptor interceptor) {
        return sOkHttpClientBuilder.addInterceptor(interceptor);
    }

    @Override
    public Retrofit.Builder setConverterFactory(Converter.Factory factory) {
        return sRetrofitBuilder.addConverterFactory(factory);
    }
}
