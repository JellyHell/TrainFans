package com.peixunfan.trainfans.ERP.RenewWarning.Controller;

import android.os.Bundle;

import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.R;

import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/11/30.
 */

public class SendMsgAct extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_sendmsg_layout);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews(Bundle saveInstanceState) {
        super.initViews(saveInstanceState);
        mCenterTitle.setText("发消息");
        setRightManagerTv("发送");
        showBackButton();
    }


    @Override
    protected void initVariables() {

    }

    @Override
    protected void loadData() {

    }
}
