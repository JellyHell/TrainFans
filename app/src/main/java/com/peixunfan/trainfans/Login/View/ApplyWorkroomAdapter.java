package com.peixunfan.trainfans.Login.View;

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
import com.peixunfan.trainfans.R;

import java.util.ArrayList;

/**
 * Created by chengyanfang on 2016/11/28.
 */

public class ApplyWorkroomAdapter  extends
        SectionedRecyclerViewAdapter<ApplyAccountHeaderHolder,PublicContentViewHolder,ApplyAccountFooterHolder,RecyclerView.ViewHolder> {

    private Context mContext;

    public ArrayList<String> mBaseInfo = new ArrayList<>();

    private boolean mByCompany = false;

    public ApplyWorkroomAdapter(Context context,ArrayList<String> baseInfo) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mBaseInfo = baseInfo;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    protected int getSectionCount() {
        return 2;
    }

    @Override
    protected int getItemCountForSection(int section) {
        if(section == 0){
            return 6;
        }else{
            return 2;
        }
    }

    @Override
    protected boolean hasFooterInSection(int section) {
        if (section == 1){
            return true;
        }
        return false;
    }

    @Override
    protected int getResourceId() {
        return R.layout.viewholder_baseinfocell_layout;
    }

    @Override
    protected ApplyAccountHeaderHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        return new ApplyAccountHeaderHolder(mInflater.inflate(R.layout.viewholder_applyaccount_header_layout,parent,false));
    }

    @Override
    protected ApplyAccountFooterHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        return new ApplyAccountFooterHolder(mInflater.inflate(R.layout.viewholder_applyaccount_footer_layout,parent,false));
    }

    @Override
    protected PublicContentViewHolder onCreateItemViewHolder(View arg0) {
        return new PublicContentViewHolder(arg0);
    }

    @Override
    protected void onBindSectionHeaderViewHolder(ApplyAccountHeaderHolder holder, int section) {
        if (section == 0){
            holder.titleView.setText("基本信息");
            holder.mNormalInfoLayout.setVisibility(View.VISIBLE);
            holder.mSectionheaderlaout.setVisibility(View.GONE);
        }else{
            holder.mNormalInfoLayout.setVisibility(View.GONE);
            holder.mSectionheaderlaout.setVisibility(View.VISIBLE);

            holder.mByPersonalLayout.setOnClickListener(v -> {
                mByCompany = false;
                holder.mLeftIndicator.setBackgroundColor(mContext.getResources().getColor(R.color.main_color));
                holder.mRightIndicator.setBackgroundColor(mContext.getResources().getColor(R.color.color_ffffff));

                holder.mLeftTitle.setTextColor(mContext.getResources().getColor(R.color.main_color));
                holder.mRightTitle.setTextColor(mContext.getResources().getColor(R.color.base_gray));
                notifyDataSetChanged();
            });

            holder.mByCompanyLayout.setOnClickListener(v -> {
                mByCompany = true;


                holder.mLeftTitle.setTextColor(mContext.getResources().getColor(R.color.base_gray));
                holder.mRightTitle.setTextColor(mContext.getResources().getColor(R.color.main_color));

                holder.mLeftIndicator.setBackgroundColor(mContext.getResources().getColor(R.color.color_ffffff));
                holder.mRightIndicator.setBackgroundColor(mContext.getResources().getColor(R.color.main_color));
                notifyDataSetChanged();
            });
        }
    }

    @Override
    protected void onBindSectionFooterViewHolder(ApplyAccountFooterHolder holder, int section) {
        if(mByCompany){
            holder.mTopTitleLayout.setVisibility(View.VISIBLE);
            holder.mUploadPhotoLayout.setVisibility(View.VISIBLE);
        }else {
            holder.mTopTitleLayout.setVisibility(View.GONE);
            holder.mUploadPhotoLayout.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onBindItemViewHolder(final PublicContentViewHolder holder, int section, int position) {
        holder.mClassInfoLayout.setVisibility(View.GONE);
        holder.arrowImg.setVisibility(View.GONE);
        holder.selectStudentBt.setVisibility(View.GONE);
        holder.mSettingLayout.setVisibility(View.VISIBLE);
        //Cell信息显示
        if (section == 0){//基础信息
            holder.title.setText(mBaseInfo.get(position));
            holder.inputText.setTag(section*10 + position);

            switch (position){
                //工作室名称
                case 0:{
                    holder.inputText.setHint("输入工作室名称");
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
//                                mStudentInfoModel.studentName = editable.toString();
                            }
                        }
                    });
                    break;
                }
                //工作邮箱
                case 1:{
                    holder.inputText.setHint("输入商户合作的邮箱");
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
                            }
                        }
                    });
                    break;
                }
                //电话
                case 2:{
                    holder.inputText.setHint("输入电话");
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
//                                mStudentInfoModel.studentName = editable.toString();
                            }
                        }
                    });
                    break;
                }
                //教务人员数量
                case 3:{
                    holder.inputText.setHint("不包括自己");
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
                            }
                        }
                    });
                    break;
                }
                //教师数量
                case 4:{
                    holder.inputText.setHint("包括全职和兼职");
                    holder.inputText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                            if ((Integer)holder.inputText.getTag() == 4){
                            }
                        }
                    });
                    break;
                }
                //学生数量
                case 5:{
                    holder.inputText.setHint("在读");
                    holder.inputText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                            if ((Integer)holder.inputText.getTag() == 5){
                            }
                        }
                    });
                    break;
                }
                default:
                    break;
            }

        }else{
            holder.inputText.setTag(section*10 + position);

            switch (position){
                //营业执照
                case 0:{
                    if(mByCompany){
                        holder.title.setText("企业名称");
                        holder.inputText.setHint("输入企业名称");
                    }else{
                        holder.title.setText("姓名");
                        holder.inputText.setHint("输入真实");
                    }

                    holder.inputText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                            if ((Integer)holder.inputText.getTag() == 10){
                            }
                        }
                    });
                    break;
                }
                //营业执照
                case 1:{
                    if(mByCompany){
                        holder.title.setText("统一社会信用代码");
                        holder.inputText.setHint("18位信用代码或营业执照");
                    }else{
                        holder.title.setText("身份证号");
                        holder.inputText.setHint("输入身份证号码");
                    }
                    holder.inputText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                            if ((Integer)holder.inputText.getTag() == 10){
                            }
                        }
                    });
                    break;
                }
            }
        }

        //设置分割线
        boolean isSectionLastLine = false;
        if(section == 0 && position == mBaseInfo.size()-1){
            isSectionLastLine = true;
        }else if(section == 1 && position == 1){
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