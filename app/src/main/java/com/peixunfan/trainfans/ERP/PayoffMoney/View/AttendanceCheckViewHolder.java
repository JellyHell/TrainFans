package com.peixunfan.trainfans.ERP.PayoffMoney.View;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.peixunfan.trainfans.R;

/**
 * Created by chengyanfang on 2016/11/29.
 */

public class AttendanceCheckViewHolder extends RecyclerView.ViewHolder {

    public View line;

    public CardView mCardView;

    public AttendanceCheckViewHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        line = itemView.findViewById(R.id.line);
        mCardView = (CardView)itemView.findViewById(R.id.cell_item_view);
    }
}
