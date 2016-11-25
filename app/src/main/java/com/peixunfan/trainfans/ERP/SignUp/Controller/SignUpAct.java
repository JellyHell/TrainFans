package com.peixunfan.trainfans.ERP.SignUp.Controller;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;
import android.widget.TextView;

import com.infrastructure.utils.AppUtil;
import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.Base.SectionedRecyclerViewAdapter;
import com.peixunfan.trainfans.ERP.SignUp.Model.StudentInfoModel;
import com.peixunfan.trainfans.ERP.SignUp.View.SignUpAdapter;
import com.peixunfan.trainfans.R;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/21.
 *
 * 页面名称：报名页面
 */

public class SignUpAct extends BaseActivity{

    @Bind(R.id.tv_basetitle_cetener)
    TextView mCenterTitle;

    @Bind(R.id.recyclerview)
    SwipeMenuRecyclerView mRecyclerview;

    SignUpAdapter mAdapter;

    public ArrayList<String> mCourse = new ArrayList<>();
    public ArrayList<String> mStudentInfo = new ArrayList<>();
    public ArrayList<String> mMoneyInfo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_erp_signup_layout);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {
        mStudentInfo = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.signup_studentinfo)));
        mMoneyInfo = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.signup_moneyinfo)));
    }

    @Override
    protected void initViews(Bundle saveInstanceState) {
        mCenterTitle.setText("学员报名");
        showBackButton();

        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        //设置左滑菜单
        mRecyclerview.setSwipeMenuCreator((swipeLeftMenu, swipeRightMenu, viewType) -> {
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            if(viewType == SectionedRecyclerViewAdapter.TYPE_ITEM_CLASS){
                SwipeMenuItem deleteItem = new SwipeMenuItem(SignUpAct.this)
                        .setBackgroundDrawable(R.drawable.red_drawable)
                        .setText("删除") // 文字，还可以设置文字颜色，大小等。。
                        .setTextColor(Color.WHITE)
                        .setWidth(AppUtil.dip2px(SignUpAct.this,80))
                        .setHeight(height);
                swipeRightMenu.addMenuItem(deleteItem);
            }
        });

        mRecyclerview.setSwipeMenuItemClickListener((closeable, adapterPosition, menuPosition, direction) -> {
            int redundantCellCnt = mAdapter.isOpen()? 12 : 5;
            mCourse.remove(adapterPosition-redundantCellCnt);
            closeable.smoothCloseMenu();
            mAdapter.notifyItemRemoved(adapterPosition);
        });
    }


    @Override
    protected void loadData() {
        mAdapter = new SignUpAdapter(this,mCourse,mStudentInfo,mMoneyInfo,mCourse);
        mAdapter.setChangeClassCallback(() -> {
            mCourse.add("class");
            mAdapter.notifyDataSetChanged();
        });

        mAdapter.setSelectStudentCallBack(() -> {
            StudentInfoModel aStudentInfo = new StudentInfoModel();
            aStudentInfo.studentName = "程言方";
            aStudentInfo.studentMobile = "18811778682";
            aStudentInfo.studentBirthDay = "1991-10-22";
            aStudentInfo.studentSchool = "中国矿业大学";
            aStudentInfo.studentSex = "男";
            aStudentInfo.studentClass = "大三";
            aStudentInfo.studentStudentFrom = "江苏";
            aStudentInfo.studentExtMobile = "18811778682";
            aStudentInfo.studentAddress = "丰台区玉林小区";
            mAdapter.setStudentInfoModel(aStudentInfo);
        });
        mRecyclerview.setAdapter(mAdapter);
    }
}
