package com.peixunfan.trainfans.Login.Controller;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.Login.View.ApplyWorkroomAdapter;
import com.peixunfan.trainfans.R;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/11/28.
 */

public class ApplyWorkRoomAct extends BaseActivity {

    @Bind(R.id.tv_basetitle_cetener)
    TextView mCenterTitle;

    @Bind(R.id.recyclerview)
    SwipeMenuRecyclerView mRecyclerview;

    ApplyWorkroomAdapter mAdapter;

    public ArrayList<String> mApplyBaseInfo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_erp_signup_layout);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {
        mApplyBaseInfo = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.apply_workroom_account_baseinfo)));
    }

    @Override
    protected void initViews(Bundle saveInstanceState) {
        mCenterTitle.setText("认证工作室版");
        setRightManagerTv("提交");
        showBackButton();

        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));

    }


    @Override
    protected void loadData() {
        mAdapter = new ApplyWorkroomAdapter(this,mApplyBaseInfo);
        mRecyclerview.setAdapter(mAdapter);
    }
}
