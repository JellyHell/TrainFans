package com.peixunfan.trainfans.ERP.Class.Controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.peixunfan.trainfans.Base.BaseFragment;
import com.peixunfan.trainfans.ERP.Class.View.RollCallAdapter;
import com.peixunfan.trainfans.R;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/11/26.
 */

public class RollCallFragment   extends BaseFragment {

    @Bind(R.id.recyclerview)
    SwipeMenuRecyclerView mRecyclerview;

    RollCallAdapter mAdapter;

    public ArrayList<String> mBaseInfoList = new ArrayList<>();
    public ArrayList<String> mStudentList = new ArrayList<>();
    public ArrayList<String> mTempStudentList = new ArrayList<>();


    /**
     * 创建页面
     * */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        if (mView == null)
        {
            mView = inflater.inflate(R.layout.fragment_rollcall_layout, container, false);
            ButterKnife.bind(this,mView);
        }
        isCreateView = true;
        return mView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    protected void initView() {
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void lazyLoad() {
        loadData();
    }

    @Override
    protected void initData(){
        mBaseInfoList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.class_roll_call)));
        mStudentList.add("student");
        mStudentList.add("student");
    }


    private void loadData()
    {
        setApapter();
    }


    private void setApapter(){
        if(mAdapter == null){
            mAdapter = new RollCallAdapter(getActivity(),mBaseInfoList,mStudentList,mTempStudentList);
            mRecyclerview.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener((parent, view, position, id) -> {
                if(position == 2){//选择收费方式

                }
            });
        }else {
            mAdapter.notifyDataSetChanged();
        }
    }
}
