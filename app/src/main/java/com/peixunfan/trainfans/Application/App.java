package com.peixunfan.trainfans.Application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.infrastructure.ui.PXFLog.PXFLog;
import com.peixunfan.trainfans.Api.ApiProvider;

/**
 * Created by Administrator on 2016/7/21.
 */
public class App extends Application {
    public static App mApp;
    private Activity activity = null;

    public static App getInstance() {
        return mApp;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;

        //初始化Retrofit
        ApiProvider.initialize(mApp);

        //初始化Fresco
        Fresco.initialize(this);
    }


}
