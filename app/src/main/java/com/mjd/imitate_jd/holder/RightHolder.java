package com.mjd.imitate_jd.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mjd.imitate_jd.R;

/**
 * author:admin
 * Date:2018-7-26 20:57
 * Project：Classification_RecyclerView
 */
public class RightHolder extends RecyclerView.ViewHolder {
    public TextView item_right_title;
    public GridView item_right_grid;
    public RightHolder(@NonNull View itemView) {
        super(itemView);
        item_right_grid = itemView.findViewById(R.id.item_right_grid);
        item_right_title = itemView.findViewById(R.id.item_right_title);
    }
}
