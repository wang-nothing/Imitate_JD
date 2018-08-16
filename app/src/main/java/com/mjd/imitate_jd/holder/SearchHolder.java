package com.mjd.imitate_jd.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mjd.imitate_jd.R;

/**
 * Created by admin on 2018-8-15.
 */

public class SearchHolder extends RecyclerView.ViewHolder {
    public SimpleDraweeView item_search_simle;
    public TextView item_search_tv,item_search_price,item_search_bargainPrice;

    public SearchHolder(View itemView) {
        super(itemView);
        item_search_simle = itemView.findViewById(R.id.item_search_simle);
        item_search_tv = itemView.findViewById(R.id.item_search_tv);
        item_search_price = itemView.findViewById(R.id.item_search_price);
        item_search_bargainPrice = itemView.findViewById(R.id.item_search_bargainPrice);
    }
}
