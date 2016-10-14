package com.meetcity.calabash;

import android.app.Activity;
import android.os.Bundle;

import com.meetcity.calabash.widget.McDialog;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by wds1993225 on 2016/9/26.
 */
public class BaseActivity extends Activity {

    private McDialog mcDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mcDialog = new McDialog(this);
        mcDialog.show();
    }

    protected void dismiss(){
        if(mcDialog.isShowing()){
            mcDialog.dismiss();
        }
    }
}
