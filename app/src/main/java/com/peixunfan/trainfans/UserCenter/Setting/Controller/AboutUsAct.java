package com.peixunfan.trainfans.UserCenter.Setting.Controller;

import android.os.Bundle;
import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.R;
import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/12/2.
 */

public class AboutUsAct extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_aboutus_layout);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews(Bundle saveInstanceState) {
        super.initViews(saveInstanceState);
        mCenterTitle.setText("关于我们");
        setRightManagerTv("");
        showBackButton();
    }


    @Override
    protected void initVariables() {
    }

    @Override
    protected void loadData() {
    }
}
