package com.peixunfan.trainfans.ERP.StudentList.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.infrastructure.utils.AppUtil;
import com.peixunfan.trainfans.Base.PublicContentViewHolder;
import com.peixunfan.trainfans.Base.SectionedRecyclerViewAdapter;
import com.peixunfan.trainfans.ERP.SignUp.Model.StudentInfoModel;
import com.peixunfan.trainfans.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/23.
 */

public class StudentInfoAdapter  extends
        SectionedRecyclerViewAdapter<StudentInfoAdapter.BlankHeaderHolder,PublicContentViewHolder,StudentInfoAdapter.RemarkFooterHolder,RecyclerView.ViewHolder> {

    private Context mContext;

    public ArrayList<String> mStudentInfo = new ArrayList<>();
    private StudentInfoModel mStudentInfoModel;

    public StudentInfoAdapter(Context context,ArrayList<String> studentInfo) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mStudentInfo = studentInfo;
        mStudentInfoModel = new StudentInfoModel();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    protected int getSectionCount() {
        return 1;
    }

    @Override
    protected int getItemCountForSection(int section) {
        return mStudentInfo.size();
    }

    @Override
    protected boolean hasFooterInSection(int section) {
        return true;
    }

    @Override
    protected int getResourceId() {
        return R.layout.viewholder_baseinfocell_layout;
    }

    @Override
    protected BlankHeaderHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        return new BlankHeaderHolder(mInflater.inflate(R.layout.view_blank_view,parent,false));
    }

    @Override
    protected RemarkFooterHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        return new RemarkFooterHolder(mInflater.inflate(R.layout.viewholder_remark_footer_layout,parent,false));
    }

    @Override
    protected PublicContentViewHolder onCreateItemViewHolder(View arg0) {
        return new PublicContentViewHolder(arg0);
    }

    @Override
    protected void onBindSectionHeaderViewHolder(BlankHeaderHolder holder, int section) {

    }

    @Override
    protected void onBindSectionFooterViewHolder(RemarkFooterHolder holder, int section) {

    }


    @Override
    protected void onBindItemViewHolder(final PublicContentViewHolder holder, int section, int position) {
        //Cell信息显示
        if (section == 0){//学生信息
            holder.mSettingLayout.setVisibility(View.VISIBLE);
            holder.mClassInfoLayout.setVisibility(View.GONE);
            holder.title.setText(mStudentInfo.get(position));
            holder.inputText.setTag(section*10 + position);
            switch (position){
                //学生姓名
                case 0:{
                    holder.arrowImg.setVisibility(View.GONE);
                    holder.selectStudentBt.setVisibility(View.GONE);
                    holder.inputText.setEnabled(true);
                    holder.inputText.setHint("请输入学生姓名");
                    holder.inputText.setText(mStudentInfoModel.studentName);
                    holder.inputText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                            if ((Integer)holder.inputText.getTag() == 0){
                                mStudentInfoModel.studentName = editable.toString();
                            }
                        }
                    });
                    break;
                }
                //手机号
                case 1:{
                    holder.arrowImg.setVisibility(View.GONE);
                    holder.selectStudentBt.setVisibility(View.GONE);
                    holder.inputText.setEnabled(true);
                    holder.inputText.setHint("确保手机号可用");
                    holder.inputText.setText(mStudentInfoModel.studentMobile);
                    holder.inputText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                            if ((Integer)holder.inputText.getTag() == 1){
                                mStudentInfoModel.studentMobile = editable.toString();
                            }
                        }
                    });
                    break;
                }
                //出生日期
                case 2:{
                    holder.arrowImg.setVisibility(View.VISIBLE);
                    holder.selectStudentBt.setVisibility(View.GONE);
                    holder.inputText.setEnabled(false);
                    holder.inputText.setHint("选择出生日期");
                    holder.inputText.setText(mStudentInfoModel.studentBirthDay);
                    break;
                }
                //学校
                case 3:{
                    holder.arrowImg.setVisibility(View.GONE);
                    holder.selectStudentBt.setVisibility(View.GONE);
                    holder.inputText.setEnabled(true);
                    holder.inputText.setHint("所在学校");
                    holder.inputText.setText(mStudentInfoModel.studentSchool);
                    holder.inputText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                            if ((Integer)holder.inputText.getTag() == 3){
                                mStudentInfoModel.studentSchool = editable.toString();
                            }
                        }
                    });
                    break;
                }
                //性别
                case 4:{
                    holder.arrowImg.setVisibility(View.VISIBLE);
                    holder.selectStudentBt.setVisibility(View.GONE);
                    holder.inputText.setEnabled(false);
                    holder.inputText.setHint("选择性别");
                    holder.inputText.setText(mStudentInfoModel.studentSex);
                    break;
                }
                //年级
                case 5:{
                    holder.arrowImg.setVisibility(View.VISIBLE);
                    holder.selectStudentBt.setVisibility(View.GONE);
                    holder.inputText.setEnabled(false);
                    holder.inputText.setHint("选择年级");
                    holder.inputText.setText(mStudentInfoModel.studentClass);
                    break;
                }
                //生源
                case 6:{
                    holder.arrowImg.setVisibility(View.GONE);
                    holder.selectStudentBt.setVisibility(View.GONE);
                    holder.inputText.setEnabled(true);
                    holder.inputText.setHint("输入学生来源");
                    holder.inputText.setText(mStudentInfoModel.studentStudentFrom);
                    holder.inputText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                            if ((Integer)holder.inputText.getTag() == 6){
                                mStudentInfoModel.studentStudentFrom = editable.toString();
                            }
                        }
                    });
                    break;
                }
                //备用手机号
                case 7:{
                    holder.arrowImg.setVisibility(View.GONE);
                    holder.selectStudentBt.setVisibility(View.GONE);
                    holder.inputText.setEnabled(true);
                    holder.inputText.setHint("备用手机号");
                    holder.inputText.setText(mStudentInfoModel.studentExtMobile);
                    holder.inputText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                            if ((Integer)holder.inputText.getTag() == 7){
                                mStudentInfoModel.studentExtMobile = editable.toString();
                            }
                        }
                    });

                    break;
                }
                //住址
                case 8:{
                    holder.arrowImg.setVisibility(View.GONE);
                    holder.selectStudentBt.setVisibility(View.GONE);
                    holder.inputText.setEnabled(true);
                    holder.inputText.setHint("家庭现住址");
                    holder.inputText.setText(mStudentInfoModel.studentAddress);
                    holder.inputText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                            if ((Integer)holder.inputText.getTag() == 8){
                                mStudentInfoModel.studentAddress = editable.toString();
                            }
                        }
                    });
                    break;
                }
                default:
                    break;
            }
        }

        //设置分割线
        boolean isSectionLastLine = false;
        if(position == mStudentInfo.size()-1){
            isSectionLastLine = true;
        }
        RelativeLayout.LayoutParams bottomLps = (RelativeLayout.LayoutParams) holder.line.getLayoutParams();
        RelativeLayout.LayoutParams bottomLps2 = (RelativeLayout.LayoutParams) holder.mClassLine.getLayoutParams();
        if (isSectionLastLine) {
            bottomLps.setMargins(0, 0, 0, 0);
            bottomLps2.setMargins(0, 0, 0, 0);
        }else {
            bottomLps.setMargins(AppUtil.dip2px(mContext, 12), 0, 0, 0);
            bottomLps2.setMargins(AppUtil.dip2px(mContext, 12), 0, 0, 0);
        }
    }

    /**设置学生信息*/
    public void setStudentInfoModel(StudentInfoModel aStudentInfoModel) {
        this.mStudentInfoModel = aStudentInfoModel;
        notifyDataSetChanged();
    }


    /*********HEADHolder*********/
    public class BlankHeaderHolder extends RecyclerView.ViewHolder {
        public BlankHeaderHolder(View itemView) {
            super(itemView);
        }
    }

    public class RemarkFooterHolder extends RecyclerView.ViewHolder {
        public RemarkFooterHolder(View itemView) {
            super(itemView);
        }
    }

}