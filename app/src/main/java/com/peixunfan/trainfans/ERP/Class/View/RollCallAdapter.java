package com.peixunfan.trainfans.ERP.Class.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.infrastructure.utils.AppUtil;
import com.peixunfan.trainfans.Base.SectionedRecyclerViewAdapter;
import com.peixunfan.trainfans.ERP.Class.Model.ClassInfo;
import com.peixunfan.trainfans.R;

import java.util.ArrayList;

/**
 * Created by chengyanfang on 2016/11/26.
 */

public class RollCallAdapter   extends
        SectionedRecyclerViewAdapter<RollCallHeaderViewHolder,RollCallViewHolder,RecyclerView.ViewHolder,RecyclerView.ViewHolder> {

    private Context mContext;

    ClassInfo mClassInfo = new ClassInfo();

    public ArrayList<String> mBaseInfo = new ArrayList<>();
    public ArrayList<String> mStudentList = new ArrayList<>();
    public ArrayList<String> mTempStudentList = new ArrayList<>();

    public RollCallAdapter(Context context,ArrayList<String> baseInfos,ArrayList<String> studentList,ArrayList<String>
            mTempStudentlist) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mBaseInfo = baseInfos;
        mStudentList = studentList;
        mTempStudentList = mTempStudentlist;
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
            return mStudentList.size();
        }else{
            return mTempStudentList.size();
        }
    }

    @Override
    protected boolean hasFooterInSection(int section) {
        return false;
    }

    @Override
    protected int getResourceId() {
        return R.layout.viewholder_rollcall_layout;
    }

    @Override
    protected RollCallHeaderViewHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        return new RollCallHeaderViewHolder(mInflater.inflate(R.layout.viewholder_rollcall_header_layout,parent,false));
    }

    @Override
    protected RecyclerView.ViewHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }


    @Override
    protected RollCallViewHolder onCreateItemViewHolder(View arg0) {
        return new RollCallViewHolder(arg0);
    }

    @Override
    protected void onBindSectionHeaderViewHolder(RollCallHeaderViewHolder holder, int section) {
        if (section == 0){
            holder.managerLayout.setVisibility(View.GONE);
            holder.titleLayout.setVisibility(View.GONE);
            holder.blankView.setVisibility(View.VISIBLE);
        }else if(section == 1){
            holder.managerLayout.setVisibility(View.GONE);
            holder.titleLayout.setVisibility(View.VISIBLE);
            holder.blankView.setVisibility(View.GONE);
        }else{
            holder.managerLayout.setVisibility(View.VISIBLE);
            holder.titleLayout.setVisibility(View.GONE);

            if(mStudentList.size()>0){
                holder.blankView.setVisibility(View.VISIBLE);
            }else{
                holder.blankView.setVisibility(View.GONE);
            }

            if(mTempStudentList.size()>0){
                holder.line.setVisibility(View.VISIBLE);
            }else{
                holder.line.setVisibility(View.GONE);
            }

            holder.mAddStudentImg.setOnClickListener(v -> {
                mTempStudentList.add("student");
                notifyDataSetChanged();
            });
        }
    }

    @Override
    protected void onBindSectionFooterViewHolder(RecyclerView.ViewHolder holder, int section) {

    }


    @Override
    protected void onBindItemViewHolder(final RollCallViewHolder holder, int section, int position) {
        //Cell信息显示
        if (section == 0){//课程信息
            holder.mSettingLayout.setVisibility(View.VISIBLE);
            holder.mStudentInfoLayout.setVisibility(View.GONE);

            holder.title.setText(mBaseInfo.get(position));
            holder.inputText.setTag(position);

            holder.mSettingLayout.setOnClickListener(v -> {
                if(mItemClickListener != null){
                    mItemClickListener.onItemClick(null,null,position,section);
                }
            });

            switch (position){
                case 0://课程名称
                {
                    holder.arrowImg.setVisibility(View.GONE);
                    holder.content.setVisibility(View.GONE);
                    holder.inputText.setVisibility(View.VISIBLE);

                    holder.inputText.setHint("输入课程名称");
                    holder.inputText.setText(mClassInfo.classCourseName);

                    holder.inputText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                            if ((Integer)holder.inputText.getTag() == 0){
                                mClassInfo.classCourseName = editable.toString();
                            }
                        }
                    });
                }
                break;
                case 1:{//班级名称
                    holder.arrowImg.setVisibility(View.GONE);
                    holder.content.setVisibility(View.VISIBLE);
                    holder.inputText.setVisibility(View.GONE);

                    holder.content.setText(mClassInfo.className);
                }
                break;
                case 2:{//收费方式
                    holder.arrowImg.setVisibility(View.VISIBLE);
                    holder.content.setVisibility(View.VISIBLE);
                    holder.inputText.setVisibility(View.GONE);

                    holder.content.setText("按课节收费");
                }
                break;
                case 3:{//收费方式
                    holder.arrowImg.setVisibility(View.VISIBLE);
                    holder.content.setVisibility(View.VISIBLE);
                    holder.inputText.setVisibility(View.GONE);
                    holder.content.setText("2016.12.11");
                }
                break;
            }

        }else if(section == 1){
            holder.mSettingLayout.setVisibility(View.GONE);
            holder.mStudentInfoLayout.setVisibility(View.VISIBLE);
            holder.mStudentClassTv.setVisibility(View.VISIBLE);
            holder.mDeleteImg.setVisibility(View.GONE);
        }else{
            holder.mSettingLayout.setVisibility(View.GONE);
            holder.mStudentInfoLayout.setVisibility(View.VISIBLE);
            holder.mDeleteImg.setVisibility(View.VISIBLE);
            holder.mStudentClassTv.setVisibility(View.GONE);

            holder.mDeleteImg.setOnClickListener(v -> {
                mTempStudentList.remove(position);
                notifyDataSetChanged();
            });
        }

        //设置分割线
        boolean isSectionLastLine = false;
        if(section == 0 && position == mBaseInfo.size()-1){
            isSectionLastLine = true;
        }else if(section == 1 && position == mStudentList.size()-1){
            isSectionLastLine = true;
        }else if(section == 2 && position == mTempStudentList.size()-1){
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