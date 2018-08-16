package com.mjd.imitate_jd.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mjd.imitate_jd.R;
import com.mjd.imitate_jd.api.RetrofitClient;
import com.mjd.imitate_jd.base.BaseActivity;
import com.mjd.imitate_jd.bean.AddcarBean;
import com.mjd.imitate_jd.bean.DetailsBean;
import com.mjd.imitate_jd.mvp.classify.presenter.Presenter_details;
import com.mjd.imitate_jd.mvp.classify.view.Iview_details;
import com.mjd.imitate_jd.utils.UserManage;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DetailsActivity extends BaseActivity implements Iview_details, View.OnClickListener {

    private String url = "https://www.zhaoapi.cn/product/getProductDetail?pid=";

    private WebView details_webview;
    private Presenter_details mDetails;
    private String mPid;
    private Button details_car,details_addcar;


    @Override
    protected void initDatas() {
        mDetails = new Presenter_details(this);
        mDetails.getdeta(mPid);
    }

    @Override
    protected void initListener() {
        details_car.setOnClickListener(this);
        details_addcar.setOnClickListener(this);
    }

    @Override
    protected void initViews() {
        setContentView(R.layout.activity_details);
        Intent intent = getIntent();
        mPid = intent.getStringExtra("pid");
        details_webview = findViewById(R.id.details_webview);
        details_car = findViewById(R.id.details_car);
        details_addcar = findViewById(R.id.details_addcar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDetails.Destroys();
    }

    @Override
    public void viewSuccess(DetailsBean detailsBean) {
        String detailUrl = detailsBean.getData().getDetailUrl();
        details_webview.loadUrl(detailUrl);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.details_addcar:
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
                                Toast.makeText(DetailsActivity.this,addcarBean.getMsg(),Toast.LENGTH_SHORT).show();
                                Log.i("TAG", "onNext: "+addcarBean.getMsg());
                            }
                        });
                break;

            case R.id.details_car:
                Intent intent=new Intent(DetailsActivity.this,ShowActivity.class);
                intent.putExtra("page",3);
                startActivity(intent);
                break;
        }
    }
}
