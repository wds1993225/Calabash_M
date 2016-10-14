package com.meetcity.calabash.widget;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.meetcity.calabash.R;

/**
 * Created by wds1993225 on 2016/8/27.
 */
public class HeadLayout extends LinearLayout {

    private ImageView back;
    private ImageView setting;
    private TextView title;

    public HeadLayout(Context context) {
        super(context);
    }

    public HeadLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.activity_head,this);
        back = (ImageView)findViewById(R.id.head_back);
        setting = (ImageView)findViewById(R.id.head_setting);
        title = (TextView)findViewById(R.id.head_title);
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
            }
        });

    }

}
