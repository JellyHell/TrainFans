package com.peixunfan.trainfans.ERP.Bill.View;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.infrastructure.utils.AppUtil;
import com.infrastructure.utils.StringUtil;
import com.peixunfan.trainfans.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/11/30.
 */

public class BillHomeHeaderView  {

    private Activity mContext;
    private View mView;

    @Bind(R.id.tv_money_count)
    TextView mMoneyCount;


    @SuppressLint("InflateParams")
    public BillHomeHeaderView(Activity context) {
        mContext = context;
        mView = LayoutInflater.from(mContext).inflate(R.layout.view_billhome_header_view, null);
        ButterKnife.bind(this,mView);

        initView();
    }

    private void initView(){
        SpannableString yesterdaySpanString = new SpannableString(mContext.getString(R.string.money_lable )+ StringUtil.getFormatedFloatString("100")+"元");
        AbsoluteSizeSpan span = new AbsoluteSizeSpan((int) AppUtil.sp2px(mContext, 44));
        yesterdaySpanString.setSpan(span, 1, StringUtil.getFormatedFloatString("100").length()-2, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mMoneyCount.setText(yesterdaySpanString);
    }

    public View getView() {
        return mView;
    }

}