package com.peixunfan.trainfans.ERP.Teacher.Controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.peixunfan.trainfans.Base.BaseFragment;
import com.peixunfan.trainfans.ERP.Teacher.View.TeacherInfoEditAdapter;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.Widgt.popupwindow.PublicMenuSelectPopWindow;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/11/28.
 */

public class TeacherBaseInfoFragment  extends BaseFragment {

    @Bind(R.id.recyclerview)
    SwipeMenuRecyclerView mRecyclerview;

    TeacherInfoEditAdapter mAdapter;

    private String currentteacherSeparateType = "固定比例";

    private String courseFluctuateType = "固定金额";

    public ArrayList<String> mBaseInfoList = new ArrayList<>();
    public ArrayList<String> mTeacherSeparateType = new ArrayList<>();
    public ArrayList<String> mFluctuateType = new ArrayList<>();


    /**
     * 创建页面
     * */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        if (mView == null)
        {
            mView = inflater.inflate(R.layout.fragment_courseinfo_layout, container, false);
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
        mBaseInfoList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.teacher_base_info)));
        mTeacherSeparateType.add("固定比例");
        mTeacherSeparateType.add("固定金额");
        mTeacherSeparateType.add("固定比例&固定金额");
        mTeacherSeparateType.add("取消");

        mFluctuateType.add("固定金额");
        mFluctuateType.add("固定比例");
        mFluctuateType.add("取消");
    }


    private void loadData()
    {
        setApapter();
    }


    private void setApapter(){
        if(mAdapter == null){
            mAdapter = new TeacherInfoEditAdapter(getActivity(),mBaseInfoList);
            mRecyclerview.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener((parent, view, position, id) -> {
                if(id == 1 && position == 0){
                    new PublicMenuSelectPopWindow(getActivity(), mRecyclerview, mTeacherSeparateType, currentteacherSeparateType, (parent1, view1, position1, id1) -> {
                        currentteacherSeparateType = mTeacherSeparateType.get(position1);
                        mAdapter.setTeacherSeparateType((position1+1)+"");
                    }).show();
                }else if(id == 2 && position == 0){
                    new PublicMenuSelectPopWindow(getActivity(), mRecyclerview, mFluctuateType, courseFluctuateType, (parent1, view1, position1, id1) -> {
                        courseFluctuateType = mFluctuateType.get(position1);
                        mAdapter.setFluctuateType((position1+1)+"");
                    }).show();
                }
            });
        }else {
            mAdapter.notifyDataSetChanged();
        }
    }
}
