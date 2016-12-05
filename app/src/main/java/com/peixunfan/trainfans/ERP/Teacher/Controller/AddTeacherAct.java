package com.peixunfan.trainfans.ERP.Teacher.Controller;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/11/28.
 */

public class AddTeacherAct  extends BaseActivity {

    FragmentManager manager;

    @Bind(R.id.left_fragment)
    FrameLayout frameLayout;

    TeacherBaseInfoFragment teacherBaseInfoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_addcourse_layout);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {
        manager = getSupportFragmentManager();
        teacherBaseInfoFragment = new TeacherBaseInfoFragment();
    }

    @Override
    protected void initViews(Bundle saveInstanceState) {
        super.initViews(saveInstanceState);
        mCenterTitle.setText("添加老师");
        setRightManagerTv("完成");
        showBackButton();

        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.left_fragment,teacherBaseInfoFragment);
        fragmentTransaction.commitNowAllowingStateLoss();
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void onRightManagerBtClick() {
        super.onRightManagerBtClick();
        this.finish();
    }
}
