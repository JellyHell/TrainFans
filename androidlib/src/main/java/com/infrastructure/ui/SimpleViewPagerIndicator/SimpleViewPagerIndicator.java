package com.infrastructure.ui.SimpleViewPagerIndicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SimpleViewPagerIndicator extends LinearLayout {
    /**选中颜色*/
    private static final int COLOR_TEXT_NORMAL = 0xFF7B7B81;
    /**正常颜色*/
    private static final int COLOR_INDICATOR_COLOR = 0xFF1AD6A8;
    /**标题*/
    private String[] mTitles;

    private int mTabCount;
    private int mTabWidth;
    private float mTranslationX;
    private Paint mPaint = new Paint();

    public SimpleViewPagerIndicator(Context context) {
        this(context, null);
    }

    public SimpleViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint.setColor(COLOR_INDICATOR_COLOR);
        mPaint.setStrokeWidth(9.0F);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTabWidth = w / mTabCount;
    }

    public void setTitles(String[] titles) {
        mTitles = titles;
        mTabCount = titles.length;
        generateTitleView();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.save();
        canvas.translate(mTranslationX, getHeight() - 2);
        canvas.drawLine(0, 0, mTabWidth, 0, mPaint);
        canvas.restore();
    }

    public void scroll(int position, float offset) {
        mTranslationX = getWidth() / mTabCount * (position + offset);
        invalidate();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    private void generateTitleView() {
        if (getChildCount() > 0)
            this.removeAllViews();
        int count = mTitles.length;

        setWeightSum(count);
        for (int i = 0; i < count; i++) {
            TextView tv = new TextView(getContext());
            LayoutParams lp = new LayoutParams(0,
                    LayoutParams.MATCH_PARENT);
            lp.weight = 1;
            tv.setGravity(Gravity.CENTER);
            tv.setTextColor(COLOR_TEXT_NORMAL);
            tv.setText(mTitles[i]);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
            TextPaint tp = tv.getPaint();
            tv.setLayoutParams(lp);
            tv.setTag(i);
            tv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mChangeIndicatorCallback != null) {
                        mChangeIndicatorCallback.changeWithCurrentIndex((int) (v.getTag()));
                    }
                }
            });
            addView(tv);

            if (i == 0) {
                tv.setTextColor(COLOR_INDICATOR_COLOR);
            } else {
                tv.setTextColor(COLOR_TEXT_NORMAL);
            }
        }
    }

    /**点击按钮回调接口*/
    public interface ChangeIndicatorCallback {
        public void changeWithCurrentIndex(int index);
    }

    private ChangeIndicatorCallback mChangeIndicatorCallback;

    public void setaChangeIndicatorCallback(ChangeIndicatorCallback aChangeIndicatorCallback) {
        this.mChangeIndicatorCallback = aChangeIndicatorCallback;
    }

    public void setChildTextSelected(int selectIndex) {
        for (int i = 0;i<mTabCount;i++)
        {
            if (i == selectIndex){
                ((TextView) getChildAt(i)).setTextColor(COLOR_INDICATOR_COLOR);
            }else{
                ((TextView) getChildAt(i)).setTextColor(COLOR_TEXT_NORMAL);
            }
        }
    }

}
