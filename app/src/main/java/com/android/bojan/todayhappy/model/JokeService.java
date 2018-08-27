package com.android.bojan.todayhappy.model;

import com.android.bojan.todayhappy.bean.JokeBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Create by bojan
 * on 2018/8/27
 */
public interface JokeService {
    String BASE_URL=" http://v.juhe.cn/joke/";
    String APP_KEY="d28acc48889c52a4570a69eaf334c909";

    @GET("randJoke.php")
    Observable<JokeBean> getData(@Query("key") String appKey);
}
