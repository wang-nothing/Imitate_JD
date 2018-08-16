package com.mjd.imitate_jd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mjd.imitate_jd.R;
import com.mjd.imitate_jd.bean.SearchBean;
import com.mjd.imitate_jd.holder.SearchHolder;

import java.util.List;

/**
 * Created by admin on 2018-8-15.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchHolder> {
    private Context mContext;
    private SearchBean searchBean;
    private View mView;

    public SearchAdapter(Context context, SearchBean searchBean) {
        mContext = context;
        this.searchBean = searchBean;
    }

    @Override
    public SearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mView = LayoutInflater.from(mContext).inflate(R.layout.item_search, null);
        return new SearchHolder(mView);
    }

    @Override
    public void onBindViewHolder(SearchHolder holder, int position) {
        holder.item_search_simle.setImageURI(searchBean.getData().get(position).getImages().split("\\|")[0]);
        holder.item_search_tv.setText(searchBean.getData().get(position).getTitle());
        holder.item_search_price.setText(searchBean.getData().get(position).getPrice());
        holder.item_search_bargainPrice.setText(searchBean.getData().get(position).getBargainPrice());
        final String pid = searchBean.getData().get(position).getPid();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemonclick.getpid(pid);
            }
        });
    }

    @Override
    public int getItemCount() {
        List<SearchBean.DataBean> data = searchBean.getData();
        return data.size();
    }

    private Itemonclick mItemonclick;

    public void setItemonclick(Itemonclick itemonclick) {
        mItemonclick = itemonclick;
    }

    public  interface Itemonclick{
        void getpid(String pid);
    }
}
