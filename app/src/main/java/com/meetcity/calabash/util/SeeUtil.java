package com.meetcity.calabash.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.util.Log;

import com.meetcity.calabash.Constants;
import com.meetcity.calabash.ConstellationActivity;
import com.meetcity.calabash.DivinationActivity;
import com.meetcity.calabash.ImageActivity;
import com.meetcity.calabash.NewsActivity;
import com.meetcity.calabash.OpenActivity;
import com.meetcity.calabash.R;
import com.meetcity.calabash.ReaderActivity;
import com.meetcity.calabash.ReaderWebActivity;
import com.meetcity.calabash.WebActivity;
import com.meetcity.calabash.YiActivity;
import com.qq.e.ads.appwall.APPWall;
import com.qq.e.ads.interstitial.AbstractInterstitialADListener;
import com.qq.e.ads.interstitial.InterstitialAD;


/**
 * Created by Administrator on 2016/8/9.
 */
public class SeeUtil {






    public static void toConstellationActivity(Context context){}


    /**
     *应用墙
     * */
    public static void appwallAD(Context context){
        APPWall wall = new APPWall(context, Constants.APPID, Constants.APPWallPosID);
        wall.setScreenOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        wall.doShowAppWall();
    }

    /**
     * 插屏广告
     * */
    public static void InterstitialAD(Context context){
        final InterstitialAD iad =  new InterstitialAD((Activity)context, Constants.APPID, Constants.InterteristalPosID);
        iad.setADListener(new AbstractInterstitialADListener() {

            @Override
            public void onNoAD(int arg0) {
                Log.i("AD_DEMO", "LoadInterstitialAd Fail:" + arg0);
            }

            @Override
            public void onADReceive() {
                Log.i("AD_DEMO", "onADReceive");
                iad.show();
                //iad.showAsPopupWindow();
            }
        });
        iad.loadAD();
    }

    public static void togoWebActivity(Context context,String url){
        Intent intent = new Intent(context,WebActivity.class);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }

    public static void togoNewsActivity(Context context,String type){
        Intent intent = new Intent(context,NewsActivity.class);
        intent.putExtra("type",type);
        context.startActivity(intent);
    }


    public static int CONSTELLATION_START = 1;
    public static void togoConstellationActivity3(Context context,String name){
        Intent intent = new Intent(context,ConstellationActivity.class);
        ((Activity)context).startActivityForResult(intent,CONSTELLATION_START);
    }

    public static int DIVINATION_START = 2;
    public static void togoDivinationActivity(Context context){
        Intent intent = new Intent(context,DivinationActivity.class);
        ((Activity)context).startActivityForResult(intent,DIVINATION_START);
    }

    public static void togoDivinationActivity(Context context,String name){
        Intent intent = new Intent(context,DivinationActivity.class);
        context.startActivity(intent);
    }

    public static void togoReaderActivity(Context context){
        Intent intent = new Intent(context,ReaderActivity.class);
        context.startActivity(intent);
    }

    public static void togoReaderWebActivity(Context context,String id,String title,String type){
        Intent intent = new Intent(context,ReaderWebActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("title",title);
        intent.putExtra("type",type);
        context.startActivity(intent);
    }

    public static void togoYiActivity(Context context){
        Intent intent = new Intent(context,YiActivity.class);
        context.startActivity(intent);
    }


    public static void togoImageActivity(Context context, String url){
        Intent intent = new Intent(context,ImageActivity.class);
        intent.putExtra("url",url);
        context.startActivity(intent);
    }

    public static void togoShare(Context context,String shareUrl,String title){
        Intent shareIntent = new Intent().setAction(Intent.ACTION_SEND).setType("text/plain");
        String shareText = title + " " + shareUrl + context.getString(R.string.share_extra);
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        context.startActivity(Intent.createChooser(shareIntent, context.getString(R.string.share_to)));
    }

    public static void togoOpen(Context context){
        Intent intent = new Intent(context,OpenActivity.class);
        context.startActivity(intent);
    }







}
