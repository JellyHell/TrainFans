package com.peixunfan.trainfans.ERP.StudentList.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.peixunfan.trainfans.Base.SectionedRecyclerViewAdapter;
import com.peixunfan.trainfans.ERP.StudentList.Model.StudentClass;
import com.peixunfan.trainfans.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/23.
 */

public class StudentClassAdapter  extends
        SectionedRecyclerViewAdapter<StudentClassHeaderHolder,StudentClassContentHolder,RecyclerView.ViewHolder,RecyclerView.ViewHolder> {

    private Context mContext;

    public ArrayList<StudentClass> mStudentClass = new ArrayList<>();

    public StudentClassAdapter(Context context,ArrayList<StudentClass> studentClasses) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mStudentClass = studentClasses;
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
       return mStudentClass.get(section).mClassRoom.size();
    }

    @Override
    protected boolean hasFooterInSection(int section) {
        return false;
    }

    @Override
    protected int getResourceId() {
        return R.layout.viewholder_studentclass_content;
    }

    @Override
    protected StudentClassHeaderHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        return new StudentClassHeaderHolder(mInflater.inflate(R.layout.view_holder_studentclass_header,parent,false),mContext);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected StudentClassContentHolder onCreateItemViewHolder(View arg0) {
        return new StudentClassContentHolder(arg0);
    }

    @Override
    protected void onBindSectionHeaderViewHolder(StudentClassHeaderHolder holder, int section) {
        if(mStudentClass.get(section).mClassRoom.size()>0){
            holder.mSelectClassImg.setVisibility(View.GONE);
        }else{
            holder.mSelectClassImg.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onBindSectionFooterViewHolder(RecyclerView.ViewHolder holder, int section) {

    }

    @Override
    protected void onBindItemViewHolder(final StudentClassContentHolder holder, int section, int position) {
//        holder.mSettingLayout.setVisibility(View.GONE);
//        holder.mClassInfoLayout.setVisibility(View.VISIBLE);
    }

}