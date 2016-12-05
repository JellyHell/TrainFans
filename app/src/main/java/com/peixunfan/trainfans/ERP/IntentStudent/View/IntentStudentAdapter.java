package com.peixunfan.trainfans.ERP.IntentStudent.View;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.infrastructure.utils.IntentUtil;
import com.peixunfan.trainfans.ERP.SignUp.Controller.SignUpAct;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.Recovery.Model.Article;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/11/30.
 */

public class IntentStudentAdapter extends IntentStudentSwipeMenuAdapter<Article> {

    public IntentStudentAdapter(Context context, ArrayList<Article> datas) {
        super(context, datas);
    }

    @Override
    protected int getResourceId() {
        return R.layout.adapter_intent_studentlist_item;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemHolderViewHolder(View arg0) {
        return new ItemViewHolder(arg0, this.mContext);
    }

    @Override
    protected void onBindContentViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final ItemViewHolder aItemViewHolder = (ItemViewHolder) viewHolder;
        // 点击事件
        if (mItemClickListener != null) {
            aItemViewHolder.mCellView.setOnClickListener(v -> mItemClickListener.onItemClick(null, null, position, 0));
        }

        if(position%2 == 1){
            aItemViewHolder.mContactView.setVisibility(View.GONE);
        }else {
            aItemViewHolder.mContactView.setVisibility(View.VISIBLE);
        }

        aItemViewHolder.mSignUpImg.setOnClickListener(v -> IntentUtil.forwordToActivity(mContext, SignUpAct.class));
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        View mView;

        @Bind(R.id.iv_sign_up)
        ImageView mSignUpImg;

        @Bind(R.id.rlv_contact)
        RelativeLayout mContactView;

        @Bind(R.id.cell_item_view)
        CardView mCellView;
        public ItemViewHolder(View arg0, Context aContext) {
            super(arg0);
            ButterKnife.bind(this,arg0);
            mView = arg0;
        }
    }
}