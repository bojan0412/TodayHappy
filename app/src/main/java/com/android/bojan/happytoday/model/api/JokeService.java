package com.android.bojan.happytoday.model.api;

import com.android.bojan.happytoday.model.entry.JokeBean;

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

    String Head_BG_BASE_URL="http://guolin.tech/api/";

    @GET("randJoke.php")
    Observable<JokeBean> getData(@Query("key") String appKey);
    @GET("bing_pic")
    Observable<String> getHeadBackGroundPic();
}
