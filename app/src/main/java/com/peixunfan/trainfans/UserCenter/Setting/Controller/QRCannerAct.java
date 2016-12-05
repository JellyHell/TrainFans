package com.peixunfan.trainfans.UserCenter.Setting.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.widget.ImageView;

import com.infrastructure.ui.supertoast.SuperToast;
import com.peixunfan.trainfans.R;

import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerActivity;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.QRCodeDecoder;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

/**
 * Created by chengyanfang on 2016/12/1.
 */

public class QRCannerAct extends Activity implements QRCodeView.Delegate{

    private static final int REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY = 666;

    private QRCodeView mQRCodeView;

    ImageView mCloseImg;
    ImageView mSelectImg;
    ImageView mSwitchFlashImg;

    boolean isOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_capture);
        super.onCreate(savedInstanceState);

        initView();
    }


    private void initView(){
        mQRCodeView = (ZXingView) findViewById(R.id.zxingview);
        mQRCodeView.setDelegate(this);
        mQRCodeView.startSpot();

        mCloseImg = (ImageView)findViewById(R.id.zxing_camera_colse);
        mSwitchFlashImg = (ImageView)findViewById(R.id.zxing_camera_light);
        mSelectImg = (ImageView)findViewById(R.id.zxing_camera_photo_album);

        mCloseImg.setOnClickListener(v -> this.finish());
        mSwitchFlashImg.setOnClickListener(v -> {
            if(isOpen){
                isOpen = false;
                mQRCodeView.closeFlashlight();
                mSwitchFlashImg.setBackgroundResource(R.drawable.image_light_off);
            }else{
                isOpen = true;
                mQRCodeView.openFlashlight();
                mSwitchFlashImg.setBackgroundResource(R.drawable.image_light_on);
            }
        });

        mSelectImg.setOnClickListener(v -> startActivityForResult(BGAPhotoPickerActivity.newIntent(this, null, 1, null, false), REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY));

    }


    @Override
    protected void onStart() {
        super.onStart();
        mQRCodeView.startCamera();
    }

    @Override
    protected void onStop() {
        mQRCodeView.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mQRCodeView.onDestroy();
        super.onDestroy();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        SuperToast.show(result,this);
        vibrate();
        mQRCodeView.startSpot();
    }

    @Override
    public void onScanQRCodeOpenCameraError(){
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        mQRCodeView.showScanRect();

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY) {

            final String picturePath = BGAPhotoPickerActivity.getSelectedImages(data).get(0);
            new AsyncTask<Void, Void, String>() {
                @Override
                protected String doInBackground(Void... params) {
                    return QRCodeDecoder.syncDecodeQRCode(picturePath);
                }

                @Override
                protected void onPostExecute(String result) {
                    if (TextUtils.isEmpty(result)) {
                        SuperToast.show("未发现二维码",QRCannerAct.this);
                    } else {
                        SuperToast.show(result,QRCannerAct.this);
                    }
                }
            }.execute();
        }
    }
}
