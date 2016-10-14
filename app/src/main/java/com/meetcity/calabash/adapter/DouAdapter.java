package com.meetcity.calabash.adapter;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.meetcity.calabash.R;
import com.meetcity.calabash.util.SeeUtil;
import com.meetcity.calabash.bean.TimeBean;
import com.meetcity.calabash.bean.DouBean;
import com.qq.e.ads.nativ.NativeADDataRef;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/9.
 */
public class DouAdapter extends Adapter<ViewHolder>  {

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






    public DouAdapter(Context context,List<Object> list,RecyclerView recyclerView){
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
                view = LayoutInflater.from(context).inflate(R.layout.dou_item,parent,false);
                holder = new TitleViewHolder(view);
                break;
            case TYPE_AD:
                view = LayoutInflater.from(context).inflate(R.layout.ad_news_item_2,parent,false);
                holder = new ADViewHolder2(view);
                break;
            case TYPE_DATA2:
                view = LayoutInflater.from(context).inflate(R.layout.dou_item2,parent,false);
                holder = new TitleViewHolder2(view);
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
        if(holder instanceof TitleViewHolder){
            final DouBean.PostsBean bean = (DouBean.PostsBean)data.get(position);

            if(!TextUtils.isEmpty(bean.getColumn())){
                ((TitleViewHolder) holder).typeTv.setText(bean.getColumn());
            }else {
                ((TitleViewHolder) holder).typeTv.setText("精选");
            }
            ((TitleViewHolder) holder).titleTv.setText(bean.getTitle());
            ((TitleViewHolder) holder).abstractTv.setText(bean.getAbstractX());

            if(bean.getThumbs().size()!=0){
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setUri(bean.getThumbs().get(0).getMedium().getUrl())
                        .setTapToRetryEnabled(true)
                        .setOldController(((TitleViewHolder)holder).pic1Im.getController())
                        .build();
                ((TitleViewHolder)holder).pic1Im.setController(controller);
                //((TitleViewHolder)holder).pic1Im.setImageURI(bean.getThumbs().get(0).getMedium().getUrl());
                /*Picasso.with(context).load(bean.getThumbs().get(0).getMedium().getUrl())
                        .into(((TitleViewHolder)holder).pic1Im);*/
            }else {
                ((TitleViewHolder) holder).pic1Im.setVisibility(View.GONE);
            }


            ((TitleViewHolder) holder).layoutLl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //SeeUtil.togoWebActivity(context,bean.getUrl());
                    SeeUtil.togoReaderWebActivity(context,String.valueOf(bean.getId()),bean.getTitle(),"dou");
                    list.add(bean.getUrl());
                }
            });
            if (list.contains(bean.getUrl())){
                ((TitleViewHolder)holder).abstractTv.setTextColor(context.getResources().getColor(R.color.colorTextAbstractRead));
                ((TitleViewHolder) holder).titleTv.setTextColor(context.getResources().getColor(R.color.colorTextRead));
            }else {
                ((TitleViewHolder)holder).abstractTv.setTextColor(context.getResources().getColor(R.color.colorTextAbstractNormal));
                ((TitleViewHolder) holder).titleTv.setTextColor(context.getResources().getColor(R.color.colorTextNormal));
            }
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


        }else if(holder instanceof TitleViewHolder2){
            final DouBean.PostsBean bean = (DouBean.PostsBean)data.get(position);

            if(!TextUtils.isEmpty(bean.getColumn())){
                ((TitleViewHolder2) holder).typeTv.setText(bean.getColumn());
            }else {
                ((TitleViewHolder2) holder).typeTv.setText("精选");
            }
            ((TitleViewHolder2) holder).titleTv.setText(bean.getTitle());

            if(bean.getThumbs().size()!=0){
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setUri(bean.getThumbs().get(0).getMedium().getUrl())
                        .setTapToRetryEnabled(true)
                        .setOldController(((TitleViewHolder2)holder).pic1Im.getController())
                        .build();
                ((TitleViewHolder2)holder).pic1Im.setController(controller);
                //((TitleViewHolder2)holder).pic1Im.setImageURI(bean.getThumbs().get(0).getMedium().getUrl());
                /*Picasso.with(context).load(bean.getThumbs().get(0).getMedium().getUrl())
                        .into(((TitleViewHolder2)holder).pic1Im);*/
            }else {
                ((TitleViewHolder2) holder).pic1Im.setVisibility(View.GONE);
            }


            ((TitleViewHolder2) holder).layoutLl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SeeUtil.togoReaderWebActivity(context,String.valueOf(bean.getId()),bean.getTitle(),"dou");
                    list.add(bean.getUrl());
                }
            });
            if (list.contains(bean.getUrl())){
                ((TitleViewHolder2)holder).titleTv.setTextColor(context.getResources().getColor(R.color.colorTextRead));
            }else {
                ((TitleViewHolder2)holder).titleTv.setTextColor(context.getResources().getColor(R.color.colorTextNormal));
            }
        }else if(holder instanceof TimeViewHolder){
            TimeBean bean = (TimeBean)data.get(position);
            ((TimeViewHolder) holder).dateTv.setText(bean.getTime());
        }



    }

    @Override
    public int getItemViewType(int position) {

        if(data.get(position) instanceof DouBean.PostsBean ){
            if((((DouBean.PostsBean) data.get(position)).getType().equals("1004"))){
                return TYPE_DATA2;
            }else {
                return TYPE_DATA;
            }
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



    class TitleViewHolder extends ViewHolder{

        LinearLayout layoutLl ;
        TextView typeTv;
        TextView titleTv;
        SimpleDraweeView pic1Im;
        TextView abstractTv;

        public TitleViewHolder(View itemView) {
            super(itemView);
            layoutLl = (LinearLayout)itemView.findViewById(R.id.dou_item_ll);
            typeTv = (TextView)itemView.findViewById(R.id.dou_item_type);
            pic1Im = (SimpleDraweeView)itemView.findViewById(R.id.dou_item_pic);
            titleTv = (TextView)itemView.findViewById(R.id.dou_item_title);
            abstractTv = (TextView)itemView.findViewById(R.id.dou_item_abstract);
        }
    }

    class TitleViewHolder2 extends ViewHolder{

        LinearLayout layoutLl ;
        TextView typeTv;
        TextView titleTv;
        SimpleDraweeView pic1Im;

        public TitleViewHolder2(View itemView) {
            super(itemView);
            layoutLl = (LinearLayout)itemView.findViewById(R.id.dou_item2_ll);
            typeTv = (TextView)itemView.findViewById(R.id.dou_item2_type);
            pic1Im = (SimpleDraweeView)itemView.findViewById(R.id.dou_item2_pic);
            titleTv = (TextView)itemView.findViewById(R.id.dou_item2_title);
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
