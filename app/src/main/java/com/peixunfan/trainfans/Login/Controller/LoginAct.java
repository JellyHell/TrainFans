package com.peixunfan.trainfans.Login.Controller;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.InputType;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.infrastructure.ui.supertoast.SuperToast;
import com.infrastructure.utils.IntentUtil;
import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.Base.BaseTabActivity;
import com.peixunfan.trainfans.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/11/28.
 */

public class LoginAct  extends BaseActivity implements View.OnClickListener{

    //登陆方式: 1:验证码登陆 2:密码登陆
    String loginType = "1";

    @Bind(R.id.rlv_normallogin_layout)
    RelativeLayout mNormalLoginLayout;

    //手机号
    @Bind(R.id.et_username)
    EditText mMobileInputView;

    //密码&验证码
    @Bind(R.id.et_password)
    EditText mKeyInputView;

    //发送&倒计时
    @Bind(R.id.tv_right_manager)
    TextView mRightManagerTv;

    //密码可见&不可见
    @Bind(R.id.iv_right_manager)
    ImageView mRightManagerImg;

    //登陆按钮
    @Bind(R.id.rlv_login_bt)
    RelativeLayout mLoginLayout;

    //登陆切换方式
    @Bind(R.id.tv_logintype)
    TextView mChangeLoginTypeTv;

    //申请账号
    @Bind(R.id.tv_loginwith_checkmsm)
    TextView mApplyAccountTv;

    //分割线
    @Bind(R.id.center_line)
    View devider;

    //忘记密码
    @Bind(R.id.tv_forgot_psd)
    TextView mForgotPsdTv;


    //申请账号
    @Bind(R.id.rlv_applyaccount_layout)
    RelativeLayout mApplyAccountLayout;

    @Bind(R.id.tv_cancle_apply)
    TextView mCancleApplyTv;

    @Bind(R.id.rlv_personal_version)
    RelativeLayout mPersonalVersion;

    @Bind(R.id.rlv_workroom_version)
    RelativeLayout mWorkroomVersion;

    @Bind(R.id.rlv_company_version)
    RelativeLayout mCompanyVersion;


    //倒计时
    Timer mTimer;
    Handler mTimerHandler;
    int mSecond = 60;
    boolean mIsInSchedule;

