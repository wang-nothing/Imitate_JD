package com.mjd.imitate_jd.mvp.my.reg.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.mjd.imitate_jd.R;
import com.mjd.imitate_jd.api.NewApi;
import com.mjd.imitate_jd.api.Retrofit_Net;
import com.mjd.imitate_jd.base.BaseActivity;
import com.mjd.imitate_jd.bean.MobileBean;
import com.mjd.imitate_jd.bean.RegBean;
import com.mjd.imitate_jd.mvp.my.login.view.LoginActivity;
import com.mjd.imitate_jd.mvp.my.reg.presenter.Reg_Presenter;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegActivity extends BaseActivity implements View.OnClickListener,Iview_reg {
    private ImageView reg_close;
    private EditText reg_mobile,reg_password;
    private Button reg_btn;
    private String mMobile;
    private String mPassword;
    private String mMsg;
    private Reg_Presenter mPresenter;

    @Override
    protected void initDatas() {
        mPresenter = new Reg_Presenter(this);
    }

    @Override
    protected void initListener() {
        reg_btn.setOnClickListener(this);
        reg_close.setOnClickListener(this);
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_reg);
        reg_close = findViewById(R.id.reg_close);
        reg_close = findViewById(R.id.reg_close);
        reg_mobile = findViewById(R.id.reg_mobile);
        reg_password = findViewById(R.id.reg_password);
        reg_btn = findViewById(R.id.reg_btn);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.reg_btn:
                mMobile = reg_mobile.getText().toString();
                mPassword = reg_password.getText().toString();
                if (!TextUtils.isEmpty(mMobile) && !TextUtils.isEmpty(mPassword)){
                    mPresenter.getdas(mMobile, mPassword);

                }else{
                    Toast.makeText(RegActivity.this,"输入框不能为空，晓得不", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.reg_close:
                finish();
                break;
        }
    }

    @Override
    public void viewSuccess(RegBean regBean) {
        mMsg = regBean.getMsg();
        Toast.makeText(RegActivity.this,""+mMsg,Toast.LENGTH_LONG).show();
        if ("注册成功".equals(mMsg)) {
            EventBus.getDefault().post(new MobileBean(mMobile));
            finish();
        }else{
            Toast.makeText(RegActivity.this,"臭小子失败了", Toast.LENGTH_SHORT).show();

        }
    }
}
