package com.mjd.imitate_jd.mvp.my.login.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.mjd.imitate_jd.R;
import com.mjd.imitate_jd.api.NewApi;
import com.mjd.imitate_jd.api.RetrofitClient;
import com.mjd.imitate_jd.base.BaseActivity;
import com.mjd.imitate_jd.bean.LoginBean;
import com.mjd.imitate_jd.bean.MobileBean;
import com.mjd.imitate_jd.mvp.my.login.presenter.Login_Presenter;
import com.mjd.imitate_jd.mvp.my.reg.view.RegActivity;
import com.mjd.imitate_jd.utils.UserManage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginActivity extends BaseActivity implements View.OnClickListener, Iview_login {
    private EditText login_mobile, login_password;
    private ImageView login_close;
    private Button login_btn;
    private TextView login_login, login_reg;
    private String mMobile;
    private String mPassword;
    private String mMsg;
    private Login_Presenter mPresenter;


    @Override
    protected void initDatas() {

    }

    @Override
    protected void initListener() {
        mPresenter = new Login_Presenter(this);
        login_btn.setOnClickListener(this);
        login_login.setOnClickListener(this);
        login_reg.setOnClickListener(this);
        login_close.setOnClickListener(this);
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_login);
        EventBus.getDefault().register(this);
        login_close = findViewById(R.id.login_close);
        login_mobile = findViewById(R.id.login_mobile);
        login_password = findViewById(R.id.login_password);
        login_close = findViewById(R.id.login_close);
        login_btn = findViewById(R.id.login_btn);
        login_login = findViewById(R.id.login_login);
        login_reg = findViewById(R.id.login_reg);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MobileBean mobileBean){
        login_mobile.setText(mobileBean.getMobile());
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                mMobile = login_mobile.getText().toString();
                mPassword = login_password.getText().toString();
                if (!TextUtils.isEmpty(mMobile) && !TextUtils.isEmpty(mPassword)){
                    mPresenter.getdas(mMobile, mPassword);
                }else{
                    Toast.makeText(LoginActivity.this,"不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.login_login:

                break;
            case R.id.login_reg:
                startActivity(new Intent(LoginActivity.this, RegActivity.class));
                break;
            case R.id.login_close:
                finish();
                break;
        }
    }


    @Override
    public void viewSuccess(LoginBean loginBean) {
        mMsg = loginBean.getMsg();
        String uid = loginBean.getData().getUid();
        if ("登录成功".equals(mMsg)) {
            UserManage.getInstances().saveUserInfo(this,mMobile, mPassword, uid);
            EventBus.getDefault().post(new MobileBean(mMobile));
            finish();
        }else{
            Toast.makeText(LoginActivity.this,"臭小子失败了", Toast.LENGTH_SHORT).show();

        }
    }
}

