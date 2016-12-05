package com.peixunfan.trainfans.ERP.AttendClassRecord.View;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.peixunfan.trainfans.R;

/**
 * Created by chengyanfang on 2016/11/28.
 */

public class AttendClassHomeViewHolder  extends RecyclerView.ViewHolder {

    public View line;

    public AttendClassHomeViewHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        line = itemView.findViewById(R.id.line);
    }
}
