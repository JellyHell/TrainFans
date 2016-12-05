package com.peixunfan.trainfans.ERP.Teacher.View;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.peixunfan.trainfans.R;

/**
 * Created by chengyanfang on 2016/11/28.
 */

public class TeacherInfoViewHolder  extends RecyclerView.ViewHolder {
    /**设置Cell*/
    public RelativeLayout mSettingLayout;
    /**标题*/
    public TextView title;
    /**箭头*/
    public ImageView arrowImg;
    /**输入框*/
    public EditText inputText;
    /**右侧文本*/
    public TextView content;
    /**分割线*/
    public View line;

    public View leftColoredLine;


    public TeacherInfoViewHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        mSettingLayout = (RelativeLayout) itemView.findViewById(R.id.rlv_setting_info);
        title = (TextView) itemView.findViewById(R.id.tv_cell_title);
        line = itemView.findViewById(R.id.line);
        arrowImg = (ImageView) itemView.findViewById(R.id.iv_arrow);
        inputText = (EditText) itemView.findViewById(R.id.etv_input);
        content = (TextView) itemView.findViewById(R.id.tv_right_content);
        leftColoredLine = itemView.findViewById(R.id.left_colorful_line);
    }
}
