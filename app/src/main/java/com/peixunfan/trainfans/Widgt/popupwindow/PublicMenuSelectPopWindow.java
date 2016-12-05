package com.peixunfan.trainfans.Widgt.popupwindow;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.RelativeLayout;

import com.infrastructure.utils.AppUtil;
import com.peixunfan.trainfans.R;

import java.util.ArrayList;

/**
 * Created by chengyanfang on 2016/11/26.
 */

public class PublicMenuSelectPopWindow implements View.OnClickListener{

    Context mContext;

    View mView;

    Dialog alertDialog;

    RecyclerView recyclerView;

    String mCurrentType;
    ArrayList<String> mTypes = new ArrayList<>();

    TypeSelectAdapter mTypeSelectAdapter;

    AdapterView.OnItemClickListener mOnItemClickListener;

    float popwindowHeight = 0;


    public PublicMenuSelectPopWindow(Context context, View view, ArrayList<String> types, String currentSelected, AdapterView.OnItemClickListener onItemClickListener){
        mContext = context;
        mTypes.addAll(types);
        mCurrentType = currentSelected;
        mOnItemClickListener = onItemClickListener;
        popwindowHeight = AppUtil.dip2px(mContext,50)*mTypes.size()+ AppUtil.dip2px(mContext,10);
        initUI();
    }

    private void initUI(){
        mView = LayoutInflater.from(mContext).inflate(R.layout.popwindow_typeselect_type,null);
        recyclerView =(RecyclerView)mView.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        recyclerView.setAlpha(0);

        mTypeSelectAdapter = new TypeSelectAdapter(mContext,mTypes,mCurrentType);
        recyclerView.setAdapter(mTypeSelectAdapter);

        mTypeSelectAdapter.setOnItemClickListener((adapterView, view, i, l) -> {
            dismiss(i);
        });
    }

    public void show(){
        alertDialog = new Dialog(mContext,R.style.pxf_dialog_style);
        alertDialog.setContentView(mView, new ViewGroup.LayoutParams(AppUtil.getWidth(mContext), (int)popwindowHeight));
        alertDialog.getWindow().setGravity(Gravity.BOTTOM);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();



        openMenu();
    }

    private void openMenu(){
        PropertyValuesHolder tranY0 = PropertyValuesHolder.ofFloat("translationY", (int)popwindowHeight, 0);
        PropertyValuesHolder alpha0 = PropertyValuesHolder.ofFloat("alpha", 0f, 1f);

        ObjectAnimator animator0 = ObjectAnimator.ofPropertyValuesHolder(recyclerView,tranY0, alpha0);
        animator0.setDuration(300);
        animator0.setInterpolator(new LinearInterpolator());
        animator0.start();
    }

    public void dismiss(int position){

        if (position < mTypes.size()-1){
            mOnItemClickListener.onItemClick(null,null,position,0);
        }

        PropertyValuesHolder tranY0 = PropertyValuesHolder.ofFloat("translationY", 0, (int)popwindowHeight);
        PropertyValuesHolder alpha0 = PropertyValuesHolder.ofFloat("alpha", 1f, 0.4f);

        ObjectAnimator animator0 = ObjectAnimator.ofPropertyValuesHolder(recyclerView,tranY0, alpha0);
        animator0.setDuration(200);
        animator0.setInterpolator(new LinearInterpolator());
        animator0.start();
        animator0.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                alertDialog.dismiss();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }
}
