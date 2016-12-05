package com.peixunfan.trainfans.ERP.RenewWarning.Controller;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.infrastructure.net.RetrofitSingleton;
import com.infrastructure.utils.AppUtil;
import com.infrastructure.utils.IntentUtil;
import com.peixunfan.trainfans.Api.ApiProvider;
import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.ERP.RenewWarning.View.RenewWarningAdapter;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.Recovery.Model.Article;
import com.peixunfan.trainfans.Recovery.Model.ArticleList;
import com.peixunfan.trainfans.Widgt.RefreshableRecyclerView.RefreshableRecyclerView;
import com.peixunfan.trainfans.Widgt.popupwindow.PublicMenuSelectPopWindow;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;

/**
 * Created by chengyanfang on 2016/11/29.
 */

public class RenewWarningHomeAct  extends BaseActivity implements Observer<ArticleList> {

    @Bind(R.id.container_layout)
    RelativeLayout mContainer;

    @Bind(R.id.tv_error_tip_text)
    TextView mTipView;

    RefreshableRecyclerView mRefreshableRecyclerView;

    RenewWarningAdapter mAdapter;
    ArrayList<Article> mStudentList = new ArrayList();

    public ArrayList<String> userManager = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_erp_studentlist_home);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {
        userManager.add("续费");
        userManager.add("发通知");
        userManager.add("取消");
    }

    @Override
    protected void initViews(Bundle saveInstanceState) {
        super.initViews(saveInstanceState);
        mCenterTitle.setText("续费预警");
        mRightManagerBt.setImageResource(R.drawable.icon_search_bt);
        mRightSecondManagerBt.setImageResource(R.drawable.icon_renewcount_settting);
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
            SwipeMenuItem deleteItem = new SwipeMenuItem(RenewWarningHomeAct.this)
                    .setBackgroundDrawable(R.drawable.red_drawable)
                    .setText("续费") // 文字，还可以设置文字颜色，大小等。。
                    .setTextColor(Color.WHITE)
                    .setWidth(AppUtil.dip2px(RenewWarningHomeAct.this,80))
                    .setHeight(AppUtil.dip2px(RenewWarningHomeAct.this,65));

            SwipeMenuItem sendMessageItem = new SwipeMenuItem(RenewWarningHomeAct.this)
                    .setBackgroundDrawable(R.drawable.dray_background)
                    .setText("发消息") // 文字，还可以设置文字颜色，大小等。。
                    .setTextColor(Color.WHITE)
                    .setWidth(AppUtil.dip2px(RenewWarningHomeAct.this,100))
                    .setHeight(AppUtil.dip2px(RenewWarningHomeAct.this,65));

            swipeRightMenu.addMenuItem(sendMessageItem);
            swipeRightMenu.addMenuItem(deleteItem);
        });

        mRefreshableRecyclerView.getRecyclerview().setSwipeMenuItemClickListener((closeable, adapterPosition, menuPosition, direction) -> {
            closeable.smoothCloseMenu();

            if(menuPosition == 0){
                IntentUtil.forwordToActivity(RenewWarningHomeAct.this,SendMsgAct.class);
            }else if(menuPosition == 1){
                IntentUtil.forwordToActivity(RenewWarningHomeAct.this,BuyClassAct.class);
            }
        });

        //设置自动刷新
        mRefreshableRecyclerView.autoRefresh();

        mTipView.setText("剩余课数少于10");
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
            mAdapter = new RenewWarningAdapter(this,mStudentList);
            mAdapter.setOnItemClickListener((adapterView, view, i, l) -> {
                new PublicMenuSelectPopWindow(RenewWarningHomeAct.this, mRefreshableRecyclerView.getRecyclerview(), userManager, "", new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if(position == 0){
                            IntentUtil.forwordToActivity(RenewWarningHomeAct.this,BuyClassAct.class);
                        }else{
                            IntentUtil.forwordToActivity(RenewWarningHomeAct.this,SendMsgAct.class);
                        }
                    }
                }).show();
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
