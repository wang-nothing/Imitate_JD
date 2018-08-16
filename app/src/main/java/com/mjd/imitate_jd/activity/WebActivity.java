package com.mjd.imitate_jd.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.mjd.imitate_jd.R;
import com.mjd.imitate_jd.api.RetrofitClient;
import com.mjd.imitate_jd.base.BaseActivity;
import com.mjd.imitate_jd.bean.AddcarBean;
import com.mjd.imitate_jd.mvp.classify.presenter.Presenter_details;
import com.mjd.imitate_jd.mvp.my.login.view.LoginActivity;
import com.mjd.imitate_jd.utils.UserManage;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WebActivity extends BaseActivity implements View.OnClickListener {

    private WebView web_webview;
    private String mPid;
    private Button web_car,web_addcar;
    private String mDetailUrl;

    @Override
    protected void initDatas() {
        web_webview.loadUrl(mDetailUrl);
    }

    @Override
    protected void initListener() {
        web_car.setOnClickListener(this);
        web_addcar.setOnClickListener(this);
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_web);
        Intent intent = getIntent();
        mPid = intent.getStringExtra("pid");
        mDetailUrl = intent.getStringExtra("detailUrl");
        web_webview = findViewById(R.id.web_webview);
        web_car = findViewById(R.id.web_car);
        web_addcar = findViewById(R.id.web_addcar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.web_car:
                Intent intent=new Intent(WebActivity.this,ShowActivity.class);
                intent.putExtra("page",3);
                startActivity(intent);
                break;

            case R.id.web_addcar:
                if (UserManage.getInstances().hasUserInfo(this)){
                    String uid = UserManage.getInstances().getUserInfo(this).getUid();
                    RetrofitClient
                            .getInstance()
                            .getCommonApi()
                            .getaddcar(mPid, uid)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<AddcarBean>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onNext(AddcarBean addcarBean) {

                                    Log.d("TAG", "initViews: "+mPid);
                                    Toast.makeText(WebActivity.this,addcarBean.getMsg(),Toast.LENGTH_SHORT).show();
                                    Log.i("TAG", "onNext: "+addcarBean.getMsg());
                                }
                            });
                }else{
                    startActivity(new Intent(WebActivity.this, LoginActivity.class));
                }
                break;
        }
    }
}
