package com.meetcity.calabash.interf;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by wds1993225 on 2016/9/14.
 */
public interface McResponseHandle<T> {

    void successHandle(Response<T> response);

}
