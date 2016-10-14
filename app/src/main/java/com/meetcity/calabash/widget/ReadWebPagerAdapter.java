package com.meetcity.calabash.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.meetcity.calabash.NetUtil.McApi;
import com.meetcity.calabash.NetUtil.NetUtil;
import com.meetcity.calabash.R;
import com.meetcity.calabash.adapter.DouAdapter;
import com.meetcity.calabash.adapter.JiandanAdapter;
import com.meetcity.calabash.adapter.JokerAdapter;
import com.meetcity.calabash.adapter.NewsAdapter;
import com.meetcity.calabash.adapter.ZhihuAdapter;
import com.meetcity.calabash.bean.DouBean;
import com.meetcity.calabash.bean.JiandanBean;
import com.meetcity.calabash.bean.JokerPicBean;
import com.meetcity.calabash.bean.JokerTextBean;
import com.meetcity.calabash.bean.NewsBean;
import com.meetcity.calabash.bean.TimeBean;
import com.meetcity.calabash.bean.ZhiHuListBean;
import com.meetcity.calabash.interf.McResponseSuccessHandler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author varun on 08/07/16.
 */
public class ReadWebPagerAdapter extends PagerAdapter {


    private Context context;

    private XRecyclerView douXRecyclerView;
    private DouAdapter douAdapter;
    private List<Object> douList = new ArrayList<>();

    private XRecyclerView zhihuRecyclerView;
    private ZhihuAdapter zhihuAdapter;
    private List<Object> zhihuList = new ArrayList<>();

    private XRecyclerView jokerRecyclerView;
    private JokerAdapter jokerAdapter;
    private List<Object> jokerList = new ArrayList<>();




