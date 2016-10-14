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

import com.meetcity.calabash.R;
import com.meetcity.calabash.util.SeeUtil;
import com.meetcity.calabash.bean.WeixinBean;
import com.qq.e.ads.nativ.NativeADDataRef;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/9.
 */
public class WeixinAdapter extends Adapter<ViewHolder> {

    private Context context;
    private List<Object> data;
    private List<String > list = new ArrayList<>();

    static final int TYPE_DATA = 0;
    static final int TYPE_AD = 1;
    private RecyclerView recyclerView;

    private static final int TYPE_LOADMORE = 13;

    public WeixinAdapter(Context context, List<Object> list, RecyclerView recyclerView){
        //super(context,list);
        //super(context,list);
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
        //super.onCreateViewHolder(parent,viewType);
        View view ;
        if(viewType == TYPE_DATA){
            view = LayoutInflater.from(context).inflate(R.layout.news_item,null);
            TitleViewHolder holder = new TitleViewHolder(view);
            return holder;
        }/*else if (viewType == TYPE_DATA){
            view = LayoutInflater.from(context).inflate(R.layout.ad_news_item,null);
            ADViewHolder holder = new ADViewHolder(view);
            return holder;
        }*/else if(viewType == TYPE_AD){
            view = LayoutInflater.from(context).inflate(R.layout.ad_news_item_2,null);
            ADViewHolder2 holder = new ADViewHolder2(view);
            return holder;
        }

        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //super.onBindViewHolder(holder,position);
        if(holder instanceof TitleViewHolder){
            final WeixinBean.ResultBean.ListBean bean = (WeixinBean.ResultBean.ListBean)data.get(position);

            ((TitleViewHolder)holder).titleTv.setText(bean.getTitle());


            if(TextUtils.isEmpty(bean.getFirstImg())){
                Picasso.with(context).load(R.drawable.image_default).into(((TitleViewHolder)holder).pic1Im);
            }else {
                Picasso.with(context).load(bean.getFirstImg())
                        .into(((TitleViewHolder)holder).pic1Im);
            }

            ((TitleViewHolder)holder).fromTv.setText(bean.getSource());
            ((TitleViewHolder)holder).timeTv.setText(bean.getMark());


            ((TitleViewHolder) holder).titleLl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SeeUtil.togoWebActivity(context,bean.getUrl());
                    list.add(bean.getId());
                }
            });
            if (list.contains(bean.getId())){
                ((TitleViewHolder)holder).titleTv.setTextColor(context.getResources().getColor(R.color.colorTextRead));
            }else {
                ((TitleViewHolder)holder).titleTv.setTextColor(context.getResources().getColor(R.color.colorTextNormal));
            }
        }/*else if(holder instanceof ADViewHolder){
            NativeADDataRef adItem = (NativeADDataRef)data.get(position);
            Picasso.with(context).load(adItem.getIconUrl()).into(((ADViewHolder) holder).logoIm);
            //Picasso.with(context).load(adItem.getImgUrl()).into(((ADViewHolder) holder).posterIm);
            ((ADViewHolder) holder).desTv.setText(adItem.getDesc());
            ((ADViewHolder) holder).titleTv.setText(adItem.getTitle());
        }*/else if(holder instanceof ADViewHolder2){
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
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(data.get(position) instanceof WeixinBean.ResultBean.ListBean ){
            return TYPE_DATA;
        } else if(data.get(position)instanceof NativeADDataRef){
            return TYPE_AD;
        }else {
            return TYPE_LOADMORE;
        }
        /*if(mADSet.contains(position)){
            return TYPE_AD;
        }else {
            return TYPE_DATA;
        }*/
    }

    @Override
    public int getItemCount() {
        if(data != null){
            return data.size();
        }else {
            return 0;
        }

    }

    class TitleViewHolder extends ViewHolder{

        LinearLayout titleLl ;
        TextView titleTv;
        ImageView pic1Im;
        TextView fromTv;
        TextView timeTv;



        public TitleViewHolder(View itemView) {
            super(itemView);
            titleLl = (LinearLayout)itemView.findViewById(R.id.news_item_title_ll);
            titleTv = (TextView)itemView.findViewById(R.id.news_item_title_tv);
            pic1Im = (ImageView)itemView.findViewById(R.id.news_item_pic1_im);
            fromTv = (TextView)itemView.findViewById(R.id.news_item_from_tv);
            timeTv = (TextView)itemView.findViewById(R.id.news_item_time_tv);
        }
    }
    class ADViewHolder extends ViewHolder{

        TextView desTv;
        TextView titleTv;
        ImageView logoIm;
        ImageView posterIm;

        public ADViewHolder(View itemView) {
            super(itemView);
            desTv = (TextView)itemView.findViewById(R.id.ad_news_item_desc_tv);
            titleTv = (TextView)itemView.findViewById(R.id.ad_news_item_title_tv);
            logoIm = (ImageView)itemView.findViewById(R.id.ad_news_item_logo_im);
            posterIm = (ImageView)itemView.findViewById(R.id.ad_news_item_poster_im);
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
}
