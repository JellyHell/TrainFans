package com.peixunfan.trainfans.ERP.PayoffMoney.View;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.peixunfan.trainfans.R;

import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/11/29.
 */

public class TeacherMoneyCountViewHolder  extends RecyclerView.ViewHolder {

    View mView;

    public View line;

    public TeacherMoneyCountViewHolder(View arg0) {
        super(arg0);
        ButterKnife.bind(this,arg0);
        mView = arg0;

        initView();
    }

    private void initView() {
        line = itemView.findViewById(R.id.bottom_devider);
    }
}