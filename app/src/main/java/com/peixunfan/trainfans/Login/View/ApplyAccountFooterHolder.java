package com.peixunfan.trainfans.Login.View;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.peixunfan.trainfans.R;

/**
 * Created by chengyanfang on 2016/11/28.
 */

public class ApplyAccountFooterHolder extends RecyclerView.ViewHolder {

    RelativeLayout mTopTitleLayout;
    RelativeLayout mUploadPhotoLayout;


    public ApplyAccountFooterHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        mTopTitleLayout = (RelativeLayout)itemView.findViewById(R.id.rlv_top_title);
        mUploadPhotoLayout = (RelativeLayout)itemView.findViewById(R.id.rlv_photo);
    }
}
