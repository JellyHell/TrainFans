package com.peixunfan.trainfans.UserCenter.Home.View;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.infrastructure.ui.ImageLoader.ImageLoader;
import com.infrastructure.utils.IntentUtil;
import com.peixunfan.trainfans.Base.SimpleOutLinkAct;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.UserCenter.InstitutionInfo.Controller.InstitutionInfoAct;
import com.peixunfan.trainfans.UserCenter.UserInfo.Controller.ChangeInstitutionAct;
import com.peixunfan.trainfans.UserCenter.UserInfo.Controller.PersonalIofoAct;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/10.
 */

public class UsercenterHeaderView {

    private Activity mContext;
    private View mView;

    /**个人信息*/
    @Bind(R.id.rlv_personal_info)
    CardView mForwardToPersonalLayout;

    /**个人信息*/
    @Bind(R.id.rlv_institution_info)
    CardView mForwardToInstitutionLayout;

    /**个人头像*/
    @Bind(R.id.display_image)
    SimpleDraweeView mSimpleDraweeView;

    /**机构头像*/
    @Bind(R.id.institution_image)
    SimpleDraweeView mInstitutionHeaderImg;

    /**查看角色权限*/
    @Bind(R.id.tv_see_roleinfo)
    TextView mSeeRoleInfo;

    /**切换机构*/
    @Bind(R.id.tv_change_role)
    TextView mChangeInstitutionTv;


    @SuppressLint("InflateParams")
    public UsercenterHeaderView(Activity context) {
        mContext = context;
        mView = LayoutInflater.from(mContext).inflate(R.layout.view_header_usercenter, null);
        ButterKnife.bind(this,mView);

        initView();
    }

    private void initView()
    {
        mForwardToPersonalLayout.setOnClickListener(v -> IntentUtil.forwordToActivity(mContext, PersonalIofoAct.class));

        mSeeRoleInfo.setOnClickListener(v -> {
            Intent aIntent = new Intent(mContext, SimpleOutLinkAct.class);
            aIntent.putExtra("titleStr","角色权限");
            aIntent.putExtra("outLintStr","https://www.baidu.com/");
            mContext.startActivity(aIntent);
        });

        mChangeInstitutionTv.setOnClickListener(v -> IntentUtil.forwordToActivity(mContext, ChangeInstitutionAct.class));

        mForwardToInstitutionLayout.setOnClickListener(v -> IntentUtil.forwordToActivity(mContext, InstitutionInfoAct.class));
    }

    public View getView() {
        ImageLoader.progressiveLoad("http://a.hiphotos.baidu.com/image/pic/item/a08b87d6277f9e2f875fbad61a30e924b999f382.jpg",mSimpleDraweeView);

        ImageLoader.progressiveLoad("http://a.hiphotos.baidu.com/image/pic/item/a08b87d6277f9e2f875fbad61a30e924b999f382.jpg",mInstitutionHeaderImg);

        return mView;
    }

    public void refresh(){

    }
}

