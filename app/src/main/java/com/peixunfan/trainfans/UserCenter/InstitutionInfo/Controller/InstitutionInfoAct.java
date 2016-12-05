package com.peixunfan.trainfans.UserCenter.InstitutionInfo.Controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.infrastructure.ui.ImageLoader.ImageLoader;
import com.infrastructure.ui.SimpleViewPagerIndicator.SimpleViewPagerIndicator;
import com.infrastructure.utils.IntentUtil;
import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.Base.BaseFragment;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.Widgt.CanForbitScrollViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/12/1.
 */

public class InstitutionInfoAct  extends BaseActivity {
    /**机构信息*/
    @Bind(R.id.rlv_institution_info)
    CardView mInstitutionInfoLayout;

    /**机构头像*/
    @Bind(R.id.display_image)
    SimpleDraweeView mSimpleDraweeView;

    /**短信信息*/
    @Bind(R.id.rlv_sms_info)
    CardView mSMSInfoLayout;

    @Bind(R.id.id_stickynavlayout_indicator)
    SimpleViewPagerIndicator mIndicator;

    @Bind(R.id.id_stickynavlayout_viewpager)
    CanForbitScrollViewPager mViewPager;


    private String[] mTitles = new String[]{"个人版", "工作室版","企业版"};

    private FragmentPagerAdapter mAdapter;
    private BaseFragment[] mFragments = new BaseFragment[mTitles.length];


    InstitutionFunctionFragment mPersonalInstitution;
    InstitutionFunctionFragment mWorkroomInstitution;
    InstitutionFunctionFragment mCompanyInstitution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_intitution_info_layout);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {
        mPersonalInstitution = new InstitutionFunctionFragment("1");
        mWorkroomInstitution = new InstitutionFunctionFragment("2");
        mCompanyInstitution = new InstitutionFunctionFragment("3");
        mFragments[0] = mPersonalInstitution;
        mFragments[1] = mWorkroomInstitution;
        mFragments[2] = mCompanyInstitution;

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
        mCenterTitle.setText("机构信息");
        showBackButton();

        //机构信息
        mInstitutionInfoLayout.setOnClickListener(v -> IntentUtil.forwordToActivity(this,InstitutionEditAct.class));

        //短信
        mSMSInfoLayout.setOnClickListener(v -> IntentUtil.forwordToActivity(this,SMSInfoAct.class));

        //配置指示器
        findViewById(R.id.tab_bar_layout).setVisibility(View.VISIBLE);
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
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        loadInstitutionInfo();
    }

    @Override
    protected void loadData() {

    }

    private void loadInstitutionInfo(){
        ImageLoader.progressiveLoad("http://a.hiphotos.baidu.com/image/pic/item/a08b87d6277f9e2f875fbad61a30e924b999f382.jpg",mSimpleDraweeView);
    }

}
