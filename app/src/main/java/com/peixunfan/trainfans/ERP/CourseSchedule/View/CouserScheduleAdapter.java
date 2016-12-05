package com.peixunfan.trainfans.ERP.CourseSchedule.View;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.infrastructure.ui.supertoast.SuperToast;
import com.infrastructure.utils.AppUtil;
import com.infrastructure.utils.TimeUtil;
import com.peixunfan.trainfans.Base.BaseAdapter;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.Recovery.Model.Article;

import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/12/3.
 */

public class CouserScheduleAdapter extends BaseAdapter<Date> {

    ArrayList courseCellList = new ArrayList();


    int height,width;

    public CouserScheduleAdapter(Context context, ArrayList<Date> datas) {
        super(context, datas);
        courseCellList.add("1");
        courseCellList.add("1");
        courseCellList.add("1");
        courseCellList.add("1");
        courseCellList.add("1");
        courseCellList.add("1");
        courseCellList.add("1");
        courseCellList.add("1");
        courseCellList.add("1");
        courseCellList.add("1");
        courseCellList.add("1");
        courseCellList.add("1");
        courseCellList.add("1");
        courseCellList.add("1");
        courseCellList.add("1");
        courseCellList.add("1");
        courseCellList.add("1");
        courseCellList.add("1");

        height = AppUtil.getHeight(mContext) - AppUtil.dip2px(mContext,78) - AppUtil.getStatusBarHeight(mContext);
        width = AppUtil.dip2px(mContext,100);
    }

    @Override
    protected int getResourceId() {
        return R.layout.adapter_course_schedule_layout;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemHolderViewHolder(View arg0) {
        return new ItemViewHolder(arg0, this.mContext);
    }

    @Override
    protected void onBindContentViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final ItemViewHolder aItemViewHolder = (ItemViewHolder) viewHolder;

        // 点击事件
        if (mItemClickListener != null) {
            aItemViewHolder.mView.setOnClickListener(v -> mItemClickListener.onItemClick(null, null, position, 0));
        }

        ViewGroup.LayoutParams lps = aItemViewHolder.mRecyclerView.getLayoutParams();
        lps.height = height;
        lps.width = width;
        aItemViewHolder.mRecyclerView.setLayoutParams(lps);
        aItemViewHolder.mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        CourseScheduleCellAdapter cellAdapter = new CourseScheduleCellAdapter(mContext, courseCellList);;
        aItemViewHolder.mRecyclerView.setAdapter(cellAdapter);
        cellAdapter.setOnItemClickListener((parent, view, position1, id) -> {
            //
            SuperToast.show("click At "+TimeUtil.getTimeDateStr(mDatas.get(position)) + "Day and Time:" + position1,mContext);
        });

        aItemViewHolder.mDateTitle.setText(TimeUtil.getTimeDateStr(mDatas.get(position)));
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        View mView;

        @Bind(R.id.tv_date_title)
        TextView mDateTitle;

        RecyclerView mRecyclerView;

        public ItemViewHolder(View arg0, Context aContext) {
            super(arg0);
            ButterKnife.bind(this,arg0);
            mView = arg0;

            mRecyclerView = (RecyclerView)mView.findViewById(R.id.recyclerview);
        }
    }
}