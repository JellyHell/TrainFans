package com.peixunfan.trainfans.ERP.PayoffMoney.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.peixunfan.trainfans.Base.BaseAdapter;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.Recovery.Model.Article;

import java.util.ArrayList;

/**
 * Created by chengyanfang on 2016/11/29.
 */

public class TeacherMoneyCountAdapter  extends BaseAdapter<Article> {

    public TeacherMoneyCountAdapter(Context context, ArrayList<Article> datas) {
        super(context, datas);
    }

    @Override
    protected int getResourceId() {
        return R.layout.adapter_teacher_moneycoumt_item;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemHolderViewHolder(View arg0) {
        return new TeacherMoneyCountViewHolder(arg0);
    }

    @Override
    protected void onBindContentViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final TeacherMoneyCountViewHolder aItemViewHolder = (TeacherMoneyCountViewHolder) viewHolder;

        if (mItemClickListener != null) {
            aItemViewHolder.mView.setOnClickListener(v -> mItemClickListener.onItemClick(null, null, position, 0));
        }
    }

}
