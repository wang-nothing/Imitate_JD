package com.mjd.imitate_jd.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mjd.imitate_jd.R;

/**
 * Created by admin on 2018-8-14.
 */

public class MiaoShaHolder extends RecyclerView.ViewHolder {
    public TextView miaosha_tv;
    public RecyclerView miaosha_recyclerview;
    public MiaoShaHolder(View itemView) {
        super(itemView);
        miaosha_tv = itemView.findViewById(R.id.miaosha_tv);
        miaosha_recyclerview = itemView.findViewById(R.id.miaosha_recyclerview);
    }
}
