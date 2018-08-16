package com.mjd.imitate_jd.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mjd.imitate_jd.R;
import com.mjd.imitate_jd.bean.LoginBean;
import com.mjd.imitate_jd.bean.MobileBean;
import com.mjd.imitate_jd.mvp.my.login.presenter.Login_Presenter;
import com.mjd.imitate_jd.mvp.my.login.view.Iview_login;
import com.mjd.imitate_jd.mvp.my.login.view.LoginActivity;
import com.mjd.imitate_jd.utils.UserManage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by admin on 2018-8-7.
 */

public class Fragment_My extends Fragment implements View.OnClickListener,Iview_login {
    private SimpleDraweeView my_simple;
    private TextView my_login;
    private View mView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_my, null);
        return mView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventBus.getDefault().register(this);
        initViews();
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MobileBean mobileBean){
        my_login.setText(mobileBean.getMobile());
    }

    private void initViews() {
        my_simple = mView.findViewById(R.id.my_simple);
        my_login = mView.findViewById(R.id.my_login);

        Login_Presenter login_presenter = new Login_Presenter(this);
        if (UserManage.getInstances().hasUserInfo(getActivity())){
            String userName = UserManage.getInstances().getUserInfo(getActivity()).getUserName();
            String password = UserManage.getInstances().getUserInfo(getActivity()).getPassword();
            login_presenter.getdas(userName,password);
        }else{
            my_login.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_login:
                startActivity(new Intent(getActivity(), LoginActivity.class));
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void viewSuccess(LoginBean loginBean) {
        String mobile = loginBean.getData().getMobile();
        Log.i("TAG", "viewSuccess: "+mobile);
        my_login.setText(mobile);
    }
}
