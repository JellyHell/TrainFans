package com.peixunfan.trainfans.ERP.Class.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.peixunfan.trainfans.Base.BaseAdapter;
import com.peixunfan.trainfans.ERP.Class.Model.StudentModel;
import com.peixunfan.trainfans.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/11/27.
 */

public class ManagerStudentAdapter  extends BaseAdapter<StudentModel> {

    public ManagerStudentAdapter(Context context, ArrayList<StudentModel> datas) {
        super(context, datas);
    }

    @Override
    protected int getResourceId() {
        return R.layout.adapter_student_manager_layout;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemHolderViewHolder(View arg0) {
        return new ItemViewHolder(arg0, this.mContext);
    }

    @Override
    protected void onBindContentViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final ItemViewHolder aItemViewHolder = (ItemViewHolder) viewHolder;

        aItemViewHolder.mCellTitle.setText(mDatas.get(position).studentName);

        if(mDatas.get(position).selected){
            aItemViewHolder.mSelectBgLayout.setBackgroundResource(R.drawable.bg_student_selected);
        }else{
            aItemViewHolder.mSelectBgLayout.setBackgroundResource(0);
        }


        // 点击时间
        if (mItemClickListener != null) {
            aItemViewHolder.mView.setOnClickListener(v -> mItemClickListener.onItemClick(null, null, position, 0));
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_cell_title)
        TextView mCellTitle;

        @Bind(R.id.rlv_info_layout)
        RelativeLayout mTopLayout;

        @Bind(R.id.rlv_select_layout)
        RelativeLayout mSelectBgLayout;

        View mView;

        public ItemViewHolder(View arg0, Context aContext) {
            super(arg0);
            ButterKnife.bind(this,arg0);
            mView = arg0;
        }
    }
}