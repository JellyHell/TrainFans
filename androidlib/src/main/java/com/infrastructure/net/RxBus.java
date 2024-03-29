package com.infrastructure.net;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by Administrator on 2016/8/10.
 */
public class RxBus {

    //主题
    private final Subject<Object,Object> bus;

    // PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
    private RxBus(){
        bus = new SerializedSubject<>(PublishSubject.create());
    }

    //定义单例
    private static class RxBusHolder{
        private static final RxBus instance = new RxBus();
    }

    public static RxBus getDefault(){
        return RxBusHolder.instance;
    }

    //提供一个新的事件
    public void post(Object o){bus.onNext(o);}

    // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
    public <T> Observable<T> toObserverable(Class<T> eventType) {
        return bus.ofType(eventType);
    }


}
