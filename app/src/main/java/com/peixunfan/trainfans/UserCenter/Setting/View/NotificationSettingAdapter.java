package com.peixunfan.trainfans.UserCenter.Setting.View;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.peixunfan.trainfans.Base.BaseAdapter;
import com.peixunfan.trainfans.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/12/2.
 */

public class NotificationSettingAdapter   extends BaseAdapter<String> {

    public NotificationSettingAdapter(Context context, ArrayList<String> datas) {
        super(context, datas);
    }

    @Override
    protected int getResourceId() {
        return R.layout.adapter_setting_item;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemHolderViewHolder(View arg0) {
        return new ItemViewHolder(arg0, this.mContext);
    }

    @Override
    protected void onBindContentViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final ItemViewHolder aItemViewHolder = (ItemViewHolder) viewHolder;

        aItemViewHolder.mCellTitle.setText(mDatas.get(position));

        switch (position){
            case 0:
                aItemViewHolder.mBlankView.setVisibility(View.VISIBLE);
                aItemViewHolder.mLine.setVisibility(View.VISIBLE);
                break;
            case 1:
                aItemViewHolder.mBlankView.setVisibility(View.GONE);
                aItemViewHolder.mLine.setVisibility(View.VISIBLE);
                break;
            case 2:
                aItemViewHolder.mBlankView.setVisibility(View.GONE);
                aItemViewHolder.mLine.setVisibility(View.VISIBLE);
                break;
            case 3:
                aItemViewHolder.mBlankView.setVisibility(View.GONE);
                aItemViewHolder.mLine.setVisibility(View.GONE);
                break;
        }

        // 点击事件
        if (mItemClickListener != null) {
            aItemViewHolder.mCardView.setOnClickListener(v -> mItemClickListener.onItemClick(null, null, position, 0));
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_setting_name)
        TextView mCellTitle;
        @Bind(R.id.blank_view)
        View mBlankView;
        @Bind(R.id.line)
        View mLine;
        @Bind(R.id.cell_item_view)
        CardView mCardView;

        View mView;

        public ItemViewHolder(View arg0, Context aContext) {
            super(arg0);
            ButterKnife.bind(this,arg0);
            mView = arg0;
        }
    }
}

