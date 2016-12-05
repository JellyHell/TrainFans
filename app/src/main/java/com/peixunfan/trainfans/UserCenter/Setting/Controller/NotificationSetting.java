package com.peixunfan.trainfans.UserCenter.Setting.Controller;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.UserCenter.Setting.View.NotificationSettingAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/12/2.
 */

public class NotificationSetting  extends BaseActivity {

    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;

    NotificationSettingAdapter mAdapter;

    ArrayList<String> settings = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_setting_layout);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews(Bundle saveInstanceState) {
        super.initViews(saveInstanceState);
        mCenterTitle.setText("通知管理");
        setRightManagerTv("");
        showBackButton();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void initVariables() {
        settings = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.notification_setting_titles)));
    }

    @Override
    protected void loadData() {
        mAdapter = new NotificationSettingAdapter(this,settings);
        mRecyclerView.setAdapter(mAdapter);
    }
}
