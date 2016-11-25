package com.peixunfan.trainfans;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.peixunfan.trainfans.Base.BaseFragment;
import com.peixunfan.trainfans.Module.Classes.adapter.BorrowListAdapter;
import com.peixunfan.trainfans.Module.Classes.domain.BorrowList;
import com.peixunfan.trainfans.Module.Home.Model.Borrow;
import com.peixunfan.trainfans.Widgt.RefreshableRecyclerView.RefreshableRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;

/**
 * Created by Administrator on 2016/8/9.
 */
public class BFragment  extends BaseFragment
{
    @Bind(R.id.container_layout)
    RelativeLayout mContainer;

    RefreshableRecyclerView mRefreshableRecyclerView;

    Observer<BorrowList> mBorrowListObserver;

    BorrowListAdapter mAdapter;
    ArrayList<Borrow> mBorrowList = new ArrayList<Borrow>();

    int mPage = 1;
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
    }

    @Override
    protected void lazyLoad() {
        loadData();
    }

    private void loadData()
    {
    }

}