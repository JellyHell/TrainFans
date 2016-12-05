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
import com.peixunfan.trainfans.ERP.Class.View.ClassInfoViewHolder;
import com.peixunfan.trainfans.R;

import java.util.ArrayList;

/**
 * Created by chengyanfang on 2016/11/29.
 */

public class PayCourseInfoAdapter extends
        SectionedRecyclerViewAdapter<SectionHeaderHolder,ClassInfoViewHolder,RecyclerView.ViewHolder,RecyclerView.ViewHolder> {

    private Context mContext;

    public String mCurrentType = "0";
    public String mCurrentDiscontType = "1";

    public ArrayList<String> mBaseInfo = new ArrayList<>();
    public ArrayList<String> mDiscountInfo = new ArrayList<>();
    public ArrayList<String> mBalanceInfo = new ArrayList<>();


    public void refreshData(ArrayList<String> baseInfos,ArrayList<String> discountInfo,ArrayList<String> balanceInfo){
        mBaseInfo = baseInfos;
        mDiscountInfo = discountInfo;
        mBalanceInfo = balanceInfo;
        notifyDataSetChanged();
    }

    public PayCourseInfoAdapter(Context context,ArrayList<String> baseInfos,ArrayList<String> discountInfo,ArrayList<String> balanceInfo) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mBaseInfo = baseInfos;
        mDiscountInfo = discountInfo;
        mBalanceInfo = balanceInfo;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    protected int getSectionCount() {
        return 3;
    }

    @Override
    protected int getItemCountForSection(int section) {
        if(section == 0){
            return mBaseInfo.size();
        }else if(section == 1){
            return mDiscountInfo.size();
        }else{
            return mBalanceInfo.size();
        }
    }

    @Override
    protected boolean hasFooterInSection(int section) {
        return false;
    }

    @Override
    protected int getResourceId() {
        return R.layout.viewholder_classinfo_layout;
    }

    @Override
    protected SectionHeaderHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        return new SectionHeaderHolder(mInflater.inflate(R.layout.viewholder_base_title_layout,parent,false));
    }

    @Override
    protected RecyclerView.ViewHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }


    @Override
    protected ClassInfoViewHolder onCreateItemViewHolder(View arg0) {
        return new ClassInfoViewHolder(arg0);
    }

    @Override
    protected void onBindSectionHeaderViewHolder(SectionHeaderHolder holder, int section) {
        if (section == 0){
            holder.titleView.setText("课程信息");
        }else if(section == 1){
            holder.titleView.setText("优惠");
        }else{
            holder.titleView.setText("结算");
        }
    }

    @Override
    protected void onBindSectionFooterViewHolder(RecyclerView.ViewHolder holder, int section) {

    }


    @Override
    protected void onBindItemViewHolder(final ClassInfoViewHolder holder, int section, int position) {

        holder.mSettingLayout.setVisibility(View.VISIBLE);
        holder.mClassTimeLayout.setVisibility(View.GONE);

        holder.mSettingLayout.setOnClickListener(v -> {
            if(mItemClickListener != null){
                mItemClickListener.onItemClick(null,null,position,section);
            }
        });

        //Cell信息显示
        if (section == 0){//课程信息
            holder.title.setText(mBaseInfo.get(position));
            holder.inputText.setTag(position);

            switch (position){
                case 0://课程名称
                {
                    holder.arrowImg.setVisibility(View.GONE);
                    holder.inputText.setVisibility(View.VISIBLE);
                    holder.inputText.setHint("请输入课程名称");
                    holder.content.setVisibility(View.GONE);
                }
                break;
                case 1://收费方式
                {
                    holder.arrowImg.setVisibility(View.VISIBLE);
                    holder.inputText.setVisibility(View.GONE);
                    holder.inputText.setHint("请输入课程名称");
                    holder.content.setVisibility(View.VISIBLE);
                    holder.content.setText("选择");
                }
                break;
                case 2://授课老师
                {
                    holder.arrowImg.setVisibility(View.VISIBLE);
                    holder.inputText.setVisibility(View.GONE);
                    holder.content.setVisibility(View.VISIBLE);
                    holder.content.setText("选择");
                }
                break;
                case 3://收费方式
                {

                }
                break;
            }

        }else if(section == 1){
            holder.title.setText(mDiscountInfo.get(position));

        }else{
            holder.title.setText(mBalanceInfo.get(position));
        }

        //设置分割线
        boolean isSectionLastLine = false;
        if(section == 0 && position == mBaseInfo.size()-1){
            isSectionLastLine = true;
        }else if(section == 1 && position == mDiscountInfo.size()-1){
            isSectionLastLine = true;
        }else if(section == 2 && position == mBalanceInfo.size()-1){
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