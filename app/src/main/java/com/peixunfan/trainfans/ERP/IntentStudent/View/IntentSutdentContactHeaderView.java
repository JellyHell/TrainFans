package com.peixunfan.trainfans.ERP.IntentStudent.View;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.infrastructure.utils.AppUtil;
import com.infrastructure.utils.IntentUtil;
import com.peixunfan.trainfans.ERP.IntentStudent.Controller.EditTagAct;
import com.peixunfan.trainfans.R;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/11/30.
 */

public class IntentSutdentContactHeaderView {

    private Activity mContext;
    private View mView;

    LinearLayout mTagLayout;

    ImageView mEditTagImg;

    @SuppressLint("InflateParams")
    public IntentSutdentContactHeaderView(Activity context) {
        mContext = context;
        mView = LayoutInflater.from(mContext).inflate(R.layout.view_header_intent_student_contacts, null);

        mTagLayout = (LinearLayout)mView.findViewById(R.id.llv_taglayout);

        mEditTagImg = (ImageView)mView.findViewById(R.id.iv_edit_tag);
        mEditTagImg.setOnClickListener(v -> IntentUtil.forwordToActivity(mContext, EditTagAct.class));

        ButterKnife.bind(this,mView);
    }

    public View getView() {
        return mView;
    }

    public void refresh(){

    }


    public void setTagView(ArrayList<String> mTags,boolean isEdit){

        mTagLayout.removeAllViews();

        int size = mTags.size();
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT); // 每行的水平LinearLayout
        layoutParams.setMargins(10, 0, 10, 0);

        LinearLayout.LayoutParams itemParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        itemParams.setMargins(AppUtil.dip2px(mContext,4),AppUtil.dip2px(mContext,12),AppUtil.dip2px(mContext,4),AppUtil.dip2px(mContext,0));

        ArrayList<TagView> childBtns = new ArrayList<TagView>();

        int currentWidth = 0;
        for(int i = 0; i < size; i++){

            //1.初始化tagview
            TagView tagView = new TagView(mContext,mTags.get(i),isEdit,i);
            tagView.getView().setLayoutParams(itemParams);
            tagView.getView().setTag(i);
            tagView.getView().setOnClickListener(v -> {
                if(isEdit && mTagClickCallback!=null){
                    mTagClickCallback.onTagClick((Integer)v.getTag());
                }
            });
            childBtns.add(tagView);

            //2.判断当前的长度
            String item = mTags.get(i);
            int length= item.length();
            currentWidth = currentWidth + (int)AppUtil.sp2px(mContext,14)*length + AppUtil.dip2px(mContext,20);
            if(isEdit){
                currentWidth = currentWidth + AppUtil.dip2px(mContext,18);
            }

            if(currentWidth > AppUtil.getWidth(mContext)){
                LinearLayout  horizLL = new LinearLayout(mContext);
                horizLL.setOrientation(LinearLayout.HORIZONTAL);
                horizLL.setLayoutParams(layoutParams);

                for(TagView addBtn:childBtns.subList(0,childBtns.size()-1)){
                    horizLL.addView(addBtn.getView());
                }
                mTagLayout.addView(horizLL);

                childBtns.clear();
                currentWidth = 0;

                childBtns.add(tagView);
                currentWidth = currentWidth + (int)AppUtil.sp2px(mContext,14)*length + AppUtil.dip2px(mContext,20);
            }
        }


        LinearLayout.LayoutParams lastLinelayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT); // 每行的水平LinearLayout
        lastLinelayoutParams.setMargins(10, 0, 10, AppUtil.dip2px(mContext,10));

        //最后一行添加一下
        if(!childBtns.isEmpty()){
            LinearLayout horizLL = new LinearLayout(mContext);
            horizLL.setOrientation(LinearLayout.HORIZONTAL);
            horizLL.setLayoutParams(lastLinelayoutParams);

            for(TagView addBtn:childBtns){
                horizLL.addView(addBtn.getView());
            }
            mTagLayout.addView(horizLL);
            childBtns.clear();
        }

    }


    public interface TagClickCallback{
        void onTagClick(int index);
    }

    TagClickCallback mTagClickCallback;

    public void setTagClickCallback(TagClickCallback tagClickCallback) {
        this.mTagClickCallback = tagClickCallback;
    }
}
