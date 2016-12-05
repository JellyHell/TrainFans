package com.peixunfan.trainfans.ERP.Teacher.Controller;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.infrastructure.net.RetrofitSingleton;
import com.infrastructure.utils.AppUtil;
import com.peixunfan.trainfans.Api.ApiProvider;
import com.peixunfan.trainfans.Base.BaseBlankHeaderView;
import com.peixunfan.trainfans.Base.BaseFragment;
import com.peixunfan.trainfans.ERP.Courses.View.CourseHomeAdapter;
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
 * Created by chengyanfang on 2016/11/28.
 */

public class TeacherCourseFragment  extends BaseFragment implements Observer<ArticleList>
{
    @Bind(R.id.container_layout)
    RelativeLayout mContainer;

    @Bind(R.id.rlv_addteacher_layout)
    RelativeLayout mAddCourselayout;

    @Bind(R.id.tv_bottom_title)
    TextView mBottomTitle;

    RefreshableRecyclerView mRefreshableRecyclerView;

    CourseHomeAdapter mAdapter;
    ArrayList<Article> mCourseList = new ArrayList();

    /**
     * 创建页面
     * */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        if (mView == null)
        {
            mView = inflater.inflate(R.layout.fragment_courseteacher_layout, container, false);
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

        //设置左滑菜单
        mRefreshableRecyclerView.getRecyclerview().setSwipeMenuCreator((swipeLeftMenu, swipeRightMenu, viewType) -> {
            SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity())
                    .setBackgroundDrawable(R.drawable.red_drawable)
                    .setText("移出") // 文字，还可以设置文字颜色，大小等。。
                    .setTextColor(Color.WHITE)
                    .setWidth(AppUtil.dip2px(getActivity(),80))
                    .setHeight(AppUtil.dip2px(getActivity(),75));
            swipeRightMenu.addMenuItem(deleteItem);
        });

        mRefreshableRecyclerView.getRecyclerview().setSwipeMenuItemClickListener((closeable, adapterPosition, menuPosition, direction) -> {
            closeable.smoothCloseMenu();
            mCourseList.remove(adapterPosition);
            mAdapter.notifyItemRemoved(adapterPosition);
        });

        mBottomTitle.setText("添加可授课程");
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
            mAdapter = new CourseHomeAdapter(getActivity(),mCourseList);
            mAdapter.setOnItemClickListener((adapterView, view, i, l) -> {
            });
            mAdapter.setLoadMoreListener(() -> {
                mPage++;
                loadData();
            });
            mRefreshableRecyclerView.setAdapter(mAdapter);

            mAdapter.setHeaderView(new BaseBlankHeaderView(getActivity()).getView());
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
            mCourseList.clear();
        }
        mCourseList.addAll(articleList.borrowList);

        setApapter();

        if (articleList.borrowList.size() < 10)
            mAdapter.canLoadMore(false);
        else
            mAdapter.canLoadMore(true);
    }
}