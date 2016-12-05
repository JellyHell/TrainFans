package com.peixunfan.trainfans.Login.View;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.peixunfan.trainfans.R;

/**
 * Created by chengyanfang on 2016/11/28.
 */

public class ApplyAccountHeaderHolder extends RecyclerView.ViewHolder {
    RelativeLayout mNormalInfoLayout;
    public TextView titleView;

    RelativeLayout mSectionheaderlaout;

    RelativeLayout mByPersonalLayout;
    View mLeftIndicator;
    TextView mLeftTitle;

    RelativeLayout mByCompanyLayout;
    View mRightIndicator;
    TextView mRightTitle;

    public ApplyAccountHeaderHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        titleView = (TextView) itemView.findViewById(R.id.tv_section_title);

        mNormalInfoLayout = (RelativeLayout) itemView.findViewById(R.id.rlv_normal_info);

        mSectionheaderlaout = (RelativeLayout) itemView.findViewById(R.id.rlv_section_header);


        mByPersonalLayout = (RelativeLayout) itemView.findViewById(R.id.rlv_left_title);
        mLeftIndicator = itemView.findViewById(R.id.view_left_indicator);
        mLeftTitle = (TextView)itemView.findViewById(R.id.tv_left_title);


        mByCompanyLayout = (RelativeLayout) itemView.findViewById(R.id.rlv_right_title);
        mRightIndicator = itemView.findViewById(R.id.view_right_indicator);
        mRightTitle = (TextView)itemView.findViewById(R.id.tv_right_title);
    }
}