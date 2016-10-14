package com.meetcity.calabash.NetUtil;

import com.google.gson.Gson;
import com.meetcity.calabash.McApplication;
import com.meetcity.calabash.bean.DouBean;
import com.meetcity.calabash.bean.DouWebBean;
import com.meetcity.calabash.bean.JokerPicBean;
import com.meetcity.calabash.bean.JokerTextBean;
import com.meetcity.calabash.bean.NewsBean;
import com.meetcity.calabash.bean.WeixinBean;
import com.meetcity.calabash.bean.YiBean;
import com.meetcity.calabash.bean.ZhiHuListBean;
import com.meetcity.calabash.interf.McResponseHandle;
import com.meetcity.calabash.interf.McResponseSuccessHandler;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wds1993225 on 2016/8/17.
 */
public class NetUtil <T>{





    public static OkHttpClient httpLog(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        return client;
    }

    public static McApi createMcApi(String baseUrl){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(NetUtil.httpLog())
                .build();
        McApi mcApi = retrofit.create(McApi.class);
        return mcApi;
    }


    /**
     * @param day 时间
     * @param timeType day week month year
     * @param type 星座类型
     * */
    public static void constellationRequest(String day, String timeType, String type
            , final McResponseHandle<ResponseBody> mcResponseHandle){

        Call<ResponseBody> call = createMcApi(McApi.constellation_E_base_url)
                .getConstellationERequest(day,"99817749",timeType
                ,"8794a644893074fa11ed0a64a036a30a","own",type);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                mcResponseHandle.successHandle(response);

            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                McApplication.getInstance().handleGlobeResponseError().errorHandle(t);
            }
        });
    }


    /**
     * @param type  新闻的类型
     * */
    public static void newsRequest(String type, final McResponseSuccessHandler<NewsBean> mcResponseSuccessHandler){
        Call<ResponseBody> call = createMcApi(McApi.news_base_url).getNewsRequest(type,McApi.news_key);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Gson gson = new Gson();
                try {
                    NewsBean bean = gson.fromJson(response.body().string(),NewsBean.class);
                    mcResponseSuccessHandler.successHandle(bean);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                McApplication.getInstance().handleGlobeResponseError().errorHandle(t);
            }
        });
    }

    public static void zhiRequest(String id, final McResponseSuccessHandler<ZhiHuListBean> mcResponseSuccessHandler){
        Call<ResponseBody> call = createMcApi(McApi.zhihu_base_url).getZhiRequest(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Gson gson = new Gson();
                try {
                    ZhiHuListBean bean = gson.fromJson(response.body().string(),ZhiHuListBean.class);
                    mcResponseSuccessHandler.successHandle(bean);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                McApplication.getInstance().handleGlobeResponseError().errorHandle(t);
            }
        });

    }


    public static void YiRequest(String time, final McResponseSuccessHandler<YiBean> mcResponseHandler){
        Call<ResponseBody> call = createMcApi(McApi.yike_base_url).getYigeRequest(time);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Gson gson = new Gson();
                try {
                    YiBean bean = gson.fromJson(response.body().string(),YiBean.class);
                    mcResponseHandler.successHandle(bean);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public static void WeiRequest(final McResponseSuccessHandler<WeixinBean> mcResponseSuccessHandler){
        Call<ResponseBody> call = createMcApi(McApi.weixin_base_url).getWeixinRequest(McApi.weixin_key);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Gson gson = new Gson();
                try {
                    WeixinBean bean = gson.fromJson(response.body().string(),WeixinBean.class);
                    mcResponseSuccessHandler.successHandle(bean);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                McApplication.getInstance().handleGlobeResponseError().errorHandle(t);
            }
        });
    }

    public static void douRequest(String date, final McResponseSuccessHandler<DouBean> mcResponseSuccessHandler){
        Call<ResponseBody> call = createMcApi(McApi.dou_base_url).getDoubanRequest(date);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Gson gson = new Gson();
                try {
                    DouBean bean = gson.fromJson(response.body().string(),DouBean.class);
                    mcResponseSuccessHandler.successHandle(bean);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                McApplication.getInstance().handleGlobeResponseError().errorHandle(t);
            }
        });
    }

    public static void douWebRequest(String id, final McResponseSuccessHandler<DouWebBean> mcResponseSuccessHandler){
        Call<ResponseBody> call = createMcApi(McApi.douban_ARTICLE_DETAIL).getDoubanDetailRequest(id);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Gson gson = new Gson();
                try {
                    DouWebBean bean = gson.fromJson(response.body().string(),DouWebBean.class);
                    mcResponseSuccessHandler.successHandle(bean);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                McApplication.getInstance().handleGlobeResponseError().errorHandle(t);
            }
        });
    }

    public static void jokerTextRequest(String page, String pageSize
            , final McResponseSuccessHandler<JokerTextBean> mcResponseSuccessHandler){
        Call<ResponseBody> call = createMcApi(McApi.joker_text_base_url).getJokerTextRequest(McApi.joker_base_key
                ,page,pageSize);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Gson gson = new Gson();
                try {
                    JokerTextBean bean = gson.fromJson(response.body().string(),JokerTextBean.class);
                    mcResponseSuccessHandler.successHandle(bean);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                McApplication.getInstance().handleGlobeResponseError().errorHandle(t);
            }
        });
    }

    public static void jokerPicRequest(String page, String pageSize
            , final McResponseSuccessHandler<JokerPicBean> mcResponseSuccessHandler){
        Call<ResponseBody> call = createMcApi(McApi.joker_pic_base_url).getJokerTextRequest(McApi.joker_base_key
                ,page,pageSize);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Gson gson = new Gson();
                try {
                    JokerPicBean bean = gson.fromJson(response.body().string(),JokerPicBean.class);
                    mcResponseSuccessHandler.successHandle(bean);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                McApplication.getInstance().handleGlobeResponseError().errorHandle(t);
            }
        });
    }
/*
    public  T convertBean(T t,Response<ResponseBody> response){
        Gson gson = new Gson();
        try {
            T bean = gson.fromJson(response.body().string(),((Object)T).class);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }*/

  /*  public T fun(T type ,Response<ResponseBody> response) {
        Gson gson = new Gson();
        T bean = null;
        try {
            bean = gson.fromJson(response.body().string(),type.getClass());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;

    }*/







}
