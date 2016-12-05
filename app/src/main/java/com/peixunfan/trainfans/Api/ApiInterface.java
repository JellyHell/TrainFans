package com.peixunfan.trainfans.Api;

import com.peixunfan.trainfans.Recovery.Model.ArticleList;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/11/8.
 */

public interface ApiInterface {

    String BASE_URL = "http://www.jinbank.com.cn/app/";


    /**首页*/
//    @FormUrlEncoded
//    @GET("front_borrowList.html")
//    Observable<ArticleList> mFrontIndexApi(@Field("currentPageIndex")String currentPageIndex, @Field("pageSize") String pageSize);


    @GET("front_borrowList.html")
    Observable<ArticleList> mFrontIndexApi(@Query("page") String page);
}
