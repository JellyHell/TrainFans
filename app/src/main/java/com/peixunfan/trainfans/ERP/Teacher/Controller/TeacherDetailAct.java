package com.peixunfan.trainfans.ERP.Teacher.Controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.infrastructure.ui.SimpleViewPagerIndicator.SimpleViewPagerIndicator;
import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.Base.BaseFragment;
import com.peixunfan.trainfans.ERP.StudentList.Controller.StudentBaseInfoFragment;
import com.peixunfan.trainfans.ERP.StudentList.Controller.StudentBuyClassFragment;
import com.peixunfan.trainfans.ERP.StudentList.Controller.StudentContactRecordFragment;
import com.peixunfan.trainfans.ERP.StudentList.Controller.StudentsClassListFragment;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.Widgt.CanForbitScrollViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/11/28.
 */

public class TeacherDetailAct  extends BaseActivity {
    @Bind(R.id.id_stickynavlayout_indicator)
    SimpleViewPagerIndicator mIndicator;

    @Bind(R.id.id_stickynavlayout_viewpager)
    CanForbitScrollViewPager mViewPager;

    private String[] mTitles = new String[]{"基本信息", "可授课程","授课班级","学员管理"};
    private FragmentPagerAdapter mAdapter;
    private BaseFragment[] mFragments = new BaseFragment[mTitles.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_erp_student_detail);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {
        mFragments[0] = new TeacherBaseInfoFragment();
        mFragments[1] = new TeacherCourseFragment();
        mFragments[2] = new TeacherClassFragment();
        mFragments[3] = new TeacherStudentFragment();

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments[position];
            }

            @Override
            public int getCount() {
                return mTitles.length;
            }
        };

        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
    }

    @Override
    protected void initViews(Bundle saveInstanceState) {
        super.initViews(saveInstanceState);
        mCenterTitle.setText("教师详情");
        showBackButton();
        mRightManagerBt.setImageResource(R.drawable.icon_tel);

        //配置指示器
        findViewById(R.id.tab_bar_layout).setVisibility(View.VISIBLE);
        mIndicator.setTitles(mTitles);
        mIndicator.setaChangeIndicatorCallback(index -> mViewPager.setCurrentItem(index));

        //配置ViewPager
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mIndicator.scroll(position,positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0){
                    setSwipeBackEnable(true);
                }else {
                    setSwipeBackEnable(false);

                }

                if(position == 1){
                    mViewPager.setNoScroll(true);
                }else{
                    mViewPager.setNoScroll(false);
                }

                mIndicator.setChildTextSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void loadData() {

    }

    @Override
    protected void onRightManagerBtClick() {
        super.onRightManagerBtClick();
    }
}
