package com.meetcity.calabash.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.meetcity.calabash.NetUtil.McApi;
import com.meetcity.calabash.NetUtil.NetUtil;
import com.meetcity.calabash.R;
import com.meetcity.calabash.util.SPUtil;
import com.meetcity.calabash.bean.ConstellationEBean;
import com.meetcity.calabash.interf.McResponseHandle;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author varun on 08/07/16.
 */
public class ConstellationPagerAdapter extends PagerAdapter {


    private Context context;


    public ConstellationPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = null;
        switch (position) {
            case 0:
                view =  LayoutInflater.from(context).inflate(R.layout.constellation_content, container, false);
                setupToday(view);
                break;
            case 1:
                view = LayoutInflater.from(context).inflate(R.layout.constellation_content, container, false);
                setupTomorrow(view);
                break;
            case 2:
                view = LayoutInflater.from(context).inflate(R.layout.constellation_content,container,false);
                setupWeek(view);
                break;
            case 3:
                view = LayoutInflater.from(context).inflate(R.layout.constellation_content,container,false);
                setupMonth(view);
                break;
            case 4:
                view = LayoutInflater.from(context).inflate(R.layout.constellation_content,container,false);
                setupYear(view);
                break;

        }
        if (view != null) {
            view.setTag(String.valueOf(position));
            container.addView(view);
        }
        return view;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = context.getString(R.string.constellation_today);
                break;
            case 1:
                title = context.getString(R.string.constellation_tomorrow);
                break;
            case 2:
                title = context.getString(R.string.constellation_week);
                break;
            case 3:
                title = context.getString(R.string.constellation_month);
                break;
            case 4:
                title = context.getString(R.string.constellation_year);
                break;

        }
        return title;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void setupToday(final View view) {
        String date = initTodayDate();
        String type = initType();
        initData(view,date,"day",type);
    }
    public void setupTomorrow(final View view) {
        String date = initTomorrowDate();
        String type = initType();
        initData(view,date,"day",type);
    }
    public void setupWeek(final View view) {
        String date = initTodayDate();
        String type = initType();
        initData(view,date,"week",type);
    }
    public void setupMonth(final View view) {
        String date = initTodayDate();
        String type = initType();
        initData(view,date,"month",type);
    }
    public void setupYear(final View view) {
        String date = initTodayDate();
        String type = initType();
        initData(view,date,"year",type);
    }

    private String initType(){
        //aries 白羊座
        //Taurus 金牛座
        //Gemini 双子座
        //Cancer 巨蟹座
        //leo 狮子座
        //Virgo 处女座
        //Libra 天秤座
        //Scorpio 天蝎座
        //Sagittarius 人马座 射手座
        //Capricorn 摩羯座
        //Aquarius 水瓶座
        //Pisces 双鱼座
        return SPUtil.getConstellation(context);
    }


    private String initTodayDate(){
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
        return dateStr;

    }

    private String initTomorrowDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(calendar.DATE,1);
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
        return dateStr;
    }



    private void initView(final View view,ConstellationEBean bean,String timeType){
        NestedScrollView ns = (NestedScrollView)view.findViewById(R.id.constellation_content_ns);
        RatingBar allRb = (RatingBar)view.findViewById(R.id.constellation_all_rb);
        RatingBar loveRb = (RatingBar)view.findViewById(R.id.constellation_love_rb);
        RatingBar workRb = (RatingBar)view.findViewById(R.id.constellation_work_rb);
        RatingBar moneyRb = (RatingBar)view.findViewById(R.id.constellation_money_rb);
        TextView healthTv = (TextView)view.findViewById(R.id.constellation_health_tv);
        TextView allTv = (TextView)view.findViewById(R.id.constellation_all_tv);
        TextView loveTv = (TextView)view.findViewById(R.id.constellation_love_tv);
        TextView workTv = (TextView)view.findViewById(R.id.constellation_work_tv);
        TextView moneyTv = (TextView)view.findViewById(R.id.constellation_money_tv);
        TextView number = (TextView)view.findViewById(R.id.constellation_number_tv);
        TextView color = (TextView)view.findViewById(R.id.constellation_color_tv);
        TextView friend = (TextView)view.findViewById(R.id.constellation_friend_tv);

        LinearLayout healthLl = (LinearLayout)view.findViewById(R.id.constellation_health_ll);
        LinearLayout friendLl = (LinearLayout)view.findViewById(R.id.constellation_friend_ll);
        LinearLayout colorLl = (LinearLayout)view.findViewById(R.id.constellation_color_ll);
        LinearLayout numberLl = (LinearLayout)view.findViewById(R.id.constellation_number_ll);

        ns.setNestedScrollingEnabled(true);

        allRb.setRating(Float.parseFloat(bean.get综合运势()));
        loveRb.setRating(Float.parseFloat(bean.get爱情运势()));
        workRb.setRating(Float.parseFloat(bean.get工作状况()));
        moneyRb.setRating(Float.parseFloat(bean.get理财投资()));
        //healthRb.setRating(Float.parseFloat(bean.get健康指数()));

        allTv.setText(bean.get星运解读());
        healthTv.setText(bean.get健康指数());
        loveTv.setText(bean.get爱情运());
        workTv.setText(bean.get事业运());
        moneyTv.setText(bean.get财运());
        number.setText(bean.get幸运数字());
        color.setText(bean.get幸运颜色());
        friend.setText(bean.get速配星座());

        TextView directionTv = (TextView)view.findViewById(R.id.constellation_direction_tv);
        TextView advantageTv = (TextView)view.findViewById(R.id.constellation_advantage_tv);
        TextView disadvantageTv = (TextView)view.findViewById(R.id.constellation_disadvantage_tv);
        LinearLayout monthLl = (LinearLayout)view.findViewById(R.id.constellation_month_ll);

        directionTv.setText(bean.get开运方位());
        advantageTv.setText(bean.get本月优势());
        disadvantageTv.setText(bean.get本月劣势());

        TextView goodMonthTv = (TextView)view.findViewById(R.id.constellation_goodmonth_tv);
        TextView goodColorTv = (TextView)view.findViewById(R.id.constellation_goodcolor_tv);
        LinearLayout yearLl = (LinearLayout)view.findViewById(R.id.constellation_year_ll);

        goodMonthTv.setText(bean.get幸运月份());
        goodColorTv.setText(bean.get幸运颜色());

        switch (timeType){
            case "day":
                monthLl.setVisibility(View.GONE);
                yearLl.setVisibility(View.GONE);
                break;
            case "month":
                healthLl.setVisibility(View.GONE);
                friendLl.setVisibility(View.GONE);
                colorLl.setVisibility(View.GONE);
                numberLl.setVisibility(View.GONE);
                yearLl.setVisibility(View.GONE);
                break;
            case "week":
                monthLl.setVisibility(View.GONE);
                yearLl.setVisibility(View.GONE);
                break;
            case "year":
                healthLl.setVisibility(View.GONE);
                friendLl.setVisibility(View.GONE);
                colorLl.setVisibility(View.GONE);
                numberLl.setVisibility(View.GONE);
                monthLl.setVisibility(View.GONE);
                break;
            default:
                break;
        }

    }


    private void initData1(final View view, String day,String timeType,String type){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(McApi.constellation_E_base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(NetUtil.httpLog())
                .build();
        McApi mcApi = retrofit.create(McApi.class);
        //taobao , own
        Call<ResponseBody> call = mcApi.getConstellationERequest(day,"99817749",timeType
                ,"8794a644893074fa11ed0a64a036a30a","own",type);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    Gson gson = new Gson();
                    ConstellationEBean bean = gson.fromJson(response.body().string(),ConstellationEBean.class);
                    initView(view,bean,"");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void initData(final View view, String day, final String timeType, String type){

        NetUtil.constellationRequest(day, timeType, type, new McResponseHandle<ResponseBody>() {
            @Override
            public void successHandle(Response<ResponseBody> response) {
                try {
                    Gson gson = new Gson();
                    ConstellationEBean bean = gson.fromJson(response.body().string(),ConstellationEBean.class);
                    initView(view,bean,timeType);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


    }







}
