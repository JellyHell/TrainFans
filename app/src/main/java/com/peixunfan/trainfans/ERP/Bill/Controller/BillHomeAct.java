package com.peixunfan.trainfans.ERP.Bill.Controller;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.infrastructure.net.RetrofitSingleton;
import com.infrastructure.utils.IntentUtil;
import com.peixunfan.trainfans.Api.ApiProvider;
import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.Base.BaseBlankHeaderView;
import com.peixunfan.trainfans.ERP.Bill.View.BillHomeHeaderView;
import com.peixunfan.trainfans.ERP.Class.Controller.AddClassAct;
import com.peixunfan.trainfans.ERP.Class.Controller.ClassHomeListAct;
import com.peixunfan.trainfans.ERP.Class.Controller.EditClassAct;
import com.peixunfan.trainfans.ERP.Class.View.ClassAdapter;
import com.peixunfan.trainfans.ERP.Home.View.BillHomeAdapter;
import com.peixunfan.trainfans.ERP.StudentList.Controller.StudentPaybackDetailAct;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.Recovery.Model.Article;
import com.peixunfan.trainfans.Recovery.Model.ArticleList;
import com.peixunfan.trainfans.Widgt.RefreshableRecyclerView.RefreshableRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;

/**
 * Created by chengyanfang on 2016/11/30.
 */

public class BillHomeAct   extends BaseActivity implements Observer<ArticleList> {

    @Bind(R.id.container_layout)
    RelativeLayout mContainer;

    RefreshableRecyclerView mRefreshableRecyclerView;

    BillHomeAdapter mAdapter;
    ArrayList<Article> mBillList = new ArrayList();

    BillHomeHeaderView mBillHomeHeaderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_erp_message_home);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle saveInstanceState) {
        super.initViews(saveInstanceState);
        mCenterTitle.setText("月账单");
        mRightManagerBt.setImageResource(R.drawable.icon_nopay);
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

        //设置自动刷新
        mRefreshableRecyclerView.autoRefresh();

        mBillHomeHeaderView = new BillHomeHeaderView(this);
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
            mBillList.clear();
        }
        mBillList.addAll(articleList.borrowList);

        setApapter();

        if (articleList.borrowList.size() < 10)
            mAdapter.canLoadMore(false);
        else
            mAdapter.canLoadMore(true);
    }


    private void setApapter(){
        if(mAdapter == null){
            mAdapter = new BillHomeAdapter(this,mBillList);
            mAdapter.setOnItemClickListener((adapterView, view, i, l) -> IntentUtil.forwordToActivity(BillHomeAct.this,StudentPaybackDetailAct.class));
            mAdapter.setLoadMoreListener(() -> {
                mPage++;
                loadData();
            });
            mRefreshableRecyclerView.setAdapter(mAdapter);

            mAdapter.setHeaderView(mBillHomeHeaderView.getView());
        }else {
            mAdapter.notifyDataSetChanged();
        }
    }


    @Override
    protected void onRightManagerBtClick() {
        super.onRightManagerBtClick();
    }

}
