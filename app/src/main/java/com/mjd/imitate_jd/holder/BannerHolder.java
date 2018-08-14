package com.mjd.imitate_jd.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mjd.imitate_jd.R;
import com.stx.xhb.xbanner.XBanner;

/**
 * Created by admin on 2018-8-13.
 */

public class BannerHolder extends RecyclerView.ViewHolder {
    public XBanner item_home_xbanner;

    public BannerHolder(View itemView) {
        super(itemView);
        item_home_xbanner = itemView.findViewById(R.id.item_home_xbanner);
    }
}
