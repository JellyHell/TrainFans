package com.peixunfan.trainfans.UserCenter.Setting.Controller;

import android.os.Bundle;

import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.R;

import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/12/2.
 */

public class FeedbackAct  extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_feedback_layout);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews(Bundle saveInstanceState) {
        super.initViews(saveInstanceState);
        mCenterTitle.setText("意见反馈");
        setRightManagerTv("");
        showBackButton();
    }


    @Override
    protected void initVariables() {
    }

    @Override
    protected void loadData() {
    }
}