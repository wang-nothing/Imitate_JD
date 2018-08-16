package com.mjd.imitate_jd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
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
    private Callback mCallback;
    private int price, number;

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    public ExpandableAdpter(Context context, List<CarBean.DataBean> data, Callback callback) {
        mContext = context;
        mData = data;
        mCallback = callback;
    }
    public void calculate(){
        price = 0;
        number = 0;
        for (CarBean.DataBean cd : mData) {
            List<CarBean.DataBean.ListBean> list = cd.getList();
            for (CarBean.DataBean.ListBean cl: list) {
                if (cl.getSelected() == 1){
                    price += (cl.getNum() * cl.getBargainPrice());
                    number += cl.getNum();
                }
            }
        }
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
            groupHolder.item_group_title = convertView.findViewById(R.id.item_group_title);
            convertView.setTag(groupHolder);
        }else{
            groupHolder = (GroupHolder) convertView.getTag();
        }
            groupHolder.item_group_title.setText(mData.get(groupPosition).getSellerName());
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildHolder childHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_child, null);
            childHolder = new ChildHolder();
            childHolder.child_box = convertView.findViewById(R.id.child_box);
            childHolder.child_img = convertView.findViewById(R.id.child_img);
            childHolder.child_title = convertView.findViewById(R.id.child_title);
            childHolder.child_price = convertView.findViewById(R.id.child_price);
            childHolder.iv_add = convertView.findViewById(R.id.iv_add);
            childHolder.tv_commodity_show_num = convertView.findViewById(R.id.tv_commodity_show_num);
            childHolder.iv_sub = convertView.findViewById(R.id.iv_sub);
            childHolder.child_delete = convertView.findViewById(R.id.child_delete);
            convertView.setTag(childHolder);
        }else{
            childHolder = (ChildHolder) convertView.getTag();
        }
            childHolder.child_img.setImageURI(mData.get(groupPosition).getList().get(childPosition).getImages().split("\\|")[0]);
            childHolder.child_title.setText(mData.get(groupPosition).getList().get(childPosition).getTitle());
            childHolder.child_price.setText(mData.get(groupPosition).getList().get(childPosition).getBargainPrice()+"");
            childHolder.child_box.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int selected = mData.get(groupPosition).getList().get(childPosition).getSelected();
                    if (selected == 1){
                        mData.get(groupPosition).getList().get(childPosition).setSelected(0);
                    }else if (selected == 0){
                        mData.get(groupPosition).getList().get(childPosition).setSelected(1);
                    }
                }
            });
            if (mData.get(groupPosition).getList().get(childPosition).getSelected()==0){
                childHolder.child_box.setChecked(false);
            }else{
                childHolder.child_box.setChecked(true);
            }
        final ChildHolder finalChildHolder = childHolder;
        childHolder.iv_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s = finalChildHolder.tv_commodity_show_num.getText().toString();
                    int i = Integer.parseInt(s);
                    i++;
                    String s1 = String.valueOf(i);
                    finalChildHolder.tv_commodity_show_num.setText(s1);
                    notifyDataSetChanged();
                }
            });
        childHolder.iv_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = finalChildHolder.tv_commodity_show_num.getText().toString();
                int i = Integer.parseInt(s);
                i--;
                if (i >= 0) {
                    String s1 = String.valueOf(i);
                    finalChildHolder.tv_commodity_show_num.setText(s1);
                }
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    class GroupHolder{
        TextView item_group_title;
    }
    class ChildHolder{
        CheckBox child_box;
        SimpleDraweeView child_img;
        TextView child_title,child_price,iv_add,iv_sub;
        ImageView child_delete;
        EditText tv_commodity_show_num;
    }
    public interface Callback{
        void json(int price, int number);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        calculate();
        mCallback.json(price, number);
    }
}
