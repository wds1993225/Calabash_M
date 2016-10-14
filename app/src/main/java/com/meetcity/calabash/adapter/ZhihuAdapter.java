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
import com.meetcity.calabash.bean.TimeBean;
import com.meetcity.calabash.bean.ZhiHuListBean;
import com.qq.e.ads.nativ.NativeADDataRef;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/9.
 */
public class ZhihuAdapter extends Adapter<ViewHolder> {

    private Context context;
    private List<Object> data;
    private List<String> list = new ArrayList<>();

    static final int TYPE_DATA = 0;
    static final int TYPE_AD = 1;
    static final int TYPE_TIME = 3;

    private RecyclerView recyclerView ;



    public ZhihuAdapter(Context context,List<Object> list,RecyclerView recyclerView){
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
                view = LayoutInflater.from(context).inflate(R.layout.zhihu_item,parent,false);
                holder = new TitleViewHolder(view);
                break;
            case TYPE_AD:
                view = LayoutInflater.from(context).inflate(R.layout.ad_news_item_2,parent,false);
                holder = new ADViewHolder2(view);
                break;
            case TYPE_TIME:
                view = LayoutInflater.from(context).inflate(R.layout.date_item,parent,false);
                holder = new TimeViewHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if(holder instanceof TitleViewHolder){
            final ZhiHuListBean.StoriesBean bean = (ZhiHuListBean.StoriesBean)data.get(position);


            ((TitleViewHolder)holder).titleTv.setText(bean.getTitle());

            if(TextUtils.isEmpty(bean.getImages().get(0))){
                Picasso.with(context).load(R.drawable.image_default).into(((TitleViewHolder)holder).pic1Im);
            }else {
                Picasso.with(context).load(bean.getImages().get(0))
                        .into(((TitleViewHolder)holder).pic1Im);
            }
            ((TitleViewHolder)holder).fromTv.setText("知乎");
            /*((TitleViewHolder)holder).timeTv.setText(bean.getId());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            ParsePosition pos = new ParsePosition(0);
            Date newsDate = formatter.parse(bean.getDate(), pos);
            Calendar calendar = Calendar.getInstance();
            Date nowDate = calendar.getTime();
            ((TitleViewHolder)holder).timeTv.setText(Math.abs(nowDate.getMinutes()-newsDate.getMinutes())+"分钟前");*/


            ((TitleViewHolder) holder).titleLl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SeeUtil.togoReaderWebActivity(context,String.valueOf(bean.getId()),bean.getTitle(),"zhi");
                    list.add(String.valueOf(bean.getId()));
                }
            });
            if (list.contains(String.valueOf(bean.getId()))){
                ((TitleViewHolder)holder).titleTv.setTextColor(context.getResources().getColor(R.color.colorTextRead));
            }else {
                ((TitleViewHolder)holder).titleTv.setTextColor(context.getResources().getColor(R.color.colorTextNormal));
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
        }else if(holder instanceof TimeViewHolder){
            TimeBean bean = (TimeBean)data.get(position);
            ((TimeViewHolder) holder).dateTv.setText(bean.getTime());
        }



    }

    @Override
    public int getItemViewType(int position) {
        if(data.get(position) instanceof ZhiHuListBean.StoriesBean ){
            return TYPE_DATA;
        }else if(data.get(position) instanceof TimeBean){
            return TYPE_TIME;
        } else {
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

        LinearLayout titleLl ;
        TextView titleTv;
        ImageView pic1Im;
        TextView fromTv;
        TextView timeTv;

        public TitleViewHolder(View itemView) {
            super(itemView);
            titleLl = (LinearLayout)itemView.findViewById(R.id.zhihu_item_title_ll);
            titleTv = (TextView)itemView.findViewById(R.id.zhihu_item_title_tv);
            pic1Im = (ImageView)itemView.findViewById(R.id.zhihu_item_pic1_im);
            fromTv = (TextView)itemView.findViewById(R.id.zhihu_item_from_tv);
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
