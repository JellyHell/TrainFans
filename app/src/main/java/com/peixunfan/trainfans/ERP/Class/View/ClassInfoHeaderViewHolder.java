package com.peixunfan.trainfans.ERP.Class.View;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.peixunfan.trainfans.R;

/**
 * Created by chengyanfang on 2016/11/26.
 */

public class ClassInfoHeaderViewHolder   extends RecyclerView.ViewHolder {
    /**标题*/
    public RelativeLayout mTitleLayout;
    public TextView title;
    /**分割线*/
    public View line;
    public ImageView mAddImageView;

    public View blankView;


    public ClassInfoHeaderViewHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        mTitleLayout = (RelativeLayout)itemView.findViewById(R.id.rlv_setting_info);
        title = (TextView) itemView.findViewById(R.id.tv_cell_title);
        mAddImageView = (ImageView) itemView.findViewById(R.id.iv_add_view);
        blankView = itemView.findViewById(R.id.top_blankview);
        line = itemView.findViewById(R.id.line);
    }
}
