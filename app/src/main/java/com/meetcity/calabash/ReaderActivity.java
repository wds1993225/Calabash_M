package com.meetcity.calabash;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.meetcity.calabash.widget.ReadWebPagerAdapter;

import lib.app.SwipeBackActivity;


/**
 * Created by wds1993225 on 2016/8/30.
 */
public class ReaderActivity extends SwipeBackActivity {

    private ViewPager viewPager;
    private ReadWebPagerAdapter pagerAdapter;
    private TabLayout tabLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);
        initTab();
    }

    private void initTab(){
        toolbar = (Toolbar)findViewById(R.id.reader_toolbar);
        toolbar.setNavigationIcon(R.drawable.pic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        viewPager = (ViewPager)findViewById(R.id.reader_viewpager);
        tabLayout = (TabLayout)findViewById(R.id.reader_tablayout);
        pagerAdapter = new ReadWebPagerAdapter(ReaderActivity.this);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onPause() {
        super.onPause();
        pagerAdapter.notifyMCData();
    }
}
