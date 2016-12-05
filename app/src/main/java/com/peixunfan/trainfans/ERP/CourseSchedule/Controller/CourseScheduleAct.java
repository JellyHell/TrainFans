package com.peixunfan.trainfans.ERP.CourseSchedule.Controller;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.infrastructure.net.RetrofitSingleton;
import com.infrastructure.utils.AppUtil;
import com.infrastructure.utils.TimeUtil;
import com.peixunfan.trainfans.Api.ApiProvider;
import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.ERP.CourseSchedule.View.CouserScheduleAdapter;
import com.peixunfan.trainfans.ERP.CourseSchedule.View.PXFCourseScheduleView;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.Recovery.Model.ArticleList;

import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;

/**
 * Created by chengyanfang on 2016/12/3.
 */

public class CourseScheduleAct  extends BaseActivity implements Observer<ArticleList> {

    @Bind(R.id.container_layout)
    RelativeLayout mContainer;

    @Bind(R.id.tv_basetitle_cetener)
    TextView mCenterTitle;

    @Bind(R.id.tv_month)
    TextView mMonthTitle;

    PXFCourseScheduleView mPXFCourseScheduleView;
    CouserScheduleAdapter mAdapter;
    ArrayList<Date> mScheduleList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_erp_course_schedule_layout);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle saveInstanceState) {
        mCenterTitle.setText("课程表");
        showBackButton();

        mPXFCourseScheduleView = new PXFCourseScheduleView(this);
        mContainer.addView(mPXFCourseScheduleView.getView());

        mPXFCourseScheduleView.getHorizatalableRefreshLayout().setOnRefreshListener(() -> {
            mScheduleList.addAll(0,TimeUtil.getWeekDaysListWithDate(TimeUtil.getPreDay(mScheduleList.get(0))));
            setApapter();
        });

        mPXFCourseScheduleView.getHorizatalableRefreshLayout().setOnLoadMoreListener(() -> {
            mScheduleList.addAll(TimeUtil.getWeekDaysListWithDate(TimeUtil.getNextDay(mScheduleList.get(mScheduleList.size()-1))));
            setApapter();
            mPXFCourseScheduleView.getRecyclerview().smoothScrollBy(AppUtil.getWidth(this)-AppUtil.dip2px(this,30),0);
        });
    }

    @Override
    protected void loadData() {
        ApiProvider.getInstance().getRecommendBorrow(this,String.valueOf(mPage));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        RetrofitSingleton.disposeFailureInfo(e,this);
    }

    @Override
    public void onNext(ArticleList articleList) {
        if(mPage == 1){
            mScheduleList.clear();
        }

        if (mScheduleList.isEmpty()){
            mScheduleList.addAll(TimeUtil.getWeekDaysListWithDate(new Date()));
        }else{
            mScheduleList.addAll(TimeUtil.getWeekDaysListWithDate(TimeUtil.getNextDay(mScheduleList.get(mScheduleList.size()-1))));
        }

        setApapter();
    }


    private void setApapter(){
        if(mAdapter == null){
            mAdapter = new CouserScheduleAdapter(this,mScheduleList);
            mAdapter.setOnItemClickListener((adapterView, view, i, l) -> {
            });

            mAdapter.setmOnRecyclerViewScroll(positon -> mMonthTitle.setText(TimeUtil.getMonthDateStr(mScheduleList.get(positon))));

            mPXFCourseScheduleView.setAdapter(mAdapter);

            mHandler.postDelayed(() -> mPXFCourseScheduleView.skipToToday(),50);

        }else {
            mAdapter.notifyDataSetChanged();
        }

    }



}
