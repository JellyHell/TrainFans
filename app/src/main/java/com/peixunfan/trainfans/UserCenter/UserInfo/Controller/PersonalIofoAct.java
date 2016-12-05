package com.peixunfan.trainfans.UserCenter.UserInfo.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.infrastructure.ui.ImageLoader.ImageLoader;
import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/11/30.
 */

public class PersonalIofoAct extends BaseActivity{

    @Bind(R.id.user_header)
    SimpleDraweeView mSimpleDraweeView;

    @Bind(R.id.rlv_msg_title_layout)
    RelativeLayout mChangeUserName;

    @Bind(R.id.rlv_mobile_layout)
    RelativeLayout mChangeMobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_personal_info_layout);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews(Bundle saveInstanceState) {
        super.initViews(saveInstanceState);
        mCenterTitle.setText("个人信息");
        setRightManagerTv("");
        showBackButton();

        ImageLoader.progressiveLoad("http://a.hiphotos.baidu.com/image/pic/item/a08b87d6277f9e2f875fbad61a30e924b999f382.jpg",mSimpleDraweeView);

        mChangeUserName.setOnClickListener(v -> {
            Intent aIntent = new Intent(this,SingleChangeUserInfoAct.class);
            aIntent.putExtra("changeType","0");
            aIntent.putExtra("defaultStr","刘巨炮");
            startActivity(aIntent);
        });

        mChangeMobile.setOnClickListener(v -> {
            Intent aIntent = new Intent(this,SingleChangeUserInfoAct.class);
            aIntent.putExtra("changeType","1");
            aIntent.putExtra("defaultStr","18811778628");
            startActivity(aIntent);
        });
    }




    @Override
    protected void initVariables() {

    }

    @Override
    protected void loadData() {

    }
}
