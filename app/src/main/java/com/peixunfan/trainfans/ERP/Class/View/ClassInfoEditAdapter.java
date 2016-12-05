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

public class ClassInfoEditAdapter  extends
        SectionedRecyclerViewAdapter<ClassInfoHeaderViewHolder,ClassInfoViewHolder,RecyclerView.ViewHolder,RecyclerView.ViewHolder> {

    private Context mContext;

    ClassInfo mClassInfo = new ClassInfo();

    public ArrayList<String> mBaseInfo = new ArrayList<>();
    public ArrayList<String> mClassTime = new ArrayList<>();
    public ArrayList<String> mClassTeacher = new ArrayList<>();

    public ClassInfoEditAdapter(Context context,ArrayList<String> baseInfos,ArrayList<String> classTime,ArrayList<String>
            classTeacher) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mBaseInfo = baseInfos;
        mClassTime = classTime;
        mClassTeacher = classTeacher;
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
            return mClassTime.size();
        }else{
            return mClassTeacher.size();
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
    protected ClassInfoHeaderViewHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        return new ClassInfoHeaderViewHolder(mInflater.inflate(R.layout.viewholder_classinfo_header_layout,parent,false));
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
    protected void onBindSectionHeaderViewHolder(ClassInfoHeaderViewHolder holder, int section) {
        if (section == 0){
            holder.mTitleLayout.setVisibility(View.GONE);
        }else if(section == 1){
            holder.mTitleLayout.setVisibility(View.VISIBLE);
            holder.title.setText("排课时间");
            holder.mAddImageView.setOnClickListener(v -> {
                mClassTime.add("classTime");
                notifyDataSetChanged();
            });
            if(mClassTime.size() == 0){
                holder.line.setVisibility(View.GONE);
            }else{
                holder.line.setVisibility(View.VISIBLE);
            }
        }else{
            holder.mTitleLayout.setVisibility(View.VISIBLE);
            holder.title.setText("授课老师");
            holder.mAddImageView.setOnClickListener(v -> {
                mClassTeacher.add("classTeacher");
                notifyDataSetChanged();
            });
            if(mClassTeacher.size() == 0){
                holder.line.setVisibility(View.GONE);
            }else{
                holder.line.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    protected void onBindSectionFooterViewHolder(RecyclerView.ViewHolder holder, int section) {

    }


    @Override
    protected void onBindItemViewHolder(final ClassInfoViewHolder holder, int section, int position) {
        //Cell信息显示
        if (section == 0){//课程信息
            holder.mSettingLayout.setVisibility(View.VISIBLE);
            holder.mClassTimeLayout.setVisibility(View.GONE);

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
            holder.mClassTimeLayout.setVisibility(View.VISIBLE);
            holder.mTeacherIcon.setVisibility(View.INVISIBLE);
            holder.mTeacherType.setVisibility(View.GONE);
            holder.mClassTime.setText("星期一(9:00-11:00)");

            holder.mDeleteImg.setOnClickListener(v -> {
                mClassTime.remove(position);

                notifyDataSetChanged();
            });

        }else{
            holder.mSettingLayout.setVisibility(View.GONE);
            holder.mClassTimeLayout.setVisibility(View.VISIBLE);
            holder.mTeacherIcon.setVisibility(View.VISIBLE);
            holder.mTeacherType.setVisibility(View.VISIBLE);
            holder.mClassTime.setText("李丹丹");
            holder.mTeacherType.setText("钢琴");
            holder.mDeleteImg.setOnClickListener(v -> {
                mClassTeacher.remove(position);

                notifyDataSetChanged();
            });

        }

        //设置分割线
        boolean isSectionLastLine = false;
        if(section == 0 && position == mBaseInfo.size()-1){
            isSectionLastLine = true;
        }else if(section == 1 && position == mClassTime.size()-1){
            isSectionLastLine = true;
        }else if(section == 2 && position == mClassTeacher.size()-1){
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