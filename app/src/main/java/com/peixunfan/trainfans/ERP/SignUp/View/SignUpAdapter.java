package com.peixunfan.trainfans.ERP.SignUp.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.infrastructure.utils.AppUtil;
import com.peixunfan.trainfans.Base.PublicContentViewHolder;
import com.peixunfan.trainfans.Base.SectionHeaderHolder;
import com.peixunfan.trainfans.Base.SectionedRecyclerViewAdapter;
import com.peixunfan.trainfans.ERP.SignUp.Model.SignUpMoneyModel;
import com.peixunfan.trainfans.ERP.SignUp.Model.StudentInfoModel;
import com.peixunfan.trainfans.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/21.
 */

public class SignUpAdapter extends
        SectionedRecyclerViewAdapter<SectionHeaderHolder,PublicContentViewHolder,SignUpFooterHolder,ViewHolder> {

    private Context mContext;

    public ArrayList<String> mCourse = new ArrayList<>();
    public ArrayList<String> mStudentInfo = new ArrayList<>();
    public ArrayList<String> mMoneyInfo = new ArrayList<>();
    private StudentInfoModel mStudentInfoModel;
    private SignUpMoneyModel mSignUpMoneyModel = new SignUpMoneyModel();
    private boolean isOpen = false;

    public SignUpAdapter(Context context,ArrayList<String> courses,ArrayList<String> studentInfo,ArrayList<String>
            moneyInfo,ArrayList<String> course) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mStudentInfo = studentInfo;
        mMoneyInfo = moneyInfo;
        mStudentInfoModel = new StudentInfoModel();
        mCourse = course;
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
            if (isOpen){
                return mStudentInfo.size();
            }else {
                return 2;
            }
        }else if(section == 1){
            return mCourse.size();
        }else{
            return mMoneyInfo.size();
        }
    }

    @Override
    protected boolean hasFooterInSection(int section) {
        if (section == 0 || section == 1){
            return true;
        }
        return false;
    }

    @Override
    protected int getResourceId() {
        return R.layout.viewholder_baseinfocell_layout;
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
    protected PublicContentViewHolder onCreateItemViewHolder(View arg0) {
        return new PublicContentViewHolder(arg0);
    }

    @Override
    protected void onBindSectionHeaderViewHolder(SectionHeaderHolder holder, int section) {
        if (section == 0){
            holder.titleView.setText("学生信息");
        }else if(section == 1){
            holder.titleView.setText("课程信息");
        }else{
            holder.titleView.setText("结算");
        }
    }

    @Override
    protected void onBindSectionFooterViewHolder(SignUpFooterHolder holder, int section) {
        if (section == 0){
            holder.itemView.setOnClickListener(view -> {
                isOpen = !isOpen;
                notifyDataSetChanged();
            });

            if (isOpen){
                holder.title.setText("收起更多信息");
                holder.titleIcon.setBackgroundResource(R.drawable.icon_close_more);
            }else{
                holder.title.setText("展开更多信息");
                holder.titleIcon.setBackgroundResource(R.drawable.icon_open_more);
            }
        }else if(section == 1){
            holder.title.setText("添加课程");
            holder.titleIcon.setBackgroundResource(R.drawable.icon_addclass);

            holder.itemView.setOnClickListener(view -> {
                if(mChangeClassCallback != null){
                    mChangeClassCallback.addClass();
                }
            });
        }
    }

    @Override
    protected void onBindItemViewHolder(final PublicContentViewHolder holder, int section, int position) {
        //Cell信息显示
        if (section == 0){//学生信息
            holder.mSettingLayout.setVisibility(View.VISIBLE);
            holder.mClassInfoLayout.setVisibility(View.GONE);
            holder.title.setText(mStudentInfo.get(position));
            holder.selectStudentBt.setOnClickListener(view -> {
                if(mSelectStudentCallBack != null){
                    mSelectStudentCallBack.selectStudent();
                }
            });
            holder.inputText.setTag(section*10 + position);
            switch (position){
                //学生姓名
                case 0:{
                    holder.arrowImg.setVisibility(View.GONE);
                    holder.selectStudentBt.setVisibility(View.VISIBLE);
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

        }else if(section == 1){
            holder.mSettingLayout.setVisibility(View.GONE);
            holder.mClassInfoLayout.setVisibility(View.VISIBLE);
            holder.title.setText(mCourse.get(position));

            if(position%2==0){
                holder.mClassTypeImage.setBackgroundResource(R.drawable.icon_class_type_team);
                holder.mClassName.setText("钢琴拉布拉多");
                holder.mClassInfo.setText("24节课 (单节时长45分钟)");
            }else {
                holder.mClassTypeImage.setBackgroundResource(R.drawable.icon_class_type_class);
                holder.mClassName.setText("钢琴拉布拉西");
                holder.mClassInfo.setText("30节课 (单节时长60分钟)");
            }

        }else{
            holder.title.setText(mMoneyInfo.get(position));
            holder.mSettingLayout.setVisibility(View.VISIBLE);
            holder.mClassInfoLayout.setVisibility(View.GONE);
            switch (position){
                //其他费用
                case 0:{
                    holder.arrowImg.setVisibility(View.GONE);
                    holder.selectStudentBt.setVisibility(View.GONE);
                    holder.inputText.setEnabled(true);
                    holder.inputText.setHint("请输入其他费用");
                    holder.inputText.setText("");
                    break;
                }
                //应收金额
                case 1:{
                    holder.arrowImg.setVisibility(View.GONE);
                    holder.selectStudentBt.setVisibility(View.GONE);
                    holder.inputText.setEnabled(false);
                    holder.inputText.setHint("");
                    holder.inputText.setText("");
                    break;
                }
                //实收金额
                case 2:{
                    holder.arrowImg.setVisibility(View.GONE);
                    holder.selectStudentBt.setVisibility(View.GONE);
                    holder.inputText.setEnabled(true);
                    holder.inputText.setHint("填写实际入账金额");
                    holder.inputText.setText("");
                    break;
                }
                //付款日期
                case 3:{
                    holder.arrowImg.setVisibility(View.VISIBLE);
                    holder.selectStudentBt.setVisibility(View.GONE);
                    holder.inputText.setEnabled(false);
                    holder.inputText.setText("2016-11-21");
                    break;
                }
                //支付方式
                case 4:{
                    holder.arrowImg.setVisibility(View.VISIBLE);
                    holder.selectStudentBt.setVisibility(View.GONE);
                    holder.inputText.setEnabled(false);
                    holder.inputText.setHint("选择支付方式");
                    holder.inputText.setText("");
                    break;
                }
                //经办人
                case 5:{
                    holder.arrowImg.setVisibility(View.GONE);
                    holder.selectStudentBt.setVisibility(View.GONE);
                    holder.inputText.setEnabled(true);
                    holder.inputText.setHint("选填");
                    holder.inputText.setText("");
                    break;
                }
                //备注
                case 6:{
                    holder.arrowImg.setVisibility(View.GONE);
                    holder.selectStudentBt.setVisibility(View.GONE);
                    holder.inputText.setEnabled(true);
                    holder.inputText.setHint("选填");
                    holder.inputText.setText("");
                    break;
                }
            }
        }

        //设置分割线
        boolean isSectionLastLine = false;
        if(section == 0 && position == mStudentInfo.size()-1){
            isSectionLastLine = true;
        }else if(section == 1 && position == mCourse.size()-1){
            isSectionLastLine = true;
        }else if(section == 2 && position == mMoneyInfo.size()-1){
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

    public boolean isOpen() {
        return isOpen;
    }

    /**设置学生信息*/
    public void setStudentInfoModel(StudentInfoModel aStudentInfoModel) {
        this.mStudentInfoModel = aStudentInfoModel;
        notifyDataSetChanged();
    }

    /**接口回调 --- 操作课程*/
    public interface ChangeClassCallback{
        void addClass();
    }

    private ChangeClassCallback mChangeClassCallback;

    public void setChangeClassCallback(ChangeClassCallback aChangeClassCallback) {
        this.mChangeClassCallback = aChangeClassCallback;
    }

    /**接口回调---选择学生*/
    public interface SelectStudentCallBack{
        void selectStudent();
    }

    SelectStudentCallBack mSelectStudentCallBack;

    public void setSelectStudentCallBack(SelectStudentCallBack aSelectStudentCallBack) {
        this.mSelectStudentCallBack = aSelectStudentCallBack;
    }
}