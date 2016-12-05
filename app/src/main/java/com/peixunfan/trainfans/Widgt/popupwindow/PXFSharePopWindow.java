package com.peixunfan.trainfans.Widgt.popupwindow;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;

import com.infrastructure.utils.AppUtil;
import com.peixunfan.trainfans.R;


/**
 * Created by chengyanfang on 2016/12/1.
 */

public class PXFSharePopWindow implements View.OnClickListener{

    Context mContext;

    View mView;

    Dialog alertDialog;

    int popwindowHeight;

    RelativeLayout mCancleLayout;


    public PXFSharePopWindow(Context context, View view){
        mContext = context;
        initUI();
    }

    private void initUI(){
        mView = LayoutInflater.from(mContext).inflate(R.layout.popwindow_public_share_layout,null);

        mCancleLayout = (RelativeLayout)mView.findViewById(R.id.rlv_cancle_layout);
        mCancleLayout.setOnClickListener(v -> dismiss());
    }

    public void show(){
        popwindowHeight = AppUtil.dip2px(mContext,220);

        alertDialog = new Dialog(mContext,R.style.pxf_dialog_style);
        alertDialog.setContentView(mView, new ViewGroup.LayoutParams(AppUtil.getWidth(mContext),popwindowHeight));
        alertDialog.getWindow().setGravity(Gravity.BOTTOM);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

        alertDialog.setOnKeyListener((dialog, keyCode, event) -> true);

        openMenu();
    }

    private void openMenu(){
        PropertyValuesHolder tranY0 = PropertyValuesHolder.ofFloat("translationY", popwindowHeight, 0);
        PropertyValuesHolder alpha0 = PropertyValuesHolder.ofFloat("alpha", 0f, 1f);

        ObjectAnimator animator0 = ObjectAnimator.ofPropertyValuesHolder(mView,tranY0, alpha0);
        animator0.setDuration(300);
        animator0.setInterpolator(new LinearInterpolator());
        animator0.start();
    }

    public void dismiss(){

        PropertyValuesHolder tranY0 = PropertyValuesHolder.ofFloat("translationY", 0, (int)popwindowHeight);
        PropertyValuesHolder alpha0 = PropertyValuesHolder.ofFloat("alpha", 1f, 0.4f);

        ObjectAnimator animator0 = ObjectAnimator.ofPropertyValuesHolder(mView,tranY0, alpha0);
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
