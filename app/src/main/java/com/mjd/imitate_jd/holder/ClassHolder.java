package com.mjd.imitate_jd.holder;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.mjd.imitate_jd.R;

/**
 * Created by admin on 2018-8-14.
 */

public class ClassHolder extends RecyclerView.ViewHolder {
    public ViewPager home_viewpager;
    public LinearLayout home_viewPager_points;
    public ClassHolder(View itemView) {
        super(itemView);
        home_viewpager = itemView.findViewById(R.id.home_viewPager);
        home_viewPager_points = itemView.findViewById(R.id.home_viewPager_points);
    }
}
