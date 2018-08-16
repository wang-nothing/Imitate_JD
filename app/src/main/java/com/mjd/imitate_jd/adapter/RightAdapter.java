package com.mjd.imitate_jd.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mjd.imitate_jd.R;
import com.mjd.imitate_jd.activity.SearchActivity;
import com.mjd.imitate_jd.bean.RightBean;
import com.mjd.imitate_jd.holder.RightHolder;

import java.util.List;

/**
 * author:admin
 * Date:2018-7-26 20:57
 * Project：Classification_RecyclerView
 */
public class RightAdapter extends RecyclerView.Adapter<RightHolder> {
    private Context mContext;
    private List<RightBean.DataBean> mList;
    private View mView;
    private List<RightBean.DataBean.ListBean> mData;

    public RightAdapter(Context context, List<RightBean.DataBean> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public RightHolder onCreateViewHolder(@NonNull ViewGroup pViewGroup, int pI) {
        mView = LayoutInflater.from(mContext).inflate(R.layout.item_right, null);
        return new RightHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull RightHolder pRightHolder, final int pI) {
        pRightHolder.item_right_title.setText(mList.get(pI).getName());
        GridView item_right_grid = pRightHolder.item_right_grid;
        mData = mList.get(pI).getList();
        MyAdapter myAdapter = new MyAdapter(mContext, mData);
        item_right_grid.setAdapter(myAdapter);
        item_right_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(mContext,mList.get(pI).getList().get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, SearchActivity.class);
                intent.putExtra("s",mList.get(pI).getList().get(position).getName());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


}
