package com.peixunfan.trainfans.Widgt.popupwindow;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.peixunfan.trainfans.Base.BaseAdapter;
import com.peixunfan.trainfans.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/11/26.
 */

public class TypeSelectAdapter  extends BaseAdapter<String> {

    String mCurrentType;

    public TypeSelectAdapter(Context context, ArrayList<String> datas, String currentType) {
        super(context, datas);
        mCurrentType = currentType;
    }

    @Override
    protected int getResourceId() {
        return R.layout.adapter_base_type_select_item;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemHolderViewHolder(View arg0) {
        return new ItemViewHolder(arg0, this.mContext);
    }

    @Override
    protected void onBindContentViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final ItemViewHolder aItemViewHolder = (ItemViewHolder) viewHolder;

        if (mItemClickListener != null) {
            aItemViewHolder.mView.setOnClickListener(v -> mItemClickListener.onItemClick(null, null, position, 0));
        }

        if(position == mDatas.size() - 1)
        {
            aItemViewHolder.bottomLine.setVisibility(View.GONE);
            aItemViewHolder.topblank.setVisibility(View.VISIBLE);
        }else{
            aItemViewHolder.topblank.setVisibility(View.GONE);
            aItemViewHolder.bottomLine.setVisibility(View.VISIBLE);
        }

        aItemViewHolder.mTypeTv.setText(mDatas.get(position));

        if (mDatas.get(position).equals(mCurrentType)){
            aItemViewHolder.mTypeTv.setTextColor(mContext.getResources().getColor(R.color.main_color));
        }else{
            aItemViewHolder.mTypeTv.setTextColor(mContext.getResources().getColor(R.color.color_a0a0a0));
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_type)
        TextView mTypeTv;
        @Bind(R.id.iv_bottom_line)
        View bottomLine;
        @Bind(R.id.rlv_topblank)
        View topblank;

        View mView;

        public ItemViewHolder(View arg0, Context aContext) {
            super(arg0);
            ButterKnife.bind(this,arg0);
            mView = arg0;
        }
    }
}

