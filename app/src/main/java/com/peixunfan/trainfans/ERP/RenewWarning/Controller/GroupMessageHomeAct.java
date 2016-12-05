package com.peixunfan.trainfans.ERP.RenewWarning.Controller;

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
 * Created by chengyanfang on 2016/12/2.
 */

public class GroupMessageHomeAct extends BaseActivity {

    FragmentManager manager;

    @Bind(R.id.left_fragment)
    FrameLayout leftFrameLayout;

    @Bind(R.id.right_fragment)
    FrameLayout rightFrameLayout;

    SendSMSClassFragment sendSMSClassFragment;
    SendSMSStudentFragment sendSMSStudentFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_addcourse_layout);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {
        manager = getSupportFragmentManager();
        sendSMSClassFragment = new SendSMSClassFragment();
        sendSMSStudentFragment = new SendSMSStudentFragment();
    }

    @Override
    protected void initViews(Bundle saveInstanceState) {
        super.initViews(saveInstanceState);
        showSegmentControl("班级","学员");
//        setRightManagerTv("选择");
        showBackButton();

        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.left_fragment,sendSMSClassFragment);


        fragmentTransaction.add(R.id.right_fragment,sendSMSStudentFragment);
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
