package com.android.bojan.happytoday.model.api;

import com.android.bojan.happytoday.model.entry.JokeBean;
import com.android.bojan.happytoday.model.entry.NewsBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Create by bojan
 * on 2018/8/27
 */
public interface JokeService {
    String BASE_URL = " http://v.juhe.cn/";
    String APP_KEY_JOKE = "d28acc48889c52a4570a69eaf334c909";
    String APP_KEY_NEWS = "4147f55dfe849cf2bbf0d517f68b7f3b";
    String Head_BG_BASE_URL = "http://guolin.tech/api/";

    //笑话
    @GET("joke/randJoke.php")
    Observable<JokeBean> getJokeData(@Query("key") String appKey);

    //背景图片
    @GET("bing_pic")
    Observable<String> getHeadBackGroundPic();

    //新闻头条
    @GET("toutiao/index?")
    Observable<NewsBean> getNewsTopData(@Query("type") String type, @Query("key") String appKey);
}
