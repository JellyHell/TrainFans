package com.peixunfan.trainfans.ERP.IntentStudent.View;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.peixunfan.trainfans.R;

import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/11/30.
 */

public class TagView {

    private Activity mContext;
    private View mView;

    private TextView mTagName;
    private ImageView mDelTagImg;

    @SuppressLint("InflateParams")
    public TagView(Activity context,String tagName,boolean isEdit,int index) {
        mContext = context;
        mView = LayoutInflater.from(mContext).inflate(R.layout.view_pxf_tag, null);

        mTagName = (TextView)mView.findViewById(R.id.tv_tagname);
        mDelTagImg = (ImageView)mView.findViewById(R.id.iv_delete_tag);

        mTagName.setText(tagName);

        if(isEdit){
            mDelTagImg.setVisibility(View.VISIBLE);
        }else {
            mDelTagImg.setVisibility(View.GONE);
        }

        switch (index%5){
            case 0:
                mView.setBackgroundResource(R.drawable.bg_tag_e67e63);
                mTagName.setTextColor(context.getResources().getColor(R.color.color_e67e63));
                break;
            case 1:
                mView.setBackgroundResource(R.drawable.bg_tag_529ce7);
                mTagName.setTextColor(context.getResources().getColor(R.color.color_529ce7));
                break;
            case 2:
                mView.setBackgroundResource(R.drawable.bg_tag_f1b461);
                mTagName.setTextColor(context.getResources().getColor(R.color.color_f1b461));
                break;
            case 3:
                mTagName.setTextColor(context.getResources().getColor(R.color.color_15b98b));
                mView.setBackgroundResource(R.drawable.bg_tag_15b98b);
                break;
            case 4:
                mTagName.setTextColor(context.getResources().getColor(R.color.color_8f6fe9));
                mView.setBackgroundResource(R.drawable.bg_tag_8f6fe9);
                break;
        }

        ButterKnife.bind(this,mView);
    }

    public View getView() {
        return mView;
    }

    public void refresh(){

    }

    public void setDisabled(){
        mTagName.setTextColor(mContext.getResources().getColor(R.color.color_a0a0a0));
        mView.setBackgroundResource(R.drawable.bg_tag_a0a0a0);
    }
}
