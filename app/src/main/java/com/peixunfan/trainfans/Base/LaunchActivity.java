package com.peixunfan.trainfans.Base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;

import com.peixunfan.trainfans.R;

/**
 * Created by Administrator on 2016/8/9.
 */
public class LaunchActivity  extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_welcome_layout);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                forwardToHomeAct();
            }
        }, 1500);
    }

    private void forwardToHomeAct(){
        Intent aIntent = new Intent(this,BaseTabActivity.class);
        startActivity(aIntent);
        finish();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return false;
    }

}
