package com.peixunfan.trainfans.ERP.PayoffMoney.Controller;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.TextView;

import com.infrastructure.net.RetrofitSingleton;
import com.infrastructure.utils.AppUtil;
import com.infrastructure.utils.IntentUtil;
import com.infrastructure.utils.StringUtil;
import com.peixunfan.trainfans.Api.ApiProvider;
import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.ERP.AttendClassRecord.Controller.NoAttendStudentListAct;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.Recovery.Model.ArticleList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;

/**
 * Created by chengyanfang on 2016/11/29.
 */

public class PayoffMoneyHomeAct  extends BaseActivity implements Observer<ArticleList>,View.OnClickListener {

    @Bind(R.id.tv_money_count)
    TextView mMoneyCount;

    /**课酬详情布局*/
    @Bind(R.id.cardview_moneyinfo)
    CardView mMoneyInfoCardView;


    /**本月课酬*/
    @Bind(R.id.cardview_thismonth_moneyinfo)
    CardView mThisMonthCardView;

    /**历史课酬*/
    @Bind(R.id.cardview_history_moneyinfo)
    CardView mHistoryCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_payoffmoney_homelayout);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {
    }

    @Override
    protected void initViews(Bundle saveInstanceState) {
        super.initViews(saveInstanceState);
        mCenterTitle.setText("课酬核算");
        mRightManagerBt.setImageResource(R.drawable.icon_noattend_class);
        showBackButton();


        SpannableString yesterdaySpanString = new SpannableString(getString(R.string.money_lable )+ StringUtil.getFormatedFloatString("100")+"元");
        AbsoluteSizeSpan span = new AbsoluteSizeSpan((int) AppUtil.sp2px(this, 44));
        yesterdaySpanString.setSpan(span, 1, StringUtil.getFormatedFloatString("100").length()-2, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mMoneyCount.setText(yesterdaySpanString);

        mMoneyInfoCardView.setOnClickListener(this);
        mThisMonthCardView.setOnClickListener(this);
        mHistoryCardView.setOnClickListener(this);
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
        IntentUtil.forwordToActivity(this,NoPayedListAct.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cardview_moneyinfo:{
                IntentUtil.forwordToActivity(this,PayoffMoneyCountAct.class);
                break;
            }
            case R.id.cardview_thismonth_moneyinfo:{
                IntentUtil.forwordToActivity(this,PayoffMoneyCountAct.class);
                break;
            }
            case R.id.cardview_history_moneyinfo:{
                IntentUtil.forwordToActivity(this,HistoryMoneyAct.class);
                break;
            }
        }
    }
}
