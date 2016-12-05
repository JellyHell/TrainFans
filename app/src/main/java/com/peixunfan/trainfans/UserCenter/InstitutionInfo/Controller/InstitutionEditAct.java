package com.peixunfan.trainfans.UserCenter.InstitutionInfo.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.infrastructure.ui.ImageLoader.ImageLoader;
import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.UserCenter.UserInfo.Controller.SingleChangeUserInfoAct;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/12/1.
 */

public class InstitutionEditAct extends BaseActivity {

    /**头像*/
    @Bind(R.id.user_header)
    SimpleDraweeView mSimpleDraweeView;

    /**机构名称Layout*/
    @Bind(R.id.rlv_msg_title_layout)
    RelativeLayout mChangeUserName;

    /**手机号Layout*/
    @Bind(R.id.rlv_mobile_layout)
    RelativeLayout mChangeMobile;

    /**地址Layout*/
    @Bind(R.id.rlv_address_layout)
    RelativeLayout mAddressLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_institution_info_layout);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews(Bundle saveInstanceState) {
        super.initViews(saveInstanceState);
        mCenterTitle.setText("机构信息");
        setRightManagerTv("");
        showBackButton();

        ImageLoader.progressiveLoad("http://a.hiphotos.baidu.com/image/pic/item/a08b87d6277f9e2f875fbad61a30e924b999f382.jpg",mSimpleDraweeView);

        mChangeUserName.setOnClickListener(v -> {
            Intent aIntent = new Intent(this,SingleChangeUserInfoAct.class);
            aIntent.putExtra("changeType","2");
            aIntent.putExtra("defaultStr","大众音乐");
            startActivity(aIntent);
        });

        mChangeMobile.setOnClickListener(v -> {
            Intent aIntent = new Intent(this,SingleChangeUserInfoAct.class);
            aIntent.putExtra("changeType","3");
            aIntent.putExtra("defaultStr","18811778628");
            startActivity(aIntent);
        });

        mAddressLayout.setOnClickListener(v -> {
            Intent aIntent = new Intent(this,SingleChangeUserInfoAct.class);
            aIntent.putExtra("changeType","4");
            aIntent.putExtra("defaultStr","北京市东程序富华大厦F座11层京金所");
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
