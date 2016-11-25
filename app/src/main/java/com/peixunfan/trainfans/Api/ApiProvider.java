package com.peixunfan.trainfans.Api;

import android.content.Context;

import com.infrastructure.net.RetrofitSingleton;
import com.infrastructure.ui.supertoast.SuperToast;
import com.infrastructure.utils.RxUtils;
import com.peixunfan.trainfans.Recovery.Model.ArticleList;

import rx.Observer;

/**
 * Created by Administrator on 2016/11/8.
 */

public class ApiProvider {
    private static ApiInterface apiService = null;
    private static Context mContext = null;
    /**
     * 构造单例
     * */
    private ApiProvider()
    {
        init();
    }

    private static class SingletonHolder
    {
        private static  final ApiProvider INSTANCE = new ApiProvider();
    }

    public static ApiProvider getInstance()
    {
        return ApiProvider.SingletonHolder.INSTANCE;
    }

    public static ApiProvider initialize(Context context)
    {
        if (mContext == null){
            mContext = context;
        }
        return ApiProvider.SingletonHolder.INSTANCE;
    }

    private void init()
    {
        apiService = RetrofitSingleton.getInstance(mContext,ApiInterface.BASE_URL).retrofit.create(ApiInterface.class);
    }

    /*************************************具体的网络连接请求****************************************/
    public void getRecommendBorrow(Observer<ArticleList> observer, String page)
    {
        apiService.mFrontIndexApi(page,"10").compose(RxUtils.rxSchedulerHelper())
                .doOnError(throwable -> SuperToast.show("请求失败",mContext))
                .subscribe(observer);
    }
}
