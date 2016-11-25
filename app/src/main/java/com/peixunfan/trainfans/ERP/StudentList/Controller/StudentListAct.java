package com.peixunfan.trainfans.ERP.StudentList.Controller;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.infrastructure.net.RetrofitSingleton;
import com.infrastructure.utils.AppUtil;
import com.infrastructure.utils.IntentUtil;
import com.peixunfan.trainfans.Api.ApiProvider;
import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.ERP.StudentList.View.StudentAdapter;
import com.peixunfan.trainfans.ERP.StudentList.View.StudentTypeSelectPW;
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
 * Created by Administrator on 2016/11/22.
 */

public class StudentListAct extends BaseActivity implements Observer<ArticleList> {

    @Bind(R.id.container_layout)
    RelativeLayout mContainer;

    RefreshableRecyclerView mRefreshableRecyclerView;

    StudentAdapter mAdapter;
    ArrayList<Article> mStudentList = new ArrayList();
    ArrayList<String> mStudentType = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_erp_studentlist_home);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {
        mStudentType.add("全部学生");
        mStudentType.add("意向学员");
        mStudentType.add("在读学员");
        mStudentType.add("非在读学员");
    }

    @Override
    protected void initViews(Bundle saveInstanceState) {
        super.initViews(saveInstanceState);
        mCenterTitle.setText("学生列表");
        mMenuArrow.setVisibility(View.VISIBLE);
        mCenterTitle.setOnClickListener((view)-> new StudentTypeSelectPW(StudentListAct.this,mStudentType).show(view));
        mMenuArrow.setOnClickListener((view)-> new StudentTypeSelectPW(StudentListAct.this,mStudentType).show(mCenterTitle));
        mRightManagerBt.setImageResource(R.drawable.icon_search_bt);
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

        //设置左滑菜单
        mRefreshableRecyclerView.getRecyclerview().setSwipeMenuCreator((swipeLeftMenu, swipeRightMenu, viewType) -> {
            SwipeMenuItem deleteItem = new SwipeMenuItem(StudentListAct.this)
                    .setBackgroundDrawable(R.drawable.red_drawable)
                    .setText("删除") // 文字，还可以设置文字颜色，大小等。。
                    .setTextColor(Color.WHITE)
                    .setWidth(AppUtil.dip2px(StudentListAct.this,80))
                    .setHeight(AppUtil.dip2px(StudentListAct.this,65));
            swipeRightMenu.addMenuItem(deleteItem);
        });

        mRefreshableRecyclerView.getRecyclerview().setSwipeMenuItemClickListener((closeable, adapterPosition, menuPosition, direction) -> {
            closeable.smoothCloseMenu();
            mStudentList.remove(adapterPosition);
            mAdapter.notifyItemRemoved(adapterPosition);
        });

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
        mStudentList.addAll(articleList.result);

        setApapter();

        if (articleList.result.size() < 10)
            mAdapter.canLoadMore(false);
        else
            mAdapter.canLoadMore(true);
    }


    private void setApapter(){
        if(mAdapter == null){
            mAdapter = new StudentAdapter(this,mStudentList);
            mAdapter.setOnItemClickListener((adapterView, view, i, l) -> IntentUtil.forwordToActivity(StudentListAct.this,StudentDetailInfoAct.class));
            mAdapter.setLoadMoreListener(() -> {
                mPage++;
                loadData();
            });
            mRefreshableRecyclerView.setAdapter(mAdapter);
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
}
