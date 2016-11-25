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
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.infrastructure.utils.AppUtil;
import com.peixunfan.trainfans.R;

/**
 * Created by Administrator on 2016/11/17.
 */

public class ExtendMenuPopWindow implements View.OnClickListener{

    Context mContext;

    View mView;

    Dialog alertDialog;

    ImageView mCloseImg;

    RelativeLayout mGroupSendSMSLayout,mScanQRCodeLayout,mAddStudentLayout;

    public ExtendMenuPopWindow(Context context, View view, OnMenuClickListener onMenuClickListener){
        mContext = context;
        mOnMenuClickListener = onMenuClickListener;
        initUI();
    }

    private void initUI(){
        mView = LayoutInflater.from(mContext).inflate(R.layout.popwindow_home_extend_menu,null);
        mCloseImg = (ImageView)mView.findViewById(R.id.close_img);
        mCloseImg.setOnClickListener(this);

        mGroupSendSMSLayout = (RelativeLayout) mView.findViewById(R.id.rlv_group_sendSMS);
        mGroupSendSMSLayout.setOnClickListener(this);
        mScanQRCodeLayout = (RelativeLayout) mView.findViewById(R.id.rlv_qrcode_scanner);
        mScanQRCodeLayout.setOnClickListener(this);
        mAddStudentLayout = (RelativeLayout) mView.findViewById(R.id.rlv_add_student);
        mAddStudentLayout.setOnClickListener(this);
    }

    public void show(){
        alertDialog = new Dialog(mContext,R.style.pxf_dialog_style);
        alertDialog.setContentView(mView, new ViewGroup.LayoutParams(AppUtil.getWidth(mContext), AppUtil.getHeight(mContext)));
        alertDialog.getWindow().setGravity(Gravity.LEFT|Gravity.TOP);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

        alertDialog.setOnKeyListener((dialog, keyCode, event) -> true);
        openMenu();
    }
    private void openMenu(){
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 0f, 1f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(mCloseImg, pvhX);
        animator.setDuration(300);
        animator.start();

        //显示群发好友
        PropertyValuesHolder pvhY0 = PropertyValuesHolder.ofFloat("scaleX", 0.2f, 1.0f);
        PropertyValuesHolder pvhZ0 = PropertyValuesHolder.ofFloat("scaleY", 0.2f, 1.0f);
        PropertyValuesHolder tranX0 = PropertyValuesHolder.ofFloat("translationX", 0f, AppUtil.dip2px(mContext, 165));
        PropertyValuesHolder tranY0 = PropertyValuesHolder.ofFloat("translationY", 0f, 0f);
        PropertyValuesHolder alpha0 = PropertyValuesHolder.ofFloat("alpha", 0f, 1f);

        ObjectAnimator animator0 = ObjectAnimator.ofPropertyValuesHolder(mGroupSendSMSLayout, tranX0, tranY0, alpha0, pvhY0, pvhZ0);
        animator0.setDuration(300);
        animator0.setInterpolator(new AccelerateInterpolator());
        animator0.start();

        //显示扫一扫
        PropertyValuesHolder pvhY1 = PropertyValuesHolder.ofFloat("scaleX", 0.2f, 1.0f);
        PropertyValuesHolder pvhZ1 = PropertyValuesHolder.ofFloat("scaleY", 0.2f, 1.0f);
        PropertyValuesHolder tranX1 = PropertyValuesHolder.ofFloat("translationX", 0,AppUtil.dip2px(mContext, 110));
        PropertyValuesHolder tranY1 = PropertyValuesHolder.ofFloat("translationY", 0f, AppUtil.dip2px(mContext, 85));
        PropertyValuesHolder alpha1 = PropertyValuesHolder.ofFloat("alpha", 0f, 1f);

        ObjectAnimator animator1 = ObjectAnimator.ofPropertyValuesHolder(mScanQRCodeLayout, tranX1, tranY1, alpha1, pvhY1, pvhZ1);
        animator1.setDuration(300);
        animator1.setInterpolator(new AccelerateInterpolator());
        animator1.start();

        //显示扫一扫
        PropertyValuesHolder pvhY2 = PropertyValuesHolder.ofFloat("scaleX", 0.2f, 1.0f);
        PropertyValuesHolder pvhZ2 = PropertyValuesHolder.ofFloat("scaleY", 0.2f, 1.0f);
        PropertyValuesHolder tranX2 = PropertyValuesHolder.ofFloat("translationX", 0,0);
        PropertyValuesHolder tranY2 = PropertyValuesHolder.ofFloat("translationY", 0f, AppUtil.dip2px(mContext, 165));
        PropertyValuesHolder alpha2 = PropertyValuesHolder.ofFloat("alpha", 0f, 1f);

        ObjectAnimator animator2 = ObjectAnimator.ofPropertyValuesHolder(mAddStudentLayout, tranX2, tranY2, alpha2, pvhY2, pvhZ2);
        animator2.setDuration(300);
        animator2.setInterpolator(new AccelerateInterpolator());
        animator2.start();
    }

