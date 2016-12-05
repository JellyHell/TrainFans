package com.peixunfan.trainfans.ERP.CourseSchedule.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.infrastructure.utils.AppUtil;
import com.peixunfan.trainfans.Base.BaseAdapter;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.Recovery.Model.Article;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/12/4.
 */

public class CourseScheduleCellAdapter  extends BaseAdapter<Article> {

    int height,widht;

    public CourseScheduleCellAdapter(Context context, ArrayList<Article> datas) {
        super(context, datas);
        height = (AppUtil.getHeight(mContext) - AppUtil.dip2px(mContext,78) - AppUtil.getStatusBarHeight(mContext))/18;
        widht = AppUtil.dip2px(mContext,100);
    }

    @Override
    protected int getResourceId() {
        return R.layout.adapter_course_schedule_cell;
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
        aItemViewHolder.conetntCell.setLayoutParams(new RelativeLayout.LayoutParams(widht, height));
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        View mView;

        @Bind(R.id.rlv_cell)
        RelativeLayout conetntCell;

        public ItemViewHolder(View arg0, Context aContext) {
            super(arg0);
            ButterKnife.bind(this,arg0);
            mView = arg0;
        }
    }
}