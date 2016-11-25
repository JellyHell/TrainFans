package com.peixunfan.trainfans.ERP.StudentList.Controller;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.infrastructure.net.RetrofitSingleton;
import com.peixunfan.trainfans.Api.ApiProvider;
import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.ERP.StudentList.View.StudentPaybackDetailAdapter;
import com.peixunfan.trainfans.ERP.StudentList.View.StudentPaybackDetailFooter;
import com.peixunfan.trainfans.ERP.StudentList.View.StudentPaybackDetailHeader;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.Recovery.Model.Article;
import com.peixunfan.trainfans.Recovery.Model.ArticleList;
import com.peixunfan.trainfans.Widgt.RefreshableRecyclerView.RefreshableRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;

/**
 * Created by Administrator on 2016/11/24.
 */

public class StudentPaybackDetailAct  extends BaseActivity implements Observer<ArticleList> {

    @Bind(R.id.container_layout)
    RelativeLayout mContainer;

    @Bind(R.id.tv_basetitle_cetener)
    TextView mCenterTitle;

    RefreshableRecyclerView mRefreshableRecyclerView;
    StudentPaybackDetailAdapter mAdapter;
    ArrayList<Article> mBuyedClassList = new ArrayList();

    StudentPaybackDetailHeader mStudentPaybackDetailHeader;
    StudentPaybackDetailFooter mStudentPaybackDetailFooter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_erp_student_payback_layout);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {
        mBuyedClassList.add(new Article());
        mBuyedClassList.add(new Article());
    }

    @Override
    protected void initViews(Bundle saveInstanceState) {
        mCenterTitle.setText("退费管理");
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

        mStudentPaybackDetailHeader = new StudentPaybackDetailHeader(this);
        mStudentPaybackDetailFooter = new StudentPaybackDetailFooter(this);
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
        mRefreshableRecyclerView.setRefreshing(false);
        RetrofitSingleton.disposeFailureInfo(e,this);
    }

    @Override
    public void onNext(ArticleList articleList) {
        setApapter();
    }


    private void setApapter(){
        if(mAdapter == null){
            mAdapter = new StudentPaybackDetailAdapter(this,mBuyedClassList);
            mAdapter.setOnItemClickListener((adapterView, view, i, l) -> {
            });
            mAdapter.setLoadMoreListener(() -> {
                mPage++;
                loadData();
            });
            mRefreshableRecyclerView.setAdapter(mAdapter);

            mAdapter.setHeaderView(mStudentPaybackDetailHeader.getView());
            mAdapter.setFooterView(mStudentPaybackDetailFooter.getView());
        }else {
            mAdapter.notifyDataSetChanged();
        }
    }
}

