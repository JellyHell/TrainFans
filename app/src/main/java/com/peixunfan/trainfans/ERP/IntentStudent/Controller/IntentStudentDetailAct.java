package com.peixunfan.trainfans.ERP.IntentStudent.Controller;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.infrastructure.net.RetrofitSingleton;
import com.infrastructure.utils.IntentUtil;
import com.peixunfan.trainfans.Api.ApiProvider;
import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.ERP.IntentStudent.View.IntentSutdentContactHeaderView;
import com.peixunfan.trainfans.ERP.StudentList.View.StudentContactAdapter;
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

public class IntentStudentDetailAct  extends BaseActivity implements Observer<ArticleList> {

    @Bind(R.id.container_layout)
    RelativeLayout mContainer;

    RefreshableRecyclerView mRefreshableRecyclerView;

    StudentContactAdapter mAdapter;
    ArrayList<Article> mContactList = new ArrayList();

    ArrayList<String> mTags = new ArrayList();

    IntentSutdentContactHeaderView mIntentSutdentContactHeaderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_erp_intentstudent_layout);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {
        mTags.add("小鲜肉");
        mTags.add("天才");
        mTags.add("天生神力");
        mTags.add("有才华的逗逼");
        mTags.add("土豪");
        mTags.add("钢琴世家");
        mTags.add("奥数第一名");
        mTags.add("天才");
        mTags.add("天才");
        mTags.add("天才");
        mTags.add("天才");
    }

    @Override
    protected void initViews(Bundle saveInstanceState) {
        super.initViews(saveInstanceState);
        mCenterTitle.setText("学员信息");
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

        mIntentSutdentContactHeaderView = new IntentSutdentContactHeaderView(this);
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
            mContactList.clear();
        }
        mContactList.addAll(articleList.borrowList);

        setApapter();

        if (articleList.borrowList.size() < 10)
            mAdapter.canLoadMore(false);
        else
            mAdapter.canLoadMore(true);
    }


    private void setApapter(){
        if(mAdapter == null){
            mAdapter = new StudentContactAdapter(this,mContactList);
            mAdapter.setLoadMoreListener(() -> {
                mPage++;
                loadData();
            });
            mRefreshableRecyclerView.setAdapter(mAdapter);
            mAdapter.setHeaderView(mIntentSutdentContactHeaderView.getView());
            mIntentSutdentContactHeaderView.setTagView(mTags,true);
            mIntentSutdentContactHeaderView.setTagClickCallback(index -> {
                mTags.remove(index);
                mIntentSutdentContactHeaderView.setTagView(mTags,false);
            });
        }else {
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void cancleSearch() {
        super.cancleSearch();

        //恢复数据
    }

    @Override
    protected void onRightManagerBtClick() {
        super.onRightManagerBtClick();
        showSearchView(true);
    }

    @Override
    protected void onRightSecondManagerBtClick() {
        super.onRightSecondManagerBtClick();
        IntentUtil.forwordToActivity(this,AddIntentStudentAct.class);
    }
}
