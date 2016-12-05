package com.peixunfan.trainfans.UserCenter.InstitutionInfo.Controller;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.infrastructure.net.RetrofitSingleton;
import com.infrastructure.utils.IntentUtil;
import com.peixunfan.trainfans.Api.ApiProvider;
import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.Recovery.Model.Article;
import com.peixunfan.trainfans.Recovery.Model.ArticleList;
import com.peixunfan.trainfans.UserCenter.InstitutionInfo.View.BuySmsRecordAdapter;
import com.peixunfan.trainfans.Widgt.RefreshableRecyclerView.RefreshableRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;

/**
 * Created by chengyanfang on 2016/12/1.
 */

public class BuySmsRecordAct  extends BaseActivity implements Observer<ArticleList> {

    @Bind(R.id.container_layout)
    RelativeLayout mContainer;

    @Bind(R.id.rlv_buy_sms)
    RelativeLayout mBuyLayout;

    @Bind(R.id.tv_basetitle_cetener)
    TextView mCenterTitle;

    RefreshableRecyclerView mRefreshableRecyclerView;
    BuySmsRecordAdapter mAdapter;
    ArrayList<Article> mRecordList = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_buysms_record_layout);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle saveInstanceState) {
        mCenterTitle.setText("购买记录");
        showBackButton();

        //设置刷新列表
        mRefreshableRecyclerView = new RefreshableRecyclerView(this, new RefreshableRecyclerView.RefreshCallback() {
            @Override
            public void refresh() {
                mPage = 1;
                loadData();
            }

            @Override
            public void loadMore() {
                mPage = mPage + 1;
                loadData();
            }
        });
        mContainer.addView(mRefreshableRecyclerView.getView());

        mRefreshableRecyclerView.autoRefresh();

        mBuyLayout.setOnClickListener(v -> IntentUtil.forwordToActivity(this, BuySmsAct.class));
    }

    @Override
    protected void loadData() {
        ApiProvider.getInstance().getRecommendBorrow(this,String.valueOf(mPage));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onCompleted() {
        mRefreshableRecyclerView.setRefreshing(false);
    }

    @Override
    public void onError(Throwable e) {
        RetrofitSingleton.disposeFailureInfo(e,this);
    }

    @Override
    public void onNext(ArticleList articleList) {
        if(mPage == 1){
            mRecordList.clear();
        }
        mRecordList.addAll(articleList.borrowList);

        setApapter();

        if (articleList.borrowList.size() < 10)
            mAdapter.canLoadMore(false);
        else
            mAdapter.canLoadMore(true);
    }


    private void setApapter(){
        if(mAdapter == null){
            mAdapter = new BuySmsRecordAdapter(this,mRecordList);
            mAdapter.setOnItemClickListener((adapterView, view, i, l) -> {
            });
            mAdapter.setLoadMoreListener(() -> {
                mPage++;
                loadData();
            });
            mRefreshableRecyclerView.setAdapter(mAdapter);
        }else {
            mAdapter.notifyDataSetChanged();
        }
    }
}
