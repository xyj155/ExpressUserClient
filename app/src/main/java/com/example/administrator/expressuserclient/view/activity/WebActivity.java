package com.example.administrator.expressuserclient.view.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.administrator.expressuserclient.R;
import com.example.administrator.expressuserclient.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * @author Administrator
 */
public class WebActivity extends BaseActivity {

    String urlurl;

    boolean b = false;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.webview)
    WebView webView;


    @Override
    public int intiLayout() {
        return R.layout.web_activity;
    }

    @Override
    public void initView(Bundle savedInstanceState) {
        // TODO: add setContentView(...) invocation
        ButterKnife.inject(this);
        initToolBar().setToolNavigationIco(R.mipmap.ic_back)
                .setToolNavigationIcoOnClickListener(new OnClickListener() {
                    @Override
                    public void OnClickListener() {
                        finish();
                    }
                });
        webView.loadUrl(getIntent().getStringExtra("url"));
        // TODO: add setContentView(...) invocation
        WebSettings webSettings = webView.getSettings();

// 支持javascript
        webSettings.setJavaScriptEnabled(true);

// 支持使用localStorage(H5页面的支持)
        webSettings.setDomStorageEnabled(true);

// 支持数据库
        webSettings.setDatabaseEnabled(true);

// 支持缓存
        webSettings.setAppCacheEnabled(true);
        String appCaceDir = this.getApplicationContext().getDir("cache", Context.MODE_PRIVATE).getPath();
        webSettings.setAppCachePath(appCaceDir);

// 设置可以支持缩放
        webSettings.setUseWideViewPort(true);

// 扩大比例的缩放
        webSettings.setSupportZoom(true);

        webSettings.setBuiltInZoomControls(true);

// 隐藏缩放按钮
        webSettings.setDisplayZoomControls(false);

// 自适应屏幕
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true);

// 隐藏滚动条
        webView.setHorizontalScrollBarEnabled(false);
        webView.setVerticalScrollBarEnabled(false);

// 进度显示及隐藏


        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String s, String s1, String s2, String s3, long l) {
                downloadByBrowser(s);
            }
        });

// 处理网页内的连接（自身打开）
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                showmDialog("数据加载中...");

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                hidemDialog();
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                hidemDialog();
            }


            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                hidemDialog();
                return true;


            }


        });

// 使用返回键的方式防止网页重定向
        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
                        webView.goBack();
                        return true;
                    }
                }
                return false;
            }
        });


    }

    @Override
    public void initData() {

    }


    public boolean parseScheme(String url) {

        if (url.contains("platformapi/startapp")) {
            return true;
        } else if ((Build.VERSION.SDK_INT > 23)
                && (url.contains("platformapi") && url.contains("startapp"))) {
            return true;
        } else {
            return false;
        }
    }

    private void downloadByBrowser(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }


    public boolean isyou(String s1, String s2) {


        String str = s1;
        if (str.indexOf(s2) != -1) {

            return true;

        } else {

            return false;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        webView.reload();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}

