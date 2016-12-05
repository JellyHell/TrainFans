package com.peixunfan.trainfans.ERP.Teacher.Controller;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.infrastructure.net.RetrofitSingleton;
import com.infrastructure.utils.IntentUtil;
import com.peixunfan.trainfans.Api.ApiProvider;
import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.Base.BaseBlankHeaderView;
import com.peixunfan.trainfans.ERP.Courses.Controller.AddCouserAct;
import com.peixunfan.trainfans.ERP.Courses.Controller.EditCourseAct;
import com.peixunfan.trainfans.ERP.Teacher.View.TeacherAdapter;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.Recovery.Model.Article;
import com.peixunfan.trainfans.Recovery.Model.ArticleList;
import com.peixunfan.trainfans.Widgt.RefreshableRecyclerView.RefreshableRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;

/**
 * Created by chengyanfang on 2016/11/27.
 */

public class TeacherListAct  extends BaseActivity implements Observer<ArticleList> {

    @Bind(R.id.container_layout)
    RelativeLayout mContainer;

    RefreshableRecyclerView mRefreshableRecyclerView;

    TeacherAdapter mAdapter;
    ArrayList<Article> mTeacherList = new ArrayList();

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
        mCenterTitle.setText("全部老师");
        mRightManagerBt.setImageResource(R.drawable.icon_search_bt);
        mRightSecondManagerBt.setImageResource(R.drawable.icon_add_more);
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
            mTeacherList.clear();
        }
        mTeacherList.addAll(articleList.borrowList);

        setApapter();

        if (articleList.borrowList.size() < 10)
            mAdapter.canLoadMore(false);
        else
            mAdapter.canLoadMore(true);
    }


    private void setApapter(){
        if(mAdapter == null){
            mAdapter = new TeacherAdapter(this,mTeacherList);
            mAdapter.setOnItemClickListener((adapterView, view, i, l) -> IntentUtil.forwordToActivity(TeacherListAct.this,TeacherDetailAct.class));
            mAdapter.setLoadMoreListener(() -> {
                mPage++;
                loadData();
            });
            mRefreshableRecyclerView.setAdapter(mAdapter);

            mAdapter.setHeaderView(new BaseBlankHeaderView(this).getView());
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
        IntentUtil.forwordToActivity(this,AddTeacherAct.class);
    }
}
