package com.meetcity.calabash;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qq.e.ads.nativ.MediaListener;
import com.qq.e.ads.nativ.MediaView;
import com.qq.e.ads.nativ.NativeAD;
import com.qq.e.ads.nativ.NativeADDataRef;
import com.qq.e.ads.nativ.NativeMediaAD;
import com.qq.e.ads.nativ.NativeMediaAD.NativeMediaADListener;
import com.qq.e.ads.nativ.NativeMediaADData;
import com.squareup.picasso.Picasso;

import java.util.List;

import lib.app.SwipeBackActivity;


/**
 * Created by wds1993225 on 2016/8/18.
 */
public class WebActivity extends SwipeBackActivity {

    private static final String TAG = WebActivity.class.getSimpleName();
    public static final int AD_COUNT = 1;                 // 一次拉取的广告条数：范围和以前的原生广告一样，是1-30。但是我们强烈建议：需要展示几个广告就加载几个，不要过多加载广告而不去曝光它们。这样会导致曝光率过低。


    private ImageView logoIm;                              //logo，大图，标题，描述
    private ImageView posterIm;
    private TextView aDTitleTv;
    private TextView desTv;
    private Button downloadBt;
    private RelativeLayout containerRl;

    private WebView webView;
    private NestedScrollView ns;
    private LinearLayout toTopLl;

    private NativeADDataRef adItem;
    private NativeAD nativeAD;

    private RelativeLayout adBackRl;
    private LinearLayout adRootLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        initWebView();
        initView();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adBackRl.setVisibility(View.VISIBLE);
                adRootLl.setVisibility(View.VISIBLE);
                initNativeVideoAD();
            }
        },2000);
        //initNativeVideoAD();
    }

    private void initWebView(){
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        webView = (WebView)findViewById(R.id.web_activity);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
        toTopLl = (LinearLayout)findViewById(R.id.web_totop);
        ns = (NestedScrollView)findViewById(R.id.web_ns);
        toTopLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ns.smoothScrollTo(0,0);
            }
        });
        adBackRl = (RelativeLayout)findViewById(R.id.web_activity_adback_rl);
        adRootLl = (LinearLayout)findViewById(R.id.web_activity_root);

    }




    private void initView() {
        posterIm = (ImageView)findViewById(R.id.ad_web_poster_im);
        logoIm = (ImageView)findViewById(R.id.ad_web_logo_im);
        aDTitleTv = (TextView)findViewById(R.id.ad_web_title_tv);
        desTv = (TextView)findViewById(R.id.ad_web_des_tv);
        downloadBt = (Button)findViewById(R.id.ad_web_download_bt);
        containerRl = (RelativeLayout)findViewById(R.id.ad_web_container_rl);

    }
    private void initNativeVideoAD(){
        NativeAD.NativeAdListener listener = new NativeAD.NativeAdListener() {
            @Override
            public void onADLoaded(List<NativeADDataRef> list) {
                if(list.size()>0){
                    adItem = list.get(0);
                    initAdUi(adItem);
                }
            }
            @Override
            public void onNoAD(int i) {
            }
            @Override
            public void onADStatusChanged(NativeADDataRef nativeADDataRef) {
                updateDownloadingUi(nativeADDataRef);
            }
            @Override
            public void onADError(NativeADDataRef nativeADDataRef, int i) {
            }
        };
        if(nativeAD == null){
            this.nativeAD = new NativeAD(this, Constants.APPID, Constants.NativePosID, listener);
            nativeAD.loadAD(20);
        }
        nativeAD.loadAD(20);

    }

    private void initAdUi(final NativeADDataRef adItem){
        Picasso.with(WebActivity.this).load(adItem.getImgUrl()).into(posterIm);
        Picasso.with(WebActivity.this).load(adItem.getIconUrl()).into(logoIm);
        aDTitleTv.setText(adItem.getTitle());
        desTv.setText(adItem.getDesc());
        adItem.onExposured(containerRl);
        downloadBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adItem.onClicked(v);
            }
        });
        downloadBt.setText("立即下载");

    }

    private void updateDownloadingUi(NativeADDataRef adItem){
        switch (adItem.getAPPStatus()) {
            case 0:
                downloadBt.setText("下载");
                break;
            case 1:
                downloadBt.setText("启动");
                break;
            case 2:
                downloadBt.setText("更新");
                break;
            case 4:
                downloadBt.setText(adItem.getProgress() + "%");
                break;
            case 8:
                downloadBt.setText("安装");
                break;
            case 16:
                downloadBt.setText("下载失败，重新下载");
                break;
            default:
                downloadBt.setText("浏览");
                break;
        }
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
