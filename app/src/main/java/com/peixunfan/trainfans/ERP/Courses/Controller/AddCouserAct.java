package com.peixunfan.trainfans.ERP.Courses.Controller;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/25.
 */

public class AddCouserAct  extends BaseActivity {

    FragmentManager manager;

    @Bind(R.id.eidtinfo_fragment)
    FrameLayout frameLayout;

    CourseInfoEditFragment courseInfoEditFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_addcourse_layout);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {
        manager = getSupportFragmentManager();
        courseInfoEditFragment = new CourseInfoEditFragment();
        setSwipeBackEnable(false);
    }

    @Override
    protected void initViews(Bundle saveInstanceState) {
        super.initViews(saveInstanceState);
        mCenterTitle.setText("添加课程");
        setRightManagerTv("完成");
        showBackButton();

        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.eidtinfo_fragment,courseInfoEditFragment);
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
