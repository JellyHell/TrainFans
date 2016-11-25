package com.peixunfan.trainfans.Api;

import com.peixunfan.trainfans.Recovery.Model.ArticleList;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/11/8.
 */

public interface ApiInterface {

    String BASE_URL = "http://m.htxq.net/servlet/";

    /**首页*/
    @FormUrlEncoded
    @POST("SysArticleServlet?action=mainList")
    Observable<ArticleList> mFrontIndexApi(@Field("currentPageIndex")String currentPageIndex, @Field("pageSize") String pageSize);
}
