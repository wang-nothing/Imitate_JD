package com.mjd.imitate_jd.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mjd.imitate_jd.R;

/**
 * Created by admin on 2018-8-14.
 */

public class GridcycHolder extends RecyclerView.ViewHolder {
    public SimpleDraweeView miaosha_item_simple;
    public TextView miaosha_item_tv;
    public GridcycHolder(View itemView) {
        super(itemView);
        miaosha_item_simple = itemView.findViewById(R.id.miaosha_item_simple);
        miaosha_item_tv = itemView.findViewById(R.id.miaosha_item_tv);
    }
}
