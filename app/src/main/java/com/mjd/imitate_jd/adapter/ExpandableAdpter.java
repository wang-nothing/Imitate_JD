package com.mjd.imitate_jd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mjd.imitate_jd.R;
import com.mjd.imitate_jd.bean.CarBean;

import java.util.List;

/**
 * Created by admin on 2018-8-16.
 */

public class ExpandableAdpter extends BaseExpandableListAdapter {

    private Context mContext;
    private List<CarBean.DataBean> mData;

    public ExpandableAdpter(Context context, List<CarBean.DataBean> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public int getGroupCount() {
        return mData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mData.get(groupPosition).getList().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mData.get(groupPosition).getList().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupHolder groupHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_group, null);
            groupHolder = new GroupHolder();
            groupHolder.item_group_checkbox = convertView.findViewById(R.id.item_group_checkbox);
            groupHolder.item_group_title = convertView.findViewById(R.id.item_group_title);
            convertView.setTag(groupHolder);
        }else{
            groupHolder = (GroupHolder) convertView.getTag();
        }
            groupHolder.item_group_title.setText(mData.get(groupPosition).getSellerName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_child, null);
            childHolder = new ChildHolder();
            childHolder.child_box = convertView.findViewById(R.id.child_box);
            childHolder.child_img = convertView.findViewById(R.id.child_img);
            childHolder.child_title = convertView.findViewById(R.id.child_title);
            childHolder.child_price = convertView.findViewById(R.id.child_price);
            convertView.setTag(childHolder);
        }else{
            childHolder = (ChildHolder) convertView.getTag();
        }
            childHolder.child_img.setImageURI(mData.get(groupPosition).getList().get(childPosition).getImages().split("\\|")[0]);
            childHolder.child_title.setText(mData.get(groupPosition).getList().get(childPosition).getTitle());
            childHolder.child_price.setText(mData.get(groupPosition).getList().get(childPosition).getBargainPrice());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    class GroupHolder{
        TextView item_group_title;
        CheckBox item_group_checkbox;
    }
    class ChildHolder{
        CheckBox child_box;
        SimpleDraweeView child_img;
        TextView child_title,child_price;
    }
}
