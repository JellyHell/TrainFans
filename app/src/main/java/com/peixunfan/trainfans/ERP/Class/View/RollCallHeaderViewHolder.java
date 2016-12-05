package com.peixunfan.trainfans.ERP.Class.View;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.peixunfan.trainfans.R;

/**
 * Created by chengyanfang on 2016/11/27.
 */

public class RollCallHeaderViewHolder  extends RecyclerView.ViewHolder {
    public RelativeLayout managerLayout;


    public RelativeLayout titleLayout;


    public RelativeLayout blankView;

    public ImageView mAddStudentImg;

    public View line;

    public RollCallHeaderViewHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        blankView = (RelativeLayout) itemView.findViewById(R.id.rlv_blank_view);

        titleLayout = (RelativeLayout) itemView.findViewById(R.id.rlv_title_layout);

        managerLayout = (RelativeLayout) itemView.findViewById(R.id.rlv_manager_layout);

        mAddStudentImg = (ImageView) itemView.findViewById(R.id.iv_add_view);

        line = itemView.findViewById(R.id.line);
    }
}