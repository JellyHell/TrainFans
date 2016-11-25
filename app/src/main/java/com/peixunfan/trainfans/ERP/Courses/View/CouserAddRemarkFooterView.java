package com.peixunfan.trainfans.ERP.Courses.View;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

import com.peixunfan.trainfans.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/25.
 */

public class CouserAddRemarkFooterView  {

    private Activity mContext;
    private View mView;

    @SuppressLint("InflateParams")
    public CouserAddRemarkFooterView(Activity context) {
        mContext = context;
        mView = LayoutInflater.from(mContext).inflate(R.layout.view_footerview_addremark_layout, null);
        ButterKnife.bind(this,mView);
    }

    public View getView() {
        return mView;
    }

    public void refresh(){

    }
}
