package com.peixunfan.trainfans.ERP.Courses.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.peixunfan.trainfans.Base.BaseAdapter;
import com.peixunfan.trainfans.ERP.Courses.Model.Course;
import com.peixunfan.trainfans.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/24.
 */

public class CouserInfoAdapter  extends BaseAdapter<String> {

    public Course mCouse;

    public CouserInfoAdapter(Context context, ArrayList<String> datas,Course course) {
        super(context, datas);
        mCouse = course;
    }

    @Override
    protected int getResourceId() {
        return R.layout.adapter_classinfo_edit_item;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemHolderViewHolder(View arg0) {
        return new ItemViewHolder(arg0, this.mContext);
    }

    @Override
    protected void onBindContentViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final ItemViewHolder holder = (ItemViewHolder) viewHolder;

        // 点击事件
        if (mItemClickListener != null) {
            holder.mView.setOnClickListener(v -> mItemClickListener.onItemClick(null, null, position, 0));
        }

        holder.title.setText(mDatas.get(position));
        holder.inputText.setTag(position);
        switch (position){
            //课程名
            case 0:{
                holder.arrowImg.setVisibility(View.GONE);
                holder.inputText.setEnabled(true);
                holder.content.setVisibility(View.GONE);
                holder.inputText.setVisibility(View.VISIBLE);
                holder.inputText.setHint("请输入课程名称");
                holder.inputText.setText(mCouse.course_name);
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
                            mCouse.course_name = editable.toString();
                        }
                    }
                });
                break;
            }
            //课程类型
            case 1:{
                holder.arrowImg.setVisibility(View.VISIBLE);
                holder.inputText.setEnabled(false);
                holder.content.setText("选择");
                holder.content.setVisibility(View.VISIBLE);
                holder.inputText.setVisibility(View.GONE);
                holder.inputText.setText(mCouse.class_type);
                break;
            }
            //授课类型
            case 2:{
                holder.arrowImg.setVisibility(View.VISIBLE);
                holder.inputText.setEnabled(false);
                holder.content.setText(mCouse.getTeach_typeStr());
                holder.content.setVisibility(View.VISIBLE);
                holder.inputText.setVisibility(View.GONE);
                break;
            }
            //单节时长/分
            case 3:{
                holder.arrowImg.setVisibility(View.GONE);
                holder.inputText.setEnabled(true);
                holder.content.setVisibility(View.GONE);
                holder.inputText.setVisibility(View.VISIBLE);
                holder.inputText.setHint("请输入时长");
                holder.inputText.setText(mCouse.course_length);
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
                            mCouse.course_length = editable.toString();
                        }
                    }
                });
                break;
            }
            //收费方式
            case 4:{
                holder.arrowImg.setVisibility(View.VISIBLE);
                holder.inputText.setEnabled(false);
                holder.content.setVisibility(View.VISIBLE);
                holder.inputText.setVisibility(View.GONE);
                holder.content.setText(mCouse.getCharge_typeStr());
                break;
            }
            case 5:{
                if("1".equals(mCouse.charge_type)||"3".equals(mCouse.charge_type)){//课节单价
                    holder.arrowImg.setVisibility(View.GONE);
                    holder.inputText.setEnabled(true);
                    holder.content.setVisibility(View.GONE);
                    holder.inputText.setVisibility(View.VISIBLE);
                    holder.inputText.setHint("课节单价");
                    holder.inputText.setText(mCouse.course_unit_price);
                }else if("2".equals(mCouse.charge_type)){ //总课节数
                    holder.arrowImg.setVisibility(View.GONE);
                    holder.inputText.setEnabled(true);
                    holder.content.setVisibility(View.GONE);
                    holder.inputText.setVisibility(View.VISIBLE);
                    holder.inputText.setHint("总课节数");
                    holder.inputText.setText(mCouse.course_count);
                }else{//未到学生标记补课
                    holder.arrowImg.setVisibility(View.GONE);
                    holder.inputText.setEnabled(false);
                    holder.content.setVisibility(View.VISIBLE);
                    holder.inputText.setVisibility(View.GONE);
                    holder.inputText.setHint("");
                    holder.inputText.setText("");
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
                        if ((Integer)holder.inputText.getTag() == 5){
                            if("2".equals(mCouse.charge_type)){//总课节数
                                mCouse.course_count = editable.toString();
                            }else{ //课节单价
                                mCouse.course_unit_price = editable.toString();
                            }
                        }
                    }
                });
            }
            break;
            case 6:{
                if("3".equals(mCouse.charge_type)){//总课节数目
                    holder.arrowImg.setVisibility(View.GONE);
                    holder.inputText.setEnabled(true);
                    holder.content.setVisibility(View.GONE);
                    holder.inputText.setVisibility(View.VISIBLE);
                    holder.inputText.setHint("总课节数");
                    holder.inputText.setText(mCouse.course_count);
                }else if("2".equals(mCouse.charge_type)){//课程总价
                    holder.arrowImg.setVisibility(View.GONE);
                    holder.inputText.setEnabled(true);
                    holder.content.setVisibility(View.GONE);
                    holder.inputText.setVisibility(View.VISIBLE);
                    holder.inputText.setHint("课程总价");
                    holder.inputText.setText(mCouse.course_price);
                }else{//未到学生标记补课
                    holder.arrowImg.setVisibility(View.GONE);
                    holder.inputText.setEnabled(false);
                    holder.content.setVisibility(View.VISIBLE);
                    holder.inputText.setVisibility(View.GONE);
                    holder.inputText.setHint("");
                    holder.inputText.setText("");
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
                        if ((Integer)holder.inputText.getTag() == 6){
                            if("3".equals(mCouse.charge_type)){//总课节数
                                mCouse.course_count = editable.toString();
                            }else{ //课程总价
                                mCouse.course_price = editable.toString();
                            }
                        }
                    }
                });
            }
            break;
            case 7:{
                if("3".equals(mCouse.charge_type)){
                    holder.arrowImg.setVisibility(View.GONE);
                    holder.inputText.setEnabled(true);
                    holder.content.setVisibility(View.GONE);
                    holder.inputText.setVisibility(View.VISIBLE);
                    holder.inputText.setHint("课程总价");
                    holder.inputText.setText(mCouse.course_price);
                }else{//未到学生标记补课
                    holder.arrowImg.setVisibility(View.GONE);
                    holder.inputText.setEnabled(false);
                    holder.content.setVisibility(View.VISIBLE);
                    holder.inputText.setVisibility(View.GONE);
                    holder.content.setText("");
                    holder.inputText.setHint("");
                    holder.inputText.setText("");
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
                        if ((Integer)holder.inputText.getTag() == 7){
                            mCouse.course_price = editable.toString();
                        }
                    }
                });
            }
            break;
            case 8:
            {
                //未到学生标记补课
                holder.arrowImg.setVisibility(View.GONE);
                holder.inputText.setEnabled(false);
                holder.content.setVisibility(View.VISIBLE);
                holder.inputText.setVisibility(View.GONE);
                holder.content.setText("");
                holder.inputText.setText("");
                break;
            }
            default:
                break;
        }

        //分割线
        if(mDatas.get(position).equals("收费方式")||mDatas.get(position).equals("课节单价")||mDatas.get(position).equals("课程总价")||mDatas.get(position).equals("未到学生标记补课")){
            holder.line.setVisibility(View.GONE);
        }else {
            holder.line.setVisibility(View.VISIBLE);
        }

        //空栏目
        if(mDatas.get(position).equals("课程名称")||mDatas.get(position).equals("课节单价")||mDatas.get(position).equals("总课节数")||mDatas.get(position).equals("未到学生标记补课")){
            holder.blankView.setVisibility(View.VISIBLE);
        }else {
            holder.blankView.setVisibility(View.GONE);
        }

        //色彩线
        if(mDatas.get(position).equals("课节单价")){
            holder.leftColoredLine.setVisibility(View.VISIBLE);
            holder.leftColoredLine.setBackgroundColor(mContext.getResources().getColor(R.color.color_8f6fe9));
        }else if(mDatas.get(position).equals("总课节数")||mDatas.get(position).equals("课程总价")){
            holder.leftColoredLine.setVisibility(View.VISIBLE);
            holder.leftColoredLine.setBackgroundColor(mContext.getResources().getColor(R.color.color_59a0e5));
        }else{
            holder.leftColoredLine.setVisibility(View.GONE);
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        View mView;

        /**标题*/
        @Bind(R.id.tv_cell_title)
        public TextView title;
        /**内容*/
        @Bind(R.id.tv_right_content)
        public TextView content;
        /**箭头*/
        @Bind(R.id.iv_arrow)
        public ImageView arrowImg;
        /**输入框*/
        @Bind(R.id.etv_input)
        public EditText inputText;
        /**分割线*/
        @Bind(R.id.line)
        public View line;
        /**顶部空View*/
        @Bind(R.id.view_blank)
        public View blankView;
        /**左侧色彩条*/
        @Bind(R.id.left_colorful_line)
        public View leftColoredLine;


        public ItemViewHolder(View arg0, Context aContext) {
            super(arg0);
            ButterKnife.bind(this,arg0);
            mView = arg0;
        }
    }
}