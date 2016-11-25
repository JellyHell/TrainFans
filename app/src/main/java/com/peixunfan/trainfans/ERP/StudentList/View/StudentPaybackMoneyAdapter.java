package com.peixunfan.trainfans.ERP.StudentList.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.peixunfan.trainfans.Base.BaseAdapter;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.Recovery.Model.Article;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/24.
 */

public class StudentPaybackMoneyAdapter extends BaseAdapter<Article> {

    public StudentPaybackMoneyAdapter(Context context, ArrayList<Article> datas) {
        super(context, datas);
    }

    @Override
    protected int getResourceId() {
        return R.layout.adapter_student_payback_item;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemHolderViewHolder(View arg0) {
        return new StudentAttendClassRecordAdapter.ItemViewHolder(arg0, this.mContext);
    }

    @Override
    protected void onBindContentViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final StudentAttendClassRecordAdapter.ItemViewHolder aItemViewHolder = (StudentAttendClassRecordAdapter.ItemViewHolder) viewHolder;

        // 点击事件
        if (mItemClickListener != null) {
            aItemViewHolder.mView.setOnClickListener(v -> mItemClickListener.onItemClick(null, null, position, 0));
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public ItemViewHolder(View arg0, Context aContext) {
            super(arg0);
            ButterKnife.bind(this,arg0);
            mView = arg0;
        }
    }
}