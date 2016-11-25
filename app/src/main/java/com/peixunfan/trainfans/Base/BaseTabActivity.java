package com.peixunfan.trainfans.Base;

import android.app.ActivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.infrastructure.ui.supertoast.SuperToast;
import com.peixunfan.trainfans.Application.App;
import com.peixunfan.trainfans.ERP.Home.Controller.ERPHomeFragment;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.Recovery.Controller.RecoveryHomeFragment;
import com.peixunfan.trainfans.UserCenter.Controller.UsercenterHomeFragment;


/**
 * Created by Administrator on 2016/8/9.
 */
public class BaseTabActivity  extends BaseActivity implements TabHost.OnTabChangeListener
{
    private FragmentTransaction mFragmentTransaction;
    private FragmentManager mFragmentManager;
    private TabHost mTabHost;

    private ERPHomeFragment mERPHomeFragment;
    private RecoveryHomeFragment mFinancingHomeFragmentNew;
    private UsercenterHomeFragment mUserHomeFragment;

    private ERPHomeFragment mCacheERPHomeFragment;
    private RecoveryHomeFragment mCacheFinancingHomeFragment;
    private UsercenterHomeFragment mCacheUserHomeFragment;

    private TextView erpText,recoveryText,userCenterText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.pxf_home_tab_layout);
        super.onCreate(savedInstanceState);
        initFragment();
        initTab();
        setSwipeBackEnable(false);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle saveInstanceState) {

    }

    @Override
    protected void loadData() {

    }

    private void initFragment() {
        this.mERPHomeFragment = (ERPHomeFragment) Fragment
                .instantiate(this, ERPHomeFragment.class.getName(), null);
        this.mFinancingHomeFragmentNew = (RecoveryHomeFragment) Fragment
                .instantiate(this, RecoveryHomeFragment.class.getName(), null);
        this.mUserHomeFragment = (UsercenterHomeFragment) Fragment.instantiate(this,
                UsercenterHomeFragment.class.getName(), null);
    }


    private void initTab() {
        mTabHost = (TabHost) this.findViewById(android.R.id.tabhost);
        mTabHost.setup();

        FrameLayout layout = (FrameLayout) this.mTabHost.getChildAt(0);
        TabWidget tw = (TabWidget) layout.getChildAt(2);
        View aRecommendView;
        View aFinancingView;
        View aMoreView;

        mTabHost.setBackgroundResource(R.drawable.pxf_teacher_tab_bg);
        aRecommendView = LayoutInflater.from(this)
                .inflate(R.layout.pxf_tab_indicator_layout_home,
                        tw, false);
        aFinancingView = LayoutInflater.from(this)
                .inflate(R.layout.pxf_tab_indicator_layout_recovery,
                        tw, false);
        aMoreView = LayoutInflater.from(this).inflate(
                R.layout.pxf_tab_indicator_layout_usercenter, tw, false);

        addTab(tw, R.string.pxf_tab_home, aRecommendView);
        addTab(tw, R.string.pxf_tab_class, aFinancingView);
        addTab(tw, R.string.pxf_tab_profile, aMoreView);

        erpText = (TextView)aRecommendView.findViewById(R.id.erp_title);
        recoveryText = (TextView)aFinancingView.findViewById(R.id.recovery_title);
        userCenterText = (TextView)aMoreView.findViewById(R.id.usercenter_title);

        mTabHost.setOnTabChangedListener(this);
        mTabHost.setCurrentTab(0);
        onTabChanged("首页");
    }

    private void addTab(TabWidget tw, int strId, View aView) {
        TabHost.TabSpec home = this.mTabHost.newTabSpec(getString(strId));
        home.setIndicator(aView);
        home.setContent(new TabHost.TabContentFactory() {
            @Override
            public View createTabContent(String tag) {
                View v = new View(BaseTabActivity.this);
                return v;
            }
        });
        mTabHost.addTab(home);
    }

    @Override
    public void onTabChanged(String tabId) {

        if (this.mFragmentManager == null) {
            this.mFragmentManager = this.getSupportFragmentManager();
        }
        this.mFragmentTransaction = this.mFragmentManager.beginTransaction();
        this.getCacheFragement();

        if (this.isEquStr(tabId, R.string.pxf_tab_home)) {
            this.setCurrentFragment(mCacheERPHomeFragment,
                    mERPHomeFragment, R.string.pxf_tab_home);
            erpText.setTextColor(getResources().getColor(R.color.main_color));
            recoveryText.setTextColor(getResources().getColor(R.color.base_gray));
            userCenterText.setTextColor(getResources().getColor(R.color.base_gray));
        } else if (this.isEquStr(tabId, R.string.pxf_tab_class)) {
            this.setCurrentFragment(mCacheFinancingHomeFragment,
                    mFinancingHomeFragmentNew, R.string.pxf_tab_class);
            erpText.setTextColor(getResources().getColor(R.color.base_gray));
            recoveryText.setTextColor(getResources().getColor(R.color.main_color));
            userCenterText.setTextColor(getResources().getColor(R.color.base_gray));
        } else if (this.isEquStr(tabId, R.string.pxf_tab_profile)) {
            this.setCurrentFragment(mCacheUserHomeFragment,
                    mUserHomeFragment, R.string.pxf_tab_profile);
            erpText.setTextColor(getResources().getColor(R.color.base_gray));
            recoveryText.setTextColor(getResources().getColor(R.color.base_gray));
            userCenterText.setTextColor(getResources().getColor(R.color.main_color));
        }

        this.mFragmentTransaction.commitAllowingStateLoss();

        BaseTabActivity.this.mFragmentManager.executePendingTransactions();
    }

    private void getCacheFragement() {
        this.mCacheERPHomeFragment = (ERPHomeFragment) getCacheFragment(R.string.pxf_tab_home);
        this.mCacheFinancingHomeFragment = (RecoveryHomeFragment) getCacheFragment(R.string.pxf_tab_class);
        this.mCacheUserHomeFragment = (UsercenterHomeFragment) getCacheFragment(R.string.pxf_tab_profile);

        this.hideFragment(mCacheERPHomeFragment);
        this.hideFragment(mCacheFinancingHomeFragment);
        this.hideFragment(mCacheUserHomeFragment);
    }

    private boolean isEquStr(String tabId, int resId) {
        return TextUtils.equals(tabId, getString(resId));
    }

    private void setCurrentFragment(Fragment aCache, Fragment aCurrt, int resId) {
        if (aCache == null) {
            this.mFragmentTransaction.add(R.id.realtabcontent, aCurrt,
                    getString(resId));
        } else {
            mFragmentTransaction.show(aCache);
        }
    }

    private void hideFragment(Fragment aHideFragment) {
        if (aHideFragment != null) {
            this.mFragmentTransaction.hide(aHideFragment);
        }
    }
    private Fragment getCacheFragment(int resId) {
        return this.mFragmentManager.findFragmentByTag(this.getString(resId));
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom);
    }

    /**
     * 退出应用
     * */
    private long clickBackTime;
    @SuppressWarnings("deprecation")
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (clickBackTime == 0 || System.currentTimeMillis() - clickBackTime > 2000) {
                SuperToast.show("再按一次退出培训范", App.getInstance(), true);
                clickBackTime = System.currentTimeMillis();
            } else {
                SuperToast.cancelAllSuperToasts();

                mHandler.post(() -> {
                    if (Build.VERSION.SDK_INT < 8) {
                        BaseTabActivity.super.finish();
                        System.exit(0);
                        ((ActivityManager) getSystemService(ACTIVITY_SERVICE)).restartPackage(getPackageName());
                    } else {
                        BaseTabActivity.super.finish();
                        ((ActivityManager) getSystemService(ACTIVITY_SERVICE)).killBackgroundProcesses(getPackageName());
                        System.exit(0);
                    }
                });
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
