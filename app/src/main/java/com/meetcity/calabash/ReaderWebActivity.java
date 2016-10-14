package com.meetcity.calabash;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.meetcity.calabash.NetUtil.McApi;
import com.meetcity.calabash.NetUtil.NetUtil;
import com.meetcity.calabash.bean.DouBean;
import com.meetcity.calabash.bean.DouWebBean;
import com.meetcity.calabash.bean.ZhiHuListBean;
import com.meetcity.calabash.bean.ZhihuWebBean;
import com.meetcity.calabash.interf.McResponseSuccessHandler;
import com.meetcity.calabash.widget.McDialog;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


import cn.pedant.SweetAlert.SweetAlertDialog;
import lib.app.SwipeBackActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ReaderWebActivity extends SwipeBackActivity{

    private WebView webViewRead;
    private FloatingActionButton fab;
    private ImageView ivFirstImg;
    private TextView tvCopyRight;
    private CollapsingToolbarLayout toolbarLayout;


    private String shareUrl = null;
    private String id;

    private McDialog dialog;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader_web);

        initViews();
        initWebView();
        dialog = new McDialog(this);
        dialog.setCancelable(false);
        dialog.show();

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        final String title = intent.getStringExtra("title");

        setCollapsingToolbarLayoutTitle(title);
        if(intent.getStringExtra("type").equals("dou")){
            initDoubanData();
        }else if(intent.getStringExtra("type").equals("zhi")){
            initZhihuData();
        }




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Intent shareIntent = new Intent().setAction(Intent.ACTION_SEND).setType("text/plain");
                    String shareText = title + " " + shareUrl + getString(R.string.share_extra);
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
                    startActivity(Intent.createChooser(shareIntent, getString(R.string.share_to)));
                } catch (android.content.ActivityNotFoundException ex) {
                    Snackbar.make(fab, "加载失败", Snackbar.LENGTH_SHORT).show();
                }


            }
        });

    }


    // 初始化view
    private void initViews() {
        webViewRead = (WebView) findViewById(R.id.reader_web_web_view);
        webViewRead.setScrollbarFadingEnabled(true);

        fab = (FloatingActionButton) findViewById(R.id.reader_web_fab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.reader_web_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ivFirstImg = (ImageView) findViewById(R.id.reader_web_image_view);
        tvCopyRight = (TextView) findViewById(R.id.reader_web_text_view);
        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.reader_web_toolbar_layout);
    }

    private void initWebView() {
        //能够和js交互
        webViewRead.getSettings().setJavaScriptEnabled(true);
        //缩放,设置为不能缩放可以防止页面上出现放大和缩小的图标
        webViewRead.getSettings().setBuiltInZoomControls(false);
        //缓存
        webViewRead.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        //开启DOM storage API功能
        webViewRead.getSettings().setDomStorageEnabled(true);
        //开启application Cache功能
        webViewRead.getSettings().setAppCacheEnabled(false);
        //不调用第三方浏览器即可进行页面反应
        webViewRead.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webViewRead.loadUrl(url);
                return true;
            }
        });
        // 设置在本WebView内可以通过按下返回上一个html页面
        webViewRead.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && webViewRead.canGoBack()) {
                        webViewRead.goBack();
                        return true;
                    }
                }
                return false;
            }
        });
        // 设置是否加载图片，true不加载，false加载图片
        webViewRead.getSettings().setBlockNetworkImage(false);
    }
    private void initZhihuData(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(McApi.zhihu_base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(NetUtil.httpLog())
                .build();
        McApi mcApi = retrofit.create(McApi.class);
        Call<ResponseBody> callWeixin = mcApi.getZhiRequest(id);
        callWeixin.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    Gson gson = new Gson();
                    ZhihuWebBean bean = gson.fromJson(response.body().string(),ZhihuWebBean.class);
                    if(bean.getBody() == null){
                        webViewRead.loadUrl(bean.getShare_url());
                        ivFirstImg.setImageResource(R.drawable.pic_zhihu_web_def);
                        ivFirstImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        shareUrl = bean.getShare_url();
                    }else {
                        shareUrl = bean.getShare_url();
                        if(TextUtils.isEmpty(bean.getImage())){
                            ivFirstImg.setImageResource(R.drawable.pic_zhihu_web_def);
                            ivFirstImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        } else {
                            Picasso.with(ReaderWebActivity.this).load(bean.getImage()).into(ivFirstImg);
                            tvCopyRight.setText(bean.getImage_source());
                        }
                        // 在api中，css的地址是以一个数组的形式给出，这里需要设置
                        // in fact,in api,css addresses are given as an array
                        // api中还有js的部分，这里不再解析js
                        // javascript is included,but here I don't use it
                        // 不再选择加载网络css，而是加载本地assets文件夹中的css
                        // use the css file from local assets folder,not from network
                        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/zhihu_daily.css\" type=\"text/css\">";

                        // body中替换掉img-place-holder div
                        // replace the img-place-holder with an empty value in body
                        // 可以去除网页中div所占的区域
                        // to avoid the div occupy the area
                        // 如果没有去除这个div，那么整个网页的头部将会出现一部分的空白区域
                        // if we don't delete the div, the web page will have a blank area

                        String content = bean.getBody().replace("<div class=\"img-place-holder\">", "");
                        // div headline占据了一段高度，需要手动去除
                        // delete the headline div
                        content = content.replace("<div class=\"headline\">", "");

                        // 根据主题的不同确定不同的加载内容
                        // load content judging by different theme
                        String theme = "<body className=\"\" onload=\"onLoaded()\">";


                        String html = "<!DOCTYPE html>\n"
                                + "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                                + "<head>\n"
                                + "\t<meta charset=\"utf-8\" />"
                                + css
                                + "\n</head>\n"
                                + theme
                                + content
                                + "</body></html>";

                        webViewRead.loadDataWithBaseURL("x-data://base",html,"text/html","utf-8",null);
                    }
                    if(dialog.isShowing()){
                        dialog.dismiss();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void initDoubanData(){
        NetUtil.douWebRequest(id, new McResponseSuccessHandler<DouWebBean>() {
            @Override
            public void successHandle(DouWebBean douWebBean) {
                Log.i("WATER",douWebBean.getTitle());
                if(douWebBean.getThumbs().size() == 0){
                    webViewRead.loadUrl(douWebBean.getUrl());
                    ivFirstImg.setImageResource(R.drawable.pic_zhihu_web_def);
                    ivFirstImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    shareUrl = douWebBean.getUrl();
                }else {
                    shareUrl = douWebBean.getUrl();
                    if(TextUtils.isEmpty(douWebBean.getThumbs().get(0).getMedium().getUrl())){
                        ivFirstImg.setImageResource(R.drawable.pic_zhihu_web_def);
                        ivFirstImg.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    } else {
                        Picasso.with(ReaderWebActivity.this).load(douWebBean.getThumbs().get(0).getMedium().getUrl()).into(ivFirstImg);
                        if(douWebBean.getAuthor() !=null){
                            if(!TextUtils.isEmpty(douWebBean.getAuthor().getName())&&douWebBean.getAuthor() != null){
                                tvCopyRight.setText(douWebBean.getAuthor().getName());
                            }
                        }


                    }
                    // 在api中，css的地址是以一个数组的形式给出，这里需要设置
                    // in fact,in api,css addresses are given as an array
                    // api中还有js的部分，这里不再解析js
                    // javascript is included,but here I don't use it
                    // 不再选择加载网络css，而是加载本地assets文件夹中的css
                    // use the css file from local assets folder,not from network
                    String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/douban_light.css\" type=\"text/css\">";
                    String content = douWebBean.getContent();
                    for(int i = 0;i<douWebBean.getPhotos().size();i++){
                        String tag = douWebBean.getPhotos().get(i).getTag_name();
                        String old = "<img id=\"" + tag + "\" />";
                        String newStr = "<img id=\"" + tag + "\" " + "src=\"" + douWebBean.getPhotos().get(i).getMedium().getUrl() + "\"/>";
                        content = content.replace(old,newStr);
                    }
                    String html = "<!DOCTYPE html>\n"
                            + "<html lang=\"ZH-CN\" xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                            + "<head>\n"
                            + "\t<meta charset=\"utf-8\" />"
                            + css
                            + "\n</head>\n <body>"
                            + "<div class=\"container bs-docs-container\">\n" +
                            "            <div class=\"post-container\">\n" +
                            content +
                            "            </div>\n" +
                            "        </div>"
                            + "</body></html>";

                    webViewRead.loadDataWithBaseURL("x-data://base",html,"text/html","utf-8",null);

                }
                if(dialog.isShowing()){
                    dialog.dismiss();
                }

            }
        });


    }


    // to change the title's font size of toolbar layout
    private void setCollapsingToolbarLayoutTitle(String title) {
        toolbarLayout.setTitle(title);
        toolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        toolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        toolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBarPlus1);
        toolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBarPlus1);
    }


}