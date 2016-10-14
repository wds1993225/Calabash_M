package com.meetcity.calabash;

import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.meetcity.calabash.util.McUtil;
import com.meetcity.calabash.util.SPUtil;
import com.meetcity.calabash.widget.ConstellationPagerAdapter;
import com.meetcity.calabash.widget.ConstellationPopUtil;


/**
 * Created by wds1993225 on 2016/8/30.
 */
public class ConstellationActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ConstellationPagerAdapter pagerAdapter;
    private TabLayout tabLayout;
    private Toolbar toolbar;

    private LinearLayout changeLl;
    private TextView changeTv;
    private PopupWindow popupWindow;
    private View view;
    private String name = "";

    private ImageView backIm;
    private SimpleDraweeView simpleDraweeView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constellation2);
        initTab();
        initImageView();
        //initChange();
        initPop();
        initColor(name);


    }


    private void initTab(){
        toolbar = (Toolbar)findViewById(R.id.reader_toolbar);
        /*toolbar.setNavigationIcon(R.drawable.pic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/
        viewPager = (ViewPager)findViewById(R.id.reader_viewpager);
        tabLayout = (TabLayout)findViewById(R.id.reader_tablayout);
        pagerAdapter = new ConstellationPagerAdapter(ConstellationActivity.this);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);



    }
    private View.OnClickListener listener;
    private void initChange(){
        LayoutInflater inflater = LayoutInflater.from(ConstellationActivity.this);
        view = inflater.inflate(R.layout.constellation_pop,null);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewPager.LayoutParams.WRAP_CONTENT,false);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.constellation_anim_style);

        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()){
                    case R.id.constellation_baiyang_ll:
                        changeConstellation("白羊座","aries");
                        break;
                    case R.id.constellation_jinniu_ll:
                        changeConstellation("金牛座","Taurus");
                        break;
                    case R.id.constellation_shuangzi_ll:
                        changeConstellation("双子座","Gemini");
                        break;
                    case R.id.constellation_juxie_ll:
                        changeConstellation("巨蟹座","Cancer");
                        break;
                    case R.id.constellation_shizi_ll:
                        changeConstellation("狮子座","leo");
                        break;
                    case R.id.constellation_chunv_ll:
                        changeConstellation("处女座","Virgo");
                        break;
                    case R.id.constellation_tiancheng_ll:
                        changeConstellation("天秤座","Libra");
                        break;
                    case R.id.constellation_tianxie_ll:
                        changeConstellation("天蝎座","Scorpio");
                        break;
                    case R.id.constellation_sheshou_ll:
                        changeConstellation("射手座","Sagittarius");
                        break;
                    case R.id.constellation_mojie_ll:
                        changeConstellation("摩羯座","Capricorn");
                        break;
                    case R.id.constellation_shuiping_ll:
                        changeConstellation("水瓶座","Aquarius");
                        break;
                    case R.id.constellation_shuangyu_ll:
                        changeConstellation("双鱼座","Pisces");
                        break;
                    default:
                        popupWindow.dismiss();
                        break;
                }
            }
        };

        LinearLayout baiyang = (LinearLayout)view.findViewById(R.id.constellation_baiyang_ll);
        LinearLayout jinniu = (LinearLayout)view.findViewById(R.id.constellation_jinniu_ll);
        LinearLayout shuangzi = (LinearLayout)view.findViewById(R.id.constellation_shuangzi_ll);
        LinearLayout juxie = (LinearLayout)view.findViewById(R.id.constellation_juxie_ll);
        LinearLayout shizi = (LinearLayout)view.findViewById(R.id.constellation_shizi_ll);
        LinearLayout chunv = (LinearLayout)view.findViewById(R.id.constellation_chunv_ll);
        LinearLayout tiancheng = (LinearLayout)view.findViewById(R.id.constellation_tiancheng_ll);
        LinearLayout tianxie = (LinearLayout)view.findViewById(R.id.constellation_tianxie_ll);
        LinearLayout sheshou = (LinearLayout)view.findViewById(R.id.constellation_sheshou_ll);
        LinearLayout mojie = (LinearLayout)view.findViewById(R.id.constellation_mojie_ll);
        LinearLayout shuiping = (LinearLayout)view.findViewById(R.id.constellation_shuiping_ll);
        LinearLayout shuangyu = (LinearLayout)view.findViewById(R.id.constellation_shuangyu_ll);
        baiyang.setOnClickListener(listener);
        jinniu.setOnClickListener(listener);
        shuangzi.setOnClickListener(listener);
        juxie.setOnClickListener(listener);
        shizi.setOnClickListener(listener);
        chunv.setOnClickListener(listener);
        tiancheng.setOnClickListener(listener);
        tianxie.setOnClickListener(listener);
        sheshou.setOnClickListener(listener);
        mojie.setOnClickListener(listener);
        shuiping.setOnClickListener(listener);
        shuangyu.setOnClickListener(listener);



        changeLl = (LinearLayout)findViewById(R.id.constellation_change_ll);
        changeTv = (TextView)findViewById(R.id.constellation_change_tv);
        changeLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.showAtLocation(tabLayout, Gravity.CENTER,0,0);
            }
        });

    }

    private void changeConstellation(String name,String nameType){
        SPUtil.saveConstellation(ConstellationActivity.this,nameType);
        changeTv.setText(name);
        popupWindow.dismiss();
        pagerAdapter.notifyDataSetChanged();
        //pagerAdapter.instantiateItem(viewPager,1);
    }

    private void initPop(){
        ConstellationPopUtil constellationPopUtil = new ConstellationPopUtil();
        popupWindow = constellationPopUtil.initConstellationPop(ConstellationActivity.this);
        changeLl = (LinearLayout)findViewById(R.id.constellation_change_ll);
        changeTv = (TextView)findViewById(R.id.constellation_change_tv);
        changeLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                popupWindow.showAtLocation(tabLayout, Gravity.CENTER,0,0);
            }
        });

        name = SPUtil.getConstellation(ConstellationActivity.this);
        if(name.equals("aries")){
            changeTv.setText("白羊座");
        }else if(name.equals("Taurus")){
            changeTv.setText("金牛座");
        }
        else if(name.equals("Gemini")){
            changeTv.setText("双子座");
        }
        else if(name.equals("Cancer")){
            changeTv.setText("巨蟹座");
        }
        else if(name.equals("leo")){
            changeTv.setText("狮子座");
        }
        else if(name.equals("Virgo")){
            changeTv.setText("处女座");
        }
        else if(name.equals("Libra")){
            changeTv.setText("天秤座");
        }
        else if(name.equals("Scorpio")){
            changeTv.setText("天蝎座");
        }
        else if(name.equals("Sagittarius")){
            changeTv.setText("射手座");
        }
        else if(name.equals("Capricorn")){
            changeTv.setText("摩羯座");
        }
        else if(name.equals("Aquarius")){
            changeTv.setText("水瓶座");
        }
        else if(name.equals("Pisces")){
            changeTv.setText("双鱼座");
        }
        constellationPopUtil.setSwapConstellation(new ConstellationPopUtil.SwapConstellation() {
            @Override
            public void OnSwap(String name,String nameType) {
                changeTv.setText(name);
                pagerAdapter.notifyDataSetChanged();
                initColor(nameType);
            }
        });


    }

    private String initName(String name){
        String nameRe = "白羊座";
        switch (name){
            case "aries":
                nameRe = "白羊座";
                break;
            case "Taurus":
                nameRe = "金牛座";
                break;
            case "Gemini":
                nameRe = "双子座";
                break;
            case "Cancer":
                nameRe = "巨蟹座";
                break;
            case "leo":
                nameRe = "狮子座";
                break;
            case "Virgo":
                nameRe = "处女座";
                break;
            case "Libra":
                nameRe = "天秤座";
                break;
            case "Scorpio":
                nameRe = "天蝎座";
                break;
            case "Sagittarius":
                nameRe = "射手座";
                break;
            case "Capricorn":
                nameRe = "摩羯座";
                break;
            case "Aquarius":
                nameRe = "水瓶座";
                break;
            case "Pisces":
                nameRe = "双鱼座";
                break;
            default:
                break;
        }
        return nameRe;
    }

    private void initColor(String name){
        int pic = chooseColor(name);
        int color = McUtil.changeColor(ConstellationActivity.this,pic);
        Log.i("WATER","color"+color);
        Log.i("WATER","name"+name);

        tabLayout.setBackgroundColor(color);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorWhite));

    }
    private int chooseColor(String name){
        int pic = R.mipmap.astro_logo_baiyang;
        switch (name){
            case "aries":
                pic = R.mipmap.astro_logo_baiyang;
                break;
            case "Taurus":
                pic = R.mipmap.astro_logo_jinniu;
                break;
            case "Gemini":
                pic = R.mipmap.astro_logo_shuangzi;
                break;
            case "Cancer":
                pic = R.mipmap.astro_logo_juxie;
                break;
            case "leo":
                pic = R.mipmap.astro_logo_shizi;
                break;
            case "Virgo":
                pic = R.mipmap.astro_logo_chunv;
                break;
            case "Libra":
                pic = R.mipmap.astro_logo_tiancheng;
                break;
            case "Scorpio":
                pic = R.mipmap.astro_logo_tianxie;
                break;
            case "Sagittarius":
                pic = R.mipmap.astro_logo_sheshou;
                break;
            case "Capricorn":
                pic = R.mipmap.astro_logo_mojie;
                break;
            case "Aquarius":
                pic = R.mipmap.astro_logo_shuiping;
                break;
            case "Pisces":
                pic = R.mipmap.astro_logo_shuangyu;
                break;
            default:
                break;
        }
        return pic;
    }


    private void initImageView(){

        simpleDraweeView = (SimpleDraweeView)findViewById(R.id.constellation_activity_im);
        DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                .setUri(Uri.parse("res://"+getPackageName()+"/"+R.drawable.pic_constellation_star))//设置uri
                .build();
        simpleDraweeView.setController(mDraweeController);


    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(popupWindow.isShowing()){
                popupWindow.dismiss();
            }else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
