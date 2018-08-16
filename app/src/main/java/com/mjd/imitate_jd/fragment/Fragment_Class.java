package com.mjd.imitate_jd.fragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.mjd.imitate_jd.R;
import com.mjd.imitate_jd.adapter.LeftAdapter;
import com.mjd.imitate_jd.adapter.RightAdapter;
import com.mjd.imitate_jd.base.BaseFragment;
import com.mjd.imitate_jd.bean.HomeBean;
import com.mjd.imitate_jd.bean.RightBean;
import com.mjd.imitate_jd.mvp.classify.presenter.Presenter_Class;
import com.mjd.imitate_jd.mvp.classify.view.Iview_class;
import com.mjd.imitate_jd.mvp.home.presenter.Presenter_Home;
import com.mjd.imitate_jd.mvp.home.view.Iview_home;

import java.util.List;


/**
 * Created by admin on 2018-8-7.
 */

public class Fragment_Class extends BaseFragment implements Iview_home, Iview_class {
    private RecyclerView left_recycler,right_recycler;
    private HomeBean.DataBean mData;
    private Presenter_Class mPresenter_class;
    private List<RightBean.DataBean> mList;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        mPresenter_class = new Presenter_Class((Iview_class) this);
        Presenter_Home presenter_home = new Presenter_Home(this);
        presenter_home.getDatas();
        mPresenter_class.getClasdas("1");
    }

    @Override
    protected void initViews(View view) {
        left_recycler = view.findViewById(R.id.left_recycler);
        right_recycler = view.findViewById(R.id.right_recycler);
        left_recycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        right_recycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
    }

    @Override
    protected int privideLayoutId() {
        return R.layout.fragment_class;
    }

    @Override
    public void viewSuccess(HomeBean homeBean) {
        mData = homeBean.getData();
        LeftAdapter leftAdapter = new LeftAdapter(getActivity(), mData);
        left_recycler.setAdapter(leftAdapter);
        leftAdapter.setmItemClickListener(new LeftAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String i) {
                //Toast.makeText(getActivity(),i,Toast.LENGTH_SHORT).show();
                mPresenter_class.getClasdas(i);
            }
        });
    }

    @Override
    public void modelSuccess(RightBean rightBean) {
        mList = rightBean.getData();
        RightAdapter rightAdapter = new RightAdapter(getActivity(), mList);
        right_recycler.setAdapter(rightAdapter);
    }
}
