package com.mjd.imitate_jd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mjd.imitate_jd.R;
import com.mjd.imitate_jd.bean.RightBean;

import java.util.List;

/**
 * Created by admin on 2018-8-14.
 */

public class MyAdapter extends BaseAdapter {
    private Context mContext;
    private List<RightBean.DataBean.ListBean> mData;

    public MyAdapter(Context context, List<RightBean.DataBean.ListBean> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView =inflater.inflate(R.layout.item_right_item,null);
            holder = new ViewHolder();
            holder.item_right_item_simple = convertView.findViewById(R.id.item_right_item_simple);
            holder.item_right_item_tv = convertView.findViewById(R.id.item_right_item_tv);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.item_right_item_tv.setText(mData.get(position).getName());
        holder.item_right_item_simple.setImageURI(mData.get(position).getIcon());
        return convertView;
    }
    class ViewHolder{
        SimpleDraweeView item_right_item_simple;
        TextView item_right_item_tv;
    }
}
