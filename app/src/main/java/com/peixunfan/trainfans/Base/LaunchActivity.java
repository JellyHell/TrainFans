package com.peixunfan.trainfans.Base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;

import com.infrastructure.utils.IntentUtil;
import com.peixunfan.trainfans.Login.Controller.LoginAct;
import com.peixunfan.trainfans.R;
import com.peixunfan.trainfans.Utils.SPManager.UserInfoMangager;

/**
 * Created by Administrator on 2016/8/9.
 */
public class LaunchActivity  extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_welcome_layout);

        new Handler().postDelayed(() -> forwardToHomeAct(), 1500);
    }

    private void forwardToHomeAct(){
        if(UserInfoMangager.isLogined(this)){
            IntentUtil.forwordToActivity(this, BaseTabActivity.class);
        }else{
            IntentUtil.forwordToActivity(this, LoginAct.class);
        }
        finish();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

}
