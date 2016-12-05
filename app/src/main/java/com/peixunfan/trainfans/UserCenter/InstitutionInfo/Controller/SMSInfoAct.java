package com.peixunfan.trainfans.UserCenter.InstitutionInfo.Controller;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.infrastructure.net.RetrofitSingleton;
import com.infrastructure.utils.AppUtil;
import com.infrastructure.utils.IntentUtil;
import com.peixunfan.trainfans.Api.ApiProvider;
import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.ERP.PayoffMoney.Controller.NoPayedListAct;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.Recovery.Model.ArticleList;
import com.peixunfan.trainfans.Widgt.popupwindow.PXFSharePopWindow;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;

/**
 * Created by chengyanfang on 2016/12/1.
 */

public class SMSInfoAct extends BaseActivity implements Observer<ArticleList> {

    @Bind(R.id.tv_money_count)
    TextView mMoneyCount;

    /**购买短信*/
    @Bind(R.id.rlv_buy_sms)
    RelativeLayout mBuySmsLayout;

    /**分享*/
    @Bind(R.id.rlv_share_layout)
    RelativeLayout mShareLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_smsinfo_layout);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {
    }

    @Override
    protected void initViews(Bundle saveInstanceState) {
        super.initViews(saveInstanceState);
        mCenterTitle.setText("短信余量");
        setRightManagerTv("购买记录");
        showBackButton();


        SpannableString yesterdaySpanString = new SpannableString("100条");
        AbsoluteSizeSpan span = new AbsoluteSizeSpan((int) AppUtil.sp2px(this, 44));
        yesterdaySpanString.setSpan(span, 0, yesterdaySpanString.length()-1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mMoneyCount.setText(yesterdaySpanString);

        mShareLayout.setOnClickListener(v -> new PXFSharePopWindow(this,mShareLayout).show());

        mBuySmsLayout.setOnClickListener(v -> IntentUtil.forwordToActivity(this,BuySmsAct.class));
    }

    @Override
    protected void loadData() {
        ApiProvider.getInstance().getRecommendBorrow(this,String.valueOf(mPage));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        RetrofitSingleton.disposeFailureInfo(e,this);
    }

    @Override
    public void onNext(ArticleList articleList) {

    }

    @Override
    protected void onRightManagerBtClick() {
        super.onRightManagerBtClick();
        IntentUtil.forwordToActivity(this,BuySmsRecordAct.class);
    }

}
