package com.mjd.imitate_jd.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mjd.imitate_jd.R;

/**
 * Created by admin on 2018-8-14.
 */

public class TuijianHolder extends RecyclerView.ViewHolder {
    public TextView tuijian_tv;
    public RecyclerView tuijian_recyclerview;
    public TuijianHolder(View itemView) {
        super(itemView);
        tuijian_tv = itemView.findViewById(R.id.tuijian_tv);
        tuijian_recyclerview = itemView.findViewById(R.id.tuijian_recyclerview);
    }
}
