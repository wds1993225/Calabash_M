package com.meetcity.calabash;

import android.app.Application;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.meetcity.calabash.interf.McResponseErrorHandler;
import com.squareup.leakcanary.LeakCanary;

import im.fir.sdk.FIR;

/**
 * Created by wds1993225 on 2016/9/14.
 */
public class McApplication extends Application {

    private static McApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Fresco.initialize(this);
        //LeakCanary.install(this);
        FIR.init(this);
    }

    public static synchronized McApplication getInstance(){
        return mInstance;
    }

    public McResponseErrorHandler handleGlobeResponseError(){
        return new McResponseErrorHandler() {
            @Override
            public void errorHandle(Throwable t) {
                Log.i("WATER",t.getMessage());

            }
        };
    }
}
