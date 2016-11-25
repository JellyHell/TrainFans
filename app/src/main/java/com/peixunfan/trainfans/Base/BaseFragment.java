package com.peixunfan.trainfans.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by Administrator on 2016/8/10.
 */
public abstract class BaseFragment extends Fragment{

    protected boolean isCreateView = false;

    protected View mView;

    protected int mPage = 1;

    /**
     * view创建完毕后开始配置View控件
     * */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    protected void initData(){}

    /**
     * 此方法在控件初始化前调用，所以不能在此方法中直接操作控件会出现空指针
     * */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isCreateView) {
            isCreateView = false;
            lazyLoad();
        }
    }

    /**
     * 初始化页面
     */
    protected abstract void initView();

    /**
     * 加载数据操作,在视图创建之前初始化
     */
    protected abstract void lazyLoad();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //第一个fragment会调用
        if (getUserVisibleHint()) {
            lazyLoad();
        }
    }

}
