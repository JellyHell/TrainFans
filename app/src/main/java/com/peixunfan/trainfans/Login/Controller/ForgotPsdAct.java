package com.peixunfan.trainfans.Login.Controller;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.FrameLayout;

import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.Base.BaseFragment;
import com.peixunfan.trainfans.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/11/28.
 */

public class ForgotPsdAct extends BaseActivity {

    FragmentManager manager;

    @Bind(R.id.left_fragment)
    FrameLayout frameLayout;

    InputAccountFragment inputAccountFragment;
    ChangePsdFragment changePsdFragment;

    BaseFragment currentFragment;

    String mType = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_addcourse_layout);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {
        manager = getSupportFragmentManager();
        inputAccountFragment = new InputAccountFragment();

        if("1".equals(getIntent().getStringExtra("type"))){
            mCenterTitle.setText("修改密码");
        }else{
            mCenterTitle.setText("忘记密码");
        }

    }

    @Override
    protected void initViews(Bundle saveInstanceState) {
        super.initViews(saveInstanceState);
        showBackButton();

        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.left_fragment,inputAccountFragment);
        fragmentTransaction.commitNowAllowingStateLoss();
        currentFragment = inputAccountFragment;
    }

    @Override
    protected void loadData() {

    }


    @Override
    protected void onLeftManagerBtClick() {
        if(currentFragment == inputAccountFragment){
            this.finish();
        }else {
            inputAccountFragment = new InputAccountFragment();
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.replace(R.id.left_fragment,inputAccountFragment);
            fragmentTransaction.commitNowAllowingStateLoss();
            currentFragment = inputAccountFragment;
        }
    }

    public void forwardToResetPsd(String mobile){
        changePsdFragment = new ChangePsdFragment(mobile);

        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace(R.id.left_fragment,changePsdFragment);
        fragmentTransaction.commitNowAllowingStateLoss();
        currentFragment = changePsdFragment;
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            onLeftManagerBtClick();
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

}
