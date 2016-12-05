package com.peixunfan.trainfans.UserCenter.InstitutionInfo.Controller;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.infrastructure.net.RetrofitSingleton;
import com.peixunfan.trainfans.Api.ApiProvider;
import com.peixunfan.trainfans.Base.BaseActivity;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.Recovery.Model.ArticleList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;

/**
 * Created by chengyanfang on 2016/12/1.
 */

public class BuySmsAct extends BaseActivity implements Observer<ArticleList>,View.OnClickListener {

    /**购买1000*/
    @Bind(R.id.rlv_one_price_content)
    RelativeLayout mBuyOneLayout;

    @Bind(R.id.tv_one_price_content)
    TextView mOnePrice;

    /**购买2000*/
    @Bind(R.id.rlv_two_price_content)
    RelativeLayout mBuyTwoLayout;

    @Bind(R.id.tv_two_price_content)
    TextView mTwoPrice;

    /**购买5000*/
    @Bind(R.id.rlv_five_price_content)
    RelativeLayout mBuyFiveLayout;

    @Bind(R.id.tv_five_price_content)
    TextView mFivePrice;

    /**购买*/
    @Bind(R.id.rlv_buy_sms)
    RelativeLayout mBuyNowLayout;


    @Bind(R.id.tv_sms_count)
    TextView mSMSCount;


    int buyType = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_buy_sms_layout);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initVariables() {
    }

    @Override
    protected void initViews(Bundle saveInstanceState) {
        super.initViews(saveInstanceState);
        mCenterTitle.setText("购买短信");
        showBackButton();

        mBuyOneLayout.setOnClickListener(this);
        mBuyTwoLayout.setOnClickListener(this);
        mBuyFiveLayout.setOnClickListener(this);
        mBuyNowLayout.setOnClickListener(this);
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
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.rlv_one_price_content:{
                buyType = 0;
                refreshPriceView();
                break;
            }
            case R.id.rlv_two_price_content:{
                buyType = 1;
                refreshPriceView();
                break;
            }
            case R.id.rlv_five_price_content:{
                buyType = 2;
                refreshPriceView();
                break;
            }
            case R.id.rlv_buy_sms:{
                break;
            }
        }
    }


    private void refreshPriceView(){
        switch (buyType){
            case 0:
            {
                mBuyOneLayout.setBackgroundResource(R.drawable.bg_price_border_maincolor);
                mBuyTwoLayout.setBackgroundResource(R.drawable.bg_price_border_color_eeeeee);
                mBuyFiveLayout.setBackgroundResource(R.drawable.bg_price_border_color_eeeeee);

                mOnePrice.setTextColor(getResources().getColor(R.color.main_color));
                mTwoPrice.setTextColor(getResources().getColor(R.color.color_7a7a81));
                mFivePrice.setTextColor(getResources().getColor(R.color.color_7a7a81));

                mSMSCount.setText("1000");
                break;
            }
            case 1:
            {
                mBuyOneLayout.setBackgroundResource(R.drawable.bg_price_border_color_eeeeee);
                mBuyTwoLayout.setBackgroundResource(R.drawable.bg_price_border_maincolor);
                mBuyFiveLayout.setBackgroundResource(R.drawable.bg_price_border_color_eeeeee);

                mOnePrice.setTextColor(getResources().getColor(R.color.color_7a7a81));
                mTwoPrice.setTextColor(getResources().getColor(R.color.main_color));
                mFivePrice.setTextColor(getResources().getColor(R.color.color_7a7a81));

                mSMSCount.setText("2000");
                break;
            }
            case 2:
            {
                mBuyOneLayout.setBackgroundResource(R.drawable.bg_price_border_color_eeeeee);
                mBuyTwoLayout.setBackgroundResource(R.drawable.bg_price_border_color_eeeeee);
                mBuyFiveLayout.setBackgroundResource(R.drawable.bg_price_border_maincolor);

                mOnePrice.setTextColor(getResources().getColor(R.color.color_7a7a81));
                mTwoPrice.setTextColor(getResources().getColor(R.color.color_7a7a81));
                mFivePrice.setTextColor(getResources().getColor(R.color.main_color));

                mSMSCount.setText("5000");
                break;
            }
        }
    }


}
