package com.mjd.imitate_jd.fragment;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.mjd.imitate_jd.R;
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

public class Fragment_Home extends BaseFragment implements Iview_home {

    private XRecyclerView home_xrecyclerview;
    private Presenter_Home mHome;
    private HomeBean.DataBean mData;


    @Override
    protected void initListener() {
        home_xrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void initData() {
        mHome = new Presenter_Home(this);
        mHome.getDatas();
    }

    @Override
    protected void initViews(View view) {
        home_xrecyclerview = view.findViewById(R.id.home_xrecyclerview);
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
}


