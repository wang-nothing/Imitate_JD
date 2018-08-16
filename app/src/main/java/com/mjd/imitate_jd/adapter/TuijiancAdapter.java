package com.mjd.imitate_jd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mjd.imitate_jd.R;
import com.mjd.imitate_jd.bean.HomeBean;
import com.mjd.imitate_jd.holder.GridcycHolder;

/**
 * Created by admin on 2018-8-14.
 */

public class TuijiancAdapter extends RecyclerView.Adapter<GridcycHolder> {
    private HomeBean.DataBean.TuijianBean tuijian;
    private Context mContext;

    public TuijiancAdapter(HomeBean.DataBean.TuijianBean tuijian, Context context) {
        this.tuijian = tuijian;
        mContext = context;
    }

    @Override
    public GridcycHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_home_miaosha_item, null);
        return new GridcycHolder(view);
    }

    @Override
    public void onBindViewHolder(GridcycHolder holder, int position) {
        holder.miaosha_item_tv.setText(tuijian.getList().get(position).getTitle());
        holder.miaosha_item_simple.setImageURI(tuijian.getList().get(position).getImages().split("\\|")[0]);
    }

    @Override
    public int getItemCount() {
        return tuijian.getList().size();
    }
}
