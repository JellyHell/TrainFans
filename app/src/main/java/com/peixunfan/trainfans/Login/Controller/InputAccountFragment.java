package com.peixunfan.trainfans.Login.Controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.peixunfan.trainfans.Base.BaseFragment;
import com.peixunfan.trainfans.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/11/28.
 */

public class InputAccountFragment  extends BaseFragment {

    @Bind(R.id.rlv_send_msm)
    RelativeLayout mSendMSMLayout;

    @Bind(R.id.et_username)
    EditText mInputMobile;
    /**
     * 创建页面
     * */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        if (mView == null)
        {
            mView = inflater.inflate(R.layout.fragment_forgotpsd_inputaccount_layout, container, false);
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
        mSendMSMLayout.setOnClickListener(v -> ((ForgotPsdAct)getActivity()).forwardToResetPsd(mInputMobile.getText().toString()));
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void initData(){

    }

}
