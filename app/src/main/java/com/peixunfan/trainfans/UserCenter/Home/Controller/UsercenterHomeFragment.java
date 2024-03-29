package com.peixunfan.trainfans.UserCenter.Home.Controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.infrastructure.net.RetrofitSingleton;
import com.infrastructure.utils.IntentUtil;
import com.peixunfan.trainfans.Base.BaseFragment;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.Recovery.Model.ArticleList;
import com.peixunfan.trainfans.UserCenter.Home.View.UserCenterHomeAdapter;
import com.peixunfan.trainfans.UserCenter.Home.View.UsercenterHeaderView;
import com.peixunfan.trainfans.UserCenter.Setting.Controller.QRCannerAct;
import com.peixunfan.trainfans.UserCenter.Setting.Controller.SettingAct;
import com.peixunfan.trainfans.Widgt.RefreshableRecyclerView.RefreshableRecyclerView;
import com.peixunfan.trainfans.Widgt.popupwindow.ChequesPopWindow;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;

/**
 * Created by Administrator on 2016/11/9.
 */

public class UsercenterHomeFragment  extends BaseFragment implements Observer<ArticleList>
{
    @Bind(R.id.container_layout)
    RelativeLayout mContainer;

    @Bind(R.id.tv_basetitle_cetener)
    TextView mCenterTitle;

    RefreshableRecyclerView mRefreshableRecyclerView;

    UserCenterHomeAdapter mAdapter;
    ArrayList<String> mTitle = new ArrayList();

    UsercenterHeaderView mHeaderView;

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
        mCenterTitle.setText("我");

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

        mHeaderView = new UsercenterHeaderView(getActivity());
    }

    @Override
    protected void lazyLoad() {
        loadData();
    }

    private void loadData()
    {
        mTitle = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.user_center_type)));
        setApapter();
        mRefreshableRecyclerView.setRefreshing(false);
    }

    private void setApapter(){
        if(mAdapter == null){
            mAdapter = new UserCenterHomeAdapter(getActivity(),mTitle);
            mAdapter.setOnItemClickListener((adapterView, view, i, l) -> {

                switch (i){
                    case 0:
                    {
                        IntentUtil.forwordToActivity(getActivity(), QRCannerAct.class);
                        break;
                    }
                    case 1:{
                        new ChequesPopWindow(getActivity()).show();
                        break;
                    }
                    case 3:{
                        IntentUtil.forwordToActivity(getActivity(), SettingAct.class);
                        break;
                    }
                }

            });
            mRefreshableRecyclerView.setAdapter(mAdapter);
            mAdapter.setHeaderView(mHeaderView.getView());
        }else {
            mHeaderView.refresh();
            mAdapter.notifyDataSetChanged();
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

    }
}