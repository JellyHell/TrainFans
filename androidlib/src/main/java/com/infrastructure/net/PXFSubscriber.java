package com.infrastructure.net;

import com.infrastructure.ui.PXFLog.PXFLog;

import rx.Subscriber;

/**
 * Created by Administrator on 2016/8/11.
 *
 *  * Info: 简化 Rx 的模式,用在只关乎 onNext() 逻辑
 */
public abstract class PXFSubscriber<T> extends Subscriber<T> {
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        PXFLog.e(e.toString());
    }
}