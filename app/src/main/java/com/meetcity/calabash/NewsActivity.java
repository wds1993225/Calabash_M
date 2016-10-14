package com.meetcity.calabash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.meetcity.calabash.NetUtil.McApi;
import com.meetcity.calabash.NetUtil.NetUtil;
import com.meetcity.calabash.adapter.BaseAdapter;
import com.meetcity.calabash.adapter.NewsAdapter;
import com.meetcity.calabash.adapter.WeixinAdapter;
import com.meetcity.calabash.bean.LoadMoreBean;
import com.meetcity.calabash.bean.NewsBean;
import com.meetcity.calabash.bean.WeixinBean;
import com.meetcity.calabash.interf.McResponseHandle;
import com.meetcity.calabash.interf.McResponseSuccessHandler;
import com.meetcity.calabash.widget.HeadLayout;
import com.meetcity.calabash.widget.XDefaultItemAnimator;
import com.qq.e.ads.nativ.NativeAD;
import com.qq.e.ads.nativ.NativeADDataRef;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wds1993225 on 2016/8/19.
 */
public class NewsActivity extends BaseActivity {

    private XRecyclerView recyclerView;

    private NewsAdapter newsAdapter;
    private List<Object> newsList = new ArrayList<>();


    private HeadLayout headLayout;

    public static int AD_POSITION = 1;   // 把广告摆在RecyclerView数据集的第2个位置
    private NativeADDataRef adItem;
    private NativeAD nativeAD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        initView();
        initData();
    }

    private void initView(){
        recyclerView = (XRecyclerView)findViewById(R.id.news_rv);
        newsAdapter = new NewsAdapter(NewsActivity.this,newsList,recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setItemAnimator(new XDefaultItemAnimator());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(newsAdapter);
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }

            @Override
            public void onLoadMore() {
                loadMoreData();
            }
        });
    }
    private void initData(){
        int i = (int)(1+Math.random()*10);
        String [] BB = {"top","shehui","guonei","guoji","yule","tiyu","junshi","keji","caijing","shishang"};
        NetUtil.newsRequest(BB[i - 1], new McResponseSuccessHandler<NewsBean>() {
            @Override
            public void successHandle(NewsBean bean) {
                for(int i = 0 ;i<bean.getResult().getData().size();i++){
                    NewsBean.ResultBean.DataBean bean1 = bean.getResult().getData().get(i);
                    newsList.add(bean1);
                }
                initNativeAD();
                stopRefresh();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismiss();
                    }
                },550);
            }
        });
    }
    private void loadMoreData(){
        int i = (int)(1+Math.random()*10);
        String [] BB = {"top","shehui","guonei","guoji","yule","tiyu","junshi","keji","caijing","shishang"};
        NetUtil.newsRequest(BB[i - 1], new McResponseSuccessHandler<NewsBean>() {
            @Override
            public void successHandle(NewsBean bean) {
                for(int i = 0 ;i<bean.getResult().getData().size();i++){
                    NewsBean.ResultBean.DataBean bean1 = bean.getResult().getData().get(i);
                    newsList.add(newsList.size(),bean1);
                }
                stopRefresh();
            }
        });
    }
    private void refreshData(){
        int i = (int)(1+Math.random()*10);
        String [] BB = {"top","shehui","guonei","guoji","yule","tiyu","junshi","keji","caijing","shishang"};
        NetUtil.newsRequest(BB[i - 1], new McResponseSuccessHandler<NewsBean>() {
            @Override
            public void successHandle(NewsBean bean) {
                //newsList.clear();
                for(int i = 0 ;i<bean.getResult().getData().size();i++){
                    NewsBean.ResultBean.DataBean bean1 = bean.getResult().getData().get(i);
                    newsList.add(0,bean1);
                }
                stopRefresh();
            }
        });
    }

    private void stopRefresh(){
        newsAdapter.notifyDataSetChanged();
        recyclerView.loadMoreComplete();
        recyclerView.refreshComplete();
    }






    private void initNativeAD() {


        NativeAD.NativeAdListener listener = new NativeAD.NativeAdListener() {
            @Override
            public void onADLoaded(List<NativeADDataRef> list) {
                Log.i("WATER","加载原始广告成功，native");
                if(list.size()>0){
                    adItem = list.get(0);
                    addADIntoList();
                }
            }
            @Override
            public void onNoAD(int i) {
            }
            @Override
            public void onADStatusChanged(NativeADDataRef nativeADDataRef) {

                    /**
                     * 虽然RecyclerView提供了局部刷新单条Item的方法，但是不要直接调用mAdapter.notifyItemChanged(AD_POSITION)来更新广告item中的下载进度。
                     * 这样会引起onBindViewHolder重复调用，从而以导致视频播放反复重头播放、数据上报出现问题。
                     * 我们应该采用局部刷新的方法，仅更新下载进度显示的这个子View。这个地方的处理和ListView的处理是一样的。
                     */

                if (newsAdapter != null && nativeADDataRef != null) {
                    newsAdapter.updateDownloadingItem(nativeADDataRef);
                }
            }
            @Override
            public void onADError(NativeADDataRef nativeADDataRef, int i) {
                Log.i("WATER","onADError，native"+i);
            }
        };
        if (nativeAD == null) {
            this.nativeAD = new NativeAD(NewsActivity.this, Constants.APPID, Constants.NativePosID, listener);
        }
        int count = 20; // 一次拉取的广告条数：范围1-30
        nativeAD.loadAD(count);

    }

    private void addADIntoList(){
        if(adItem != null && newsAdapter != null){
            if(newsList.size()!=0){
                newsList.add(4,adItem);
                newsAdapter.notifyDataSetChanged();
            }

        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if(newsAdapter != null){
            for(int i = 0;i<newsList.size();i++){
                newsAdapter.notifyItemChanged(i);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        recyclerView.destroyDrawingCache();
        stopRefresh();
    }

}
