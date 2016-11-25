package com.peixunfan.trainfans.ERP.SignUp.View;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.peixunfan.trainfans.R;

/**
 * Created by Administrator on 2016/11/21.
 */

public class SignUpFooterHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public ImageView titleIcon;

    public SignUpFooterHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        title = (TextView) itemView.findViewById(R.id.tv_sign_footer_title);
        titleIcon = (ImageView) itemView.findViewById(R.id.iv_sign_footer);
    }
}
