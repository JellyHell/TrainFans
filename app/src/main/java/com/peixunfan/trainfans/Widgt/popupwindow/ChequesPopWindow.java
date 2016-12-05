package com.peixunfan.trainfans.Widgt.popupwindow;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.infrastructure.utils.AppUtil;
import com.peixunfan.trainfans.R;

/**
 * Created by chengyanfang on 2016/12/1.
 */

public class ChequesPopWindow implements View.OnClickListener{

    Context mContext;

    View mView;

    Dialog alertDialog;

    RelativeLayout mCancleLayout;

    /**关闭弹窗*/
    ImageView mClosePopwindow;

    /**消除&填写金额*/
    TextView mMoneyChangeTv;

    TextView mMoneyCountTv;

    public ChequesPopWindow(Context context){
        mContext = context;
        initUI();
    }

    private void initUI(){
        mView = LayoutInflater.from(mContext).inflate(R.layout.popwindow_cheques_layout,null);

        mClosePopwindow = (ImageView)mView.findViewById(R.id.iv_close_img);
        mClosePopwindow.setOnClickListener(v -> alertDialog.dismiss());

        mMoneyCountTv = (TextView)mView.findViewById(R.id.tv_money_count);

        mMoneyChangeTv = (TextView)mView.findViewById(R.id.tv_change_money_count);
        mMoneyChangeTv.setOnClickListener(v ->
        {
            if (mMoneyChangeTv.getText().equals("消除金额")){
                mMoneyChangeTv.setText("设置金额");
                mMoneyCountTv.setVisibility(View.GONE);
            }else{
                mMoneyChangeTv.setText("消除金额");
                mMoneyCountTv.setVisibility(View.VISIBLE);
            }
        });


    }

    public void show(){
        alertDialog = new Dialog(mContext,R.style.pxf_dialog_style);
        alertDialog.setContentView(mView, new ViewGroup.LayoutParams(AppUtil.getWidth(mContext)-AppUtil.dip2px(mContext,130), ViewGroup.LayoutParams.WRAP_CONTENT));
        alertDialog.getWindow().setGravity(Gravity.CENTER);
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }
}
