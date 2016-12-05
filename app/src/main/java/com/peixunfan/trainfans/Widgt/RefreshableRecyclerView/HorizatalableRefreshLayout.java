package com.peixunfan.trainfans.Widgt.RefreshableRecyclerView;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.infrastructure.utils.AppUtil;
import com.peixunfan.trainfans.R;

/**
 * Created by chengyanfang on 2016/12/4.
 */

public class HorizatalableRefreshLayout  extends FrameLayout {

    private static final Interpolator ANIMATION_INTERPOLATOR = new LinearInterpolator();
    private static final long BACK_ANIM_DUR = 500;
    private static final int ROTATION_ANIM_DUR = 200;

    /**
     * MoreView移动的最大距离
     */
    private static float MORE_VIEW_MOVE_DIMEN;
    private static final int ROTATION_ANGLE  = 180;


    private static String REFRESH_GRAG_TIP;
    private static String REFRESH_RELEASE_TIP;

    private static String LOADMORE_GRAG_TIP;

    boolean isLeftRefresh;
    boolean isRightLoadMore;


    private float mTouchStartX;
    private float mTouchCurX;

    private float mPullWidth;

    /**
     * 目的是为了将moreView隐藏以便滑动
     */
    private int moreViewMarginRight;

    private boolean isRefresh = false;
    private boolean scrollState = false;

    private View mChildView;

    private View refreshView;
    private TextView refreshText;
    private ImageView refreshArrowIv;

    private View loadmoreView;
    private TextView loadmoreText;
    private ImageView loadmoreArrowIv;


    private ValueAnimator mBackAnimator;
    private RotateAnimation mArrowRotateAnim;
    private RotateAnimation mArrowRotateBackAnim;

    OnScrollListener onScrollListener;
    OnRefreshListener onRefreshListener;
    OnLoadMoreListener onLoadMoreListener;

    private DecelerateInterpolator interpolator = new DecelerateInterpolator(10);

    public HorizatalableRefreshLayout(Context context) {
        this(context, null, 0);
    }

