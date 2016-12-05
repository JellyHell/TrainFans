package com.peixunfan.trainfans.Login.Controller;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.peixunfan.trainfans.Base.BaseFragment;
import com.peixunfan.trainfans.R;

import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/11/28.
 */

@SuppressLint("ValidFragment")
public class ChangePsdFragment extends BaseFragment {


    String mMobile;

    public ChangePsdFragment(String mobile){

    }

    /**
     * 创建页面
     * */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        if (mView == null)
        {
            mView = inflater.inflate(R.layout.fragment_forgotpsd_resetpsd_layout,container, false);
            ButterKnife.bind(this,mView);
        }
        isCreateView = true;
        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void lazyLoad() {
    }

    @Override
    protected void initData(){
    }

}