    public ReadWebPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = null;
        switch (position) {
            case 0:
                view =  LayoutInflater.from(context).inflate(R.layout.reader_container, container, false);
                setupDouLayoutClickEvents(view);
                break;
            case 1:
                view = LayoutInflater.from(context).inflate(R.layout.reader_container, container, false);
                setupZhiLayoutClickEvents(view);
                break;
            case 2:
                view = LayoutInflater.from(context).inflate(R.layout.reader_container,container,false);
                setupJokerLayoutClickEvents(view);

        }
        if (view != null) {
            view.setTag(String.valueOf(position));
            container.addView(view);
        }
        return view;
    }

    @Override
    public int getCount() {
        return 3;
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
                title = context.getString(R.string.reader_news);
                break;
            case 1:
                title = context.getString(R.string.reader_zhi);
                break;
            case 2:
                title = context.getString(R.string.reader_joker);
                break;

        }
        return title;
    }

    ///////////////////////////////////////////////////////////////////
    public void setupDouLayoutClickEvents(final View view) {
        initDouView(view);
        initDouData(douType());
    }
    private void initDouData(String date){
        NetUtil.douRequest(date, new McResponseSuccessHandler<DouBean>() {
            @Override
            public void successHandle(DouBean douBean) {
                TimeBean timeBean = new TimeBean();
                timeBean.setTime(createTime());
                douList.add(timeBean);
                for(int i = 0;i<douBean.getPosts().size();i++){
                    DouBean.PostsBean bean = douBean.getPosts().get(i);
                    douList.add(bean);
                }
                stopRefresh(douAdapter,douXRecyclerView);
            }
        });

    }
    private void initDouView(View view){
        douAdapter = new DouAdapter(context,douList,douXRecyclerView);
        douXRecyclerView = (XRecyclerView)view.findViewById(R.id.reader_container_rc);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        douXRecyclerView.setItemAnimator(new XDefaultItemAnimator());
        douXRecyclerView.setLayoutManager(layoutManager);
        douXRecyclerView.setAdapter(douAdapter);
        douXRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                initDouData(douType());
            }

            @Override
            public void onLoadMore() {
                initDouData(douType());
            }
        });
        if(douList.size()==0){
            douXRecyclerView.setPullRefreshEnabled(true);
        }else {
            douXRecyclerView.setPullRefreshEnabled(false);
        }

    }
    /**
     * 拼接douban的参数
     * */
    private int douIndex = 1;
    private int indexCreate = 1;
    private String douType(){
        douIndex = --douIndex ;
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH,douIndex);

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(year-1900,month,day);
        String dateStr = format.format(date);
        return dateStr;

    }

    /**
     * 给时间bean生成数据
     * */
    private String createTime(){
        indexCreate = --indexCreate ;
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH,indexCreate);
        final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
                "星期六" };
        int week = c.get(Calendar.DAY_OF_WEEK)-1;
        String weekStr = dayNames[week];
        int month = c.get(Calendar.MONTH)+1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        String dateStr = month+"月"+day+"日"+"  "+weekStr;
        return dateStr;
    }


    ///////////////////////////////////////////////////////////////////////////////////////
    private void setupZhiLayoutClickEvents(View view) {
        //initRecyclerView(zhihuRecyclerView,view);
        zhihuRecyclerView = (XRecyclerView)view.findViewById(R.id.reader_container_rc);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        zhihuRecyclerView.setItemAnimator(new XDefaultItemAnimator());
        zhihuRecyclerView.setLayoutManager(layoutManager);
        zhihuAdapter = new ZhihuAdapter(context,zhihuList,zhihuRecyclerView);
        zhihuRecyclerView.setAdapter(zhihuAdapter);
        zhihuRecyclerView.setPullRefreshEnabled(false);
        zhihuRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                initZhihuData();
            }
            @Override
            public void onLoadMore() {
                zhiLoadMoreData(zhiType(),zhihuAdapter,zhihuRecyclerView);
            }
        });
        if(zhihuList.size() == 0){
            zhihuRecyclerView.setPullRefreshEnabled(true);
        }else {
            zhihuRecyclerView.setPullRefreshEnabled(false);
        }
        initZhihuData();

    }

    private int zhiIndex = 0;
    private int zhiTimeIndex = 1;
    /**
     * 拼接知乎的参数
     * */
    private String zhiType( ){
        zhiIndex = --zhiIndex ;
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH,zhiIndex);

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(year-1900,month,day);
        String dateStr = format.format(date);
        return dateStr;

    }
    private void initZhihuData(){
        NetUtil.zhiRequest("latest", new McResponseSuccessHandler<ZhiHuListBean>() {
            @Override
            public void successHandle(ZhiHuListBean zhiHuListBean) {
                TimeBean timeBean = new TimeBean();
                timeBean.setTime(createZhiTime());
                zhihuList.add(timeBean);
                for(int i = 0;i<zhiHuListBean.getStories().size();i++){
                    ZhiHuListBean.StoriesBean bean1 = zhiHuListBean.getStories().get(i);
                    zhihuList.add(bean1);
                }
                if(zhihuAdapter != null){
                    zhihuAdapter.notifyDataSetChanged();
                }
                zhihuRecyclerView.setPullRefreshEnabled(false);
            }
        });

    }

    private void zhiLoadMoreData(String type, final RecyclerView.Adapter adapter, final XRecyclerView recyclerView){
        NetUtil.zhiRequest("before/" + type, new McResponseSuccessHandler<ZhiHuListBean>() {
            @Override
            public void successHandle(ZhiHuListBean zhiHuListBean) {
                TimeBean timeBean = new TimeBean();
                timeBean.setTime(createZhiTime());
                zhihuList.add(timeBean);
                for(int i = 0;i<zhiHuListBean.getStories().size();i++){
                    ZhiHuListBean.StoriesBean bean = zhiHuListBean.getStories().get(i);
                    zhihuList.add(zhihuList.size(),bean);
                }
                stopRefresh(adapter,recyclerView);
            }
        });
    }

    /**
     * 给时间bean生成数据
     * */
    private String createZhiTime(){
        zhiTimeIndex = --zhiTimeIndex ;
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH,zhiTimeIndex);
        final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
                "星期六" };
        int week = c.get(Calendar.DAY_OF_WEEK)-1;
        String weekStr = dayNames[week];
        int month = c.get(Calendar.MONTH)+1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        String dateStr = month+"月"+day+"日"+"  "+weekStr;
        return dateStr;
    }


    ///////////////////////////////////////////////////////////////////////////
    private void setupJokerLayoutClickEvents(View view){
        initJokerView(view);
        initJokerData();

    }

    private void initJokerView(View view){
        jokerRecyclerView = (XRecyclerView)view.findViewById(R.id.reader_container_rc);
        jokerAdapter = new JokerAdapter(context,jokerList,jokerRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        jokerRecyclerView.setItemAnimator(new XDefaultItemAnimator());
        jokerRecyclerView.setLayoutManager(layoutManager);
        jokerRecyclerView.setAdapter(jokerAdapter);
        jokerRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                initJokerData();
            }

            @Override
            public void onLoadMore() {
                initJokerData();
            }
        });
        if(jokerList.size() == 0){
            jokerRecyclerView.setPullRefreshEnabled(true);
        }else {
            jokerRecyclerView.setPullRefreshEnabled(false);
        }
    }

    private int jokerIndex = 0;
    private void initJokerData(){
        jokerIndex = ++ jokerIndex;
        final String page = jokerIndex+"";
        final List<Object> list = new ArrayList<>();
        NetUtil.jokerTextRequest(page, "10", new McResponseSuccessHandler<JokerTextBean>() {
            @Override
            public void successHandle(JokerTextBean jokerTextBean) {
                for (int i = 0;i<jokerTextBean.getResult().getData().size();i++){
                    JokerTextBean.ResultBean.DataBean bean = jokerTextBean.getResult().getData().get(i);
                    list.add(bean);
                }
                initJokerDataPic(page,list);
            }
        });

    }
    private void initJokerDataPic(String page,final List list ){
        NetUtil.jokerPicRequest(page, "10", new McResponseSuccessHandler<JokerPicBean>() {
            @Override
            public void successHandle(JokerPicBean jokerPicBean) {
                for (int i = 0;i<jokerPicBean.getResult().getData().size();i++){
                    JokerPicBean.ResultBean.DataBean bean = jokerPicBean.getResult().getData().get(i);
                    list.add(bean);
                }
                Collections.shuffle(list);
                for(int i = 0 ;i<list.size();i++){
                    jokerList.add(list.get(i));
                }
                stopRefresh(jokerAdapter,jokerRecyclerView);

            }
        });
    }


    ////////////////////////////////////////////////////////////////////////////
    public void notifyMCData(){
        if(douAdapter != null){
            for(int i = 0;i<douList.size();i++){
                douAdapter.notifyItemChanged(i);
            }
        }
        if(zhihuAdapter != null) {
            for (int i = 0; i < zhihuList.size(); i++) {
                zhihuAdapter.notifyItemChanged(i);
            }
        }

    }

    private void initRecyclerView(XRecyclerView xRecyclerView,View view){
        xRecyclerView = (XRecyclerView)view.findViewById(R.id.reader_container_rc);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(layoutManager);
    }

    private void stopRefresh(RecyclerView.Adapter adapter,XRecyclerView recyclerView){
        recyclerView.setPullRefreshEnabled(false);
        recyclerView.loadMoreComplete();
        adapter.notifyDataSetChanged();
    }


}
