package com.peixunfan.trainfans.Widgt.loadMoreView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.peixunfan.trainfans.R;

/**
 * Created by Administrator on 2016/8/12.
 */
public class LoadMoreView  {

    private Context mContext;
    private View mView;
    private TextView mTextView;

    @SuppressLint("InflateParams")
    public LoadMoreView(Context context) {
        mContext = context;
        mView = LayoutInflater.from(mContext).inflate(
                R.layout.widgt_loadmoreview_layout, null);
        mTextView = (TextView) mView.findViewById(R.id.loadmore_text);
        mTextView.setText("加载中...");
    }

    public View getView() {
        return mView;
    }

}
