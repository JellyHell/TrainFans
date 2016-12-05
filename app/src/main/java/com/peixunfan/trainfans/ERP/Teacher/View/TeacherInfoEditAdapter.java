package com.peixunfan.trainfans.ERP.Teacher.View;

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
import com.peixunfan.trainfans.ERP.Class.View.ClassInfoHeaderViewHolder;
import com.peixunfan.trainfans.ERP.Teacher.Model.TeacherInfo;
import com.peixunfan.trainfans.R;

import java.util.ArrayList;

/**
 * Created by chengyanfang on 2016/11/28.
 */

public class TeacherInfoEditAdapter   extends
        SectionedRecyclerViewAdapter<ClassInfoHeaderViewHolder,TeacherInfoViewHolder,RecyclerView.ViewHolder,RecyclerView.ViewHolder> {

    private Context mContext;

    TeacherInfo mTeacherInfo = new TeacherInfo();

    public ArrayList<String> mBaseInfo = new ArrayList<>();



    public TeacherInfoEditAdapter(Context context,ArrayList<String> baseInfos) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mBaseInfo = baseInfos;
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
        if(section == 0){
            return mBaseInfo.size();
        }else if(section == 1){
            if(mTeacherInfo.teacherSeparateType.equals("1")||mTeacherInfo.teacherSeparateType.equals("2")){
                return  2;
            }else{
                return 3;
            }
        }else if(section == 2){
            return  2;
        }else{
            return 1;
        }
    }

    @Override
    protected boolean hasFooterInSection(int section) {
        return false;
    }

    @Override
    protected int getResourceId() {
        return R.layout.viewholder_teacherinfo_layout;
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
    protected TeacherInfoViewHolder onCreateItemViewHolder(View arg0) {
        return new TeacherInfoViewHolder(arg0);
    }

    @Override
    protected void onBindSectionHeaderViewHolder(ClassInfoHeaderViewHolder holder, int section) {
        holder.mTitleLayout.setVisibility(View.GONE);
    }

    @Override
    protected void onBindSectionFooterViewHolder(RecyclerView.ViewHolder holder, int section) {

    }


    @Override
    protected void onBindItemViewHolder(final TeacherInfoViewHolder holder, int section, int position) {

        holder.mSettingLayout.setOnClickListener(v -> {
            if(mItemClickListener != null){
                mItemClickListener.onItemClick(null,null,position,section);
            }
        });

        holder.inputText.setTag(section *10 +position);

        //Cell信息显示
        if (section == 0){//老师基本信息
            holder.mSettingLayout.setVisibility(View.VISIBLE);
            holder.title.setText(mBaseInfo.get(position));

            switch (position){
                case 0://课程名称
                {
                    holder.arrowImg.setVisibility(View.GONE);
                    holder.content.setVisibility(View.GONE);
                    holder.inputText.setVisibility(View.VISIBLE);
                    holder.inputText.setHint("输入教师姓名");
                    holder.inputText.setText(mTeacherInfo.teacherName);

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
                                mTeacherInfo.teacherName = editable.toString();
                            }
                        }
                    });
                }
                break;
                case 1:{//性别
                    holder.arrowImg.setVisibility(View.VISIBLE);
                    holder.content.setVisibility(View.VISIBLE);
                    holder.inputText.setVisibility(View.GONE);
                    holder.content.setText(mTeacherInfo.teacherSex);
                }
                break;
                case 2:{//擅长科目
                    holder.arrowImg.setVisibility(View.VISIBLE);
                    holder.content.setVisibility(View.VISIBLE);
                    holder.inputText.setVisibility(View.GONE);
                    holder.content.setText(mTeacherInfo.teacherSkill);
                }
                break;
                case 3:{//手机号
                    holder.arrowImg.setVisibility(View.GONE);
                    holder.content.setVisibility(View.GONE);
                    holder.inputText.setVisibility(View.VISIBLE);
                    holder.inputText.setHint("请输入手机号");
                    holder.inputText.setText(mTeacherInfo.teacherMobile);

                    holder.inputText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                            if ((Integer)holder.inputText.getTag() == 3){
                                mTeacherInfo.teacherMobile = editable.toString();
                            }
                        }
                    });
                }
                break;
            }

        }else if(section == 1){//分成方式
            switch (position){
                case 0:{
                    holder.title.setText("分成方式");
                    holder.arrowImg.setVisibility(View.VISIBLE);
                    holder.content.setVisibility(View.VISIBLE);
                    holder.inputText.setVisibility(View.GONE);
                    holder.content.setText(mTeacherInfo.getSeparateTypeStr());
                }
                break;
                case 1:{
                    if(mTeacherInfo.teacherSeparateType.equals("2")){
                        holder.title.setText("固定抽取¥");
                        holder.inputText.setHint("输入金额");
                        holder.inputText.setText(mTeacherInfo.courseFluctuateCount);
                    }else{
                        holder.title.setText("课价%");
                        holder.inputText.setHint("输入比例");
                        holder.inputText.setText(mTeacherInfo.courseFluctuatePercent);
                    }
                    holder.arrowImg.setVisibility(View.GONE);
                    holder.content.setVisibility(View.GONE);
                    holder.inputText.setVisibility(View.VISIBLE);

                    holder.inputText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                            if ((Integer)holder.inputText.getTag() == 11){
                                if(mTeacherInfo.teacherSeparateType.equals("2")){
                                    mTeacherInfo.courseFluctuateCount = editable.toString();
                                }else{
                                    mTeacherInfo.courseFluctuatePercent = editable.toString();
                                }
                            }
                        }
                    });
                }
                break;

                case 2:{
                    holder.title.setText("固定抽取¥");
                    holder.inputText.setText(mTeacherInfo.courseFluctuateCount);
                    holder.arrowImg.setVisibility(View.GONE);
                    holder.content.setVisibility(View.GONE);
                    holder.inputText.setVisibility(View.VISIBLE);
                    holder.inputText.setHint("输入金额");

                    holder.inputText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                            if ((Integer)holder.inputText.getTag() == 11){
                                mTeacherInfo.courseFluctuateCount = editable.toString();
                            }
                        }
                    });
                }
                break;
            }


        }else if(section == 2){

            switch (position){
                case 0:{
                    holder.title.setText("课价浮动");
                    holder.arrowImg.setVisibility(View.VISIBLE);
                    holder.content.setVisibility(View.VISIBLE);
                    holder.inputText.setVisibility(View.GONE);
                    holder.content.setText(mTeacherInfo.getFluctuateStr());
                }
                break;
                case 1:{
                    if(mTeacherInfo.courseFluctuateType.equals("1")){
                        holder.title.setText("固定上浮¥");
                        holder.inputText.setText(mTeacherInfo.courseFluctuateCount);
                        holder.inputText.setHint("输入金额");
                    }else{
                        holder.title.setText("固定上浮%");
                        holder.inputText.setHint("输入比例");
                        holder.inputText.setText(mTeacherInfo.courseFluctuatePercent);
                    }
                    holder.arrowImg.setVisibility(View.GONE);
                    holder.content.setVisibility(View.GONE);
                    holder.inputText.setVisibility(View.VISIBLE);

                    holder.inputText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                            if ((Integer)holder.inputText.getTag() == 11){
                                if(mTeacherInfo.teacherSeparateType.equals("2")){
                                    mTeacherInfo.courseFluctuateCount = editable.toString();
                                }else{
                                    mTeacherInfo.courseFluctuatePercent = editable.toString();
                                }
                            }
                        }
                    });
                }
                break;
            }
        }else{
            holder.title.setText("启动状态");
            holder.arrowImg.setVisibility(View.GONE);
            holder.content.setVisibility(View.GONE);
            holder.inputText.setVisibility(View.GONE);
        }

        //设置分割线
        boolean isSectionLastLine = false;
        if(section == 0 && position == mBaseInfo.size()-1){
            isSectionLastLine = true;
        }else if(section == 1){
            if(mTeacherInfo.teacherSeparateType.equals("1")||mTeacherInfo.teacherSeparateType.equals("2")){
                if (position == 1){
                    isSectionLastLine = true;
                }
            }else{
                if(position == 2){
                    isSectionLastLine = true;
                }
            }
        }else if(section == 2 && position == 1){
            isSectionLastLine = true;
        }

        RelativeLayout.LayoutParams bottomLps = (RelativeLayout.LayoutParams) holder.line.getLayoutParams();
        if (isSectionLastLine) {
            bottomLps.setMargins(0, 0, 0, 0);
        }else {
            bottomLps.setMargins(AppUtil.dip2px(mContext, 12), 0, 0, 0);
        }


        //设置彩色线
        if(section == 0){
            holder.leftColoredLine.setVisibility(View.GONE);
        }else if(section == 1){
            if(position == 1){
                if(mTeacherInfo.teacherSeparateType.equals("2")){
                    holder.leftColoredLine.setVisibility(View.VISIBLE);
                    holder.leftColoredLine.setBackgroundColor(mContext.getResources().getColor(R.color.color_feb747));
                }else{
                    holder.leftColoredLine.setVisibility(View.VISIBLE);
                    holder.leftColoredLine.setBackgroundColor(mContext.getResources().getColor(R.color.color_f67d53));
                }
            }else if(position ==2){
                holder.leftColoredLine.setVisibility(View.VISIBLE);
                holder.leftColoredLine.setBackgroundColor(mContext.getResources().getColor(R.color.color_feb747));
            }else{
                holder.leftColoredLine.setVisibility(View.GONE);
            }
        }else if(section == 2){
            if(position == 1){
                if(mTeacherInfo.courseFluctuateType.equals("1")){
                    holder.leftColoredLine.setVisibility(View.VISIBLE);
                    holder.leftColoredLine.setBackgroundColor(mContext.getResources().getColor(R.color.color_529ce7));
                }else{
                    holder.leftColoredLine.setVisibility(View.VISIBLE);
                    holder.leftColoredLine.setBackgroundColor(mContext.getResources().getColor(R.color.color_15b98b));
                }
            }else{
                holder.leftColoredLine.setVisibility(View.GONE);
            }
        }
    }


    public void setTeacherSeparateType(String separateType){
        mTeacherInfo.teacherSeparateType = separateType;
        notifyDataSetChanged();
    }

    public void setFluctuateType(String fluctuateType){
        mTeacherInfo.courseFluctuateType = fluctuateType;
        notifyDataSetChanged();
    }

}