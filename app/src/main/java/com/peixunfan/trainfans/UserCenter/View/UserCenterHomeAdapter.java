package com.peixunfan.trainfans.UserCenter.View;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.peixunfan.trainfans.Base.BaseAdapter;
import com.peixunfan.trainfans.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/9.
 */

public class UserCenterHomeAdapter extends BaseAdapter<String> {

    public UserCenterHomeAdapter(Context context, ArrayList<String> datas) {
        super(context, datas);
    }

    @Override
    protected int getResourceId() {
        return R.layout.adapter_usercenter_item;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemHolderViewHolder(View arg0) {
        return new UserCenterHomeAdapter.ItemViewHolder(arg0, this.mContext);
    }

    @Override
    protected void onBindContentViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final UserCenterHomeAdapter.ItemViewHolder aItemViewHolder = (UserCenterHomeAdapter.ItemViewHolder) viewHolder;

        aItemViewHolder.mCellTitle.setText(mDatas.get(position));

        switch (position){
            case 0:
                aItemViewHolder.mCellIcon.setBackgroundResource(R.drawable.icon_scanner);
                aItemViewHolder.mBlankView.setVisibility(View.GONE);
                break;
            case 1:
                aItemViewHolder.mCellIcon.setBackgroundResource(R.drawable.icon_ercode);
                aItemViewHolder.mBlankView.setVisibility(View.VISIBLE);
                break;
            case 2:
                aItemViewHolder.mCellIcon.setBackgroundResource(R.drawable.icon_helper);
                aItemViewHolder.mBlankView.setVisibility(View.GONE);
                break;
            case 3:
                aItemViewHolder.mCellIcon.setBackgroundResource(R.drawable.icon_setting);
                aItemViewHolder.mBlankView.setVisibility(View.GONE);
                break;
        }

        // 点击事件
        if (mItemClickListener != null) {
            aItemViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(null, null, position, 0);
                }
            });
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_cell_title)
        TextView mCellTitle;
        @Bind(R.id.img_cell_icon)
        ImageView mCellIcon;
        @Bind(R.id.blank_view)
        View mBlankView;

        View mView;

        public ItemViewHolder(View arg0, Context aContext) {
            super(arg0);
            ButterKnife.bind(this,arg0);
            mView = arg0;
        }
    }
}

