package com.peixunfan.trainfans.Base;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.peixunfan.trainfans.R;


/**
 * Created by Administrator on 2016/11/21.
 */

public class SectionHeaderHolder extends RecyclerView.ViewHolder {
    public TextView titleView;

    public SectionHeaderHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
                titleView = (TextView) itemView.findViewById(R.id.tv_section_title);
    }
}