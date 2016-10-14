package com.meetcity.calabash.adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.meetcity.calabash.R;
import com.meetcity.calabash.bean.LoadMoreBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wds1993225 on 2016/9/20.
 */
public class BaseAdapter extends Adapter<ViewHolder> {

    private Context context;
    private List<Object> data;
    private List<String> list = new ArrayList<>();

    static final int TYPE_DATA = 0;
    static final int TYPE_AD = 1;
    private static final int TYPE_LOADMORE = 13;

    public BaseAdapter(Context context,List<Object> data){
        this.context = context;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("WATER","BASE+onCreateViewHolder");
        View view ;
        ViewHolder holder = null;
        switch (viewType){
            case TYPE_LOADMORE:
                view = LayoutInflater.from(context).inflate(R.layout.def_loading,parent,false);
                holder = new LoadMoreHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Log.i("WATER","BASE+onBindViewHolder");
        if(holder instanceof LoadMoreHolder) {
            LoadMoreBean bean = (LoadMoreBean) data.get(position);
            switch (bean.getIndicate()) {
                case 1:
                    loadMoreListener.load();
                    break;
                case 2:
                    ((LoadMoreHolder) holder).progressBar.setVisibility(View.GONE);
                    ((LoadMoreHolder) holder).tv.setText("出错了，点击加载更多");
                    ((LoadMoreHolder) holder).loadMoreView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ((LoadMoreHolder) holder).progressBar.setVisibility(View.VISIBLE);
                            ((LoadMoreHolder) holder).tv.setText("加载中...");
                            loadMoreListener.load();
                        }
                    });
                    break;
                case 3:
                    ((LoadMoreHolder) holder).tv.setText("没有更多了...");
                    ((LoadMoreHolder) holder).progressBar.setVisibility(View.GONE);
                    break;
                default:
                    break;

            }
        }

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        } else {
            return 0;
        }
    }



    public interface OnLoadMoreListener{
        void load();
    }

    private OnLoadMoreListener loadMoreListener;

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
    }

    class LoadMoreHolder extends ViewHolder{
        LinearLayout loadMoreView;
        ProgressBar progressBar;
        TextView tv;


        public LoadMoreHolder(View view) {
            super(view);
            loadMoreView = (LinearLayout)itemView.findViewById(R.id.loading_view);
            progressBar = (ProgressBar)itemView.findViewById(R.id.loading_progress);
            tv = (TextView)itemView.findViewById(R.id.loading_text);
        }
    }
}
