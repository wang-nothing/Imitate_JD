package com.mjd.imitate_jd.mvp.my.login.model;

import com.mjd.imitate_jd.api.RetrofitClient;
import com.mjd.imitate_jd.bean.LoginBean;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by admin on 2018-8-8.
 */

public class Login_Model {
    public void getDa(String mobile, String password, final Imodel_login imodel_login){
        RetrofitClient
                .getInstance()
                .getCommonApi()
                .getlogin(mobile, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                            imodel_login.modelSuccess(loginBean);
                    }
                });
    }
}
