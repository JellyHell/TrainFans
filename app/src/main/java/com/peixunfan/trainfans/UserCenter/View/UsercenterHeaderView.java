package com.peixunfan.trainfans.UserCenter.View;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.infrastructure.ui.ImageLoader.ImageLoader;
import com.peixunfan.trainfans.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/10.
 */

public class UsercenterHeaderView {

    private Activity mContext;
    private View mView;

    @Bind(R.id.display_image)
    SimpleDraweeView mSimpleDraweeView;

    @SuppressLint("InflateParams")
    public UsercenterHeaderView(Activity context) {
        mContext = context;
        mView = LayoutInflater.from(mContext).inflate(R.layout.view_header_usercenter, null);
        ButterKnife.bind(this,mView);
    }

    public View getView() {
        ImageLoader.progressiveLoad("http://a.hiphotos.baidu.com/image/pic/item/a08b87d6277f9e2f875fbad61a30e924b999f382.jpg",mSimpleDraweeView);

        return mView;
    }

    public void refresh(){

    }
}

