package com.meetcity.calabash.widget;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.meetcity.calabash.R;
import com.meetcity.calabash.util.SPUtil;

/**
 * Created by wds1993225 on 2016/9/12.
 */
public class ConstellationPopUtil {



    public PopupWindow initConstellationPop(final Context context){
        View.OnClickListener listener;
        final PopupWindow popupWindow;
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
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
                        changeConstellation("白羊座","aries",context,popupWindow);
                        break;
                    case R.id.constellation_jinniu_ll:
                        changeConstellation("金牛座","Taurus",context,popupWindow);
                        break;
                    case R.id.constellation_shuangzi_ll:
                        changeConstellation("双子座","Gemini",context,popupWindow);
                        break;
                    case R.id.constellation_juxie_ll:
                        changeConstellation("巨蟹座","Cancer",context,popupWindow);
                        break;
                    case R.id.constellation_shizi_ll:
                        changeConstellation("狮子座","leo",context,popupWindow);
                        break;
                    case R.id.constellation_chunv_ll:
                        changeConstellation("处女座","Virgo",context,popupWindow);
                        break;
                    case R.id.constellation_tiancheng_ll:
                        changeConstellation("天秤座","Libra",context,popupWindow);
                        break;
                    case R.id.constellation_tianxie_ll:
                        changeConstellation("天蝎座","Scorpio",context,popupWindow);
                        break;
                    case R.id.constellation_sheshou_ll:
                        changeConstellation("射手座","Sagittarius",context,popupWindow);
                        break;
                    case R.id.constellation_mojie_ll:
                        changeConstellation("摩羯座","Capricorn",context,popupWindow);
                        break;
                    case R.id.constellation_shuiping_ll:
                        changeConstellation("水瓶座","Aquarius",context,popupWindow);
                        break;
                    case R.id.constellation_shuangyu_ll:
                        changeConstellation("双鱼座","Pisces",context,popupWindow);
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

        return popupWindow;
    }
    //popupWindow.showAtLocation(tabLayout, Gravity.CENTER,0,0);

    private void changeConstellation(String name,String nameType,Context context,PopupWindow popupWindow){
        SPUtil.saveConstellation(context,nameType);
        popupWindow.dismiss();
        swapConstellation.OnSwap(name,nameType);


       /* pagerAdapter.notifyDataSetChanged();
        changeTv.setText(name);*/
    }

    public interface SwapConstellation{
        void OnSwap(String name,String nameType);
    }
    private SwapConstellation swapConstellation;

    public void setSwapConstellation(SwapConstellation swapConstellation) {
        this.swapConstellation = swapConstellation;
    }
}
