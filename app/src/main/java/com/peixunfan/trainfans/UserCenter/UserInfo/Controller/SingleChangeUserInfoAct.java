package com.peixunfan.trainfans.UserCenter.UserInfo.Controller;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/11/30.
 */

public class SingleChangeUserInfoAct extends BaseActivity {

    @Bind(R.id.et_username)
    EditText mEidtView;

    @Bind(R.id.iv_clear_editview)
    ImageView mClearImg;


    /***
     * 0:修改用户名称
     * 1:修改用户手机号
     * 2:修改机构名称
     * 3:修改机构联系电话
     * 4:修改机构地址
     * */
    String changeType = "0";
    String defaultStr = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_single_changeinfo_layout);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews(Bundle saveInstanceState) {
        super.initViews(saveInstanceState);
        setRightManagerTv("完成");
        showBackButton();

        mClearImg.setOnClickListener(v -> mEidtView.setText(""));
    }


    @Override
    protected void initVariables() {
        changeType = getIntent().getStringExtra("changeType");
        defaultStr = getIntent().getStringExtra("defaultStr");

        mEidtView.setText(defaultStr);
        if(changeType.equals("0")){
            mCenterTitle.setText("修改名字");
            mEidtView.setHint("请输入名字");
        }else if(changeType.equals("1")){
            mCenterTitle.setText("修改手机号");
            mEidtView.setHint("请输入手机号");
        }else if(changeType.equals("2")){
            mCenterTitle.setText("修改机构名称");
            mEidtView.setHint("请输入机构名称");
        }else if(changeType.equals("3")){
            mCenterTitle.setText("修改联系方式");
            mEidtView.setHint("请输入联系方式");
        }else if(changeType.equals("4")){
            mCenterTitle.setText("修改机构地址");
            mEidtView.setHint("请输入机构地址");
        }
        mEidtView.setSelection(defaultStr.length());
    }

    @Override
    protected void loadData() {

    }
}
