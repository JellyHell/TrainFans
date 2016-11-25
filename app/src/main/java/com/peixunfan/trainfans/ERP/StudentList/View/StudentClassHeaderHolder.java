package com.peixunfan.trainfans.ERP.StudentList.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.peixunfan.trainfans.R;

/**
 * Created by Administrator on 2016/11/23.
 */

public class StudentClassHeaderHolder  extends RecyclerView.ViewHolder {

    ImageView mSelectClassImg;
    Context mContext;

    public StudentClassHeaderHolder(View itemView, Context context) {
        super(itemView);
        mContext = context;
        initView();
    }

    private void initView() {
        mSelectClassImg = (ImageView)itemView.findViewById(R.id.iv_selece_class);
    }
}
