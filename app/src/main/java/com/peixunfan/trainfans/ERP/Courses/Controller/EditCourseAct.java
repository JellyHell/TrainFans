package com.peixunfan.trainfans.ERP.Courses.Controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;

import com.infrastructure.ui.SimpleViewPagerIndicator.SimpleViewPagerIndicator;
import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.Base.BaseFragment;
import com.peixunfan.trainfans.ERP.StudentList.Controller.StudentBaseInfoFragment;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.Widgt.CanForbitScrollViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/24.
 */

public class EditCourseAct  extends BaseActivity {
    @Bind(R.id.id_stickynavlayout_indicator)
    SimpleViewPagerIndicator mIndicator;

    @Bind(R.id.id_stickynavlayout_viewpager)
    CanForbitScrollViewPager mViewPager;

    private String[] mTitles = new String[]{"基本信息", "授课老师"};

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
        mFragments[0] = new CourseInfoEditFragment();
        mFragments[1] = new CouserTeacherFragment();

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
        mCenterTitle.setText("编辑课程");
        setRightManagerTv("完成");
        showBackButton();

        //配置指示器
        findViewById(R.id.tab_bar_layout).setVisibility(View.VISIBLE);
        findViewById(R.id.devider1).setVisibility(View.GONE);
        findViewById(R.id.devider3).setVisibility(View.GONE);
        mIndicator.setTitles(mTitles);
        mIndicator.setaChangeIndicatorCallback(index -> mViewPager.setCurrentItem(index));

        //配置ViewPager
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setNoScroll(true);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mIndicator.scroll(position,positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                mIndicator.setChildTextSelected(position);
                if(position == 0){
                    setSwipeBackEnable(true);
                }else {
                    setSwipeBackEnable(false);
                }
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
        this.finish();
    }
}
