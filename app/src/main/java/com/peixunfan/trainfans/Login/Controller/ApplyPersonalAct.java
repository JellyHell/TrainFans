package com.peixunfan.trainfans.Login.Controller;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;

import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.Widgt.popupwindow.PublicMenuSelectPopWindow;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/11/28.
 */

public class ApplyPersonalAct extends BaseActivity {

    ArrayList<String> mSelectPhotoType = new ArrayList<>();

    @Bind(R.id.rlv_photo)
    RelativeLayout mSelectPhotoLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_applyaccount_personal);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {
        mSelectPhotoType = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.select_photo)));
    }

    @Override
    protected void initViews(Bundle saveInstanceState) {
        super.initViews(saveInstanceState);
        mCenterTitle.setText("创建机构");
        setRightManagerTv("提交");
        showBackButton();

        mSelectPhotoLayout.setOnClickListener(v -> {
            new PublicMenuSelectPopWindow(this, mSelectPhotoLayout, mSelectPhotoType, "", (parent, view, position, id) -> {

            }).show();
        });
    }

    @Override
    protected void loadData() {

    }

}
