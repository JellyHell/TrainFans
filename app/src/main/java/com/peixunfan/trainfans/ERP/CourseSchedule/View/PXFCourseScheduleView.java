package com.peixunfan.trainfans.ERP.CourseSchedule.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.infrastructure.utils.AppUtil;
import com.infrastructure.utils.TimeUtil;
import com.peixunfan.trainfans.Base.BaseAdapter;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.Widgt.RefreshableRecyclerView.HorizatalableRefreshLayout;

import java.util.Date;

import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.AnimationAdapter;

/**
 * Created by chengyanfang on 2016/12/3.
 */

public class PXFCourseScheduleView {

    RecyclerView mRecyclerview;

    HorizatalableRefreshLayout horizatalableRefreshLayout;

    Context mContext;
    View mView;

    StaggeredGridLayoutManager staggeredGridLayoutManager;


    public PXFCourseScheduleView(Context pContext) {
        mContext = pContext;
        initView();
    }

    private void initView() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.pxf_course_schedule_view, null);
        mRecyclerview = (RecyclerView)mView.findViewById(R.id.recyclerView);
        horizatalableRefreshLayout = (HorizatalableRefreshLayout)mView.findViewById(R.id.plrl);

        //设置mRecyclerview
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.HORIZONTAL);
        mRecyclerview.setLayoutManager(staggeredGridLayoutManager);
    }

    public View getView() {
        return mView;
    }

    public void setAdapter(BaseAdapter adapter)
    {
        AnimationAdapter animationAdapter = new AlphaInAnimationAdapter(adapter);
        mRecyclerview.setAdapter(animationAdapter);
    }

    public void skipToToday(){
        int pos = TimeUtil.getWeekOfDatePos(new Date());
        mRecyclerview.smoothScrollToPosition(pos);
    }

    public HorizatalableRefreshLayout getHorizatalableRefreshLayout(){
        return horizatalableRefreshLayout;
    }

    public RecyclerView getRecyclerview(){
        return mRecyclerview;
    }

}
