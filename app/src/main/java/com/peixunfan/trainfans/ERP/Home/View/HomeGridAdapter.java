package com.peixunfan.trainfans.ERP.Home.View;

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

public class HomeGridAdapter extends BaseAdapter<String> {

    public HomeGridAdapter(Context context, ArrayList<String> datas) {
        super(context, datas);
    }

    @Override
    protected int getResourceId() {
        return R.layout.adapter_homelist_item;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemHolderViewHolder(View arg0) {
        return new HomeGridAdapter.ItemViewHolder(arg0, this.mContext);
    }

    @Override
    protected void onBindContentViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final HomeGridAdapter.ItemViewHolder aItemViewHolder = (HomeGridAdapter.ItemViewHolder) viewHolder;

        /**
         * 标题
         * */
        aItemViewHolder.mCellTitle.setText(mDatas.get(position));

        /**
         * icon
         * */
        switch (position){
            case 0:
            {
                aItemViewHolder.mCellIcon.setBackgroundResource(R.drawable.icon_erp_home1);
                break;
            }
            case 1:
            {
                aItemViewHolder.mCellIcon.setBackgroundResource(R.drawable.icon_erp_home2);
                break;
            }
            case 2:
            {
                aItemViewHolder.mCellIcon.setBackgroundResource(R.drawable.icon_erp_home3);
                break;
            }
            case 3:
            {
                aItemViewHolder.mCellIcon.setBackgroundResource(R.drawable.icon_erp_home4);
                break;
            }
            case 4:
            {
                aItemViewHolder.mCellIcon.setBackgroundResource(R.drawable.icon_erp_home5);
                break;
            }
            case 5:
            {
                aItemViewHolder.mCellIcon.setBackgroundResource(R.drawable.icon_erp_home6);
                break;
            }
            case 6:
            {
                aItemViewHolder.mCellIcon.setBackgroundResource(R.drawable.icon_erp_home7);
                break;
            }
            case 7:
            {
                aItemViewHolder.mCellIcon.setBackgroundResource(R.drawable.icon_erp_home8);
                break;
            }
            case 8:
            {
                aItemViewHolder.mCellIcon.setBackgroundResource(R.drawable.icon_erp_home9);
                break;
            }
            case 9:
            {
                aItemViewHolder.mCellIcon.setBackgroundResource(R.drawable.icon_erp_home10);
                break;
            }
            case 10:
            {
                aItemViewHolder.mCellIcon.setBackgroundResource(R.drawable.icon_erp_home11);
                break;
            }

        }

        // 点击时间
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

        View mView;

        public ItemViewHolder(View arg0, Context aContext) {
            super(arg0);
            ButterKnife.bind(this,arg0);
            mView = arg0;
        }
    }
}