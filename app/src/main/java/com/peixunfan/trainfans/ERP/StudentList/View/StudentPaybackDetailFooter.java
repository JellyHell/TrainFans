package com.peixunfan.trainfans.ERP.StudentList.View;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

import com.peixunfan.trainfans.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/24.
 */

public class StudentPaybackDetailFooter {

    private Activity mContext;
    private View mView;

    @SuppressLint("InflateParams")
    public StudentPaybackDetailFooter(Activity context) {
        mContext = context;
        mView = LayoutInflater.from(mContext).inflate(R.layout.view_footer_student_payback, null);
        ButterKnife.bind(this,mView);
    }

    public View getView() {
        return mView;
    }

    public void refresh(){

    }
}