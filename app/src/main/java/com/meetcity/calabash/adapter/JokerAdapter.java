package com.meetcity.calabash.adapter;

import android.content.Context;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.meetcity.calabash.util.DateTimeHelper;
import com.meetcity.calabash.util.McUtil;
import com.meetcity.calabash.R;
import com.meetcity.calabash.util.SeeUtil;
import com.meetcity.calabash.bean.JokerPicBean;
import com.meetcity.calabash.bean.JokerTextBean;
import com.meetcity.calabash.bean.TimeBean;
import com.qq.e.ads.nativ.NativeADDataRef;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/9.
 */
public class JokerAdapter extends Adapter<ViewHolder>  {

    private Context context;
    private List<Object> data;
    private List<String> list = new ArrayList<>();

    static final int TYPE_DATA = 0;
    static final int TYPE_DATA2 = 2;
    static final int TYPE_AD = 1;
    static final int TYPE_TIME = 3;

    private RecyclerView recyclerView ;
    /**
     * 1001 1007 文字
     * 1004 图集*/






    public JokerAdapter(Context context,List<Object> list,RecyclerView recyclerView){
        this.context = context;
        this.data = list;
        this.recyclerView = recyclerView;
    }

    public void updateDownloadingItem(NativeADDataRef adItem){
        if(adItem != null){
            ViewHolder viewHolder = recyclerView.findViewHolderForPosition(3);
            if(viewHolder instanceof ADViewHolder2){
                ADViewHolder2 holder = (ADViewHolder2)viewHolder;
                switch (adItem.getAPPStatus()) {
                    case 0:
                        holder.download.setText("下载");
                        break;
                    case 1:
                        holder.download.setText("启动");
                        break;
                    case 2:
                        holder.download.setText("更新");
                        break;
                    case 4:
                        holder.download.setText(adItem.getProgress() + "%");
                        break;
                    case 8:
                        holder.download.setText("安装");
                        break;
                    case 16:
                        holder.download.setText("下载失败，重新下载");
                        break;
                    default:
                        holder.download.setText("浏览");
                        break;
                }
            }

        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        ViewHolder holder = null;
        switch (viewType){
            case TYPE_DATA:
                view = LayoutInflater.from(context).inflate(R.layout.joker_text_item,parent,false);
                holder = new JokerTextViewHolder(view);
                break;
            case TYPE_AD:
                view = LayoutInflater.from(context).inflate(R.layout.ad_news_item_2,parent,false);
                holder = new ADViewHolder2(view);
                break;
            case TYPE_DATA2:
                view = LayoutInflater.from(context).inflate(R.layout.joker_pic_item,parent,false);
                holder = new JokerPicViewHolder(view);
                break;
            case TYPE_TIME:
                view = LayoutInflater.from(context).inflate(R.layout.date_item,parent,false);
                holder = new TimeViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(holder instanceof JokerTextViewHolder){
            final JokerTextBean.ResultBean.DataBean bean = (JokerTextBean.ResultBean.DataBean)data.get(position);

            McUtil.changeFont(context,((JokerTextViewHolder) holder).contentTv);
            ((JokerTextViewHolder) holder).contentTv.setText(bean.getContent());
           ((JokerTextViewHolder) holder).timeTv.setText(DateTimeHelper.getInterval(bean.getUnixtime()));


        }else if (holder instanceof ADViewHolder2){
            final NativeADDataRef adItem = (NativeADDataRef)data.get(position);
            Picasso.with(context).load(adItem.getImgUrl()).into(((ADViewHolder2) holder).posterIm);
            ((ADViewHolder2) holder).desTv.setText(adItem.getDesc());
            ((ADViewHolder2) holder).titleTv.setText(adItem.getTitle());
            ((ADViewHolder2) holder).download.setText("立即下载");
            switch (adItem.getAPPStatus()) {
                case 0:
                    ((ADViewHolder2) holder).download.setText("下载");
                    break;
                case 1:
                    ((ADViewHolder2) holder).download.setText("启动");
                    break;
                case 2:
                    ((ADViewHolder2) holder).download.setText("更新");
                    break;
                case 4:
                    ((ADViewHolder2) holder).download.setText(adItem.getProgress() + "%");
                    break;
                case 8:
                    ((ADViewHolder2) holder).download.setText("安装");
                    break;
                case 16:
                    ((ADViewHolder2) holder).download.setText("下载失败，重新下载");
                    break;
                default:
                    ((ADViewHolder2) holder).download.setText("浏览");
                    break;
            }
            adItem.onExposured(((ADViewHolder2) holder).allLl);
            ((ADViewHolder2) holder).download.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adItem.onClicked(v);
                }
            });


        }else if(holder instanceof JokerPicViewHolder){
            final JokerPicBean.ResultBean.DataBean bean = (JokerPicBean.ResultBean.DataBean)data.get(position);
            ((JokerPicViewHolder) holder).contentTv.setText(bean.getContent());
            ((JokerPicViewHolder) holder).timeTv.setText(DateTimeHelper.getInterval(bean.getUnixtime()));

            Uri uri = Uri.parse(bean.getUrl());
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(uri).setAutoPlayAnimations(true)
                    .setTapToRetryEnabled(true)
                    .setControllerListener(new BaseControllerListener<ImageInfo>() {
                        @Override
                            public void onFinalImageSet(
                                 String id,
                                 ImageInfo imageInfo,
                                 Animatable anim) {
                            if(anim!=null){
                                anim.start();
                            }
                }
            }).build();

            ((JokerPicViewHolder) holder).pic1Im.setController(controller);

                    /*DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(bean.getUrl())
                    .setAutoPlayAnimations(true)
                    .setTapToRetryEnabled(true)
                    .setOldController(((JokerPicViewHolder)holder).pic1Im.getController())
                    .build();
            ((JokerPicViewHolder)holder).pic1Im.setController(controller);
            ((JokerPicViewHolder)holder).pic1Im.setImageURI(bean.getUrl());*/
            ((JokerPicViewHolder) holder).pic1Im.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(McUtil.isFastClick()){
                        return;
                    }
                    SeeUtil.togoImageActivity(context,bean.getUrl());
                }
            });

        }else if(holder instanceof TimeViewHolder){
            TimeBean bean = (TimeBean)data.get(position);
            ((TimeViewHolder) holder).dateTv.setText(bean.getTime());
        }



    }

    @Override
    public int getItemViewType(int position) {

        if(data.get(position) instanceof JokerTextBean.ResultBean.DataBean ){
            return TYPE_DATA;
        }else if(data.get(position) instanceof JokerPicBean.ResultBean.DataBean){
            return TYPE_DATA2;
        } else if(data.get(position) instanceof TimeBean){
            return TYPE_TIME;
        }else {
            return TYPE_AD;
        }

    }



    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        } else {
            return 0;
        }
    }



    class JokerTextViewHolder extends ViewHolder{

        LinearLayout layoutLl ;
        TextView contentTv;
        TextView timeTv;

        public JokerTextViewHolder(View itemView) {
            super(itemView);
            layoutLl = (LinearLayout)itemView.findViewById(R.id.joker_text_item_ll);
            contentTv = (TextView)itemView.findViewById(R.id.joker_text_item_content_tv);
            timeTv = (TextView)itemView.findViewById(R.id.joker_text_item_time_tv);
        }
    }

    class JokerPicViewHolder extends ViewHolder{

        LinearLayout layoutLl ;
        TextView contentTv;
        TextView timeTv;
        SimpleDraweeView pic1Im;

        public JokerPicViewHolder(View itemView) {
            super(itemView);
            layoutLl = (LinearLayout)itemView.findViewById(R.id.joker_pic_item_ll);
            contentTv = (TextView)itemView.findViewById(R.id.joker_pic_item_content_tv);
            timeTv = (TextView)itemView.findViewById(R.id.joker_pic_item_time_tv);
            pic1Im = (SimpleDraweeView)itemView.findViewById(R.id.joker_pic_item_pic);
        }
    }

    class ADViewHolder2 extends ViewHolder{

        TextView desTv;
        TextView titleTv;
        ImageView posterIm;
        Button download;
        RelativeLayout allLl;

        public ADViewHolder2(View itemView) {
            super(itemView);
            desTv = (TextView)itemView.findViewById(R.id.ad_news_item_2_desc_tv);
            titleTv = (TextView)itemView.findViewById(R.id.ad_news_item_2_title_tv);
            posterIm = (ImageView)itemView.findViewById(R.id.ad_news_item_2_poster_im);
            download = (Button)itemView.findViewById(R.id.ad_news_item_2_download_bt);
            allLl = (RelativeLayout)itemView.findViewById(R.id.ad_news_item_2_all_Rl);
        }
    }

    class TimeViewHolder extends ViewHolder{

        TextView dateTv;

        public TimeViewHolder(View itemView) {
            super(itemView);
            dateTv = (TextView)itemView.findViewById(R.id.date_item_date);
        }
    }




}
