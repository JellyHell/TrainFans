package com.peixunfan.trainfans.ERP.Courses.Controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.peixunfan.trainfans.Base.BaseFragment;
import com.peixunfan.trainfans.ERP.Courses.Model.Course;
import com.peixunfan.trainfans.ERP.Courses.View.CouserAddRemarkFooterView;
import com.peixunfan.trainfans.ERP.Courses.View.CouserInfoAdapter;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.Widgt.popupwindow.PublicMenuSelectPW;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/24.
 */

public class CourseInfoEditFragment  extends BaseFragment{

    @Bind(R.id.recyclerview)
    SwipeMenuRecyclerView mRecyclerview;

    CouserInfoAdapter mAdapter;

    Course mCouse = new Course();

    public ArrayList<String> mDatas = new ArrayList<>();
    public ArrayList<String> mBaseCourseInfo = new ArrayList<>();
    public ArrayList<String> mByClassInfo = new ArrayList<>();
    public ArrayList<String> mByTermInfo = new ArrayList<>();
    public ArrayList<String> mNoComeInfo = new ArrayList<>();

    ArrayList<String> mChargeTypes = new ArrayList<>();
    ArrayList<String> mTeachTypes = new ArrayList<>();
    String mCurrentChargeTypes,mCurrentTeachTypes;

    CouserAddRemarkFooterView mCouserAddRemarkFooterView;

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
        mCouserAddRemarkFooterView = new CouserAddRemarkFooterView(getActivity());
    }

    @Override
    protected void lazyLoad() {
        loadData();
    }

    @Override
    protected void initData(){
        mBaseCourseInfo = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.course_base_info)));
        mByClassInfo = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.course_info_byclass)));
        mByTermInfo = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.course_class_byterm)));
        mNoComeInfo = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.course_class_nocome_type)));

        mTeachTypes.add("一对一");
        mTeachTypes.add("一对多");
        mTeachTypes.add("取消");

        mChargeTypes.add("按课节收费");
        mChargeTypes.add("按期收费");
        mChargeTypes.add("按课节收费&按期收费");
        mChargeTypes.add("取消");

        filterAdapterData();
    }

    private void filterAdapterData(){
        mDatas.clear();
        mDatas.addAll(mBaseCourseInfo);

        //收费方式
        if(mChargeTypes.get(0).equals(mCurrentChargeTypes)){
            mDatas.addAll(mByClassInfo);
            mAdapter.mCouse.charge_type = "1";
        }else if(mChargeTypes.get(1).equals(mCurrentChargeTypes)){
            mDatas.addAll(mByTermInfo);
            mAdapter.mCouse.charge_type = "2";
        }else if(mChargeTypes.get(2).equals(mCurrentChargeTypes)){
            mDatas.addAll(mByClassInfo);
            mDatas.addAll(mByTermInfo);
            mAdapter.mCouse.charge_type = "3";
        }

        if(mTeachTypes.get(0).equals(mCurrentTeachTypes)){
            mAdapter.mCouse.teach_type = "1";
        }else if(mTeachTypes.get(1).equals(mCurrentTeachTypes)){
            mDatas.addAll(mNoComeInfo);
            mAdapter.mCouse.teach_type = "2";
        }
    }

    private void loadData()
    {
        setApapter();
    }


    private void setApapter(){
        if(mAdapter == null){
            mAdapter = new CouserInfoAdapter(getActivity(),mDatas,mCouse);
            mRecyclerview.setAdapter(mAdapter);

            mAdapter.setOnItemClickListener((adapterView, view, i, l) -> {
                switch (i){
                    case 2:
                        new PublicMenuSelectPW(getActivity(), mTeachTypes, mCurrentTeachTypes, (adapterView12, view12, i12, l12) -> {
                            mCurrentTeachTypes = mTeachTypes.get(i12);
                            filterAdapterData();
                            mAdapter.notifyDataSetChanged();
                        }).show(mRecyclerview);
                        break;
                    case 4:
                        new PublicMenuSelectPW(getActivity(), mChargeTypes, mCurrentChargeTypes, (adapterView1, view1, i1, l1) -> {
                            mCurrentChargeTypes = mChargeTypes.get(i1);
                            filterAdapterData();
                            mAdapter.notifyDataSetChanged();
                        }).show(mRecyclerview);
                        break;
                }
            });

            mAdapter.setFooterView(mCouserAddRemarkFooterView.getView());
        }else {
            mAdapter.notifyDataSetChanged();
        }
    }
}
