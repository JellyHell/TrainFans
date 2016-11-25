package com.peixunfan.trainfans.Base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.peixunfan.trainfans.Widgt.loadMoreView.LoadMoreView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuLayout;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/22.
 */

public abstract class BaseSwipMenuAdapter <T>  extends SwipeMenuAdapter<RecyclerView.ViewHolder> {

    protected BaseSwipMenuAdapter() {
    }

    public interface RecyclerAdapterListener {
        public void OnLoadMore();
    }

    protected Context mContext;
    protected ArrayList<T> mDatas = new ArrayList<T>();
    private LayoutInflater mInflater;
    protected View mHeaderView;
    protected View mFooterView;
    private LoadMoreView mLoadMoreView;
    private boolean mCanLoadMore;
    private RecyclerAdapterListener mListener;
    protected AdapterView.OnItemClickListener mItemClickListener;
    private static final int HEADER = 0;
    private static final int ITEM = 1;
    private static final int FOOTER = 2;

    public BaseSwipMenuAdapter(Context context, ArrayList<T> datas) {
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
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case ITEM:
                //绑定SwipeMenuView
                View itemView = viewHolder.itemView;
                if (itemView instanceof SwipeMenuLayout) {
                    SwipeMenuLayout swipeMenuLayout = (SwipeMenuLayout) itemView;
                    int childCount = swipeMenuLayout.getChildCount();
                    for (int i = 0; i < childCount; i++) {
                        View childView = swipeMenuLayout.getChildAt(i);
                        if (childView instanceof SwipeMenuView) {
                            ((SwipeMenuView) childView).bindAdapterViewHolder(viewHolder);
                        }
                    }
                }
                onBindContentViewHolder(viewHolder, position - (mHeaderView == null ? 0 : 1));
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View convertView;
        switch (viewType) {
            case HEADER:
                return new RecyclerView.ViewHolder(mHeaderView) {
                };
            case ITEM:
                convertView = mInflater.inflate(getResourceId(), viewGroup, false);

                if (mSwipeMenuCreator != null) {
                    SwipeMenuLayout swipeMenuLayout = (SwipeMenuLayout) LayoutInflater.from(viewGroup.getContext()).inflate(com.peixunfan.androidlib.R.layout.yanzhenjie_item_default, viewGroup, false);

                    SwipeMenu swipeLeftMenu = new SwipeMenu(swipeMenuLayout, viewType);
                    SwipeMenu swipeRightMenu = new SwipeMenu(swipeMenuLayout, viewType);

                    mSwipeMenuCreator.onCreateMenu(swipeLeftMenu, swipeRightMenu, viewType);

                    int leftMenuCount = swipeLeftMenu.getMenuItems().size();
                    if (leftMenuCount > 0) {
                        SwipeMenuView swipeLeftMenuView = (SwipeMenuView) swipeMenuLayout.findViewById(com.peixunfan.androidlib.R.id.swipe_left);
                        swipeLeftMenuView.setOrientation(swipeLeftMenu.getOrientation());
                        swipeLeftMenuView.bindMenu(swipeLeftMenu, SwipeMenuRecyclerView.LEFT_DIRECTION);
                        swipeLeftMenuView.bindMenuItemClickListener(mSwipeMenuItemClickListener, swipeMenuLayout);
                    }

                    int rightMenuCount = swipeRightMenu.getMenuItems().size();
                    if (rightMenuCount > 0) {
                        SwipeMenuView swipeRightMenuView = (SwipeMenuView) swipeMenuLayout.findViewById(com.peixunfan.androidlib.R.id.swipe_right);
                        swipeRightMenuView.setOrientation(swipeRightMenu.getOrientation());
                        swipeRightMenuView.bindMenu(swipeRightMenu, SwipeMenuRecyclerView.RIGHT_DIRECTION);
                        swipeRightMenuView.bindMenuItemClickListener(mSwipeMenuItemClickListener, swipeMenuLayout);
                    }

                    if (leftMenuCount > 0 || rightMenuCount > 0) {
                        ViewGroup aViewGroup = (ViewGroup) swipeMenuLayout.findViewById(com.peixunfan.androidlib.R.id.swipe_content);
                        aViewGroup.addView(convertView);
                        convertView = swipeMenuLayout;
                    }
                }
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

    protected abstract RecyclerView.ViewHolder onCreateItemHolderViewHolder(View arg0);

    protected abstract void onBindContentViewHolder(RecyclerView.ViewHolder viewHolder, int position);

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
    }

    public void setFooterView(View footerView) {
        mFooterView = footerView;
    }

    public void canLoadMore(boolean canLoadMore) {
        mCanLoadMore = canLoadMore;
    }

    public void setLoadMoreListener(RecyclerAdapterListener pListener) {
        mListener = pListener;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener pClickListener) {
        mItemClickListener = pClickListener;
    }
}