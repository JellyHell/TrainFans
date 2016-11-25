package com.peixunfan.trainfans.ERP.StudentList.Controller;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.infrastructure.net.RetrofitSingleton;
import com.peixunfan.trainfans.Api.ApiProvider;
import com.peixunfan.trainfans.Base.BaseFragment;
import com.peixunfan.trainfans.ERP.StudentList.View.StudentContactAdapter;
import com.peixunfan.trainfans.ERP.StudentList.View.StudentContactHeaderView;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.Recovery.Model.Article;
import com.peixunfan.trainfans.Recovery.Model.ArticleList;
import com.peixunfan.trainfans.Widgt.RefreshableRecyclerView.RefreshableRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;

/**
 * Created by Administrator on 2016/11/23.
 */

public class StudentContactRecordFragment  extends BaseFragment implements Observer<ArticleList>
{
    @Bind(R.id.container_layout)
    RelativeLayout mContainer;

    RefreshableRecyclerView mRefreshableRecyclerView;

    StudentContactAdapter mAdapter;
    ArrayList<Article> mArticleList = new ArrayList();

    StudentContactHeaderView mHeaderView;

    /**
     * 创建页面
     * */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        if (mView == null)
        {
            mView = inflater.inflate(R.layout.fragment_contact_record_layout, container, false);
            ButterKnife.bind(this,mView);
        }
        isCreateView = true;
        return mView;
    }

    /**
     * 销毁页面
     * */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    @Override
    protected void initView() {
        //设置刷新列表
        mRefreshableRecyclerView = new RefreshableRecyclerView(getActivity(), new RefreshableRecyclerView.RefreshCallback() {
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

        mHeaderView = new StudentContactHeaderView(getActivity());
    }

    @Override
    protected void lazyLoad() {
        mRefreshableRecyclerView.autoRefresh();
        loadData();
    }

    private void loadData()
    {
        ApiProvider.getInstance().getRecommendBorrow(this,String.valueOf(mPage));

        setApapter();
    }

    private void setApapter(){
        if(mAdapter == null){
            mAdapter = new StudentContactAdapter(getActivity(),mArticleList);
            mAdapter.setOnItemClickListener((adapterView, view, i, l) -> {
            });
            mAdapter.setLoadMoreListener(() -> {
                mPage++;
                loadData();
            });
            mRefreshableRecyclerView.setAdapter(mAdapter);
            mAdapter.setHeaderView(mHeaderView.getView());
        }else {
            new Handler().post(() -> mAdapter.notifyDataSetChanged());
        }
    }


    @Override
    public void onCompleted() {
        mRefreshableRecyclerView.setRefreshing(false);
    }

    @Override
    public void onError(Throwable e) {
        RetrofitSingleton.disposeFailureInfo(e,getActivity());
    }

    @Override
    public void onNext(ArticleList articleList) {
        if(mPage == 1){
            mArticleList.clear();
        }
        mArticleList.addAll(articleList.result);

        setApapter();

        if (articleList.result.size() < 10)
            mAdapter.canLoadMore(false);
        else
            mAdapter.canLoadMore(true);
    }
}