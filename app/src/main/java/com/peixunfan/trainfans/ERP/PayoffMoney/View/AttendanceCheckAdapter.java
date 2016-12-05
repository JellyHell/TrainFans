package com.peixunfan.trainfans.ERP.PayoffMoney.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.infrastructure.utils.AppUtil;
import com.peixunfan.trainfans.Base.SectionHeaderHolder;
import com.peixunfan.trainfans.Base.SectionedRecyclerViewAdapter;
import com.peixunfan.trainfans.ERP.SignUp.View.SignUpFooterHolder;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.Recovery.Model.Article;

import java.util.ArrayList;

/**
 * Created by chengyanfang on 2016/11/29.
 */

public class AttendanceCheckAdapter  extends
        SectionedRecyclerViewAdapter<SectionHeaderHolder,AttendanceCheckViewHolder,SignUpFooterHolder,RecyclerView.ViewHolder> {

    private Context mContext;

    public ArrayList<Article> mRecords = new ArrayList<>();

    public AttendanceCheckAdapter(Context context,ArrayList<Article> records) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mRecords = records;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    protected int getSectionCount() {
        return 4;
    }

    @Override
    protected int getItemCountForSection(int section) {
        return 5-section;
    }

    @Override
    protected boolean hasFooterInSection(int section) {
        return false;
    }

    @Override
    protected int getResourceId() {
        return R.layout.viewholder_attendcheck_layout;
    }

    @Override
    protected SectionHeaderHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        return new SectionHeaderHolder(mInflater.inflate(R.layout.viewholder_base_title_layout,parent,false));
    }

    @Override
    protected SignUpFooterHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        return new SignUpFooterHolder(mInflater.inflate(R.layout.viewholder_signup_footer_layout,parent,false));
    }

    @Override
    protected AttendanceCheckViewHolder onCreateItemViewHolder(View arg0) {
        return new AttendanceCheckViewHolder(arg0);
    }

    @Override
    protected void onBindSectionHeaderViewHolder(SectionHeaderHolder holder, int section) {
        holder.titleView.setText("9月20日");
    }

    @Override
    protected void onBindSectionFooterViewHolder(SignUpFooterHolder holder, int section) {

    }

    @Override
    protected void onBindItemViewHolder(final AttendanceCheckViewHolder holder, int section, int position) {
        //Cell信息显示
        holder.mCardView.setOnClickListener(v -> mItemClickListener.onItemClick(null,null,position,section));

        //设置分割线
        boolean isSectionLastLine = false;
        if(position == 5-section-1){
            isSectionLastLine = true;
        }

        RelativeLayout.LayoutParams bottomLps = (RelativeLayout.LayoutParams) holder.line.getLayoutParams();
        if (isSectionLastLine) {
            bottomLps.setMargins(0, 0, 0, 0);
        }else {
            bottomLps.setMargins(AppUtil.dip2px(mContext, 12), 0, 0, 0);
        }
    }


}