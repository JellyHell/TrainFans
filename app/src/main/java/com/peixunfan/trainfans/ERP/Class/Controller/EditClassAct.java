package com.peixunfan.trainfans.ERP.Class.Controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.infrastructure.ui.SimpleViewPagerIndicator.SimpleViewPagerIndicator;
import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.Base.BaseFragment;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.Widgt.CanForbitScrollViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/11/26.
 */

public class EditClassAct  extends BaseActivity {
    @Bind(R.id.id_stickynavlayout_indicator)
    SimpleViewPagerIndicator mIndicator;

    @Bind(R.id.id_stickynavlayout_viewpager)
    CanForbitScrollViewPager mViewPager;

    private String[] mTitles = new String[]{"基本信息", "点名","学员管理"};

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
        mFragments[0] = new ClassBaseInfoFragment();
        mFragments[1] = new RollCallFragment();
        mFragments[2] = new ManagerStudentFragment();

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
        mCenterTitle.setText("班级详情");
        setRightManagerTv("完成");
        showBackButton();

        //配置指示器
        findViewById(R.id.tab_bar_layout).setVisibility(View.VISIBLE);
        findViewById(R.id.devider1).setVisibility(View.GONE);
        findViewById(R.id.devider2).setVisibility(View.GONE);
        findViewById(R.id.devider3).setVisibility(View.GONE);
        mIndicator.setTitles(mTitles);
        mIndicator.setaChangeIndicatorCallback(index -> mViewPager.setCurrentItem(index));

        //配置ViewPager
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setNoScroll(false);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mIndicator.scroll(position,positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                mIndicator.setChildTextSelected(position);

                if (position == 0){
                    setSwipeBackEnable(true);
                    setRightManagerTv("完成");
                }else if(position == 1){
                    setSwipeBackEnable(false);
                    setRightManagerTv("");
                }else {
                    setSwipeBackEnable(false);
                    setRightManagerTv("调班");
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
        if(mRightManagerTv.getText().equals("完成")){
            this.finish();
        }else{
            
        }
    }
}
