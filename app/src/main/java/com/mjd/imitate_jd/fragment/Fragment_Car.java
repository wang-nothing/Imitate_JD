package com.mjd.imitate_jd.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.mjd.imitate_jd.R;
import com.mjd.imitate_jd.adapter.ExpandableAdpter;
import com.mjd.imitate_jd.base.BaseFragment;
import com.mjd.imitate_jd.bean.CarBean;
import com.mjd.imitate_jd.mvp.car.presenter.Presenter_Car;
import com.mjd.imitate_jd.mvp.car.view.Iview_car;
import com.mjd.imitate_jd.mvp.my.login.view.LoginActivity;
import com.mjd.imitate_jd.utils.UserManage;

import java.util.List;

/**
 * Created by admin on 2018-8-7.
 */

public class Fragment_Car extends BaseFragment implements Iview_car {

    private ExpandableListView car_expanable;
    private CheckBox car_check;
    private TextView car_sum;
    private Button car_btn;
    private List<CarBean.DataBean> mData;

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        if (UserManage.getInstances().hasUserInfo(getActivity())) {
            Presenter_Car presenter_car = new Presenter_Car(this);
            String uid = UserManage.getInstances().getUserInfo(getActivity()).getUid();
            presenter_car.getdatacars(uid);
        }else{
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }
    }

    @Override
    protected void initViews(View view) {
        car_expanable = view.findViewById(R.id.car_expanable);
        car_check = view.findViewById(R.id.car_check);
        car_sum = view.findViewById(R.id.car_sum);
        car_btn = view.findViewById(R.id.car_btn);
        car_expanable.setGroupIndicator(null);
    }

    @Override
    protected int privideLayoutId() {
        return R.layout.fragment_car;
    }

    @Override
    public void modelSuccess(CarBean carBean) {
        mData = carBean.getData();
        ExpandableAdpter expandableAdpter = new ExpandableAdpter(getActivity(), mData);
        car_expanable.setAdapter(expandableAdpter);
        int count = car_expanable.getCount();
        for (int i = 0; i < count; i++) {
            car_expanable.expandGroup(i);
        }
    }
}
