package com.peixunfan.trainfans.Base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.peixunfan.trainfans.R;

/**
 * Created by Administrator on 2016/11/21.
 */

public class PublicContentViewHolder  extends RecyclerView.ViewHolder {
    /**设置Cell*/
    public RelativeLayout mSettingLayout;
    /**标题*/
    public TextView title;
    /**箭头*/
    public ImageView arrowImg;
    /**学员列表选择*/
    public TextView selectStudentBt;
    /**输入框*/
    public EditText inputText;
    /**右侧文本*/
    public TextView content;
    /**分割线*/
    public View line;


    /**课程*/
    public RelativeLayout mClassInfoLayout;
    public ImageView mClassTypeImage;
    public TextView mClassName;
    public TextView mClassInfo;
    public TextView mClassPrice;
    public View mClassLine;


    public PublicContentViewHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        mSettingLayout = (RelativeLayout) itemView.findViewById(R.id.rlv_setting_info);
        title = (TextView) itemView.findViewById(R.id.tv_cell_title);
        line = itemView.findViewById(R.id.line);
        arrowImg = (ImageView) itemView.findViewById(R.id.iv_arrow);
        selectStudentBt = (TextView) itemView.findViewById(R.id.iv_student_list);
        inputText = (EditText) itemView.findViewById(R.id.etv_input);
        content = (TextView) itemView.findViewById(R.id.tv_right_content);


        mClassInfoLayout = (RelativeLayout) itemView.findViewById(R.id.rlv_class_info);
        mClassTypeImage = (ImageView) itemView.findViewById(R.id.iv_class_type);
        mClassName = (TextView) itemView.findViewById(R.id.tv_class_name);
        mClassInfo = (TextView) itemView.findViewById(R.id.tv_class_detail_info);
        mClassPrice = (TextView) itemView.findViewById(R.id.tv_class_money);
        mClassLine = itemView.findViewById(R.id.class_line);
    }
}
