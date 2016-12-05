package com.peixunfan.trainfans.UserCenter.InstitutionInfo.Controller;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.infrastructure.utils.IntentUtil;
import com.peixunfan.trainfans.Base.BaseFragment;
import com.peixunfan.trainfans.Login.Controller.ApplyCompanyAct;
import com.peixunfan.trainfans.Login.Controller.ApplyWorkRoomAct;
import com.peixunfan.trainfans.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/12/1.
 */
@SuppressLint("ValidFragment")
public class InstitutionFunctionFragment extends BaseFragment {

    /**基础功能*/
    @Bind(R.id.tv_base_function)
    TextView mBaseFunction;

    /**附加功能*/
    @Bind(R.id.tv_otherfunction)
    TextView mOtherFunction;

    /**附加功能*/
    @Bind(R.id.rlv_bottom_manager_layout)
    RelativeLayout mBottomManagerLayout;

    /**附加功能*/
    @Bind(R.id.tv_bottom_manager)
    TextView mBottomManagerText;


    /**
     * 机构类型：1：个人版 2：工作室版 3.机构版
     * */
    String mType;

    public InstitutionFunctionFragment(String type){
        mType = type;
    }

    /**
     * 创建页面
     * */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        if (mView == null)
        {
            mView = inflater.inflate(R.layout.fragment_institution_function_layout,container, false);
            ButterKnife.bind(this,mView);
        }
        isCreateView = true;
        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    protected void initView() {
        if("2".equals(mType)){
            mBottomManagerLayout.setVisibility(View.VISIBLE);
            mBottomManagerText.setText("去认证为工作室");
        }else if("3".equals(mType)){
            mBottomManagerLayout.setVisibility(View.VISIBLE);
            mBottomManagerText.setText("去认证为企业");
        }else{
            mBottomManagerLayout.setVisibility(View.GONE);
        }


        mBottomManagerLayout.setOnClickListener(v -> {
            if("2".equals(mType)){
                IntentUtil.forwordToActivity(getActivity(), ApplyWorkRoomAct.class);
            }else if("3".equals(mType)){
                IntentUtil.forwordToActivity(getActivity(), ApplyCompanyAct.class);
            }
        });
    }

    @Override
    protected void lazyLoad() {
    }

    @Override
    protected void initData(){
        if("1".equals(mType)){
            mBaseFunction.setText("不支持新建校区\n不支持添加管理账号\n老师个数：2");
            mOtherFunction.setText("- 课程管理\n- 班级管理\n- 老师管理\n- 学生管理\n- 签到\n- 短信提醒");
        }else if("2".equals(mType)){
            mBaseFunction.setText("不支持新建校区\n管理账号：2\n老师个数：不限");
            mOtherFunction.setText("- 个人版所有功能\n- 账单管理\n- 续费管理\n- 数据统计\n- 签到\n- 工资核算");
        }else{
            mBaseFunction.setText("新建校区：2\n管理账号：不限\n老师个数：不限");
            mOtherFunction.setText("- 工作室版所有功能\n- 定制短信签名");
        }
    }

}
