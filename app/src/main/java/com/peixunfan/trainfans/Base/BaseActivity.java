package com.peixunfan.trainfans.Base;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.infrastructure.ui.progressHudView.ProgressHUD;
import com.peixunfan.trainfans.R;

import butterknife.Bind;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by Administrator on 2016/8/22.
 */
public abstract class BaseActivity extends SwipeBackActivity {

    /****************Title UI*****************/
    @Bind(R.id.tv_basetitle_cetener)
    protected TextView mCenterTitle;

    @Bind(R.id.iv_menu_arrow)
    protected ImageView mMenuArrow;

    @Bind(R.id.iv_right_manager_bt)
    protected  ImageView mRightManagerBt;

    @Bind(R.id.iv_right_second_manager_bt)
    protected  ImageView mRightSecondManagerBt;

    @Bind(R.id.tv_right_manager_tv)
    protected  TextView mRightManagerTv;

    @Bind(R.id.iv_basetitle_leftimg)
    protected ImageView mTitleBackIv;

    /****************Search UI*****************/
    @Bind(R.id.rtl_search_title)
    protected RelativeLayout mSearchLayout;

    @Bind(R.id.query)
    protected EditText mQuery;

    @Bind(R.id.search_clear)
    protected ImageButton mCleachBt;

    @Bind(R.id.tv_cancle_bt)
    protected TextView mCancleBt;

    /****************Public Data*****************/
    protected ProgressHUD mProgressHUD;

    protected Handler mHandler;

    private SwipeBackLayout mSwipeBackLayout;

    protected int mPage = 1;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initHandler();
        initVariables();
        initViews(savedInstanceState);
        loadData();
        mSwipeBackLayout = new SwipeBackLayout(this);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mSwipeBackLayout.setLayoutParams(params);
    }

    /**
     * abstract 方法
     * */
    protected abstract void initVariables();
    protected abstract void loadData();

    protected void initViews(Bundle saveInstanceState){
        mCancleBt.setOnClickListener(view -> cancleSearch());

        mRightManagerBt.setOnClickListener(view -> onRightManagerBtClick());

        mRightSecondManagerBt.setOnClickListener(view -> onRightSecondManagerBtClick());

        mQuery.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                startSearch(s.toString());
                if (s.length() > 0) {
                    mCleachBt.setVisibility(View.VISIBLE);
                } else {
                    mCleachBt.setVisibility(View.INVISIBLE);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        });

        mCleachBt.setOnClickListener(view -> {
            mQuery.getText().clear();
            hideSoftKeyboard();
        });
    }

    private void initHandler() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
            }
        };
    }

    /****************ProgressHUD处理*****************/
    protected void showProgressHUD(Context context, String showMessage) {
        if (this.isFinishing())
            return;
        mProgressHUD = ProgressHUD.show(context, showMessage, true, null);
    }

    public void dismissProgressHUD() {
        try {
            if (mProgressHUD != null)
                mProgressHUD.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /****************NavgationBar处理*****************/
    protected void showBackButton(){
        mTitleBackIv.setVisibility(View.VISIBLE);
        mTitleBackIv.setOnClickListener(view -> this.finish());
    }

    /**
     * 显示/隐藏SearchView
     * */
    protected void showSearchView(boolean show){
        if(show){
            mSearchLayout.setVisibility(View.VISIBLE);
            showSoftKeyboard(mQuery,this);
        }else{
            mSearchLayout.setVisibility(View.GONE);
            hideSoftKeyboard();
            mQuery.getText().clear();
        }
    }

    /**
     * 开始搜索
     * */
    protected void startSearch(String searchKey){

    }

    /**
     * 取消搜索
     * */
    protected void cancleSearch(){
        showSearchView(false);
    }

    protected void setRightManagerTv(String rightManagerTv){
        mRightManagerTv.setVisibility(View.VISIBLE);
        mRightManagerTv.setText(rightManagerTv);
        mRightManagerTv.setOnClickListener(view -> onRightManagerBtClick());
    }

    protected void onRightManagerBtClick(){

    }

    protected void onRightSecondManagerBtClick(){

    }

    /****************隐藏软键盘*****************/
    protected void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    protected void showSoftKeyboard(View view,Context mContext) {
        if (view.requestFocus()) {
            InputMethodManager imm = (InputMethodManager)mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }


    /****************退出动画*****************/
    protected boolean mIsFirstAnim = false;
    @Override
    public void finish() {
        // TODO Auto-generated method stub
        super.finish();
        if (mIsFirstAnim) {
            inFromRightOutToLeft();
            mIsFirstAnim = false;
        } else {
            inFromLeftOutToRight();
        }
    }
    private void inFromRightOutToLeft() {
		overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }

    private void inFromLeftOutToRight() {
		overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
    }
}
