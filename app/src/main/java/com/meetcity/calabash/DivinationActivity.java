package com.meetcity.calabash;

import android.animation.LayoutTransition;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.john.waveview.WaveView;
import com.meetcity.calabash.bean.DivinationBean;
import com.meetcity.calabash.db.DBHelper;
import com.meetcity.calabash.util.SPUtil;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import lib.app.SwipeBackActivity;


/**
 * Created by wds1993225 on 2016/8/29.
 */
public class DivinationActivity extends SwipeBackActivity {

    private ScrollView containerSv;
    private SQLiteDatabase db;
    private DBHelper dbHelper = new DBHelper(DivinationActivity.this);

    private TextView qianNum;
    private TextView xiongji;
    private TextView time;
    private TextView title;
    private TextView qianci;
    private TextView beijing;
    private TextView liunian;
    private TextView shiye;
    private TextView caifu;
    private TextView zishen;
    private TextView jiating;
    private TextView yinyuan;
    private TextView yiju;
    private TextView mingyu;
    private TextView jiankang;
    private TextView youyi;


    private RelativeLayout coverLl;
    private ImageView fingerIm;
    private WaveView waveView;
    private CircularProgressBar circularProgressBar;
    private int index = 0;
    private Timer timer;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    if(index==100){
                        //initData();
                        coverLl.setVisibility(View.GONE);
                        containerSv.setVisibility(View.VISIBLE);
                        leftImage.startAnimation(leftAnimationSet);
                        rightImage.startAnimation(rightAnimationSet);
                        return;
                    }else if(index == 98){
                        initData();
                    }
                    index = index+1;
                    waveView.setProgress(index);
                    circularProgressBar.setProgress(index);
                    break;
            }
        }
    };

    private ImageView leftImage;
    private ImageView rightImage;
    private AnimationSet leftAnimationSet;
    private AnimationSet rightAnimationSet;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divination);
        initConView();
        initShowView();
        initAnim();

    }
    private void initConView(){

        coverLl = (RelativeLayout)findViewById(R.id.divination_cove_Rl);
        fingerIm = (ImageView)findViewById(R.id.divination_cover_finger);
        waveView = (WaveView)findViewById(R.id.divination_cover_wave_view);
        circularProgressBar = (CircularProgressBar)findViewById(R.id.divination_cover_pb);
        circularProgressBar.setColor(ContextCompat.getColor(this, R.color.colorBlue));
        circularProgressBar.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent));
        circularProgressBar.setProgressBarWidth(getResources().getDimension(R.dimen.common_circle_width));
        circularProgressBar.setBackgroundProgressBarWidth(getResources().getDimension(R.dimen.common_circle_width));


        containerSv = (ScrollView)findViewById(R.id.divination_container_sv);

        fingerIm.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                Message message = new Message();
                                message.what = 1;
                                mHandler.sendMessage(message);
                            }
                        },10,50);
                        break;
                    case MotionEvent.ACTION_UP:
                        timer.cancel();
                        break;

                }
                return true;
            }
        });

        LayoutTransition transition = new LayoutTransition();
        coverLl.setLayoutTransition(transition);
        containerSv.setLayoutTransition(transition);

    }
    private void initShowView(){

        qianNum = (TextView)findViewById(R.id.divination_qiannum);
        xiongji = (TextView)findViewById(R.id.divination_xiongji);
        time = (TextView)findViewById(R.id.divination_time);
        title = (TextView)findViewById(R.id.divination_title);
        qianci = (TextView)findViewById(R.id.divination_qianci);
        beijing = (TextView)findViewById(R.id.divination_beijing);
        liunian = (TextView)findViewById(R.id.divination_liunian);
        shiye = (TextView)findViewById(R.id.divination_shiye);
        caifu = (TextView)findViewById(R.id.divination_caifu);
        zishen = (TextView)findViewById(R.id.divination_zishen);
        jiating = (TextView)findViewById(R.id.divination_jiating);
        yinyuan = (TextView)findViewById(R.id.divination_yinyuan);
        yiju = (TextView)findViewById(R.id.divination_yiju);
        mingyu = (TextView)findViewById(R.id.divination_mingyu);
        jiankang = (TextView)findViewById(R.id.divination_jiankang);
        youyi = (TextView)findViewById(R.id.divination_youyi);
    }






    private void initData(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        Date date = new Date();
        String dateStr = format.format(date);
        time.setText(dateStr);


        int i = (int)(1+Math.random()*100);
        db = dbHelper.getWritableDatabase();
        String[] data = {i+""};
        Cursor cursor = db.rawQuery("select * from huangdaxianqian where num = ? ",data);
        if(cursor != null){
            if(cursor.moveToFirst()){
                do{
                    String qianNumStr = cursor.getString(cursor.getColumnIndex("qianNum"));
                    String xiongJiStr = cursor.getString(cursor.getColumnIndex("xiongJi"));
                    String titleStr = cursor.getString(cursor.getColumnIndex("title"));
                    String qianciStr = cursor.getString(cursor.getColumnIndex("qianci"));
                    String beijingStr = cursor.getString(cursor.getColumnIndex("beijing"));
                    String liunianStr = cursor.getString(cursor.getColumnIndex("liunian"));
                    String shiyeStr = cursor.getString(cursor.getColumnIndex("shiye"));
                    String caifuStr = cursor.getString(cursor.getColumnIndex("caifu"));
                    String zishenStr= cursor.getString(cursor.getColumnIndex("zishen"));
                    String jiatingStr = cursor.getString(cursor.getColumnIndex("jiating"));
                    String yinyuanStr = cursor.getString(cursor.getColumnIndex("yinyuan"));
                    String yijuStr = cursor.getString(cursor.getColumnIndex("yiju"));
                    String mingyuStr = cursor.getString(cursor.getColumnIndex("mingyu"));
                    String jiankanStr = cursor.getString(cursor.getColumnIndex("jiankan"));
                    String youyiStr = cursor.getString(cursor.getColumnIndex("youyi"));
                    qianNum.setText(qianNumStr);
                    xiongji.setText(xiongJiStr+"签");
                    title.setText(titleStr);
                    qianci.setText(qianciStr);
                    beijing.setText(beijingStr);
                    liunian.setText(liunianStr);
                    shiye.setText(shiyeStr);
                    caifu.setText(caifuStr);
                    zishen.setText(zishenStr);
                    jiating.setText(jiatingStr);
                    yinyuan.setText(yinyuanStr);
                    yiju.setText(yijuStr);
                    mingyu.setText(mingyuStr);
                    jiankang.setText(jiankanStr);
                    youyi.setText(youyiStr);
                    DivinationBean bean = new DivinationBean();
                    bean.setQianNum(qianNumStr);
                    bean.setXiongji(xiongJiStr);
                    bean.setTitle(titleStr);
                    bean.setQianci(qianciStr);
                    bean.setBeijing(beijingStr);
                    bean.setLiunian(liunianStr);
                    bean.setShiye(shiyeStr);
                    bean.setCaifu(caifuStr);
                    bean.setZishen(zishenStr);
                    bean.setJiating(jiatingStr);
                    bean.setYinyuan(yinyuanStr);
                    bean.setYiju(yijuStr);
                    bean.setMingyu(mingyuStr);
                    bean.setJiankang(jiankanStr);
                    bean.setYouyi(youyiStr);
                    SPUtil.saveDivination(DivinationActivity.this,bean);
                }while (cursor.moveToNext());
            }
            cursor.close();
        }
    }

    private void initAnim(){
        leftImage = (ImageView)findViewById(R.id.divination_activity_left);
        rightImage = (ImageView)findViewById(R.id.divination_activity_right);

        leftAnimationSet = new AnimationSet(true);               //动画集合，对加入的动画都有效
        TranslateAnimation left_translate = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF,
                0f,
                Animation.RELATIVE_TO_SELF,
                -1f,
                Animation.RELATIVE_TO_SELF,
                0f,
                Animation.RELATIVE_TO_SELF,
                0f);
        left_translate.setDuration(1500);
        leftAnimationSet.addAnimation(left_translate);
        leftAnimationSet.setFillAfter(true);
        rightAnimationSet = new AnimationSet(true);

        TranslateAnimation right_translate = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF,
                0f,
                Animation.RELATIVE_TO_SELF,
                +1f,
                Animation.RELATIVE_TO_SELF,
                0f,
                Animation.RELATIVE_TO_SELF,
                0f);
        right_translate.setDuration(1500);
        rightAnimationSet.addAnimation(right_translate);
        rightAnimationSet.setFillAfter(true);

        //right.startAnimation(right_anim);
        //left.startAnimation(left_anim);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
