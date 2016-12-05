package com.peixunfan.trainfans.ERP.Class.Controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.peixunfan.trainfans.Base.BaseFragment;
import com.peixunfan.trainfans.ERP.Class.View.ClassInfoEditAdapter;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.Widgt.popupwindow.PublicMenuSelectPopWindow;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/11/26.
 */

public class ClassBaseInfoFragment  extends BaseFragment {

    @Bind(R.id.recyclerview)
    SwipeMenuRecyclerView mRecyclerview;

    ClassInfoEditAdapter mAdapter;

    public ArrayList<String> mBaseInfoList = new ArrayList<>();
    public ArrayList<String> mClassTimeList = new ArrayList<>();
    public ArrayList<String> mTeacherList = new ArrayList<>();

    String currentChargeType = "";
    ArrayList<String> mChargeTypes = new ArrayList<>();


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
        mBaseInfoList = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.class_base_info)));

        mChargeTypes.add("按课节收费");
        mChargeTypes.add("按期收费");
        mChargeTypes.add("按课节收费&按期收费");
        mChargeTypes.add("取消");
    }


    private void loadData()
    {
        setApapter();
    }


    private void setApapter(){
        if(mAdapter == null){
            mAdapter = new ClassInfoEditAdapter(getActivity(),mBaseInfoList,mClassTimeList,mTeacherList);
            mRecyclerview.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener((parent, view, position, id) -> {
                if(position == 2){//选择收费方式
                    new PublicMenuSelectPopWindow(getActivity(),mRecyclerview,mChargeTypes,currentChargeType,(adapterView1, view1, i1, l1) -> {
                        currentChargeType = mChargeTypes.get(i1);
                    }).show();
                }
            });
        }else {
            mAdapter.notifyDataSetChanged();
        }
    }
}
