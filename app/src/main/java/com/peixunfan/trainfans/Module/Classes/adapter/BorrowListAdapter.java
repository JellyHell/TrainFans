package com.peixunfan.trainfans.Module.Classes.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.infrastructure.ui.ImageLoader.ImageLoader;
import com.peixunfan.trainfans.Base.BaseAdapter;
import com.peixunfan.trainfans.Recovery.Model.Article;
import com.peixunfan.trainfans.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/8/22.
 */
public class BorrowListAdapter extends BaseAdapter<Article> {

    public BorrowListAdapter(Context context, ArrayList<Article> datas) {
        super(context, datas);
    }

    @Override
    protected int getResourceId() {
        return R.layout.adapter_borrowlist_item;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemHolderViewHolder(View arg0) {
        return new ItemViewHolder(arg0, this.mContext);
    }

    @Override
    protected void onBindContentViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final ItemViewHolder aItemViewHolder = (ItemViewHolder) viewHolder;

        ImageLoader.progressiveLoad(mDatas.get(position).smallIcon,aItemViewHolder.mSimpleDraweeView);

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

        @Bind(R.id.display_image)
        SimpleDraweeView mSimpleDraweeView;

        View mView;

        public ItemViewHolder(View arg0, Context aContext) {
            super(arg0);
            ButterKnife.bind(this,arg0);
            mView = arg0;
        }
    }
}

