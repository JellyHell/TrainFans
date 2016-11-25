package com.peixunfan.trainfans.ERP.StudentList.Controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.peixunfan.trainfans.Base.BaseFragment;
import com.peixunfan.trainfans.ERP.StudentList.View.StudentInfoAdapter;
import com.peixunfan.trainfans.R;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/23.
 */

public class StudentBaseInfoFragment  extends BaseFragment{

    @Bind(R.id.recyclerview)
    SwipeMenuRecyclerView mRecyclerview;

    StudentInfoAdapter mAdapter;
    public ArrayList<String> mStudentInfo = new ArrayList<>();

    /**
     * 创建页面
     * */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        if (mView == null)
        {
            mView = inflater.inflate(R.layout.fragment_studentinfo_layout, container, false);
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

    private void loadData()
    {
        mStudentInfo = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.signup_studentinfo)));
        setApapter();
    }


    private void setApapter(){
        if(mAdapter == null){
            mAdapter = new StudentInfoAdapter(getActivity(),mStudentInfo);
            mRecyclerview.setAdapter(mAdapter);
        }else {
            mAdapter.notifyDataSetChanged();
        }
    }
}