    public void dismiss(int position){

        //隐藏群发好友
        PropertyValuesHolder pvhY0 = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.2f);
        PropertyValuesHolder pvhZ0 = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0.2f);
        PropertyValuesHolder tranX0 = PropertyValuesHolder.ofFloat("translationX", AppUtil.dip2px(mContext, 165),0f);
        PropertyValuesHolder tranY0 = PropertyValuesHolder.ofFloat("translationY", 0f, 0f);
        PropertyValuesHolder alpha0 = PropertyValuesHolder.ofFloat("alpha", 1f, 0f);

        ObjectAnimator animator0 = ObjectAnimator.ofPropertyValuesHolder(mGroupSendSMSLayout, tranX0, tranY0, alpha0, pvhY0, pvhZ0);
        animator0.setDuration(300);
        animator0.setInterpolator(new AccelerateInterpolator());
        animator0.start();

        //隐藏扫一扫
        PropertyValuesHolder pvhY1 = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.2f);
        PropertyValuesHolder pvhZ1 = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0.2f);
        PropertyValuesHolder tranX1 = PropertyValuesHolder.ofFloat("translationX", AppUtil.dip2px(mContext, 110),0);
        PropertyValuesHolder tranY1 = PropertyValuesHolder.ofFloat("translationY", AppUtil.dip2px(mContext, 85),0f);
        PropertyValuesHolder alpha1 = PropertyValuesHolder.ofFloat("alpha",  1f,0f);

        ObjectAnimator animator1 = ObjectAnimator.ofPropertyValuesHolder(mScanQRCodeLayout, tranX1, tranY1, alpha1, pvhY1, pvhZ1);
        animator1.setDuration(300);
        animator1.setInterpolator(new AccelerateInterpolator());
        animator1.start();

        //隐藏扫一扫
        PropertyValuesHolder pvhY2 = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.2f);
        PropertyValuesHolder pvhZ2 = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0.2f);
        PropertyValuesHolder tranX2 = PropertyValuesHolder.ofFloat("translationX", 0,0);
        PropertyValuesHolder tranY2 = PropertyValuesHolder.ofFloat("translationY", AppUtil.dip2px(mContext, 165),0f);
        PropertyValuesHolder alpha2 = PropertyValuesHolder.ofFloat("alpha", 1f,0f);

        ObjectAnimator animator2 = ObjectAnimator.ofPropertyValuesHolder(mAddStudentLayout, tranX2, tranY2, alpha2, pvhY2, pvhZ2);
        animator2.setDuration(300);
        animator2.setInterpolator(new AccelerateInterpolator());
        animator2.start();


        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("alpha", 1f, 0f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(mCloseImg, pvhX);
        animator.setDuration(400);
        animator.start();
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if(alertDialog != null && mOnMenuClickListener != null){
                    mOnMenuClickListener.onMenuClick(position);
                    alertDialog.dismiss();
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.close_img:{
                dismiss(-1);
            }
            break;
            case R.id.rlv_group_sendSMS:{
                dismiss(0);
            }
            break;
            case R.id.rlv_qrcode_scanner:{
                dismiss(1);
            }
            break;
            case R.id.rlv_add_student:{
                dismiss(2);
            }
            break;
        }
    }


    /**
     * Menu点击回调
     * */
    public interface OnMenuClickListener{
        public void onMenuClick(int position);
    }

    OnMenuClickListener mOnMenuClickListener;

    public void setmOnMenuClickListener(OnMenuClickListener mOnMenuClickListener) {
        this.mOnMenuClickListener = mOnMenuClickListener;
    }
}
