package com.meetcity.calabash;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.meetcity.calabash.NetUtil.NetUtil;
import com.meetcity.calabash.bean.YiBean;
import com.meetcity.calabash.interf.McResponseSuccessHandler;
import com.meetcity.calabash.util.McUtil;
import com.meetcity.calabash.util.FrescoUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by wds1993225 on 2016/9/20.
 */
public class YiActivity extends Activity {

    private ViewPager viewPager;
    private LayoutInflater inflater ;
    private List<LinearLayout> list = new ArrayList<>();
    private MyPagerAdapter adapter ;

    private int index = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yi);
        inflater = LayoutInflater.from(this);
        initData(dateUtil(1));
        initView();
    }

    private void initView(){
        adapter = new MyPagerAdapter(list);
        viewPager = (ViewPager)findViewById(R.id.yi_activity_vp);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                if(position == list.size()-1){
                    initData(dateUtil(1));
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void fillView(YiBean yiBean){

        for(int i = 0;i<yiBean.getData().size();i++){
            final YiBean.DataBean bean ;
            bean = yiBean.getData().get(i);
            LinearLayout view = (LinearLayout)inflater.inflate(R.layout.yi_item,null);
            SimpleDraweeView imageView = (SimpleDraweeView)view.findViewById(R.id.yi_item_pic_im);
            TextView num = (TextView)view.findViewById(R.id.yi_item_num_tv);
            TextView author = (TextView)view.findViewById(R.id.yi_item_author_tv);
            TextView time = (TextView)view.findViewById(R.id.yi_item_time_tv);
            TextView content = (TextView)view.findViewById(R.id.yi_item_content_tv);
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(bean.getHp_img_url())
                    .setTapToRetryEnabled(true)
                    .setOldController(imageView.getController())
                    .build();
            imageView.setController(controller);
            imageView.setImageURI(bean.getHp_img_url());
            //Picasso.with(this).load(bean.getHp_img_url()).memoryPolicy(MemoryPolicy.NO_CACHE,MemoryPolicy.NO_STORE).into(imageView);
            num.setText(bean.getHp_title());
            author.setText(bean.getHp_author());
            String timeStr = timeUtil(bean.getHp_makettime());
            time.setText(timeStr);
            McUtil.changeFont(YiActivity.this,content);
            content.setText(bean.getHp_content());
           /* imageView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    initDialog(bean.getHp_img_url());
                    return true;
                }
            });*/
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initDialog(bean.getHp_img_url());
                }
            });
            list.add(view);
        }
    }

    private String timeUtil(String time){
        String timeStr = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date date = format.parse(time);
            int year = date.getYear()+1900;
            int month = date.getMonth()+1;
            int day = date.getDate();
            timeStr = timeToEnd(month)+" "+day+",  "+year;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeStr;
    }
    private String timeToEnd(int i){
        String month = "";
        switch (i){
            case 1:
                month = "Jan.";
                break;
            case 2:
                month = "Feb.";
                break;
            case 3:
                month = "Mar.";
                break;
            case 4:
                month = "Apr.";
                break;
            case 5:
                month = "May.";
                break;
            case 6:
                month = "June.";
                break;
            case 7:
                month = "July.";
                break;
            case 8:
                month = "Aug.";
                break;
            case 9:
                month = "Sept";
                break;
            case 10:
                month = "Oct.";
                break;
            case 11:
                month = "Nov.";
                break;
            case 12:
                month = "Dec.";
                break;
            default:
                break;
        }
        return month;
    }

    /**
     * 生成请求中的日期
     * 往前请求一个月的数据
     * */
    private String dateUtil(int i){

        index = index +i;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        int year = date.getYear()+1900;
        int month = date.getMonth()+1-index;
        int day = date.getDate();
        //String str = format.format(date);
        String str = year+"-"+month+"-"+day;

        return str;
    }

    /**
     * 更新不及时时，请求前一天的数据
     * */
    private String dateUtil(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String timeStr = year+"-"+month+"-"+day;
        return timeStr;
    }

    private void initData(String time){
        NetUtil.YiRequest(time + "%" + "2000:00:00", new McResponseSuccessHandler<com.meetcity.calabash.bean.YiBean>() {
            @Override
            public void successHandle(YiBean yiBean) {
                if(yiBean.getData().size()==0 ){
                    initData(dateUtil());
                } else if(yiBean.getData().size() == 1){
                    fillView(yiBean);
                    initData(dateUtil());
                }else {
                    fillView(yiBean);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void initDialog(final String url){

        new SweetAlertDialog(this)
                .setContentText("保存这张图片?")
                .setConfirmText("保存")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        FrescoUtil.savePicture(url,YiActivity.this,false);
                        sweetAlertDialog.setContentText("图片已保存在“/storage/emulated/0/Calabash/MC_img/目录下”")
                                .setConfirmText("好的")
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                    }
                })
                .setCancelText("取消")
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismiss();

                    }
                })
                .show();
    }

    private class MyPagerAdapter extends PagerAdapter {
        List<LinearLayout> list;

        public MyPagerAdapter(List<LinearLayout> list) {
            super();
            this.list = list;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position,
                                Object object) {
            container.removeView(list.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(list.get(position));


            return list.get(position);
        }

    }


}