    //当前密码是否是可见
    boolean isPsdSeeable = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login_layout);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle saveInstanceState) {
        super.initViews(saveInstanceState);
        setSwipeBackEnable(false);

        mRightManagerTv.setOnClickListener(this);
        mRightManagerImg.setOnClickListener(this);
        mChangeLoginTypeTv.setOnClickListener(this);
        mLoginLayout.setOnClickListener(this);
        mForgotPsdTv.setOnClickListener(this);
        mApplyAccountTv.setOnClickListener(this);


        mCancleApplyTv.setOnClickListener(this);
        mPersonalVersion.setOnClickListener(this);
        mWorkroomVersion.setOnClickListener(this);
        mCompanyVersion.setOnClickListener(this);
    }

    @Override
    protected void loadData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tv_right_manager://点击发送
            {
                mRightManagerTv.setClickable(false);

                mRightManagerTv.setText("发送中");
                mRightManagerTv.setTextColor(getResources().getColor(R.color.color_a0a0a0));

                mHandler.postDelayed(() -> startCountDown(),500);
            }
            break;
            case R.id.iv_right_manager:
            {
                if(isPsdSeeable){
                    mRightManagerImg.setBackgroundResource(R.drawable.icon_psd_cannot_see);
                    mKeyInputView.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    isPsdSeeable = false;
                }else {
                    mRightManagerImg.setBackgroundResource(R.drawable.icon_psd_can_see);
                    mKeyInputView.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);

                    isPsdSeeable = true;
                }
            }
            break;
            case R.id.tv_logintype://切换登陆方式
            {
                changeLoginType();
            }
            break;
            case R.id.rlv_login_bt://登陆
            {
                forwardToLogin();
            }
            break;
            case R.id.tv_forgot_psd://忘记密码
            {
                forwardToGetPsd();
            }
            break;
            case R.id.tv_loginwith_checkmsm://申请账号
            {
                startApplyAccount();
            }
            break;
            case R.id.tv_cancle_apply://取消申请账号
            {
                cancleApplyAccount();
            }
            break;
            case R.id.rlv_personal_version://个人版
            {
                IntentUtil.forwordToActivity(this,ApplyPersonalAct.class);
            }
            break;
            case R.id.rlv_workroom_version://工作日版
            {
                IntentUtil.forwordToActivity(this,ApplyWorkRoomAct.class);
            }
            break;
            case R.id.rlv_company_version://企业版
            {
                IntentUtil.forwordToActivity(this,ApplyCompanyAct.class);
            }
            break;
        }
    }


    /**倒计时*/
    private void startCountDown(){
        mIsInSchedule = true;

        mTimer = new Timer();

        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                mTimerHandler.sendMessage(message);
            }
        }, 1000, 1000);

        mTimerHandler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        if (mSecond <= 0) {
                            mTimer.cancel();
                            mIsInSchedule = false;
                            mRightManagerTv.setClickable(true);
                            mSecond = 60;
                            return;
                        }

                        updateTimeCount();

                        break;
                }
                super.handleMessage(msg);
            }
        };
    }

    /**更改时间*/
    private void updateTimeCount() {
        mSecond--;
        mRightManagerTv.setTextColor(getResources().getColor(R.color.main_color));
        if (mSecond <= 0) {
            mRightManagerTv.setText("发送");
            mIsInSchedule = false;
            mRightManagerTv.setClickable(true);
            return;
        }
        mRightManagerTv.setClickable(false);
        mRightManagerTv.setText(mSecond + "s");
    }

    /**切换登陆方式*/
    private void changeLoginType(){
        if("1".equals(loginType)){//验证码登陆
            //1.修改密码框提示
            mKeyInputView.setHint("请输入密码");

            //2.修改密码框输入方式
            mKeyInputView.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);

            //3.修改右侧控制
            mRightManagerImg.setVisibility(View.VISIBLE);
            mRightManagerImg.setBackgroundResource(R.drawable.icon_psd_cannot_see);

            mRightManagerTv.setVisibility(View.GONE);

            //4.显示忘记密码
            mForgotPsdTv.setVisibility(View.VISIBLE);
            devider.setVisibility(View.VISIBLE);

            //5.更改登陆方式提示
            mChangeLoginTypeTv.setText("验证码登录");

            loginType = "2";

        }else{//密码登陆
            //1.修改密码框提示
            mKeyInputView.setHint("请输入验证码");

            //2.修改密码框输入方式
            mKeyInputView.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);

            //3.修改右侧控制
            mRightManagerImg.setVisibility(View.GONE);

            mRightManagerTv.setVisibility(View.VISIBLE);
            mRightManagerTv.setText("发送");
            mRightManagerTv.setTextColor(getResources().getColor(R.color.main_color));

            //4.隐藏忘记密码
            mForgotPsdTv.setVisibility(View.GONE);
            devider.setVisibility(View.GONE);

            //5.更改登陆方式提示
            mChangeLoginTypeTv.setText("密码登录");

            loginType = "1";
        }
    }

    /**登陆*/
    private void forwardToLogin(){

//        SuperToast.show(mMobileInputView.getText().toString(),this);

        IntentUtil.forwordToActivity(this, BaseTabActivity.class);
        finish();
    }


    /**忘记密码*/
    private void forwardToGetPsd(){
        IntentUtil.forwordToActivity(this, ForgotPsdAct.class);
    }


    /**申请账号*/
    private void startApplyAccount(){
        mApplyAccountLayout.setVisibility(View.VISIBLE);

        PropertyValuesHolder alpha0 = PropertyValuesHolder.ofFloat("alpha", 0f, 1.0f);

        ObjectAnimator animator0 = ObjectAnimator.ofPropertyValuesHolder(mApplyAccountLayout, alpha0);
        animator0.setDuration(200);
        animator0.setInterpolator(new LinearInterpolator());
        animator0.start();
        animator0.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mNormalLoginLayout.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    /**取消申请账号*/
    private void cancleApplyAccount(){
        mNormalLoginLayout.setVisibility(View.VISIBLE);
        PropertyValuesHolder alpha0 = PropertyValuesHolder.ofFloat("alpha", 1.0f, 0.0f);

        ObjectAnimator animator0 = ObjectAnimator.ofPropertyValuesHolder(mApplyAccountLayout, alpha0);
        animator0.setDuration(200);
        animator0.setInterpolator(new LinearInterpolator());
        animator0.start();
        animator0.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mApplyAccountLayout.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
