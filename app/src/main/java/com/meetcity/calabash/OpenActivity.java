package com.meetcity.calabash;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * Created by wds1993225 on 2016/10/14.
 */
public class OpenActivity extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_source);
        webView = (WebView)findViewById(R.id.open_web);
        webView.loadUrl("");
    }
}
