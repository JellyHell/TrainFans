package com.peixunfan.trainfans.Base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.peixunfan.trainfans.Widgt.loadMoreView.LoadMoreView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/8/12.
 */
public abstract class BaseAdapter <T>  extends RecyclerView.Adapter<ViewHolder> {

    protected BaseAdapter() {
    }

    public interface RecyclerAdapterListener {
        void OnLoadMore();
    }

    public interface RecyclerRefreshAdapterListener {
        void OnRefreshMore();
    }

    public interface OnRecyclerViewScroll{
        void onScroll(int positon);
    }

    protected Context mContext;
    protected ArrayList<T> mDatas = new ArrayList<T>();
    private LayoutInflater mInflater;
    protected View mHeaderView;
    protected View mFooterView;
    private LoadMoreView mLoadMoreView;
    private boolean mCanLoadMore;
    private boolean mRefreshMore;
    private RecyclerAdapterListener mListener;
    private RecyclerRefreshAdapterListener mRListener;
    private OnRecyclerViewScroll mOnRecyclerViewScroll;
    protected AdapterView.OnItemClickListener mItemClickListener;
    private static final int HEADER = 0;
    private static final int ITEM = 1;
    private static final int FOOTER = 2;

    public BaseAdapter(Context context, ArrayList<T> datas) {
        mContext = context;
        mDatas = datas;
        mInflater = LayoutInflater.from(context);
        mLoadMoreView = new LoadMoreView(context);
    }

    public void setData(ArrayList<T> datas) {
        mDatas = datas;
    }

    @Override
    public int getItemCount() {
        int count = 0;
        if (mHeaderView != null)
            count++;
        if (mFooterView != null)
            count++;
        if (mCanLoadMore)
            count++;
        return mDatas.size() + count;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case ITEM:
                onBindContentViewHolder(viewHolder, position - (mHeaderView == null ? 0 : 1));

                if (mOnRecyclerViewScroll!=null){
                    mOnRecyclerViewScroll.onScroll(position);
                }

                if(mRefreshMore && this.mRListener!=null && position == 0){
                    mRefreshMore = false;
                    mRListener.OnRefreshMore();
                }

                break;
            case FOOTER:
                if (mCanLoadMore && mListener != null)
                    mListener.OnLoadMore();
                break;
            default:
                break;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View convertView;
        switch (viewType) {
            case HEADER:
                return new RecyclerView.ViewHolder(mHeaderView) {
                };
            case ITEM:
                convertView = mInflater.inflate(getResourceId(), viewGroup, false);
                return onCreateItemHolderViewHolder(convertView);
            case FOOTER:
                if (mFooterView != null)
                    return new RecyclerView.ViewHolder(mFooterView) {
                    };
                return new RecyclerView.ViewHolder(mLoadMoreView.getView()) {
                };
            default:
                break;
        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && mHeaderView != null)
            return HEADER;
        if (position == getItemCount() - 1)
        {
            if (mCanLoadMore||mFooterView!=null)
                return FOOTER;
        }
        return ITEM;
    }

    protected abstract int getResourceId();

    protected abstract ViewHolder onCreateItemHolderViewHolder(View arg0);

    protected abstract void onBindContentViewHolder(ViewHolder viewHolder, int position);

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
    }

    public void setFooterView(View footerView) {
        mFooterView = footerView;
    }

    public void canLoadMore(boolean canLoadMore) {
        mCanLoadMore = canLoadMore;
    }

    public void canRefreshMore(boolean refreshMore) {
        mRefreshMore = refreshMore;
    }

    public void setLoadMoreListener(RecyclerAdapterListener pListener) {
        mListener = pListener;
    }

    public void setmOnRecyclerViewScroll(OnRecyclerViewScroll onRecyclerViewScroll) {
        this.mOnRecyclerViewScroll = onRecyclerViewScroll;
    }

    public void setRefreshMoreListener(RecyclerRefreshAdapterListener recyclerRefreshAdapterListener){
        this.mRListener = recyclerRefreshAdapterListener;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener pClickListener) {
        mItemClickListener = pClickListener;
    }
}