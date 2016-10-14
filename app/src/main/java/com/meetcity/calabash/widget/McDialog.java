package com.meetcity.calabash.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import com.meetcity.calabash.R;

/**
 * Created by wds1993225 on 2016/10/13.
 */
public class McDialog extends Dialog {

    private SlackLoadingView slackLoadingView;
    protected McDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public McDialog(Context context) {
        this(context,R.style.widget_mc_dialog);

    }

    public McDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.widget_mc_dialog);
        slackLoadingView = (SlackLoadingView)findViewById(R.id.widget_dialog);
    }

    @Override
    public void show() {
        super.show();
        slackLoadingView.start();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        slackLoadingView.reset();
    }
}
