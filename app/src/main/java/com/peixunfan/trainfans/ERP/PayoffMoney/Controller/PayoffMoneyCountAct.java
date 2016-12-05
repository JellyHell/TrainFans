package com.peixunfan.trainfans.ERP.PayoffMoney.Controller;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/11/29.
 */

public class PayoffMoneyCountAct extends BaseActivity {

    FragmentManager manager;

    @Bind(R.id.left_fragment)
    FrameLayout leftFrameLayout;

    @Bind(R.id.right_fragment)
    FrameLayout rightFrameLayout;

    TeacherMoneyCountFragment teacherMoneyCountFragment;
    AttendanceCheckFragment attendanceCheckFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_addcourse_layout);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {
        manager = getSupportFragmentManager();
        teacherMoneyCountFragment = new TeacherMoneyCountFragment();
        attendanceCheckFragment = new AttendanceCheckFragment();
    }

    @Override
    protected void initViews(Bundle saveInstanceState) {
        super.initViews(saveInstanceState);
        showSegmentControl("老师","考勤");
        mRightManagerBt.setImageResource(R.drawable.icon_search_bt);
        showBackButton();

        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.left_fragment,teacherMoneyCountFragment);


        fragmentTransaction.add(R.id.right_fragment,attendanceCheckFragment);
        fragmentTransaction.commitNowAllowingStateLoss();

        rightFrameLayout.setVisibility(View.GONE);
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void onRightManagerBtClick() {
        super.onRightManagerBtClick();
        showSearchView(true);
    }

    @Override
    protected void onSegmentLeftClick() {
        super.onSegmentLeftClick();
        rightFrameLayout.setVisibility(View.GONE);
        leftFrameLayout.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onSegmentRightClick() {
        super.onSegmentRightClick();
        rightFrameLayout.setVisibility(View.VISIBLE);
        leftFrameLayout.setVisibility(View.GONE);
    }
}
