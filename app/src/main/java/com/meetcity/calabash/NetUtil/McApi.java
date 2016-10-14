package com.meetcity.calabash.NetUtil;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by wds1993225 on 2016/8/17.
 */
public interface McApi {


    public static String constellation_base_url = "http://web.juhe.cn:8080/constellation/";
    public static String constellation_key = "fad7d956a2da3077a2a8b33be291cb79";

    @GET("getAll?")
    Call<ResponseBody> getConstellationRequest(@Query("consName")String name
            ,@Query("type")String type,@Query("key")String key);



    public static String news_base_url = "http://v.juhe.cn/toutiao/";
    public static String news_key = "bb9c803f0e74793bfcb5a452f9ce9fc9";
    @GET("index?")
    Call<ResponseBody> getNewsRequest(@Query("type")String type,@Query("key")String key);


    public static String weixin_base_url = "http://v.juhe.cn/weixin/";
    public static String weixin_key = "aec80264915a31d13206d51cb0d6cca9";
    @GET("query?")
    Call<ResponseBody> getWeixinRequest(@Query("key")String key);


    /**
     * 知乎的api
     * */
    // 消息内容获取与离线下载
    public static final String zhihu_base_url = "http://news-at.zhihu.com/api/4/";
    //  最新消息
    public static final String LATEST = "http://news-at.zhihu.com/api/4/news/latest";

    @GET("news/{id}")
    Call<ResponseBody> getZhiRequest(@Path(value = "id",encoded = true)String id);



    /**
     * 煎蛋的api*/
    public static final String jiandan_base_url = "http://jandan.net";
    @GET("/")
    Call<ResponseBody> getJiandanRequest(@Query("oxwlxojflwblxbsapi") String name,@Query("page")String page);


    /**
     * 星座的api*/
    public static final String constellation_E_base_url = "http://zhwnlapi.etouch.cn/Ecalender/";
    @GET("Horoscope?")
    Call<ResponseBody> getConstellationERequest(@Query("day")String day
            ,@Query("app_key")String app_key
            ,@Query("timetype")String timetype
            ,@Query("devid")String devid
            ,@Query("channel")String channel,@Query("type")String type);

    /**
     * one一刻
     * */

    public static final String yike_base_url = "http://v3.wufazhuce.com:8000/api/hp/";
    @GET("bymonth/{time}")
    Call<ResponseBody> getYigeRequest(@Path(value = "time",encoded = true)String time);

    /**
     * 豆瓣api
     * */
    // 豆瓣一刻
    // 根据日期查询消息列表
    // eg:https://moment.douban.com/api/stream/date/2016-08-11
    public static final String dou_base_url = "https://moment.douban.com/api/stream/";

    // 获取文章具体内容
    // eg:https://moment.douban.com/api/post/100484
    public static final String douban_ARTICLE_DETAIL = "https://moment.douban.com/api/";
    @GET("date/{date}")
    Call<ResponseBody> getDoubanRequest(@Path("date")String date);

    @GET("post/{id}")
    Call<ResponseBody> getDoubanDetailRequest(@Path("id")String id);


    /**
     * joker
     * */
    public static final String joker_text_base_url = "http://japi.juhe.cn/joke/content/";

    public static final String joker_pic_base_url = "http://japi.juhe.cn/joke/img/";

    public static final String joker_base_key = "58af26358b0b7f1e398ef16dbe81ce9c";

    @GET("text.from?")
    Call<ResponseBody> getJokerTextRequest(@Query("key") String key
                    ,@Query("page")String page,@Query("pagesize")String pagesize);


    @GET("text.from?")
    Call<ResponseBody> getJokerPicRequest(@Query("key")String key
            ,@Query("page")String page,@Query("pagesize")String pageSize);





}
