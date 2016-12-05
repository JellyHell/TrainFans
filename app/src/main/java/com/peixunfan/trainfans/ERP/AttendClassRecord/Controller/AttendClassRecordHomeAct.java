package com.peixunfan.trainfans.ERP.AttendClassRecord.Controller;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.infrastructure.net.RetrofitSingleton;
import com.infrastructure.utils.AppUtil;
import com.infrastructure.utils.IntentUtil;
import com.peixunfan.trainfans.Api.ApiProvider;
import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.ERP.AttendClassRecord.View.AttendClassHomeAdapter;
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
 * Created by chengyanfang on 2016/11/28.
 */

public class AttendClassRecordHomeAct extends BaseActivity implements Observer<ArticleList> {

    @Bind(R.id.container_layout)
    RelativeLayout mContainer;

    @Bind(R.id.rlv_time_accounts)
    RelativeLayout mTimeAccountLayout;

    RefreshableRecyclerView mRefreshableRecyclerView;

    AttendClassHomeAdapter mAdapter;
    ArrayList<Article> mStudentList = new ArrayList();
    ArrayList<String> mMonthType = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_erp_addtentclass_home);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {
        mMonthType.add("全部记录");
        mMonthType.add("本月记录");
        mMonthType.add("10月记录");
        mMonthType.add("9月记录");
    }

    @Override
    protected void initViews(Bundle saveInstanceState) {
        super.initViews(saveInstanceState);
        mCenterTitle.setText("本月记录");
        mMenuArrow.setVisibility(View.VISIBLE);
        mCenterTitle.setOnClickListener((view)-> new StudentTypeSelectPW(AttendClassRecordHomeAct.this,mMonthType).show(view));
        mMenuArrow.setOnClickListener((view)-> new StudentTypeSelectPW(AttendClassRecordHomeAct.this,mMonthType).show(mCenterTitle));
        mRightManagerBt.setImageResource(R.drawable.icon_noattend_class);
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


        mRefreshableRecyclerView.getRecyclerview().setSwipeMenuCreator((swipeLeftMenu, swipeRightMenu, viewType) -> {
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            SwipeMenuItem deleteItem = new SwipeMenuItem(AttendClassRecordHomeAct.this)
                    .setBackgroundDrawable(R.drawable.red_drawable)
                    .setText("删除") // 文字，还可以设置文字颜色，大小等。。
                    .setTextColor(Color.WHITE)
                    .setWidth(AppUtil.dip2px(AttendClassRecordHomeAct.this,80))
                    .setHeight(height);

            SwipeMenuItem changeTimeItem = new SwipeMenuItem(AttendClassRecordHomeAct.this)
                    .setBackgroundDrawable(R.drawable.dray_background)
                    .setText("修改上课时间") // 文字，还可以设置文字颜色，大小等。。
                    .setTextColor(Color.WHITE)
                    .setWidth(AppUtil.dip2px(AttendClassRecordHomeAct.this,120))
                    .setHeight(height);

            swipeRightMenu.addMenuItem(changeTimeItem);
            swipeRightMenu.addMenuItem(deleteItem);
        });

        mRefreshableRecyclerView.getRecyclerview().setSwipeMenuItemClickListener((closeable, adapterPosition, menuPosition, direction) -> {
            closeable.smoothCloseMenu();
        });


        //设置自动刷新
        mRefreshableRecyclerView.autoRefresh();

        mTimeAccountLayout.setOnClickListener(v -> {});
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

    }


    private void setApapter(){
        if(mAdapter == null){
            mAdapter = new AttendClassHomeAdapter(this,mStudentList);
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
        IntentUtil.forwordToActivity(this,NoAttendStudentListAct.class);
    }
}
