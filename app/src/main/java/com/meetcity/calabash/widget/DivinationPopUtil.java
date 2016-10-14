package com.meetcity.calabash.widget;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.meetcity.calabash.R;
import com.meetcity.calabash.util.SPUtil;
import com.meetcity.calabash.util.SeeUtil;
import com.meetcity.calabash.bean.DivinationBean;

/**
 * Created by wds1993225 on 2016/9/12.
 */
public class DivinationPopUtil {



    public PopupWindow initDivinationPop(final Context context){
        final PopupWindow popupWindow;
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.divination_pop,null);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT,960,false);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.constellation_anim_style);


        TextView xiongji = (TextView)view.findViewById(R.id.divination_xiongji_pop);
        TextView title = (TextView)view.findViewById(R.id.divination_title_pop);
        TextView liunian = (TextView)view.findViewById(R.id.divination_liunian_pop);
        TextView shiye = (TextView)view.findViewById(R.id.divination_shiye_pop);
        TextView caifu = (TextView)view.findViewById(R.id.divination_caifu_pop);
        TextView zishen = (TextView)view.findViewById(R.id.divination_zishen_pop);
        TextView jiating = (TextView)view.findViewById(R.id.divination_jiating_pop);
        TextView yinyuan = (TextView)view.findViewById(R.id.divination_yinyuan_pop);
        TextView yiju = (TextView)view.findViewById(R.id.divination_yiju_pop);
        TextView mingyu = (TextView)view.findViewById(R.id.divination_mingyu_pop);
        TextView jiankang = (TextView)view.findViewById(R.id.divination_jiankang_pop);
        TextView youyi = (TextView)view.findViewById(R.id.divination_youyi_pop);

        LinearLayout moreLl = (LinearLayout)view.findViewById(R.id.divination_more_pop);

        DivinationBean bean = SPUtil.getDivination(context);

        xiongji.setText(bean.getXiongji()+"签");
        title.setText(bean.getTitle());
        liunian.setText(bean.getLiunian());
        shiye.setText(bean.getShiye());
        caifu.setText(bean.getCaifu());
        zishen.setText(bean.getZishen());
        jiating.setText(bean.getJiating());
        yinyuan.setText(bean.getYinyuan());
        yiju.setText(bean.getYiju());
        mingyu.setText(bean.getMingyu());
        jiankang.setText(bean.getJiankang());
        youyi.setText(bean.getYouyi());

        moreLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeeUtil.togoDivinationActivity(context,"");
                popupWindow.dismiss();
            }
        });

        return popupWindow;
    }

}
