package com.infrastructure.net;

import android.content.Context;

import com.infrastructure.Config.Config;
import com.infrastructure.ui.PXFLog.PXFLog;
import com.infrastructure.ui.supertoast.SuperToast;
import com.infrastructure.utils.NetWorkUtil;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/8/11.
 *
 * 提供Retrofit单例
 *
 * 参考：
 * http://www.jianshu.com/p/93153b34310e
 * https://drakeet.me/retrofit-2-0-okhttp-3-0-config
 */
public class RetrofitSingleton {
    public static Retrofit retrofit = null;
    private static OkHttpClient okHttpClient = null;
    private static Context mContext = null;
    private static String mBaseURL = null;

    /**
     * 构造单例
     * */
    private RetrofitSingleton()
    {
        init();
    }

    private static class SingletonHolder
    {
        private static  final RetrofitSingleton INSTANCE = new RetrofitSingleton();
    }

    public static RetrofitSingleton getInstance(Context context,String baseURL)
    {
        if (mContext == null){
            mContext = context;
            mBaseURL = baseURL;
        }
        return SingletonHolder.INSTANCE;
    }


    /**
     * 设置
     * */
    private void init()
    {
        initOkHttp();
        initRetrofit();
    }

    /**
     * 初始化OkHttp
     * */
    private static void initOkHttp()
    {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        //Step1:Debug模式下配置网络拦截器、打印请求和返回数据
        if (Config.DEBUG)
        {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            builder.addInterceptor(loggingInterceptor);
        }

        //Step2:设置缓存
        String cacheDir = "";
        boolean isExistSDCard = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        if (mContext.getExternalCacheDir() != null && isExistSDCard) {
            cacheDir = mContext.getExternalCacheDir().toString();
        } else {
            cacheDir = mContext.getCacheDir().toString();
        }

        File cacheFile = new File(cacheDir, "/NetCache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = chain -> {
            Request request = chain.request();
            if (!NetWorkUtil.isNetworkConnected(mContext))
            {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }

            Response response = chain.proceed(request);
            if (NetWorkUtil.isNetworkConnected(mContext))
            {
                //有网络时设置网络超时时间为0个小时
                int maxAge = 0;
                response.newBuilder()
                        .header("Cache-Control","public, max-age="+maxAge)
                        .build();
            }else
            {
                //无网络时设置超时时间为4周
                int maxStale = 60 * 60 * 24 * 28;
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
            return response;
        };
        builder.cache(cache).addInterceptor(cacheInterceptor);

        //Step3:设置公共参数，避免每次都设置重复的大量基础参数
        Interceptor publicParamsInterceptor = chain ->
        {
            Request originalRequest = chain.request();
            Headers headers = originalRequest.headers();
            HttpUrl modifiedUrl = originalRequest.url().newBuilder()
                    .addQueryParameter("channel","4")
                    .build();

            Request request = originalRequest.newBuilder().url(modifiedUrl).build();
            return chain.proceed(request);
        };
        builder.addInterceptor(publicParamsInterceptor);

        //Step4:设置请求头
        Interceptor headerInterceptor = chain ->
        {
            Request originalRequest = chain.request();
            Request.Builder requestBuilder = originalRequest.newBuilder()
                    .header("AppType", "TPOS")
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .method(originalRequest.method(), originalRequest.body());
            Request request = requestBuilder.build();
            return chain.proceed(request);
        };
        builder.addInterceptor(headerInterceptor);

        //Step5:设置超时和重连
        builder.connectTimeout(20, TimeUnit.SECONDS);//链接超时20s
        builder.readTimeout(20,TimeUnit.SECONDS);//写入缓存超时20s
        builder.writeTimeout(20,TimeUnit.SECONDS);//读取缓存超时20s
        builder.retryOnConnectionFailure(true);//错误重连

        //最终一步赋值给okHttpClient
        okHttpClient = builder.build();
    }

    /**
     * 初始化Retrofit
     * */
    private static void initRetrofit()
    {
        retrofit = new Retrofit.Builder()
                .baseUrl(mBaseURL)//设置基础URL
                .client(okHttpClient)//设置核心请求端为封装好的okHttpClient
                .addConverterFactory(GsonConverterFactory.create())//
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }


    /**
     * 请求出错时提示错误消息
     * */
    public static void disposeFailureInfo(Throwable t, Context context) {
//        if (t.toString().contains("GaiException") || t.toString().contains("SocketTimeoutException") ||
//                t.toString().contains("UnknownHostException")) {
//            SuperToast.show("网络问题",context);
//        } else {
//            SuperToast.show(t.getMessage(),context);
//        }
//        PXFLog.w(t.getMessage());
    }
}
