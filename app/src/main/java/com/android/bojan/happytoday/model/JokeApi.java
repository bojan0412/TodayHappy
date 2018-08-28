package com.android.bojan.happytoday.model;

import com.android.bojan.base.retrofit.BaseApiImpl;

/**
 * Create by bojan
 * on 2018/8/27
 */
public class JokeApi extends BaseApiImpl {
    private static JokeApi sJokeApi = new JokeApi(JokeService.BASE_URL);

    public JokeApi(String baseUrL) {
        super(baseUrL);
    }

    public static JokeService getInstance() {
        return sJokeApi.getRetrofit().create(JokeService.class);
    }
}
