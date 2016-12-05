package com.peixunfan.trainfans.UserCenter.UserInfo.View;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.peixunfan.trainfans.R;

import butterknife.Bind;

/**
 * Created by chengyanfang on 2016/12/1.
 */

public class MyInstitutionContentViewHolder extends RecyclerView.ViewHolder {

    /**机构头像*/
    @Bind(R.id.institution_image)
    SimpleDraweeView mInstitutionHeaderImg;

    /**分割线*/
    public View line;

    public MyInstitutionContentViewHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        line = itemView.findViewById(R.id.line);
        mInstitutionHeaderImg = (SimpleDraweeView)itemView.findViewById(R.id.institution_image);
    }
}
