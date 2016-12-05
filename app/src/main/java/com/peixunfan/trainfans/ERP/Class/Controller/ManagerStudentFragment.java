package com.peixunfan.trainfans.ERP.Class.Controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.peixunfan.trainfans.Base.BaseFragment;
import com.peixunfan.trainfans.ERP.Class.Model.StudentModel;
import com.peixunfan.trainfans.ERP.Class.View.ManagerStudentAdapter;
import com.peixunfan.trainfans.R;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/11/27.
 */

public class ManagerStudentFragment extends BaseFragment {

    @Bind(R.id.rcv_top_recyclerview)
    RecyclerView mTopRecyclerview;

    @Bind(R.id.rcv_bottom_recyclerview)
    RecyclerView mBottomRecyclerview;

    @Bind(R.id.iv_in_img)
    ImageView mInImg;

    @Bind(R.id.iv_out_img)
    ImageView mOutImg;

    ManagerStudentAdapter mTopAdapter;

    ManagerStudentAdapter mBottomAdapter;

    public ArrayList<StudentModel> mToMoveStudentList = new ArrayList<>();
    public ArrayList<StudentModel> mTopStudentList = new ArrayList<>();
    public ArrayList<StudentModel> mBottomStudentList = new ArrayList<>();

    boolean isCanOut,isCanIn;

    /**
     * 创建页面
     * */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        if (mView == null)
        {
            mView = inflater.inflate(R.layout.fragment_managerstudent_layout, container, false);
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
        mTopRecyclerview.setLayoutManager(new GridLayoutManager(getActivity(),5));
        mBottomRecyclerview.setLayoutManager(new GridLayoutManager(getActivity(),5));

        mInImg.setOnClickListener(v -> {
            if(isCanIn){
                moveStudentIn();
            }
        });

        mOutImg.setOnClickListener(v -> {
            if(isCanOut){
                moveStudentOut();
            }
        });
    }

    @Override
    protected void lazyLoad() {
        loadData();
    }

    @Override
    protected void initData(){
        mTopStudentList.add(new StudentModel("张一"));
        mTopStudentList.add(new StudentModel("张二"));
        mTopStudentList.add(new StudentModel("张三"));
        mTopStudentList.add(new StudentModel("张四"));
        mTopStudentList.add(new StudentModel("张五"));
        mTopStudentList.add(new StudentModel("张六"));
        mTopStudentList.add(new StudentModel("张七"));
    }

    private void changeInOutBtState(boolean isTop,boolean isSelected){

        if(isTop){
            for(StudentModel bottomStudent : mBottomStudentList){
                bottomStudent.selected = false;
            }

            mInImg.setBackgroundResource(R.drawable.icon_in_normal);
            isCanIn = false;

            if(isSelected){
                mOutImg.setBackgroundResource(R.drawable.icon_out_selected);
                isCanOut = true;
            }else{
                mOutImg.setBackgroundResource(R.drawable.icon_out_normal);
                isCanOut = false;
                for(StudentModel student : mTopStudentList){
                    if(student.selected){
                        mOutImg.setBackgroundResource(R.drawable.icon_out_selected);
                        isCanOut = true;
                        break;
                    }
                }
            }

        }else {
            for(StudentModel topStudent : mTopStudentList){
                topStudent.selected = false;
            }

            mOutImg.setBackgroundResource(R.drawable.icon_out_normal);
            isCanOut = false;

            if(isSelected){
                mInImg.setBackgroundResource(R.drawable.icon_in_selected);
                isCanIn = true;
            }else{
                mInImg.setBackgroundResource(R.drawable.icon_in_normal);
                isCanIn = false;

                for(StudentModel student : mBottomStudentList){
                    if(student.selected){
                        mInImg.setBackgroundResource(R.drawable.icon_in_selected);
                        isCanOut = true;
                        break;
                    }
                }
            }
        }
    }


    private void moveStudentIn(){
        mToMoveStudentList.clear();

        for(StudentModel studentModel : mBottomStudentList){
            if(studentModel.selected){
                studentModel.selected = false;
                mToMoveStudentList.add(studentModel);
            }
        }

        mTopStudentList.addAll(mToMoveStudentList);
        mBottomStudentList.removeAll(mToMoveStudentList);

        mTopAdapter.notifyDataSetChanged();
        mBottomAdapter.notifyDataSetChanged();

        mInImg.setBackgroundResource(R.drawable.icon_in_normal);
        isCanIn = false;
    }

    private void moveStudentOut(){
        mToMoveStudentList.clear();

        for(StudentModel studentModel : mTopStudentList){
            if(studentModel.selected){
                studentModel.selected = false;
                mToMoveStudentList.add(studentModel);
            }
        }

        mBottomStudentList.addAll(mToMoveStudentList);
        mTopStudentList.removeAll(mToMoveStudentList);

        mTopAdapter.notifyDataSetChanged();
        mBottomAdapter.notifyDataSetChanged();

        mOutImg.setBackgroundResource(R.drawable.icon_out_normal);
        isCanOut = false;
    }

    private void loadData()
    {
        setTopApapter();
        setBottomApapter();
    }


    private void setTopApapter(){
        if(mTopAdapter == null){
            mTopAdapter = new ManagerStudentAdapter(getActivity(),mTopStudentList);
            mTopRecyclerview.setAdapter(mTopAdapter);
            mTopAdapter.setOnItemClickListener((parent, view, position, id) -> {
                mTopStudentList.get(position).selected = !mTopStudentList.get(position).selected;
                changeInOutBtState(true,mTopStudentList.get(position).selected);
                mTopAdapter.notifyDataSetChanged();
            });
        }else {
            mTopAdapter.notifyDataSetChanged();
        }
    }

    private void setBottomApapter(){
        if(mBottomAdapter == null){
            mBottomAdapter = new ManagerStudentAdapter(getActivity(),mBottomStudentList);
            mBottomRecyclerview.setAdapter(mBottomAdapter);
            mBottomAdapter.setOnItemClickListener((parent, view, position, id) -> {
                mBottomStudentList.get(position).selected = !mBottomStudentList.get(position).selected;
                changeInOutBtState(false,mBottomStudentList.get(position).selected);
                mBottomAdapter.notifyDataSetChanged();
            });
        }else {
            mBottomAdapter.notifyDataSetChanged();
        }
    }
}
