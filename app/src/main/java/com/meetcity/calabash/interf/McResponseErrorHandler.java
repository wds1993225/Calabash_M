package com.meetcity.calabash.interf;

/**
 * Created by wds1993225 on 2016/9/14.
 */
public interface McResponseErrorHandler<T> {
    void errorHandle(Throwable t);
}
