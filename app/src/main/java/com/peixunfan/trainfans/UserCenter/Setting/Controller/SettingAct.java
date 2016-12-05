package com.peixunfan.trainfans.UserCenter.Setting.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.infrastructure.utils.IntentUtil;
import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.Login.Controller.ForgotPsdAct;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.UserCenter.UserInfo.View.SetttingAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/12/2.
 */

public class SettingAct  extends BaseActivity {

    @Bind(R.id.recyclerview)
    RecyclerView mRecyclerView;

    SetttingAdapter mAdapter;

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
        mCenterTitle.setText("设置");
        setRightManagerTv("");
        showBackButton();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void initVariables() {
        settings = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.setting_titles)));
    }

    @Override
    protected void loadData() {
        mAdapter = new SetttingAdapter(this,settings);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener((parent, view, position, id) -> {
            switch (position){
                case  0:{
                    IntentUtil.forwordToActivity(SettingAct.this,NotificationSetting.class);
                    break;
                }
                case  1:{
                    Intent aIntent = new Intent(SettingAct.this,ForgotPsdAct.class);
                    aIntent.putExtra("type","1");
                    startActivity(aIntent);
                    break;
                }
                case  2:{
                    IntentUtil.forwordToActivity(SettingAct.this,AboutUsAct.class);
                    break;
                }
                case  3:{
                    IntentUtil.forwordToActivity(SettingAct.this,FeedbackAct.class);
                    break;
                }
            }
        });
    }
}
