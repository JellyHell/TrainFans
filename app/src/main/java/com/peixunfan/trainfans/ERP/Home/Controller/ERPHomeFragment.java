package com.peixunfan.trainfans.ERP.Home.Controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.infrastructure.net.RetrofitSingleton;
import com.infrastructure.utils.IntentUtil;
import com.peixunfan.trainfans.Base.BaseFragment;
import com.peixunfan.trainfans.ERP.Courses.Controller.CourseHomeListAct;
import com.peixunfan.trainfans.Widgt.popupwindow.ExtendMenuPopWindow;
import com.peixunfan.trainfans.ERP.Home.View.HomeGridAdapter;
import com.peixunfan.trainfans.ERP.Message.Controller.MessageListAct;
import com.peixunfan.trainfans.ERP.SignUp.Controller.SignUpAct;
import com.peixunfan.trainfans.ERP.StudentList.Controller.StudentListAct;
import com.peixunfan.trainfans.Recovery.Model.ArticleList;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.Widgt.RefreshableRecyclerView.RefreshableRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;

/**
 * Created by Administrator on 2016/11/1.
 */

public class ERPHomeFragment extends BaseFragment implements Observer<ArticleList>
{
    @Bind(R.id.main_view)
    RelativeLayout mMainView;

    @Bind(R.id.container_layout)
    RelativeLayout mContainer;

    @Bind(R.id.rlv_title_layout)
    RelativeLayout mTitleView;

    RefreshableRecyclerView mRefreshableRecyclerView;

    HomeGridAdapter mAdapter;
    ArrayList<String> mTitle = new ArrayList();

    /**
     * 创建页面
     * */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        if (mView == null)
        {
            mView = inflater.inflate(R.layout.fragment_home_base, container, false);
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
        //Title
        mTitleView.setVisibility(View.GONE);

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
        mRefreshableRecyclerView.setBannerView();
        mRefreshableRecyclerView.setGridLayout();
        mRefreshableRecyclerView.setmOnHomeBanerTitleMenuClick(new RefreshableRecyclerView.OnHomeBanerTitleMenuClick() {
            @Override
            public void onLeftMenuBtClick() {
                new ExtendMenuPopWindow(getActivity(), mMainView, position -> mRefreshableRecyclerView.showLeftBt()).show();
            }

            @Override
            public void onRightMenuBtClick() {
                IntentUtil.forwordToActivity(getActivity(), MessageListAct.class);
            }
        });
        mContainer.addView(mRefreshableRecyclerView.getView());
    }

    @Override
    protected void lazyLoad() {
        loadData();
    }

    private void loadData()
    {
        mTitle = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.erp_home_type)));
        setApapter();
        mRefreshableRecyclerView.setBannerData();
    }

    private void setApapter(){
        if(mAdapter == null){
            mAdapter = new HomeGridAdapter(getActivity(),mTitle);
            mAdapter.setOnItemClickListener((adapterView, view, i, l) -> {
                switch (i){
                    case 0:
                    {
                        IntentUtil.forwordToActivity(getActivity(), SignUpAct.class);
                        break;
                    }
                    case 4:{
                        IntentUtil.forwordToActivity(getActivity(),StudentListAct.class);
                        break;
                    }
                    case 6:{
                        IntentUtil.forwordToActivity(getActivity(),CourseHomeListAct.class);
                        break;
                    }
                }
            });
            mRefreshableRecyclerView.setAdapter(mAdapter);
        }else {
            mAdapter.notifyDataSetChanged();
        }
        mRefreshableRecyclerView.setRefreshing(false);
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
    }
}