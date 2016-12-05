package com.peixunfan.trainfans.ERP.RenewWarning.Controller;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.ERP.PayoffMoney.View.PayCourseInfoAdapter;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.Widgt.popupwindow.PublicMenuSelectPopWindow;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;


public class BuyClassAct extends BaseActivity {

    @Bind(R.id.tv_basetitle_cetener)
    TextView mCenterTitle;

    @Bind(R.id.recyclerview)
    SwipeMenuRecyclerView mRecyclerview;

    PayCourseInfoAdapter mAdapter;

    int mCurrentType = -1;
    String mCurrentDiscontType = "1";

    public ArrayList<String> mCourseInfo = new ArrayList<>();
    public ArrayList<String> mCouserBaseInfo = new ArrayList<>();
    public ArrayList<String> mCouserInfoByClass = new ArrayList<>();
    public ArrayList<String> mCouserInfoByTerm = new ArrayList<>();

    public ArrayList<String> mDiscountInfo = new ArrayList<>();
    public ArrayList<String> mResultInfo = new ArrayList<>();

    public ArrayList<String> mBuyTypes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_pay_courseinfo_layout);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {
        mCouserBaseInfo = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.signup_buyclass_baseinfo)));
        mCouserInfoByClass = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.signup_buyclass_byclass)));
        mCouserInfoByTerm = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.signup_buyclass_byterm)));

        mBuyTypes.add("按课节收费");
        mBuyTypes.add("按期收费");
        mBuyTypes.add("取消");

        filterClassInfo();
    }

    private void filterClassInfo(){
        mCourseInfo.clear();
        mCourseInfo.addAll(mCouserBaseInfo);
        if(mCurrentType==0){
            mCourseInfo.addAll(mCouserInfoByClass);
        }else if(mCurrentType==1){
            mCourseInfo.addAll(mCouserInfoByTerm);
        }

        mDiscountInfo.clear();
        mDiscountInfo.add("优惠方式");

        if(mCurrentDiscontType.equals("1")){
            mDiscountInfo.add("优惠¥");
        }else{
            mDiscountInfo.add("折扣%");
        }
        if(mCurrentType==0){
            mDiscountInfo.add("赠送课节");
        }

        mResultInfo.clear();
        if(mCurrentType==0){
            mResultInfo.add("实际课节");
        }
        mResultInfo.add("实际价格");

    }

    @Override
    protected void initViews(Bundle saveInstanceState) {
        mCenterTitle.setText("课程订单");
        showBackButton();
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void loadData() {
        loadAdapter();
    }


    private void loadAdapter(){
        if(mAdapter == null){
            mAdapter = new PayCourseInfoAdapter(this, mCourseInfo,mDiscountInfo,mResultInfo);
            mAdapter.setOnItemClickListener((parent, view, position, id) -> {
                if(id == 0){
                    if (position == 1){
                        new PublicMenuSelectPopWindow(BuyClassAct.this, mRecyclerview, mBuyTypes, mCurrentType == -1 ? "" : mBuyTypes.get(mCurrentType), new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                mCurrentType = position;
                                filterClassInfo();
                                loadAdapter();
                            }
                        }).show();
                    }
                }
            });
            mRecyclerview.setAdapter(mAdapter);
        }else{
            mAdapter.refreshData(mCourseInfo,mDiscountInfo,mResultInfo);
        }
    }

}
