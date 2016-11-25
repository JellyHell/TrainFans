package com.peixunfan.trainfans.Widgt.RefreshableRecyclerView;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.infrastructure.utils.AppUtil;
import com.infrastructure.utils.StringUtil;
import com.peixunfan.trainfans.Base.BaseAdapter;
import com.peixunfan.trainfans.Base.BaseSwipMenuAdapter;
import com.peixunfan.trainfans.R;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import butterknife.Bind;
import butterknife.ButterKnife;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.AnimationAdapter;

/**
 * Created by Administrator on 2016/8/15.
 */
public class RefreshableRecyclerView {
    @Bind(R.id.recyclerview)
    SwipeMenuRecyclerView mRecyclerview;

    @Bind(R.id.swiprefresh)
    SwipeRefreshLayout mSwiprefresh;

    @Bind(R.id.rlv_banner_layout)
    RelativeLayout mBannerView;

    @Bind(R.id.tv_money_count)
    TextView mMoneyCount;

    @Bind(R.id.iv_basetitle_leftimg)
    ImageView mLeftImg;

    @Bind(R.id.iv_basetitle_rightimg)
    ImageView mRightImg;

    Context mContext;
    View mView;
    RefreshCallback mRefreshCallback;

    public RefreshableRecyclerView(Context pContext,RefreshCallback aRefreshCallback) {
        mContext = pContext;
        mRefreshCallback = aRefreshCallback;
        initView();
    }

    private void initView() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.pxf_recyclerview_refresh_layout, null);
        ButterKnife.bind(this,mView);

        //设置下拉控件
        mSwiprefresh.setColorSchemeResources(R.color.main_color);

        mSwiprefresh.setOnRefreshListener(
                () -> {
                    mSwiprefresh.postDelayed(()->{
                        if (mRefreshCallback != null)
                        {
                            mRefreshCallback.refresh();
                        }
                    },500);
                });

        //设置mRecyclerview
        mRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
    }

    public View getView() {
        return mView;
    }

    public void setAdapter(BaseAdapter adapter)
    {
        adapter.setLoadMoreListener(()->{
            if (mRefreshCallback != null)
            {
                mRefreshCallback.loadMore();
            }
        });
        AnimationAdapter animationAdapter = new AlphaInAnimationAdapter(adapter);
        mRecyclerview.setAdapter(animationAdapter);
    }

    public void setAdapter(BaseSwipMenuAdapter adapter)
    {
        adapter.setLoadMoreListener(()->{
            if (mRefreshCallback != null)
            {
                mRefreshCallback.loadMore();
            }
        });
        mRecyclerview.setAdapter(adapter);
    }

    public SwipeMenuRecyclerView getRecyclerview() {
        return mRecyclerview;
    }

    /**
     * 设置是否刷新
     * */
    public void setRefreshing(boolean isRefreshing)
    {
        mSwiprefresh.setRefreshing(false);
    }

    /**
     * 自动刷新
     * */
    public void autoRefresh(){
        mSwiprefresh.post(new Runnable() {
            @Override
            public void run() {
                mSwiprefresh.setRefreshing(true);
            }
        });
    }

    /**
     * 设置显示16宫格
     * */
    public void setGridLayout(){
        mRecyclerview.setLayoutManager(new GridLayoutManager(mContext,4));
    }

    /**
     * 回调接口
     * */
    public interface RefreshCallback{
        void refresh();
        void loadMore();
    }

    /**
     * 设置Banner显示
     * */
    public void setBannerView(){
        mBannerView.setVisibility(View.VISIBLE);

        mLeftImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnHomeBanerTitleMenuClick != null){
                    mLeftImg.setVisibility(View.GONE);
                    mOnHomeBanerTitleMenuClick.onLeftMenuBtClick();
                }
            }
        });

        mRightImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnHomeBanerTitleMenuClick != null){
                    mOnHomeBanerTitleMenuClick.onRightMenuBtClick();
                }
            }
        });
    }

    public void showLeftBt(){
        if(mLeftImg != null){
            mLeftImg.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置Banner数据
     * */
    public void setBannerData() {
        SpannableString yesterdaySpanString = new SpannableString(mContext.getString(R.string.money_lable )+StringUtil.getFormatedFloatString("100")+"元");
        AbsoluteSizeSpan span = new AbsoluteSizeSpan((int) AppUtil.sp2px(mContext, 44));
        yesterdaySpanString.setSpan(span, 1, StringUtil.getFormatedFloatString("100").length()-2, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mMoneyCount.setText(yesterdaySpanString);
    }


    /**
     * TitleMenuBtClick
     * */
    public interface OnHomeBanerTitleMenuClick{
        public void onLeftMenuBtClick();
        public void onRightMenuBtClick();
    }

    public OnHomeBanerTitleMenuClick mOnHomeBanerTitleMenuClick;

    public void setmOnHomeBanerTitleMenuClick(OnHomeBanerTitleMenuClick mOnHomeBanerTitleMenuClick) {
        this.mOnHomeBanerTitleMenuClick = mOnHomeBanerTitleMenuClick;
    }
}
