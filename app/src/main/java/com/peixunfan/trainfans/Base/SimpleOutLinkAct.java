package com.peixunfan.trainfans.Base;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.peixunfan.trainfans.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chengyanfang on 2016/11/30.
 */

public class SimpleOutLinkAct  extends BaseActivity {


    String mTitleStr ;
    String mOutLinkStr;

    @Bind(R.id.webview)
    WebView mWebView;

    @Bind(R.id.loadbar)
    ProgressBar mLoaderBar;

    public WebChromeClient mWebChromeClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.acitivty_outlink_layout);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews(Bundle saveInstanceState) {
        super.initViews(saveInstanceState);
        showBackButton();
    }


    @Override
    protected void initVariables() {
        mTitleStr = getIntent().getStringExtra("titleStr");
        mOutLinkStr = getIntent().getStringExtra("outLintStr");
        mCenterTitle.setText(mTitleStr);
    }

    @Override
    protected void loadData() {
        try {
            initWebView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() throws Exception {
        mWebView.requestFocusFromTouch();
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setAllowFileAccess(true);
        webSettings.setPluginState(WebSettings.PluginState.ON);
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setUseWideViewPort(true);
        mWebView.addJavascriptInterface(new Handler(), "handler");
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.requestFocus();
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webSettings.setAllowFileAccess(true);
        webSettings.setBuiltInZoomControls(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                view.loadUrl("javascript:window.handler.show('<head>'+" + "document.getElementsByTagName('html')[0].innerHTML+'</head>');");
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed(); // 接受所有证书
            }

            @Override
            public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
                // TODO Auto-generated method stub
                super.onReceivedHttpAuthRequest(view, handler, host, realm);
            }

        });

        // 设置WebChromeClient
        mWebChromeClient = new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    dismissProgressHUD();
                    mLoaderBar.setProgress(0);
                    mLoaderBar.setVisibility(View.GONE);
                } else {
                    mLoaderBar.setVisibility(View.VISIBLE);
                    mLoaderBar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                // TODO Auto-generated method stub
                super.onReceivedTitle(view, title);
            }

        };

        try {
            mWebView.setWebChromeClient(mWebChromeClient);
        } catch (Exception e) {
            // TODO: handle exception
        }
        loadWebView();

    }

    private void loadWebView() {
        try {
            // 让网页自适应屏幕宽度
            WebSettings webSettings = mWebView.getSettings(); // webView:
            webSettings.setDomStorageEnabled(true);
            webSettings.setUseWideViewPort(true);
            webSettings.setLoadWithOverviewMode(true);
            mWebView.loadUrl(mOutLinkStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebView.clearCache(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
            } else {
                super.finish();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}
