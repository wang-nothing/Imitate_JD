package com.mjd.imitate_jd.fragment;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mjd.imitate_jd.R;
import com.mjd.imitate_jd.activity.SearchActivity;
import com.mjd.imitate_jd.adapter.RecyclerAdapter;
import com.mjd.imitate_jd.base.BaseFragment;
import com.mjd.imitate_jd.bean.HomeBean;
import com.mjd.imitate_jd.mvp.home.presenter.Presenter_Home;
import com.mjd.imitate_jd.mvp.home.view.Iview_home;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018-8-7.
 */

public class Fragment_Home extends BaseFragment implements Iview_home, View.OnClickListener {

    private XRecyclerView home_xrecyclerview;
    private Presenter_Home mHome;
    private HomeBean.DataBean mData;


    private ImageView item_zxing, item_ss, item_close, item_xx;
    private EditText item_ed;


    @Override
    protected void initListener() {
        home_xrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        item_zxing.setOnClickListener(this);
        item_ss.setOnClickListener(this);
        item_close.setOnClickListener(this);
        item_xx.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mHome = new Presenter_Home(this);
        mHome.getDatas();
    }

    @Override
    protected void initViews(View view) {
        home_xrecyclerview = view.findViewById(R.id.home_xrecyclerview);
        item_zxing = view.findViewById(R.id.item_zxing);
        item_ss = view.findViewById(R.id.item_ss);
        item_close = view.findViewById(R.id.item_close);
        item_xx = view.findViewById(R.id.item_xx);
        item_ed = view.findViewById(R.id.item_ed);
        item_ed.setText("笔记本");
    }

    @Override
    protected int privideLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void viewSuccess(HomeBean homeBean) {
        mData = homeBean.getData();
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getActivity(), mData);
        home_xrecyclerview.setAdapter(recyclerAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_zxing:
                //Toast.makeText(getActivity(), "1", Toast.LENGTH_SHORT).show();
                break;

            case R.id.item_ss:
                //Toast.makeText(getActivity(), "2", Toast.LENGTH_SHORT).show();
                String s = item_ed.getText().toString();
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("s",s);
                startActivity(intent);
                break;

            case R.id.item_close:
                //Toast.makeText(getActivity(), "3", Toast.LENGTH_SHORT).show();
                item_ed.getText().clear();
                break;

            case R.id.item_xx:
                //Toast.makeText(getActivity(), "4", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}


