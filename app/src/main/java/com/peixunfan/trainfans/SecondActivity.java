package com.peixunfan.trainfans;

import android.os.Bundle;
import android.widget.TextView;

import com.infrastructure.net.RxBus;
import com.jakewharton.rxbinding.view.RxView;
import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.Config.BusEventTag;

import butterknife.Bind;
import butterknife.ButterKnife;
/**
 * Created by Administrator on 2016/8/10.
 */
public class SecondActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_home_base);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
//        RxView.clicks(mTextView).subscribe(mTextView -> {
//            RxBus.getDefault().post(BusEventTag.RXBUS_TEST);
//        });
    }

    @Override
    protected void initVariables() {

    }

    @Override
    protected void initViews(Bundle saveInstanceState) {

    }

    @Override
    protected void loadData() {

    }
}
