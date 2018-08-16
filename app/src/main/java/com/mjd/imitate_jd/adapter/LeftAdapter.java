package com.mjd.imitate_jd.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mjd.imitate_jd.R;
import com.mjd.imitate_jd.bean.HomeBean;
import com.mjd.imitate_jd.holder.LeftHolder;


/**
 * author:admin
 * Date:2018-7-26 20:57
 * Project：Classification_RecyclerView
 */
public class LeftAdapter extends RecyclerView.Adapter<LeftHolder>{
    private Context mContext;
    private HomeBean.DataBean mData;
    private View mView;

    public LeftAdapter(Context context, HomeBean.DataBean data) {
        mContext = context;
        mData = data;
    }

    @NonNull
    @Override
    public LeftHolder onCreateViewHolder(@NonNull ViewGroup pViewGroup, int pI) {
        mView = LayoutInflater.from(mContext).inflate(R.layout.item_left, null);
        return new LeftHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull LeftHolder pLeftHolder, int pI) {
        String _name = mData.getFenlei().get(pI).getName();
        final String cid = mData.getFenlei().get(pI).getCid();
        pLeftHolder.item_left_tv.setText(_name);
        pLeftHolder.itemView.setTag(pI);
        pLeftHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mItemClickListener.onItemClick(cid);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.getFenlei().size();
    }


    private OnItemClickListener mItemClickListener;

    public void setmItemClickListener(OnItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }


    public interface OnItemClickListener{
        void onItemClick(String i);
    }

}
