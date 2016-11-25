package com.peixunfan.trainfans;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.peixunfan.trainfans.Base.BaseFragment;

/**
 * Created by Administrator on 2016/8/9.
 */
public class CFragment  extends BaseFragment
{
    private Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        TextView textView = new TextView(mContext);
        textView.setTextSize(30);
        textView.setGravity(Gravity.CENTER);
        textView.setText("测试页面\n\n");
        textView.setBackgroundColor(0xFFececec);
        return textView;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void lazyLoad() {

    }
}