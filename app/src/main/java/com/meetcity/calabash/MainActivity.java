package com.meetcity.calabash;

import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.meetcity.calabash.NetUtil.McApi;
import com.meetcity.calabash.NetUtil.NetUtil;
import com.meetcity.calabash.bean.ConstellationEBean;
import com.meetcity.calabash.bean.DivinationBean;
import com.meetcity.calabash.bean.NewsBean;
import com.meetcity.calabash.bean.YiBean;
import com.meetcity.calabash.bean.ZhiHuListBean;
import com.meetcity.calabash.db.DBUtil;
import com.meetcity.calabash.interf.McResponseSuccessHandler;
import com.meetcity.calabash.util.Blur;
import com.meetcity.calabash.util.McUtil;
import com.meetcity.calabash.util.SPUtil;
import com.meetcity.calabash.util.SeeUtil;
import com.meetcity.calabash.widget.ConstellationPopUtil;
import com.qq.e.ads.banner.ADSize;
import com.qq.e.ads.banner.BannerADListener;
import com.qq.e.ads.banner.BannerView;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private ViewGroup ads1Container;
    private BannerView adsBanner1;
    private ImageView adsDelete;
    private Context mContext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mContext = this;



        initDo();
        initDivinationDB();
        initConstellation2(constellationCard);
        initNews();
        initRead();
        initYi();
        initDivination(divinationCard);
        initAd(adsCard);
        initNone();
        fillDo();

    }






    private void initAd(CardView view){
        ads1Container = (ViewGroup)view.findViewById(R.id.ads_main_bannerContainer);
        this.adsBanner1 = new BannerView(this,ADSize.BANNER, Constants.APPID,Constants.BannerPosID);
        adsBanner1.setRefresh(30);
        ads1Container.addView(adsBanner1);
        adsBanner1.loadAD();

        adsDelete = (ImageView)view.findViewById(R.id.ads_main_delete);
        adsDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ViewGroup)(adsDelete.getParent().getParent().getParent())).setVisibility(View.GONE);
            }
        });
        adsDelete.setClickable(false);
        adsBanner1.setADListener(new BannerADListener() {
            @Override
            public void onNoAD(int i) {
                Log.i("AD_DEMO", "BannerNoAD，eCode=" + i);
                adsCard.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onADReceiv() {
                Log.i("AD_DEMO", "ONBannerReceive");
            }
            @Override
            public void onADExposure() {
            }
            @Override
            public void onADClosed() {
            }
            @Override
            public void onADClicked() {
                adsDelete.setClickable(true);
            }
            @Override
            public void onADLeftApplication() {
            }
            @Override
            public void onADOpenOverlay() {
            }
            @Override
            public void onADCloseOverlay() {
            }
        });
    }



    private TextView nameTv ;
    private TextView timeTv ;
    private ImageView swapIm ;
    private RatingBar allRb ;
    private TextView friendTv ;
    private TextView numberTv ;
    private ImageView pic ;
    private LinearLayout layout ;
    private PopupWindow popupWindow;
    private void initConstellation2(CardView view){
        ConstellationPopUtil constellationPopUtil = new ConstellationPopUtil();
        popupWindow = constellationPopUtil.initConstellationPop(MainActivity.this);
        nameTv = (TextView)view.findViewById(R.id.constellation_main_name_tv);
        timeTv = (TextView)view.findViewById(R.id.constellation_main_time_tv);
        swapIm = (ImageView)view.findViewById(R.id.constellation_main_swap_im);
        allRb = (RatingBar)view.findViewById(R.id.constellation_main_all_rb);
        friendTv = (TextView)view.findViewById(R.id.constellation_main_friend_tv);
        numberTv = (TextView)view.findViewById(R.id.constellation_main_number_tv);
        pic = (ImageView)view.findViewById(R.id.constellation_main_pic_im);
        layout = (LinearLayout)view.findViewById(R.id.constellation_main_ll);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //SeeUtil.togoConstellationActivity(MainActivity3.this,name);
                //SeeUtil.togoConstellationActivity2(MainActivity3.this,"");
                SeeUtil.togoConstellationActivity3(MainActivity.this,"");
            }
        });
        swapIm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //view.setVisibility(View.VISIBLE);
                popupWindow.showAtLocation(swapIm, Gravity.CENTER,0,0);
            }
        });
        constellationPopUtil.setSwapConstellation(new ConstellationPopUtil.SwapConstellation() {
            @Override
            public void OnSwap(String name,String nameType) {
                initConstellationData(nameType);
                // view.setVisibility(View.GONE);
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // view.setVisibility(View.GONE);
            }
        });


        String name = SPUtil.getConstellation(MainActivity.this);
        initConstellationData(name);



    }

    private void initConstellationData(String name){
        if(name.equals("aries")){
            nameTv.setText("白羊座");
            pic.setImageResource(R.mipmap.astro_logo_baiyang);
            timeTv.setText("3月21日-4月19日");
        }else if(name.equals("Taurus")){
            nameTv.setText("金牛座");
            pic.setImageResource(R.mipmap.astro_logo_jinniu);
            timeTv.setText("4月20日-5月20日");
        }
        else if(name.equals("Gemini")){
            nameTv.setText("双子座");
            pic.setImageResource(R.mipmap.astro_logo_shuangzi);
            timeTv.setText("5月21日-6月21日");
        }
        else if(name.equals("Cancer")){
            nameTv.setText("巨蟹座");
            pic.setImageResource(R.mipmap.astro_logo_juxie);
            timeTv.setText("6月22日-7月22日");
        }
        else if(name.equals("leo")){
            nameTv.setText("狮子座");
            pic.setImageResource(R.mipmap.astro_logo_shizi);
            timeTv.setText("7月23日-8月22日");
        }
        else if(name.equals("Virgo")){
            nameTv.setText("处女座");
            pic.setImageResource(R.mipmap.astro_logo_chunv);
            timeTv.setText("8月23日-9月22日");
        }
        else if(name.equals("Libra")){
            nameTv.setText("天秤座");
            pic.setImageResource(R.mipmap.astro_logo_tiancheng);
            timeTv.setText("9月23日-10月23日");
        }
        else if(name.equals("Scorpio")){
            nameTv.setText("天蝎座");
            pic.setImageResource(R.mipmap.astro_logo_tianxie);
            timeTv.setText("10月24日-11月22日");
        }
        else if(name.equals("Sagittarius")){
            nameTv.setText("射手座");
            pic.setImageResource(R.mipmap.astro_logo_sheshou);
            timeTv.setText("11月23日-12月21日");
        }
        else if(name.equals("Capricorn")){
            nameTv.setText("摩羯座");
            pic.setImageResource(R.mipmap.astro_logo_mojie);
            timeTv.setText("12月22日-1月19日");
        }
        else if(name.equals("Aquarius")){
            nameTv.setText("水瓶座");
            pic.setImageResource(R.mipmap.astro_logo_shuiping);
            timeTv.setText("1月20日-2月18日");
        }
        else if(name.equals("Pisces")){
            nameTv.setText("双鱼座");
            pic.setImageResource(R.mipmap.astro_logo_shuangyu);
            timeTv.setText("2月19日-3月20日");
        }

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        String dateStr = year+"";
        if(month<10){
            String monthStr = 0+""+month;
            dateStr = dateStr+monthStr;
        }else {
            String monthStr = month+"";
            dateStr = dateStr+monthStr;
        }
        if(day<10){
            String dayStr = 0+""+day;
            dateStr = dateStr+dayStr;
        }else {
            String dayStr = day+"";
            dateStr = dateStr+dayStr;
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(McApi.constellation_E_base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(NetUtil.httpLog())
                .build();
        McApi mcApi = retrofit.create(McApi.class);
        //taobao , own
        Call<ResponseBody> call = mcApi.getConstellationERequest(dateStr,"99817749","day"
                ,"8794a644893074fa11ed0a64a036a30a","own",name);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    Gson gson = new Gson();
                    ConstellationEBean bean = gson.fromJson(response.body().string(),ConstellationEBean.class);
                    allRb.setRating(Float.parseFloat(bean.get综合运势()));
                    friendTv.setText(bean.get速配星座());
                    numberTv.setText(bean.get幸运数字());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    /**
     * 填充数据库
     * */
    private void initDivinationDB(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                DBUtil.initDb(MainActivity.this);
            }
        }).start();


    }


    private TextView newsTitleTv;
    private ImageView newsPicIm;
    private ImageView newsMoreIm;
    private TextView newsMoreTv;
    private RelativeLayout newsLayout;

    private void initNewsView(CardView view){
        newsTitleTv = (TextView)view.findViewById(R.id.news_main_title_tv);
        newsPicIm = (ImageView)view.findViewById(R.id.news_main_pic_im);
        newsMoreIm = (ImageView)view.findViewById(R.id.news_main_more_im);
        newsMoreTv = (TextView)view.findViewById(R.id.news_main_more_tv);
        newsLayout = (RelativeLayout)view.findViewById(R.id.news_main_Rl);
    }
    private void fillNewsData(final NewsBean.ResultBean.DataBean bean){

        if(!TextUtils.isEmpty(bean.getTitle())
                &&!TextUtils.isEmpty(bean.getDate())
                &&!TextUtils.isEmpty(bean.getThumbnail_pic_s())){


            newsTitleTv.setText(bean.getTitle());
            Picasso.with(MainActivity.this).load(bean.getThumbnail_pic_s())
                    .into(newsPicIm);
            newsMoreIm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SeeUtil.togoNewsActivity(MainActivity.this,"");
                }
            });
            newsMoreTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SeeUtil.togoNewsActivity(MainActivity.this,"");
                }
            });
            newsLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SeeUtil.togoWebActivity(MainActivity.this,bean.getUrl());
                    newsTitleTv.setTextColor(getResources().getColor(R.color.colorTextRead));
                }
            });

        }
    }

    private void initNewsData(String type){
        NetUtil.newsRequest(type, new McResponseSuccessHandler<NewsBean>() {
            @Override
            public void successHandle(NewsBean bean) {
                fillNewsData(bean.getResult().getData().get(1));
            }
        });

    }

    private void initNews(){
        initNewsView(newsCard);
        initNewsData("yule");
    }



    private TextView readDateTv;
    private TextView readTitleTv;
    private TextView readTimeTv;
    private ImageView readPicIm;
    private ImageView readMoreIm;
    private LinearLayout readLayout;

    private void initReadView(CardView view){
        readDateTv = (TextView)view.findViewById(R.id.read_main_date_tv);
        readTitleTv = (TextView)view.findViewById(R.id.read_main_title_tv);
        readTimeTv = (TextView)view.findViewById(R.id.read_main_time_tv);
        readPicIm = (ImageView)view.findViewById(R.id.read_main_pic_im);
        readMoreIm = (ImageView)view.findViewById(R.id.read_main_more_im);
        readLayout = (LinearLayout)view.findViewById(R.id.read_main_ll);
    }
    private void fillReadData(final ZhiHuListBean.StoriesBean bean){

        if(!TextUtils.isEmpty(bean.getTitle())
                &&!TextUtils.isEmpty(bean.getImages().get(0))){


            Date date=new Date();
            String dateStr1 = (date.getMonth()+1)+"月"+date.getDate()+"日";

            readDateTv.setText(dateStr1);
            readTitleTv.setText(bean.getTitle());
            readTimeTv.setText("知乎");
            Picasso.with(MainActivity.this).load(bean.getImages().get(0))
                    .into(readPicIm);
            readMoreIm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SeeUtil.togoReaderActivity(MainActivity.this);
                }
            });
            readLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SeeUtil.togoReaderWebActivity(MainActivity.this,String.valueOf(bean.getId()),bean.getTitle(),"zhi");
                    readTitleTv.setTextColor(getResources().getColor(R.color.colorTextRead));
                }
            });

        }
    }

    private void initReadData(String id){
        NetUtil.zhiRequest(id, new McResponseSuccessHandler<ZhiHuListBean>() {
            @Override
            public void successHandle(ZhiHuListBean zhiHuListBean) {
                fillReadData(zhiHuListBean.getStories().get(1));
            }
        });

    }

    private void initRead(){
        initReadView(readCard);
        initReadData("latest");
    }






    private SimpleDraweeView yiImage;
    private TextView yiName;
    private TextView yiTime;


    private void initYi(){
        initYiView(yiCard);
        initYiData(dateYi());
    }
    private void initYiView(CardView view){
        yiImage = (SimpleDraweeView)view.findViewById(R.id.yi_main_pic);
        yiName = (TextView)view.findViewById(R.id.yi_main_name);
        yiTime = (TextView)view.findViewById(R.id.yi_main_time);
    }
    private void initYiData(String time){
        NetUtil.YiRequest(time + "%" + "2000:00:00", new McResponseSuccessHandler<com.meetcity.calabash.bean.YiBean>() {
            @Override
            public void successHandle(YiBean yiBean) {
                if(yiBean.getData().size()==0){
                    initYiData(dateUtil());
                }else {
                    fillData(yiBean.getData().get(0));
                }

            }
        });
    }

    private void fillData(YiBean.DataBean bean){
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(bean.getHp_img_url())
                .setTapToRetryEnabled(true)
                .setOldController(yiImage.getController())
                .build();
        yiImage.setController(controller);
        yiImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeeUtil.togoYiActivity(MainActivity.this);
            }
        });
        yiName.setText(bean.getHp_author());
        yiTime.setText(timeYi(bean.getHp_makettime()));
    }
    private String timeYi(String time){
        String timeStr = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date date = format.parse(time);
            int year = date.getYear()+1900;
            int month = date.getMonth()+1;
            int day = date.getDate();
            timeStr = timeYiToEnd(month)+" "+day+",  "+year;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeStr;
    }
    private String timeYiToEnd(int i){
        String month = "";
        switch (i){
            case 1:
                month = "Jan.";
                break;
            case 2:
                month = "Feb.";
                break;
            case 3:
                month = "Mar.";
                break;
            case 4:
                month = "Apr.";
                break;
            case 5:
                month = "May.";
                break;
            case 6:
                month = "June.";
                break;
            case 7:
                month = "July.";
                break;
            case 8:
                month = "Aug.";
                break;
            case 9:
                month = "Sept";
                break;
            case 10:
                month = "Oct.";
                break;
            case 11:
                month = "Nov.";
                break;
            case 12:
                month = "Dec.";
                break;
            default:
                break;
        }
        return month;
    }
    /**
     * 生成请求中的日期
     * */
    private String dateYi(){
        Date date = new Date();
        int year = date.getYear()+1900;
        int month = date.getMonth()+1;
        int day = date.getDate();
        String str = year+"-"+month+"-"+day;
        return str;
    }
    /**
     * 更新不及时时，请求前一天的数据
     * */
    private String dateUtil(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String timeStr = year+"-"+month+"-"+day;
        return timeStr;
    }


    private ShimmerTextView titleTv;
    private Shimmer shimmer;

    private void initDivination(CardView view){
        titleTv = (ShimmerTextView)view.findViewById(R.id.divination_main_title_tv);
        shimmer = new Shimmer();
        McUtil.changeFont(MainActivity.this,titleTv,"chinese.ttf");
        shimmer.start(titleTv);
        titleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeeUtil.togoDivinationActivity(MainActivity.this,"");
            }
        });

    }



    private ImageView imageBack;
    private Bitmap bitmap1;
    private Bitmap bitmap2;
    private void initBack(){
        imageBack = (ImageView)findViewById(R.id.activity_main_back_im);
        bitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.main_background5);
        bitmap2 = Bitmap.createScaledBitmap(bitmap1,bitmap1.getWidth()/10,bitmap1.getHeight()/10
                ,false);
        imageBack.setImageBitmap(Blur.apply(MainActivity.this,bitmap2,20));
        if(!bitmap1.isRecycled()){
            bitmap1.recycle();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SeeUtil.CONSTELLATION_START){
            String name = SPUtil.getConstellation(MainActivity.this);
            initConstellationData(name);
        }else if(requestCode == SeeUtil.DIVINATION_START){
            DivinationBean bean = SPUtil.getDivination(MainActivity.this);
        }


    }

    private void initNone(){
        findViewById(R.id.none_tv)
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeeUtil.togoOpen(mContext);
            }
        });
    }

    private CardView constellationCard,divinationCard,readCard,newsCard,yiCard,adsCard;
    private LinearLayout containerLl;
    private void initDo(){
        constellationCard = (CardView)LayoutInflater.from(mContext).inflate(R.layout.constellation_main,null);
        divinationCard = (CardView)LayoutInflater.from(mContext).inflate(R.layout.divination_main_cover,null);
        readCard = (CardView)LayoutInflater.from(mContext).inflate(R.layout.read_main,null);
        newsCard = (CardView)LayoutInflater.from(mContext).inflate(R.layout.news_main3,null);
        yiCard = (CardView)LayoutInflater.from(mContext).inflate(R.layout.yi_main,null);
        adsCard = (CardView)LayoutInflater.from(mContext).inflate(R.layout.ads_main,null);
        containerLl = (LinearLayout)findViewById(R.id.main_ll);
        LayoutTransition mTransitioner = new LayoutTransition();

        PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("rotationX", 90, -15, 15, 0);
        PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("alpha", 0.25f, 0.5f, 0.75f, 1);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(containerLl,holder1,holder2).setDuration(550);

        mTransitioner.setAnimator(LayoutTransition.APPEARING,animator);
        containerLl.setLayoutTransition(mTransitioner);
    }


    private McHandler mcHandler = new McHandler(this);
    private Timer timer;
    private int indexCard = 1;
    private void fillDo(){
        timer = new Timer();
        timer.schedule(new McTask(MainActivity.this),1000,350);
    }
    private static class McHandler extends Handler{
        private WeakReference<Context> reference;
        public McHandler(Context context){
            reference = new WeakReference<Context>(context);

        }
        @Override
        public void handleMessage(Message msg) {
            MainActivity mainActivity = (MainActivity)reference.get();
            if(mainActivity != null){
                switch (msg.what){
                    case 0:
                        mainActivity.initBack();
                        break;
                    case 1:
                        break;
                    case 2:
                        mainActivity.containerLl.addView(mainActivity.constellationCard,0);
                        break;
                    case 3:
                        mainActivity.containerLl.addView(mainActivity.divinationCard,1);
                        break;
                    case 4:
                        mainActivity.containerLl.addView(mainActivity.readCard,2);
                        break;
                    case 5:
                        mainActivity.containerLl.addView(mainActivity.newsCard,3);
                        break;
                    case 6:
                        mainActivity.containerLl.addView(mainActivity.yiCard,4);
                        break;
                    case 7:
                        mainActivity.containerLl.addView(mainActivity.adsCard,5);
                        break;
                    default:
                        if (mainActivity.indexCard == 8){
                            mainActivity.timer.cancel();
                        }
                        break;
                }
            }

        }
    }

    private static class McTask extends TimerTask{

        private WeakReference<Context> reference;
        public McTask(Context context){
            reference = new WeakReference<Context>(context);
        }
        @Override
        public void run() {
            MainActivity mainActivity = (MainActivity)reference.get();
            if(mainActivity != null){
                Message message = new Message();
                message.what = mainActivity.indexCard;
                mainActivity.indexCard = mainActivity.indexCard+1;
                mainActivity.mcHandler.sendMessage(message);
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(bitmap1 != null && !bitmap1.isRecycled()){
            bitmap1.recycle();
        }
        if(bitmap2 != null && !bitmap2.isRecycled()){
            bitmap2.recycle();
        }
        if(shimmer != null && shimmer.isAnimating()){
            shimmer.cancel();
        }

    }
}
