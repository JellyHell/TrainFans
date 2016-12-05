package com.peixunfan.trainfans.ERP.IntentStudent.Controller;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.infrastructure.net.RetrofitSingleton;
import com.infrastructure.utils.AppUtil;
import com.infrastructure.utils.IntentUtil;
import com.peixunfan.trainfans.Api.ApiProvider;
import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.Base.BaseBlankHeaderView;
import com.peixunfan.trainfans.ERP.IntentStudent.View.IntentStudentAdapter;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.Recovery.Model.Article;
import com.peixunfan.trainfans.Recovery.Model.ArticleList;
import com.peixunfan.trainfans.Widgt.RefreshableRecyclerView.RefreshableRecyclerView;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;

/**
 * Created by chengyanfang on 2016/11/30.
 */

public class IntentStudentHomeListAct  extends BaseActivity implements Observer<ArticleList> {

    @Bind(R.id.container_layout)
    RelativeLayout mContainer;

    RefreshableRecyclerView mRefreshableRecyclerView;

    IntentStudentAdapter mAdapter;
    ArrayList<Article> mStudentList = new ArrayList();

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
        mCenterTitle.setText("意向学员");
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


        //设置左滑菜单
        mRefreshableRecyclerView.getRecyclerview().setSwipeMenuCreator((swipeLeftMenu, swipeRightMenu, viewType) -> {

            int height = AppUtil.dip2px(IntentStudentHomeListAct.this,65);
            if(viewType == IntentStudentAdapter.ITEM_HIGHTER){
                height = AppUtil.dip2px(IntentStudentHomeListAct.this,110);
            }

            SwipeMenuItem deleteItem = new SwipeMenuItem(IntentStudentHomeListAct.this)
                    .setBackgroundDrawable(R.drawable.red_drawable)
                    .setText("删除") // 文字，还可以设置文字颜色，大小等。。
                    .setTextColor(Color.WHITE)
                    .setWidth(AppUtil.dip2px(IntentStudentHomeListAct.this,80))
                    .setHeight(height);
            swipeRightMenu.addMenuItem(deleteItem);

        });

        mRefreshableRecyclerView.getRecyclerview().setSwipeMenuItemClickListener((closeable, adapterPosition, menuPosition, direction) -> {
            closeable.smoothCloseMenu();
            mStudentList.remove(adapterPosition);
            mAdapter.notifyItemRemoved(adapterPosition);
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
            mStudentList.clear();
        }
        mStudentList.addAll(articleList.borrowList);

        setApapter();

        if (articleList.borrowList.size() < 10)
            mAdapter.canLoadMore(false);
        else
            mAdapter.canLoadMore(true);
    }


    private void setApapter(){
        if(mAdapter == null){
            mAdapter = new IntentStudentAdapter(this,mStudentList);
            mAdapter.setOnItemClickListener((adapterView, view, i, l) -> IntentUtil.forwordToActivity(IntentStudentHomeListAct.this,IntentStudentDetailAct.class));
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
        IntentUtil.forwordToActivity(this,AddIntentStudentAct.class);
    }
}
