package com.peixunfan.trainfans.ERP.StudentList.View;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.infrastructure.ui.ImageLoader.ImageLoader;
import com.peixunfan.trainfans.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/23.
 */

public class StudentContactHeaderView {

    private Activity mContext;
    private View mView;

    @SuppressLint("InflateParams")
    public StudentContactHeaderView(Activity context) {
        mContext = context;
        mView = LayoutInflater.from(mContext).inflate(R.layout.view_header_student_contacts, null);
        ButterKnife.bind(this,mView);
    }

    public View getView() {
        return mView;
    }

    public void refresh(){

    }
}
