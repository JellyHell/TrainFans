package com.peixunfan.trainfans.ERP.PayoffMoney.Controller;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.ERP.PayoffMoney.View.PayCourseInfoAdapter;
import com.peixunfan.trainfans.R;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/11/29.
 */

public class PayCourseDetailAct extends BaseActivity {

    @Bind(R.id.tv_basetitle_cetener)
    TextView mCenterTitle;

    @Bind(R.id.recyclerview)
    SwipeMenuRecyclerView mRecyclerview;

    PayCourseInfoAdapter mAdapter;

    public ArrayList<String> mCouserInfo = new ArrayList<>();
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_pay_courseinfo_layout);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {
        mCouserInfo = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.pay_class_info)));
    }

    @Override
    protected void initViews(Bundle saveInstanceState) {
        mCenterTitle.setText("钢琴拉布拉多");
        showBackButton();
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void loadData() {
//        mAdapter = new PayCourseInfoAdapter(this, mCouserInfo);
//        mRecyclerview.setAdapter(mAdapter);
    }

}