    public HorizatalableRefreshLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizatalableRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }


    /**
     * 1:初始化添加左右两侧刷新
     * */
    private void init(Context context, AttributeSet attrs) {
        mPullWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, context.getResources().getDisplayMetrics());
        MORE_VIEW_MOVE_DIMEN = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, context.getResources().getDisplayMetrics());

        moreViewMarginRight = -AppUtil.dip2px(context,26);

        REFRESH_GRAG_TIP = getResources().getString(R.string.scan_more);
        REFRESH_RELEASE_TIP = getResources().getString(R.string.release_scan_more);

        LOADMORE_GRAG_TIP = getResources().getString(R.string.load_more);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PullLeftToRefreshLayout);
        ta.recycle();

        this.post(new Runnable() {
            @Override
            public void run() {
                mChildView = getChildAt(0);
                addRefreshView();
                addLoadMoreView();
                initBackAnim();
                initRotateAnim();
            }
        });
    }


    private void addRefreshView() {
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
        params.setMargins(moreViewMarginRight, 0, 0, 0);

        refreshView = LayoutInflater.from(getContext()).inflate(R.layout.item_refresh_more, this, false);
        refreshView.setLayoutParams(params);
        refreshText = (TextView) refreshView.findViewById(R.id.tvMoreText);
        refreshArrowIv = (ImageView) refreshView.findViewById(R.id.ivRefreshArrow);

        addViewInternal(refreshView);
    }


    private void addLoadMoreView() {
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.RIGHT | Gravity.CENTER_VERTICAL;
        params.setMargins(0, 0, moreViewMarginRight, 0);

        loadmoreView = LayoutInflater.from(getContext()).inflate(R.layout.item_load_more, this, false);
        loadmoreView.setLayoutParams(params);
        loadmoreText = (TextView) loadmoreView.findViewById(R.id.tvMoreText);
        loadmoreArrowIv = (ImageView) loadmoreView.findViewById(R.id.ivRefreshArrow);

        addViewInternal(loadmoreView);
    }


    private void addViewInternal(@NonNull View child) {
        super.addView(child);
    }

    @Override
    public void addView(View child) {
        if (getChildCount() >= 1) {
            throw new RuntimeException("you can only attach one child");
        }

        mChildView = child;
        super.addView(child);
    }


    /**
     * 2:初始化动画
     * */
    private void initRotateAnim() {
        int pivotType = Animation.RELATIVE_TO_SELF;
        float pivotValue = 0.5f;
        mArrowRotateAnim = new RotateAnimation(0, ROTATION_ANGLE, pivotType, pivotValue, pivotType, pivotValue);
        mArrowRotateAnim.setInterpolator(ANIMATION_INTERPOLATOR);
        mArrowRotateAnim.setDuration(ROTATION_ANIM_DUR);
        mArrowRotateAnim.setFillAfter(true);

        mArrowRotateBackAnim = new RotateAnimation(ROTATION_ANGLE, 0, pivotType, pivotValue, pivotType, pivotValue);
        mArrowRotateBackAnim.setInterpolator(ANIMATION_INTERPOLATOR);
        mArrowRotateBackAnim.setDuration(ROTATION_ANIM_DUR);
        mArrowRotateBackAnim.setFillAfter(true);
    }


    private void initBackAnim() {
        if (mChildView == null) {
            return;
        }
        mBackAnimator = ValueAnimator.ofFloat(-mPullWidth/2, 0);
        mBackAnimator.addListener(new AnimListener());
        mBackAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float val = (float) animation.getAnimatedValue();

                if (mChildView != null) {
                    if(val>0){
                        mChildView.setTranslationX(-val/2);
                    }else {
                        mChildView.setTranslationX(val/2);
                    }

                    if(isRightLoadMore){
                        moveMoreView(val,true);
                    }else if(isLeftRefresh){
                        moveRefreshView(val, true);
                    }
                }
            }
        });
        mBackAnimator.setDuration(BACK_ANIM_DUR);
    }



    /**
     * 手势拦截
     * */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (isRefresh) {
            return true;
        }

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mTouchStartX = ev.getX();
                mTouchCurX = mTouchStartX;
                setScrollState(false);
                break;
            case MotionEvent.ACTION_MOVE:
                float curX = ev.getX();
                float dx = curX - mTouchStartX;

                if (dx >10 && !canScrollLeft()) {//点击事件要传回子类
                    setScrollState(true);
                    return true;
                }

                if(dx<-10 && !canScrollRight()){
                    setScrollState(true);
                    return true;
                }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isRefresh) {
            return super.onTouchEvent(event);
        }

        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mTouchCurX = event.getX();

                float dx =mTouchCurX - mTouchStartX;

                if(dx>0){
                    dx = Math.min(mPullWidth * 2, dx);
                    dx = Math.max(0, dx);

                    if(mChildView == null || dx<0){
                        return true;
                    }

                    float unit = dx / 2;
                    float offsetx = interpolator.getInterpolation(unit / mPullWidth) * unit;
                    mChildView.setTranslationX(offsetx);
                    moveRefreshView(offsetx, false);
                    return true;
                }else {
                    float dx2 = mTouchStartX - mTouchCurX;
                    dx2 = Math.min(mPullWidth * 2, dx2);
                    dx2 = Math.max(0, dx2);

                    if (mChildView == null || dx2 <= 0) {
                        return true;
                    }

                    float unit = dx2 / 2;
                    float offsetx = interpolator.getInterpolation(unit / mPullWidth) * unit;
                    mChildView.setTranslationX(-offsetx);
                    moveMoreView(offsetx, false);
                    return true;
                }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (mChildView == null) {
                    return true;
                }

                float childDx = Math.abs(mChildView.getTranslationX());

                if (mChildView.getTranslationX()>0){
                    isLeftRefresh = true;
                    mBackAnimator.setFloatValues(-childDx, 0);
                    mBackAnimator.start();

                    if (reachRefreshReleasePoint()) {
                        isRefresh = true;
                    }

                }else {
                    isRightLoadMore = true;
                    mBackAnimator.setFloatValues(childDx, 0);
                    mBackAnimator.start();

                    if (reachLoadMoreReleasePoint()) {
                        isRefresh = true;
                    }
                }

                setScrollState(false);

                return true;
        }

        return super.onTouchEvent(event);
    }


    private boolean reachRefreshReleasePoint() {
        return REFRESH_RELEASE_TIP.equals(refreshText.getText().toString());
    }

    private boolean reachLoadMoreReleasePoint() {
        return REFRESH_RELEASE_TIP.equals(loadmoreText.getText().toString());
    }

    /**
     * direction Negative to check scrolling left, positive to check scrolling right
     *
     * @return
     */
    private boolean canScrollRight() {
        if (mChildView == null) {
            return false;
        }

        return ViewCompat.canScrollHorizontally(mChildView, 1);
    }


    private boolean canScrollLeft(){
        if(mChildView == null){
            return false;
        }

        return  ViewCompat.canScrollHorizontally(mChildView,-1) ;
    }


    private void moveRefreshView(float offsetx, boolean release) {
        float dx = offsetx / 2;
        if (dx <= MORE_VIEW_MOVE_DIMEN) {
            refreshView.setTranslationX(dx);
            if (!release && switchRefreshText(REFRESH_GRAG_TIP)) {
                refreshArrowIv.clearAnimation();
                refreshArrowIv.startAnimation(mArrowRotateBackAnim);
            }
        } else {
            if (switchRefreshText(REFRESH_RELEASE_TIP)) {
                refreshArrowIv.clearAnimation();
                refreshArrowIv.startAnimation(mArrowRotateAnim);
            }
        }

    }



    private void moveMoreView(float offsetx, boolean release) {
        float dx = offsetx / 2;
        if (dx <= MORE_VIEW_MOVE_DIMEN) {
            loadmoreView.setTranslationX(-dx);
            if (!release && switchMoreText(LOADMORE_GRAG_TIP)) {
                loadmoreArrowIv.clearAnimation();
                loadmoreArrowIv.startAnimation(mArrowRotateBackAnim);
            }
        } else {
            if (switchMoreText(REFRESH_RELEASE_TIP)) {
                loadmoreArrowIv.clearAnimation();
                loadmoreArrowIv.startAnimation(mArrowRotateAnim);
            }
        }

    }


    private boolean switchRefreshText(String text) {
        if (text.equals(refreshText.getText().toString())) {
            return false;
        }
        refreshText.setText(text);
        return true;
    }


    private boolean switchMoreText(String text) {
        if (text.equals(loadmoreText.getText().toString())) {
            return false;
        }
        loadmoreText.setText(text);
        return true;
    }




    private class AnimListener implements Animator.AnimatorListener {

        @Override
        public void onAnimationStart(Animator animator) {
        }

        @Override
        public void onAnimationEnd(Animator animator) {

            if(isRefresh){
                if(isLeftRefresh && onRefreshListener != null){
                    onRefreshListener.onRefresh();
                }

                if(isRightLoadMore && onLoadMoreListener!=null){
                    onLoadMoreListener.loadMore();
                }
            }

            isLeftRefresh =false;
            isRightLoadMore = false;

            refreshText.setText(REFRESH_GRAG_TIP);
            refreshArrowIv.clearAnimation();
            isRefresh = false;
        }

        @Override
        public void onAnimationCancel(Animator animator) {
        }

        @Override
        public void onAnimationRepeat(Animator animator) {
        }
    }

    private void setScrollState(boolean scrollState) {
        if (this.scrollState == scrollState) {
            return;
        }
        this.scrollState = scrollState;
        if (onScrollListener != null) {
            onScrollListener.onScrollChange(scrollState);
        }
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public interface OnRefreshListener {
        void onRefresh();
    }


    public interface OnLoadMoreListener {
        void loadMore();
    }


}
