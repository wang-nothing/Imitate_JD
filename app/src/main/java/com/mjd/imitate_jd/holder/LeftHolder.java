package com.mjd.imitate_jd.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mjd.imitate_jd.R;


/**
 * author:admin
 * Date:2018-7-26 20:57
 * Project：Classification_RecyclerView
 */
public class LeftHolder extends RecyclerView.ViewHolder {
    public TextView item_left_tv;
    public LeftHolder(@NonNull View itemView) {
        super(itemView);
        item_left_tv = itemView.findViewById(R.id.item_left_tv);
    }
}
