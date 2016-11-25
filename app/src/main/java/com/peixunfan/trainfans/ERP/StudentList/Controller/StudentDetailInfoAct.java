package com.peixunfan.trainfans.ERP.StudentList.Controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;

import com.infrastructure.ui.SimpleViewPagerIndicator.SimpleViewPagerIndicator;
import com.infrastructure.ui.supertoast.SuperToast;
import com.infrastructure.utils.IntentUtil;
import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.Base.BaseFragment;
import com.peixunfan.trainfans.CFragment;
import com.peixunfan.trainfans.ERP.SignUp.Controller.SignUpAct;
import com.peixunfan.trainfans.ERP.StudentList.View.StudentMoreManagerPW;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.Widgt.CanForbitScrollViewPager;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/23.
 */

public class StudentDetailInfoAct extends BaseActivity{
    @Bind(R.id.id_stickynavlayout_indicator)
    SimpleViewPagerIndicator mIndicator;

    @Bind(R.id.id_stickynavlayout_viewpager)
    CanForbitScrollViewPager mViewPager;

    private String[] mTitles = new String[]{"报读班级", "基本信息", "沟通记录","报课日志"};
    ArrayList<String> mStudentManagers = new ArrayList<String>();
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
        mStudentManagers.add("点击报名");
        mStudentManagers.add("联系学员");
        mStudentManagers.add("上课记录");
        mStudentManagers.add("学员退费");

        mFragments[0] = new StudentsClassListFragment();
        mFragments[1] = new StudentBaseInfoFragment();
        mFragments[2] = new StudentContactRecordFragment();
        mFragments[3] = new StudentBuyClassFragment();

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
        mCenterTitle.setText("学生详情");
        showBackButton();
        mRightManagerBt.setImageResource(R.drawable.icon_more_menu);

        //配置指示器
        findViewById(R.id.tab_bar_layout).setVisibility(View.VISIBLE);
        mIndicator.setTitles(mTitles);
        mIndicator.setaChangeIndicatorCallback(index -> mViewPager.setCurrentItem(index));

        //配置ViewPager
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setNoScroll(true);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mIndicator.scroll(position,positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0){
                    setSwipeBackEnable(true);
                    mViewPager.setNoScroll(true);
                }else {
                    setSwipeBackEnable(false);
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
        new StudentMoreManagerPW(this, mStudentManagers, (adapterView, view, i, l) -> {
            switch (i){
                case 0:
                    IntentUtil.forwordToActivity(StudentDetailInfoAct.this, SignUpAct.class);
                    break;
                case 1:
//                    IntentUtil.forwordToActivity(StudentDetailInfoAct.this, StudentAttendClassRecordAct.class);
                    break;
                case 2:
                    IntentUtil.forwordToActivity(StudentDetailInfoAct.this, StudentAttendClassRecordAct.class);
                    break;
                case 3:
                    IntentUtil.forwordToActivity(StudentDetailInfoAct.this, StudentPaybackMoneyListAct.class);
                    break;
            }
        }).show(mRightManagerBt);
    }
}
